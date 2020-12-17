package com.andersen.behavioral.templateMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class PageTemplate {
    public void renderPage() {
        log.info("HEADER");
        log.info(getContent());
        log.info("FOOTER");
    }

    protected abstract String getContent();
}
