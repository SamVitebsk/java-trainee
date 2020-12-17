package com.andersen.structural.proxy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RealProject implements Project {
    private String url;

    public RealProject(String url) {
        this.url = url;
        load();
    }

    private void load() {
        log.info("Loading project from " + url + "...");
    }

    @Override
    public void run() {
        log.info("Running project from " + url + "...");
    }
}
