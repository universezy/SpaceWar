package com.example.agentzengyu.spacewar.entity.set;

/**
 * Created by Agent ZengYu on 2017/6/28.
 */

import com.example.agentzengyu.spacewar.entity.single.MapItem;

import java.util.ArrayList;

/**
 * 地图数据类
 */
public class MapData extends Data{
    private ArrayList<MapItem> maps = new ArrayList<>();

    public void setMaps(ArrayList<MapItem> maps) {
        this.maps = maps;
    }

    public ArrayList<MapItem> getMaps() {
        return maps;
    }
}
