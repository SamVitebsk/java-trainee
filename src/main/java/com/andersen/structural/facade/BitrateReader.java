package com.andersen.structural.facade;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BitrateReader {
    public static VideoFile read(VideoFile file, Codec codec) {
        log.info("BitrateReader: reading file...");
        return file;
    }

    public static VideoFile convert(VideoFile file, Codec codec) {
        log.info("BitrateReader: writing file...");
        return file;
    }
}
