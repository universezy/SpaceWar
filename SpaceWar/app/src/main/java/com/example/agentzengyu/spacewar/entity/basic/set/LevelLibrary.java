package com.example.agentzengyu.spacewar.entity.basic.set;

/**
 * Created by Agent ZengYu on 2017/6/28.
 */


import com.example.agentzengyu.spacewar.entity.basic.single.Level;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 地图库
 */
public class LevelLibrary implements Serializable {
    private Map<String, Level> levels = new HashMap<>();

    public LevelLibrary() {

    }

    public Map<String, Level> getLevels() {
        return levels;
    }
}
