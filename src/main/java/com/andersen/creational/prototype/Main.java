package com.andersen.creational.prototype;

public class Main {
    public static void main(String[] args) {
        Circle circle = new Circle();
        circle.setColor("red");
        circle.setX(1);
        circle.setY(3);
        circle.setRadius(4);
        Shape clone = circle.clone();

        System.out.println(circle);
        System.out.println(clone);
        System.out.println(circle == clone);
        System.out.println(circle.equals(clone));

        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(11);
        rectangle.setWidth(22);
        rectangle.setColor("green");
        rectangle.setX(33);
        rectangle.setY(44);
        clone = rectangle.clone();

        System.out.println(rectangle);
        System.out.println(clone);
        System.out.println(rectangle == clone);
        System.out.println(rectangle.equals(clone));
    }
}
