package com.example.agentzengyu.spacewar.view;

import com.example.agentzengyu.spacewar.entity.basic.set.PlayerData;
import com.example.agentzengyu.spacewar.entity.basic.single.Level;

/**
 * Created by Agent ZengYu on 2017/8/3.
 */

public interface ISurfaceView {
    /**
     * 默认初始化
     */
    void initDefault();

    /**
     * 初始化
     *
     * @param playerData
     * @param level
     */
    void init(PlayerData playerData, Level level);

    /**
     * 初始化地图资源
     */
    void initLevelRes();

    /**
     * 初始化玩家资源
     */
    void initPlayerRes();

    /**
     * 初始化敌人资源
     */
    void initEnemyRes();

    /**
     * 背景动作
     */
    void onBackgroundAction();

    /**
     * 绘图
     */
    void draw();

    /**
     * 开始游戏
     */
    void startGame();

    /**
     * 结束游戏
     */
    void stopGame();
}
