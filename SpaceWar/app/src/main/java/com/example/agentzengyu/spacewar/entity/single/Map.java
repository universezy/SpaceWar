package com.example.agentzengyu.spacewar.entity.single;

/**
 * Created by Agent ZengYu on 2017/6/28.
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 地图类
 */
public class Map implements Serializable {
    private String mapName = "";
    private int image = 0;
    private int music = 0;
    private String bossName = "";
    private List<Enemy> enemys = new ArrayList<>();

    private Map() {

    }

    public Map(String mapName, int image, int music, String bossName) {
        this.mapName = mapName;
        this.image = image;
        this.music = music;
        this.bossName = bossName;
    }

    public void setEnemys(List<Enemy> enemys) {
        this.enemys = enemys;
    }

    public String getMapName() {
        return mapName;
    }

    public int getImage() {
        return image;
    }

    public int getMusic() {
        return music;
    }

    public String getBossName() {
        return bossName;
    }

    public List<Enemy> getEnemys() {
        return enemys;
    }
}
