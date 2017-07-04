package com.example.agentzengyu.spacewar.loader;

import com.example.agentzengyu.spacewar.entity.set.AbstractLibrary;
import com.example.agentzengyu.spacewar.entity.set.MapLibrary;

import java.io.File;
import java.io.InputStream;

/**
 * Created by Agent ZengYu on 2017/6/28.
 */

/**
 * 地图加载器
 */
public class MapLoader extends AbstractLoader {
    private MapLibrary mapLibrary = null;

    protected MapLoader(AbstractLibrary abstractLibrary, File file, InputStream inputStream) {
        super(abstractLibrary, file, inputStream);
        this.mapLibrary = (MapLibrary) abstractLibrary;
    }

    @Override
    public void read(ILoaderCallback iLoaderCallback) {
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
    }
}
