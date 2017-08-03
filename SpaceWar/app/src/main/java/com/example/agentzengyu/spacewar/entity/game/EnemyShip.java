package com.example.agentzengyu.spacewar.entity.game;

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
    private List<EnemyBullet> bullets = new ArrayList<>();
    private int bulletResId;

    public EnemyShip(Resources resources, int objectResId, int crashResId,int bulletResId) {
        super(resources, objectResId, crashResId);
        this.bulletResId = bulletResId;
    }

    @Override
    public void onDraw(Canvas canvas) {
        action();
        canvas.save();
        canvas.clipRect(coordX - objectWidth / 2, coordY - objectHeight / 2, coordX + objectWidth / 2, coordY + objectHeight / 2);
        canvas.drawBitmap(objectBitmap, coordX - objectWidth / 2, coordY - objectHeight / 2, paint);
        canvas.restore();

        for (int i = 0; i < bullets.size(); i++) {
            EnemyBullet bullet = bullets.get(i);
            bullet.onDraw(canvas);
            if (bullet.isOutOfScreen()) {
                bullets.remove(i);
                bullet.onDestroy();
            }
        }
    }

    @Override
    public void onDestroy() {
        if (objectBitmap != null && objectBitmap.isRecycled()) {
            objectBitmap.recycle();
        }
        if (crashBitmap != null && crashBitmap.isRecycled()) {
            crashBitmap.recycle();
        }
        for (EnemyBullet bullet : bullets) {
            bullet.onDestroy();
        }
    }

    @Override
    public boolean crash(GameComponent target) {
        return false;
    }

    @Override
    protected void action() {
        coordX += Math.sin(coordX) + velocity;
        if (coordX < 0) {
            coordX = 0;
        } else if (coordX > screenWidth) {
            coordX = screenWidth;
        }
        coordY += Math.cos(coordY) + velocity;
    }

    @Override
    protected boolean isOutOfScreen() {
        if (coordY + objectHeight / 2 > screenHeight) {
            return true;
        } else {
            return false;
        }
    }

    public void shootPlayer(GameComponentFactory factory){
        bullets.add(factory.createEnemyBullet(bulletResId,coordX,coordY));
    }
}
