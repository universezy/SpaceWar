package com.example.agentzengyu.spacewar.tool;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Agent ZengYu on 2017/7/20.
 */

public class MD5Util {
    public static String getMD5FromString(String src) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(src.getBytes());
            byte[] bytes = md5.digest();
            String result = new String(bytes);
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
