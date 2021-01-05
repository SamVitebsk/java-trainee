//package com.andersen.internetShop.utils;
//
//import com.andersen.internetShop.dao.Bucket;
//import com.andersen.internetShop.dao.User;
//import lombok.extern.slf4j.Slf4j;
//
//import java.io.*;
//
//@Slf4j
//public class BucketSaver {
//    private static final String BUCKET_FILE_NAME_PREFIX = "bucket-";
//    private static final String BUCKET_FILE_NAME_SUFFIX = ".txt";
//
//    public static void saveBucket(Bucket bucket, User user) {
//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(getFileName(user)))) {
//            oos.writeObject(bucket);
//            oos.flush();
//        } catch (IOException e) {
//            log.error("Save bucket exception: {}", e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    public static Bucket load(User user) {
//        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(getFileName(user)))) {
//            return (Bucket) ois.readObject();
//        } catch (Exception e) {
//            return new Bucket(user.getId());
//        }
//    }
//
//    private static String getFileName(User user) {
//        return BUCKET_FILE_NAME_PREFIX + user.getId().toString() +  BUCKET_FILE_NAME_SUFFIX;
//    }
//}
