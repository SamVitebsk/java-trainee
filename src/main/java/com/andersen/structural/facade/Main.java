package com.andersen.structural.facade;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        VideoConversionFacade conversionFacade = new VideoConversionFacade();
        File result = conversionFacade.convertVideo("index.html", "mp4");
        System.out.println(result);

        result = conversionFacade.convertVideo("fsdfsd.ogg", "mp4");
        System.out.println(result);

        result = conversionFacade.convertVideo("fsdfsd.mp4", "ogg");
        System.out.println(result);
    }
}
