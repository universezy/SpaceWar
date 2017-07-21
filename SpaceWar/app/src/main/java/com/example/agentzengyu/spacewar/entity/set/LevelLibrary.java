package com.example.agentzengyu.spacewar.entity.set;

/**
 * Created by Agent ZengYu on 2017/6/28.
 */


import com.example.agentzengyu.spacewar.entity.single.Level;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 地图库
 */
public class LevelLibrary implements Serializable {
    private Map<String, Level> maps = new HashMap<>();

    public LevelLibrary() {

    }

    public Map<String, Level> getMaps() {
        return maps;
    }
}
