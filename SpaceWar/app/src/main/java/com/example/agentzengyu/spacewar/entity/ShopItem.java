package com.example.agentzengyu.spacewar.entity;

/**
 * Created by Agent ZengYu on 2017/6/20.
 */

public class ShopItem {
    private String Name = "";
    private String Detail = "";
    private int Level = 0;
    private int Fee = 0;
    private int Image = 0;

    public void setName(String name) {
        Name = name;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }

    public void setLevel(int level) {
        Level = level;
    }

    public void setFee(int fee) {
        Fee = fee;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public String getDetail() {
        return Detail;
    }

    public int getLevel() {
        return Level;
    }

    public int getFee() {
        return Fee;
    }

    public int getImage() {
        return Image;
    }
}