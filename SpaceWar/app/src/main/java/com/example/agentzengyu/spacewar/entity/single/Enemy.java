package com.example.agentzengyu.spacewar.entity.single;

/**
 * Created by Agent ZengYu on 2017/6/28.
 */

import java.io.Serializable;

/**
 * 敌人
 */
public class Enemy implements Serializable {
    private String name = "";
    private int image = 0;
    private int life = 0;
    private int defense = 0;
    private int velocity = 0;
    private int power = 0;
    private int speed = 0;
    private int range = 0;

    private float X = 0;
    private float Y = 0;

    private Enemy() {

    }

    public Enemy(String name, int image, int life, int defense, int velocity, int power, int speed, int range) {
        this.name = name;
        this.image = image;
        this.life = life;
        this.defense = defense;
        this.velocity = velocity;
        this.power = power;
        this.speed = speed;
        this.range = range;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(int image) {
        this.image = image;
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

    public void setRange(int range) {
        this.range = range;
    }

    public void setLocation(float x, float y) {
        this.X = x;
        this.Y = y;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
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

    public int getRange() {
        return range;
    }

    public float getX() {
        return X;
    }

    public float getY() {
        return Y;
    }
}
