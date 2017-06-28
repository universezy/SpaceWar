package com.example.agentzengyu.spacewar.handler;

import com.example.agentzengyu.spacewar.entity.Data;
import com.example.agentzengyu.spacewar.others.HandlerCallBack;

import java.io.File;
import java.io.InputStream;

/**
 * Created by Agent ZengYu on 2017/6/28.
 */

/**
 * 数据处理抽象父类类
 */
public abstract class DataHandler {
    protected File file = null;
    protected InputStream inputStream;

    protected DataHandler(Data data, File file, InputStream inputStream) {
        this.file = file;
        this.inputStream = inputStream;
    }

    abstract public void read(HandlerCallBack callBack);
}
