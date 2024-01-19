package com.example.chinesecheckersfx;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;


public class Cell extends Circle {

    private Checker checker;

    public boolean hasChecker() {
        return checker != null;
    }

    public Checker getChecker() {
        return checker;
    }

    public void setChecker(Checker checker) {
        this.checker = checker;
    }

    public Cell(int x, int y) {

        setCenterX(x);
        setCenterY(y);
        setRadius((double) CheckersApp.TILE_SIZE * 0.65);

        relocate(x * CheckersApp.TILE_SIZE, y * CheckersApp.TILE_SIZE);

        setFill(Color.valueOf("#c0c0c0"));
    }
}