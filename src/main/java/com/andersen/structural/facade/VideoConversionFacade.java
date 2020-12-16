package com.andersen.structural.facade;

import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
public class VideoConversionFacade {
    public File convertVideo(String fileName, String format) {
        log.info("VideoConversionFacade: conversion started.");
        VideoFile file = new VideoFile(fileName);
        Codec sourceCodec = CodecFactory.extract(file);
        Codec codec;

        if (file.getCodecName().equals("mp4")) {
            codec = new MPEG4Codec();
        } else {
            codec = new OggCompressionCodec();
        }

        VideoFile buffer = BitrateReader.read(file, codec);
        VideoFile tempResult = BitrateReader.convert(file, codec);
        File result = (new AudioMixer()).fix(tempResult);
        log.info("VideoConversionFacade: conversion completed.");

        return result;
    }
}
