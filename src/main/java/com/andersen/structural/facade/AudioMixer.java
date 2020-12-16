package com.andersen.structural.facade;

import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
public class AudioMixer {
    public File fix(VideoFile file) {
        log.info("AudioMixer: fixing audio...");
        return new File("tmp");
    }
}
