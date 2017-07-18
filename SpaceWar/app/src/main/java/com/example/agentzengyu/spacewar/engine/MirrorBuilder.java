package com.example.agentzengyu.spacewar.engine;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Agent ZengYu on 2017/7/4.
 */

/**
 * 镜像创建器
 */
public class MirrorBuilder {
    /**
     * 创建镜像
     *
     * @param source 数据源
     * @return 镜像
     */
    public static Object buildMirror(Object source) {
        Object mirror = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(source);

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            mirror = objectInputStream.readObject();

            objectInputStream.close();
            byteArrayInputStream.close();
            objectOutputStream.close();
            byteArrayOutputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return mirror;
    }
}
