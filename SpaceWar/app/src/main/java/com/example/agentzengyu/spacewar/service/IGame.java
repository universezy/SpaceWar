package com.example.agentzengyu.spacewar.service;

import com.example.agentzengyu.spacewar.entity.single.Bullet;

import java.util.List;

/**
 * Created by Agent ZengYu on 2017/7/25.
 */

/**
 * 游戏回调
 */
public interface IGame {
    /**
     * 更新地图位置
     */
    void updateLevelCoord();

    /**
     * 更新敌人位置
     */
    void updateEnemyCoord();

    void updatePlayerCoord(float x, float y);

    /**
     * 更新敌人子弹位置
     * @param bullets
     */
    void updateEnemyBullets(List<Bullet> bullets);

    /**
     * 更新玩家子弹位置
     * @param bullets
     */
    void updatePlayerBullets(List<Bullet> bullets);

    /**
     * 设置护盾
     * @param open
     * @param cold
     */
    void setShield(boolean open,int cold);

    /**
     * 设置炸弹
     * @param start
     * @param cold
     */
    void setLaser(boolean start, int cold);

    /**
     * 摧毁玩家
     */
    void destroyPlayer();
}
