package com.example.agentzengyu.spacewar.entity.game;

import android.content.res.Resources;
import android.graphics.Canvas;

/**
 * Created by Agent ZengYu on 2017/7/28.
 */

/**
 * 敌人子弹
 */
public class EnemyBullet extends GameComponent {


    public EnemyBullet(Resources resources, int objectResId, int crashResId) {
        super(resources, objectResId, crashResId);
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.save();
        canvas.clipRect(coordX - objectWidth / 2, coordY - objectHeight / 2, coordX + objectWidth / 2, coordY + objectHeight / 2);
        canvas.drawBitmap(objectBitmap, coordX - objectWidth / 2, coordY - objectHeight / 2, paint);
        canvas.restore();
        action();
    }

    @Override
    public void onDestroy() {
        if (objectBitmap != null && objectBitmap.isRecycled()) {
            objectBitmap.recycle();
        }
    }

    @Override
    public boolean crash(GameComponent target) {
        return false;
    }

    @Override
    protected void action() {
        coordY += 10;
    }

    @Override
    protected boolean isOutOfScreen() {
        if (coordY > objectHeight / 2 +screenHeight) {
            return true;
        } else {
            return false;
        }
    }
}
