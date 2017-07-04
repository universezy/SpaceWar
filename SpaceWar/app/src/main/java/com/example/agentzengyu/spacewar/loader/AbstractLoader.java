package com.example.agentzengyu.spacewar.loader;

import com.example.agentzengyu.spacewar.entity.set.AbstractLibrary;

import java.io.File;
import java.io.InputStream;

/**
 * Created by Agent ZengYu on 2017/6/28.
 */

/**
 * 加载器抽象父类
 */
public abstract class AbstractLoader {
    protected File file = null;
    protected InputStream inputStream;

    protected AbstractLoader(AbstractLibrary abstractLibrary, File file, InputStream inputStream) {
        this.file = file;
        this.inputStream = inputStream;
    }

    abstract public void read(ILoaderCallback iLoaderCallback);
}
