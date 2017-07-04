package com.example.agentzengyu.spacewar.engine;

/**
 * Created by Agent ZengYu on 2017/6/29.
 */

import com.example.agentzengyu.spacewar.entity.single.Bullet;

import java.util.ArrayList;

/**
 * 引擎接口
 */
public interface IEngine {
    /**
     * 通知初始化信息
     *
     * @param message 消息
     * @param status  状态
     */
    void notifyInitMsg(String message, boolean status);

    /**
     * 通知进度信息
     *
     * @param message
     */
    void notifyProgressMsg(String message);

    /**
     * 初始化地图
     */
    void initMap();

    /**
     * 初始化敌人
     */
    void initEnemy(ArrayList<Bullet> bulletsEnemy);

    /**
     * 初始化玩家
     */
    void initPlayer(ArrayList<Bullet> bulletsPlayer);

    /**
     * 更新地图
     */
    void updateMap();

    /**
     * 更新敌人
     */
    void updateEnemy();

    /**
     * 更新玩家
     *
     * @param directionX   X轴移动方向
     * @param directionY   Y轴移动方向
     * @param shieldStatus 护盾状态
     * @param destroy      破坏
     */
    void updatePlayer(String directionX, String directionY, String shieldStatus, boolean destroy);
}
