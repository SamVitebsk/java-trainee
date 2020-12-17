package com.andersen.patterns.structural.proxy;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class ProxyProject implements Project {
    private String url;
    private RealProject realProject;

    public ProxyProject(String url) {
        this.url = url;
    }

    @Override
    public void run() {
        log.info("Proxy is running");

        if (Objects.isNull(realProject)) {
            realProject = new RealProject(url);
        }

        realProject.run();
    }
}
