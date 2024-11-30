package com.chinesecheckersfx;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Flask extends Group {


    private double mouseX, mouseY;
    private double oldX, oldY;

    public Flask(int x, int y, int width, int height) {


        Rectangle rectangle = new Rectangle();
        rectangle.setX(x);
        rectangle.setY(y);
        rectangle.setWidth(width);
        rectangle.setHeight(height);

        Arc arc = new Arc();
        arc.setCenterX(x + rectangle.getWidth()/2);
        arc.setCenterY(y + rectangle.getHeight());
        arc.setLength(180);
        arc.setRadiusX((double) width /2);
        arc.setRadiusY((double) width /2);
        arc.setStartAngle(180);
        //relocate(x, y);
        this.getChildren().addAll(rectangle, arc);

        //пример нажатия и зажатия ЛКМ (для перетягивания)
        setOnMousePressed(e -> {
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();
        });

        setOnMouseDragged(e -> relocate(e.getSceneX() - mouseX + oldX, e.getSceneY() - mouseY + oldY));

        //пример обводки
        Shape flaskOutline = Shape.union(rectangle, arc);
        flaskOutline.setStroke(Color.WHITE);
        flaskOutline.setFill(null);
        this.getChildren().add(flaskOutline);
    }


}
