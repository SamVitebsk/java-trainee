package com.andersen.patterns.structural.facade;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CodecFactory {
    public static Codec extract(VideoFile file) {
        String codecName = file.getCodecName();
        if (codecName.equals("mp4")) {
            log.info("extracting mpeg audio...");
            return new MPEG4Codec();
        } else {
            log.info("extracting ogg audio...");
            return new OggCompressionCodec();
        }
    }
}
