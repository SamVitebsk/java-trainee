package com.andersen.structural.facade;

import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
public class Main {
    public static void main(String[] args) {
        VideoConversionFacade conversionFacade = new VideoConversionFacade();
        File result = conversionFacade.convertVideo("index.html", "mp4");
        log.info(result.toString());

        result = conversionFacade.convertVideo("fsdfsd.ogg", "mp4");
        log.info(result.toString());

        result = conversionFacade.convertVideo("fsdfsd.mp4", "ogg");
        log.info(result.toString());
    }
}
