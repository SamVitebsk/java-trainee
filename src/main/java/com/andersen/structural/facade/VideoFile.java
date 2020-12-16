package com.andersen.structural.facade;

import lombok.Getter;

@Getter
public class VideoFile {
    private String name;
    private String codecName;

    public VideoFile(String name) {
        this.name = name;
        this.codecName = name.substring(name.lastIndexOf('.') + 1);
    }
}
