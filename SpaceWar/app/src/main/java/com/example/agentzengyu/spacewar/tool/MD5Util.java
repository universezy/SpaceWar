package com.example.agentzengyu.spacewar.tool;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Agent ZengYu on 2017/7/20.
 */

/**
 * MD5工具
 */
public class MD5Util {
    public static String getMD5FromString(String res) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(res.getBytes("UTF-8"));
            StringBuffer buffer = new StringBuffer();
            int j;
            for (int i = 0; i < bytes.length; i++) {
                j = bytes[i];
                if (j < 0) {
                    j += 256;
                }
                if (j < 16) {
                    buffer.append("0");
                }
                buffer.append(Integer.toHexString(j));
            }
            return buffer.toString().toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
