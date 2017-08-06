package com.example.agentzengyu.spacewar.entity.single;

/**
 * Created by Agent ZengYu on 2017/6/28.
 */

import java.io.Serializable;

/**
 * 敌人
 */
public class Enemy implements Serializable {
    //敌人名
    private String name = "";
    //飞船图片
    private int image = 0;
    //飞船坠毁图片
    private int crash = 0;
    //子弹图片
    private int bullet = 0;
    //生命值
    private int life = 0;
    //防御值
    private int defense = 0;
    //移动速度
    private int velocity = 0;
    //攻击力
    private int power = 0;
    //攻击速度
    private int speed = 0;

    public Enemy() {

    }

    public Enemy(String name, int image, int crash,int bullet,int life, int defense, int velocity, int power, int speed) {
        this.name = name;
        this.image = image;
        this.crash = crash;
        this.bullet = bullet;
        this.life = life;
        this.defense = defense;
        this.velocity = velocity;
        this.power = power;
        this.speed = speed;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setCrash(int crash) {
        this.crash = crash;
    }

    public void setBullet(int bullet) {
        this.bullet = bullet;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public int getCrash() {
        return crash;
    }

    public int getBullet() {
        return bullet;
    }

    public int getLife() {
        return life;
    }

    public int getDefense() {
        return defense;
    }

    public int getVelocity() {
        return velocity;
    }

    public int getPower() {
        return power;
    }

    public int getSpeed() {
        return speed;
    }
}
