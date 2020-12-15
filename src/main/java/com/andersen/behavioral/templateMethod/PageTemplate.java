package com.andersen.behavioral.templateMethod;

public abstract class PageTemplate {
    public void renderPage() {
        System.out.println("Header");
        System.out.println(getContent());
        System.out.println("Footer");
    }

    protected abstract String getContent();
}
