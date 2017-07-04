package com.example.agentzengyu.spacewar.entity.single;

/**
 * Created by Agent ZengYu on 2017/6/28.
 */

import com.example.agentzengyu.spacewar.R;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 地图类
 */
public class MapItem implements Serializable {
    private ArrayList<EnemyItem> enemys  =new ArrayList<>();
    private String name = "";
    private int image = 0;
    private int music = R.raw.dingding;
    // add a mapSource for map
    private ArrayList mapSource = new ArrayList<>();

    public MapItem(){

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setMusic(int music) {
        this.music = music;
    }

    public ArrayList<EnemyItem> getEnemys() {
        return enemys;
    }

    public ArrayList getMapSource() {
        return mapSource;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public int getMusic() {
        return music;
    }
}
