package com.example.agentzengyu.spacewar.handler;

import com.example.agentzengyu.spacewar.entity.Data;
import com.example.agentzengyu.spacewar.entity.MapData;
import com.example.agentzengyu.spacewar.others.HandlerCallBack;

import java.io.File;
import java.io.InputStream;

/**
 * Created by Agent ZengYu on 2017/6/28.
 */

/**
 * 地图数据处理类
 */
public class MapHandler extends DataHandler{
    private MapData mapData = null;

    protected MapHandler(Data data, File file, InputStream inputStream) {
        super(data, file, inputStream);
        this.mapData = (MapData) data;
    }

    @Override
    public void read(HandlerCallBack callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
    }
}
