package com.example.agentzengyu.spacewar.entity.single;

/**
 * Created by Agent ZengYu on 2017/7/4.
 */

import java.io.Serializable;

/**
 * 子弹
 */
public class Bullet implements Serializable {
    private float x = 0;
    private float y = 0;
    private float power = 0;
    private float range = 0;
    private float speed = 0;

    public Bullet(float x, float y, float power, float range, float speed) {
        this.x = x;
        this.y = y;
        this.power = power;
        this.range = range;
        this.speed = speed;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setPower(float power) {
        this.power = power;
    }

    public void setRange(float range) {
        this.range = range;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getPower() {
        return power;
    }

    public float getRange() {
        return range;
    }

    public float getSpeed() {
        return speed;
    }
}
