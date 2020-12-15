package com.andersen.structural.proxy;

public class ProxyProject implements Project {
    private String url;
    private RealProject realProject;

    public ProxyProject(String url) {
        this.url = url;
    }

    @Override
    public void run() {
        System.out.println("Proxy is running");

        if (realProject == null) {
            realProject = new RealProject(url);
        }

        realProject.run();
    }
}
