package com.chinesecheckersfx;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Checker extends Circle {

    private final CheckerType type;
    private double mouseX, mouseY;
    private double oldX, oldY;


    public Checker(CheckerType type, int x, int y) {
        //присваивание типа шашки
        this.type = type;

        setRadius((double) CheckersApp.TILE_SIZE * 0.65);

        //setCenterX(x);
        //setCenterY(y);


        //заменить на отрисовку по типу (цвету шашки)
        setFill(Color.valueOf(type.color));
        move(x, y);
        //relocate(x * CheckersApp.TILE_SIZE, y * CheckersApp.TILE_SIZE);

        setOnMousePressed(e -> {
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();
        });

        setOnMouseDragged(e -> relocate(e.getSceneX() - mouseX + oldX, e.getSceneY() - mouseY + oldY));

    }

    public double getOldX() {
        return oldX;
    }

    public double getOldY() {
        return oldY;
    }

    public CheckerType getType() {
        return this.type;
    }

    public void move(int x, int y) {
        oldX = x * CheckersApp.TILE_SIZE;
        oldY = y * CheckersApp.TILE_SIZE;
        relocate(oldX, oldY);
    }


    public void abortMove() {
        relocate(oldX, oldY);
    }

}
