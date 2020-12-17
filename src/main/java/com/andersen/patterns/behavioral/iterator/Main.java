package com.andersen.patterns.behavioral.iterator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        JavaDeveloper developer = new JavaDeveloper("java", "spring", "hibernate");
        Iterator iterator = developer.getIterator();

        while (iterator.hasNext()) {
            log.info(iterator.next().toString());
        }
    }
}
