package com.example.agentzengyu.spacewar.entity;

/**
 * Created by Agent ZengYu on 2017/6/20.
 */

/**
 * 战舰护盾属性
 */
public class SpaceshipShield extends ShopItem {
    public String Name = "";
    public String Detail = "";
    public int Level = 0;
    public int Fee = 0;
    public int Image = 0;

    @Override
    public void setName(String name) {
        Name = name;
    }

    @Override
    public void setDetail(String detail) {
        Detail = detail;
    }

    @Override
    public void setLevel(int level) {
        Level = level;
    }

    @Override
    public void setFee(int fee) {
        Fee = fee;
    }

    @Override
    public void setImage(int image) {
        Image = image;
    }

    @Override
    public String getName() {
        return Name;
    }

    @Override
    public String getDetail() {
        return Detail;
    }

    @Override
    public int getLevel() {
        return Level;
    }

    @Override
    public int getFee() {
        return Fee;
    }

    @Override
    public int getImage() {
        return Image;
    }
}
