package com.example.agentzengyu.spacewar.entity.single;

/**
 * Created by Agent ZengYu on 2017/6/20.
 */

import android.util.Log;

import java.io.Serializable;

/**
 * 商品类
 */
public class Article implements Serializable {
    private String name = "";
    private int image = 0;
    private int value = 0;
    private int level = 0;
    private int price = 0;

    private Article() {
    }

    public Article(String name, int image, int value, int level, int price) {
        this.name = name;
        this.image = image;
        this.value = value;
        this.level = level;
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public int getValue() {
        return value;
    }

    public int getLevel() {
        return level;
    }

    public int getPrice() {
        return price;
    }
}
