package com.andersen.internetShop;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public class BucketSaver {
    public static void saveBucket(Bucket bucket, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(bucket);
            oos.flush();
        } catch (IOException e) {
            log.error("Save bucket exception: {}", e.getMessage());
            e.printStackTrace();
        }
    }

    public static Bucket load(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Bucket) ois.readObject();
        } catch (Exception e) {
            return new Bucket();
        }
    }
}
