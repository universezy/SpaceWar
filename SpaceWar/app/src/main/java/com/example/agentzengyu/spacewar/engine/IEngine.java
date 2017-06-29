package com.example.agentzengyu.spacewar.engine;

/**
 * Created by Agent ZengYu on 2017/6/29.
 */

/**
 * 引擎接口
 */
public interface IEngine {
    //通知初始化信息
    void notifyInitMsg(String message);

    //通知进度信息
    void notifyProgressMsg(String message);

    //更新敌人位置
    void updateEnemyLocation();

    //更新玩家位置
    void updatePlayerLocation();
}
