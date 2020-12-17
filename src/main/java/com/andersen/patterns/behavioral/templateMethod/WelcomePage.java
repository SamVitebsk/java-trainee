package com.andersen.patterns.behavioral.templateMethod;

public class WelcomePage extends PageTemplate {
    @Override
    public String getContent() {
        return "Welcome!";
    }
}
