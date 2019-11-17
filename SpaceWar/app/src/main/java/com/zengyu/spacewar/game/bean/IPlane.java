package com.zengyu.spacewar.game.bean;

public interface IPlane {
    int getX();

    int getY();

    int getBulletSrc();

    int getBulletVelocity();

    int getDamage();

    @AbsBean.Direction
    int getDirection();
}
