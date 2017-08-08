package com.example.agentzengyu.spacewar.game;

import android.content.res.Resources;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Agent ZengYu on 2017/7/28.
 */

/**
 * 敌人飞船
 */
public class EnemyShip extends GameComponent {
    //子弹图片ID
    private int bulletResId;
    //子弹速度
    private float bulletSpeed;
    //加速度
    private float acceleratedX = 0.0f, acceleratedY = 0.0f;
    //子弹
    public List<EnemyBullet> bullets = new ArrayList<>();
    //玩家飞船
    private PlayerShip playerShip = null;
    //是否为boss
    public boolean isBoss = false;

    public EnemyShip(Resources resources, int objectResId, int crashResId, int bulletResId, float bulletSpeed) {
        super(resources, objectResId, crashResId);
        this.bulletResId = bulletResId;
        this.bulletSpeed = bulletSpeed;
    }

    @Override
    public void onDraw(Canvas canvas) {
        //飞船
        if (!isCrash) {
            if (life > 0) {
                action();
                if (checkOutOfScreen()) {
                    isCrash = true;
                }else {
                    canvas.save();
                    canvas.clipRect(coordX - objectWidth / 2, coordY - objectHeight / 2, coordX + objectWidth / 2, coordY + objectHeight / 2);
                    canvas.drawBitmap(objectBitmap, coordX - objectWidth / 2, coordY - objectHeight / 2, paint);
                    canvas.restore();
                    crash(playerShip);
                }
            } else if (crashTimes > 0) {
                canvas.save();
                canvas.clipRect(coordX - crashWidth / 2, coordY - crashHeight / 2, coordX + crashWidth / 2, coordY + crashHeight / 2);
                canvas.drawBitmap(crashBitmap, coordX - crashWidth / 2, coordY - crashHeight / 2, paint);
                canvas.restore();
                crashTimes--;
            } else if (crashTimes == 0) {
                canvas.save();
                canvas.clipRect(coordX - crashWidth / 2, coordY - crashHeight / 2, coordX + crashWidth / 2, coordY + crashHeight / 2);
                canvas.drawBitmap(crashBitmap, coordX - crashWidth / 2, coordY - crashHeight / 2, paint);
                canvas.restore();
                isCrash = true;
            }
        } else {
            onDestroy();
        }

        //子弹
        for (int i = 0; i < bullets.size(); i++) {
            EnemyBullet bullet = bullets.get(i);
            if (bullet.isCrash) {
                bullet.onDestroy();
                bullets.remove(i);
            } else {
                bullet.onDraw(canvas);
                if (bullet.checkOutOfScreen()) {
                    bullet.onDestroy();
                    bullets.remove(i);
                } else {
                    bullet.crash(playerShip);
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        if (objectBitmap != null && !objectBitmap.isRecycled()) {
            objectBitmap.recycle();
        }
        if (crashBitmap != null && !crashBitmap.isRecycled()) {
            crashBitmap.recycle();
        }
    }

    @Override
    public void crash(GameComponent target) {
        if (isCrash || target.isCrash) return;
        if (coordX + objectWidth / 2 > target.coordX - target.objectWidth / 2 &&
                coordX - objectWidth / 2 < target.coordX + target.objectWidth / 2 &&
                coordY + objectHeight / 2 > target.coordY - target.objectHeight / 2 &&
                coordY - objectHeight / 2 < target.coordY + target.objectHeight / 2) {
            if (defense > target.defense)
                target.decreaseLife(defense / target.defense);
        }
    }

    @Override
    protected void action() {
        coordX -= acceleratedX * velocity;
        if (coordX < 0) {
            coordX = 0;
            acceleratedX = -acceleratedX;
        } else if (coordX > screenWidth) {
            coordX = screenWidth;
            acceleratedX = -acceleratedX;
        }
        coordY += acceleratedY * velocity;
        if (isBoss) {
            if (coordY > objectHeight * 2) {
                acceleratedY = (-0.01f);
            } else if (coordY < 0) {
                acceleratedY = 0.01f;
            }
        }
    }

    @Override
    public boolean checkOutOfScreen() {
        if (coordY > screenHeight + objectHeight / 2) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 设置加速度
     *
     * @param X
     * @param Y
     */
    public void setAccelerated(float X, float Y) {
        acceleratedX = X;
        acceleratedY = Y;
    }

    /**
     * 设置玩家飞船
     *
     * @param ship
     */
    public void setPlayerShip(PlayerShip ship) {
        this.playerShip = ship;
    }

    /**
     * 射击玩家
     *
     * @param factory
     */
    public void shootPlayer(GameComponentFactory factory) {
        if (isCrash) return;
        if (coordY + objectHeight / 2 < 0) return;
        bullets.add(factory.createEnemyBullet(bulletResId, coordX, coordY, power, bulletSpeed));
    }
}
