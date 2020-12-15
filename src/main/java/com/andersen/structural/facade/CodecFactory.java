package com.andersen.structural.facade;

public class CodecFactory {
    public static Codec extract(VideoFile file) {
        String codecName = file.getCodecName();
        if (codecName.equals("mp4")) {
            System.out.println("extracting mpeg audio...");
            return new MPEG4Codec();
        } else {
            System.out.println("extracting ogg audio...");
            return new OggCompressionCodec();
        }
    }
}
