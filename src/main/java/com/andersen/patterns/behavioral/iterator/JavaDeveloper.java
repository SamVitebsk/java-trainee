package com.andersen.patterns.behavioral.iterator;

public class JavaDeveloper implements IterableCollection {
    private String[] skills;

    public JavaDeveloper(String... skills) {
        this.skills = skills;
    }

    @Override
    public Iterator getIterator() {
        return new SkillIterator();
    }

    private class SkillIterator implements Iterator {
        int index = 0;

        @Override
        public boolean hasNext() {
            return index < skills.length;
        }

        @Override
        public Object next() {
            if (hasNext()) {
                return skills[index++];
            }
            return null;
        }
    }
}
