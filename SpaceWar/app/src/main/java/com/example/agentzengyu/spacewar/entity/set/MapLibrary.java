package com.example.agentzengyu.spacewar.entity.set;

/**
 * Created by Agent ZengYu on 2017/6/28.
 */

import com.example.agentzengyu.spacewar.entity.single.MapItem;

import java.io.Serializable;
import java.util.List;

/**
 * 地图库
 */
public class MapLibrary implements Serializable {
    private List<MapItem> maps = null;

    public List<MapItem> getMaps() {
        return maps;
    }
}
