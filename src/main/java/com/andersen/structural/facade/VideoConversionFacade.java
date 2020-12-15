package com.andersen.structural.facade;

import java.io.File;

public class VideoConversionFacade {
    public File convertVideo(String fileName, String format) {
        System.out.println("VideoConversionFacade: conversion started.");
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
        System.out.println("VideoConversionFacade: conversion completed.");

        return result;
    }
}
