package com.andersen.behavioral.iterator;

public class Main {
    public static void main(String[] args) {
        JavaDeveloper developer = new JavaDeveloper("java", "spring", "hibernate");
        Iterator iterator = developer.getIterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }
}
