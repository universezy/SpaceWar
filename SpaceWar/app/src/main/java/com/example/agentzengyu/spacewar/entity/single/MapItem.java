package com.example.agentzengyu.spacewar.entity.single;

/**
 * Created by Agent ZengYu on 2017/6/28.
 */

import java.io.Serializable;

/**
 * 地图类
 */
public class MapItem implements Serializable {
    private String Name = "";
    private int Image = 0;

    public MapItem(){

    }

    public void setName(String name) {
        Name = name;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public int getImage() {
        return Image;
    }
}
