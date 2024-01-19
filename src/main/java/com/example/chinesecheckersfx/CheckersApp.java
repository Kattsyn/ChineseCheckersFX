package com.example.chinesecheckersfx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Optional;


public class CheckersApp extends Application {

    String boardString;
    public static final int TILE_SIZE = 50;
    public static final int WIDTH = 25;
    public static final int HEIGHT = 17;

    public static CheckerType checkerTurn = CheckerType.RED;
    public static boolean canDoNormalMove = true;

    Label label = new Label();
    Label labelColored = new Label();
    public static boolean is2PlayerMode;

    private final Cell[][] board = new Cell[HEIGHT][WIDTH];
    private final Group cellGroup = new Group();
    private final Group checkerGroup = new Group();

    private Parent createContent() {
        boardString = is2PlayerMode ? new Board().getTwoPlayerBoard() : new Board().getBoardString();
        Pane root = new Pane();
        String[] boardArr = boardString.split("\n");
        root.getChildren().addAll(cellGroup, checkerGroup);

        label.setFont(new Font("Arial Black", 24));
        label.setText("Ходит");
        label.setPrefWidth(80);
        labelColored.relocate(90, 0);
        labelColored.setFont(new Font("Arial Black", 24));
        labelColored.setStyle("-fx-text-fill: " + CheckerType.RED.color);
        labelColored.setText("красный");
        root.getChildren().addAll(label, labelColored);
        //System.out.println(Font.getFamilies());

        for (int y = 0; y < boardArr.length; y++) {
            char[] lineArray = boardArr[y].toCharArray();
            for (int x = 0; x < boardArr[y].length(); x++) {
                if (lineArray[x] == '0') {
                    //
                } else {
                    Checker checker = null;
                    if (lineArray[x] != '1') {
                        CheckerType[] checkerTypes = CheckerType.values();
                        int num = (int) lineArray[x] - 48; //тк (int) '0' = 48
                        checker = makeChecker(checkerTypes[num], x, y);
                        checkerGroup.getChildren().add(checker);
                    }
                    Cell cell = new Cell(x, y);
                    board[y][x] = cell;
                    if (checker != null) {
                        board[y][x].setChecker(checker);
                    }
                    cellGroup.getChildren().add(cell);
                }
            }

        }

        return root;
    }

    private Checker makeChecker(CheckerType checkerType, int x, int y) {
        Checker checker = new Checker(checkerType, x, y);


        checker.setOnMouseReleased(e -> {
            int newX = toBoard(checker.getLayoutX());
            int newY = toBoard(checker.getLayoutY());

            MoveResult moveResult;

            if (newX < 0 || newY < 0 || checkerType != checkerTurn) { //если за границами экрана (дописать дальше еще условие)
                moveResult = new MoveResult(MoveType.NONE);
            } else {
                moveResult = tryMove(checker, newX, newY);
            }

            int x0 = toBoard(checker.getOldX());
            int y0 = toBoard(checker.getOldY());

            switch (moveResult.getType()) {
                case NONE:
                    checker.abortMove();
                    System.out.println("ABORT");
                    break;
                case NORMAL:
                    if (!canDoNormalMove) {
                        checker.abortMove();
                        break;
                    }
                    checker.move(newX, newY);
                    board[y0][x0].setChecker(null);
                    board[newY][newX].setChecker(checker);
                    nextTurn();
                    System.out.println("Normal");
                    break;
                case KILL:
                    checker.move(newX, newY);
                    board[y0][x0].setChecker(null);
                    board[newY][newX].setChecker(checker);
                    if (hasJumps(checker, newX, newY, x0, y0)) {
                        canDoNormalMove = false;
                    } else {
                        nextTurn();
                        canDoNormalMove = true;
                    }
                    System.out.println("kill");
                    break;
            }
            switch (checker.getType()) {
                case ORANGE -> WinChecker.ORANGE.checkWin(board);
                case PURPLE -> WinChecker.PURPLE.checkWin(board);
                case GREEN -> WinChecker.GREEN.checkWin(board);
                case BLUE -> WinChecker.BLUE.checkWin(board);
                case RED -> WinChecker.RED.checkWin(board);
                case YELLOW -> WinChecker.YELLOW.checkWin(board);
            }
            if (WinChecker.values()[checker.getType().ordinal()].checkWin(board)) {
                callWinWindow(checkerType);
            }
        });
        return checker;
    }


    private void nextTurn() {
        if (is2PlayerMode && checkerTurn == CheckerType.RED) {
            checkerTurn = CheckerType.ORANGE;
            changeLabels();
        } else if (checkerTurn == CheckerType.ORANGE) {
            checkerTurn = CheckerType.RED;
            changeLabels();
        } else {
            checkerTurn = CheckerType.values()[checkerTurn.ordinal() + 1];
            changeLabels();
        }
    }

    private void changeLabels() {
        labelColored.setStyle("-fx-text-fill: " + checkerTurn.color);
        labelColored.setText(checkerTurn.ruColorName);
    }

    private boolean hasJumps(Checker checker, int newX, int newY, int oldX, int oldY) {
        int[] steps = new int[]{-2, 2};
        for (int stepX : steps) {
            for (int stepY : steps) {
                if ((
                        newX + stepX > 0 && newY + stepY > 0)
                        && tryMove(checker, newX + stepX, newY + stepY).equals(new MoveResult(MoveType.KILL))
                        && !(newX + stepX == oldX && newY + stepY == oldY)) {
                    System.out.println("Имеет!");
                    return true;
                }
            }
        }
        return false;
    }

    private MoveResult tryMove(Checker checker, int newX, int newY) {
        if (board[newY][newX] == null || board[newY][newX].hasChecker()) { //если в ячейке стоит шашка или ставим туда, куда нельзя (дописать условие)
            return new MoveResult(MoveType.NONE);
        }

        int x0 = toBoard(checker.getOldX());
        int y0 = toBoard(checker.getOldY());

        if (Math.abs(newX - x0) == 1 && Math.abs((newY - y0)) == 1) {
            return new MoveResult(MoveType.NORMAL);
        } else if (Math.abs(newX - x0) == 2 && Math.abs(newY - y0) == 2) {
            int x1 = x0 + (newX - x0) / 2;
            int y1 = y0 + (newY - y0) / 2;

            if (board[y1][x1].hasChecker()) {
                return new MoveResult(MoveType.KILL);
            }
        }
        return new MoveResult(MoveType.NONE);
    }

    public static void callWinWindow(CheckerType checkerType) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Конец игры");
        alert.setHeaderText("Победил: " + checkerType.ruColorName + "!");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == ButtonType.OK) {
            Platform.exit();
        }

    }

    public static void callPreEnterWindow() {

        Stage stage = new Stage();

        FlowPane root = new FlowPane();

        Button btn2players = new Button("2 игрока");
        Button btn6players = new Button("6 игроков");

        btn2players.setPrefWidth(100);
        btn6players.setPrefWidth(100);
        btn2players.setPrefHeight(60);
        btn6players.setPrefHeight(60);

        btn2players.setOnAction(e -> {
            is2PlayerMode = true;
            stage.close();
        });
        btn6players.setOnAction(e -> {
            is2PlayerMode = false;
            stage.close();
        });

        root.getChildren().addAll(btn2players, btn6players);

        Scene scene = new Scene(root, 200, 120);
        stage.setScene(scene);
        stage.setTitle("Выбор количества игроков");
        stage.showAndWait();

    }

    private int toBoard(double pixel) {
        return (int) ((pixel) / TILE_SIZE);
    }

    @Override
    public void start(Stage primaryStage) {
        callPreEnterWindow();

        Scene scene = new Scene(createContent());
        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.SPACE) {
                canDoNormalMove = true;
                nextTurn();
            }
        });


        primaryStage.setTitle("ChineseCheckersApp");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
