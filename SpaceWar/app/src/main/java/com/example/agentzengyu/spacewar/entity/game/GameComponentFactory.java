package com.example.agentzengyu.spacewar.entity.game;

import android.content.res.Resources;

import com.example.agentzengyu.spacewar.entity.basic.set.PlayerData;
import com.example.agentzengyu.spacewar.entity.basic.single.Enemy;

import java.util.Random;

/**
 * Created by Agent ZengYu on 2017/8/2.
 */

/**
 * 游戏组件工厂
 */
public class GameComponentFactory {
    private float screenWidth, screenHeight;
    private Resources resources;
    private PlayerData data;

    private GameComponentFactory() {
    }

    public GameComponentFactory(float screenWidth, float screenHeight, Resources resources, PlayerData data) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.resources = resources;
        this.data = data;
    }

    /**
     * 创建玩家飞船
     *
     * @return
     */
    public PlayerShip createPlayerShip() {
        PlayerShip ship = new PlayerShip(resources, data.getPlayer().getImage(), data.getPlayer().getCrash());
        ship.setParams(data.getLife().getValue(), data.getDefense().getValue(), data.getPower().getValue(), data.getVelocity().getValue());
        ship.setScreenSize(screenWidth, screenHeight);
        ship.coordX = screenWidth / 2;
        ship.coordY = screenHeight;
        return ship;
    }

    /**
     * 创建敌人飞船
     *
     * @return
     */
    public EnemyShip createEnemyShip(Enemy enemy) {
        EnemyShip ship = new EnemyShip(resources, enemy.getImage(), enemy.getCrash(), enemy.getBullet());
        ship.setParams(enemy.getLife(), enemy.getDefense(), enemy.getPower(), enemy.getVelocity());
        ship.setScreenSize(screenWidth, screenHeight);
        ship.coordX = new Random((long) screenWidth).nextFloat();
        ship.coordY = new Random((long) (-20)).nextFloat();
        return ship;
    }

    /**
     * 创建玩家子弹
     *
     * @param objectResId
     * @param X
     * @param Y
     * @return
     */
    public PlayerBullet createPlayerBullet(int objectResId, float X, float Y) {
        PlayerBullet bullet = new PlayerBullet(resources, objectResId, 0);
        bullet.coordX = X;
        bullet.coordY = Y;
        return bullet;
    }

    /**
     * 创建敌人子弹
     *
     * @param objectResId
     * @param X
     * @param Y
     * @return
     */
    public EnemyBullet createEnemyBullet(int objectResId, float X, float Y) {
        EnemyBullet bullet = new EnemyBullet(resources, objectResId, 0);
        bullet.coordX = X;
        bullet.coordY = Y;
        return bullet;
    }
}
