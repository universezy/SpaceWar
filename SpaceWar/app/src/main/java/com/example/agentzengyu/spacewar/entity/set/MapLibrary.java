package com.example.agentzengyu.spacewar.entity.set;

/**
 * Created by Agent ZengYu on 2017/6/28.
 */

import com.example.agentzengyu.spacewar.entity.single.MapItem;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 地图库
 */
public class MapLibrary extends AbstractLibrary implements Serializable {
    private ArrayList<MapItem> maps = new ArrayList<>();

    public ArrayList<MapItem> getMaps() {
        return maps;
    }
}
