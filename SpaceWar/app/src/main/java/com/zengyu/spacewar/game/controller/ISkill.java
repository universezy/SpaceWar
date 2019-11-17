package com.zengyu.spacewar.game.controller;

public interface ISkill {
    void onBullet(boolean shot);

    void onShield();

    void onLaser();

    void onNuclear();
}
