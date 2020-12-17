package com.andersen.patterns.creational.prototype;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        Circle circle = new Circle();
        circle.setColor("red");
        circle.setX(1);
        circle.setY(3);
        circle.setRadius(4);
        Shape clone = circle.clone();

        log.info(circle.toString());
        log.info(clone.toString());
        log.info(String.valueOf(circle == clone));
        log.info(String.valueOf(circle.equals(clone)));

        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(11);
        rectangle.setWidth(22);
        rectangle.setColor("green");
        rectangle.setX(33);
        rectangle.setY(44);
        clone = rectangle.clone();

        log.info(rectangle.toString());
        log.info(clone.toString());
        log.info(String.valueOf(rectangle == clone));
        log.info(String.valueOf(rectangle.equals(clone)));
    }
}
