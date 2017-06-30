package com.example.agentzengyu.spacewar.engine;

/**
 * Created by Agent ZengYu on 2017/6/29.
 */

/**
 * 引擎接口
 */
public interface IEngine {
    /**
     * 通知初始化信息
     *
     * @param message
     */
    void notifyInitMsg(String message);

    /**
     * 通知进度信息
     *
     * @param message
     */
    void notifyProgressMsg(String message);

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
     */
    void updatePlayer();
}
