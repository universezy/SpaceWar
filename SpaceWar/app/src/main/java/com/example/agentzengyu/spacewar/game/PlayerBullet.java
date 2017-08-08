package com.example.agentzengyu.spacewar.game;

import android.content.res.Resources;
import android.graphics.Canvas;

/**
 * Created by Agent ZengYu on 2017/7/28.
 */

/**
 * 玩家子弹
 */
public class PlayerBullet extends GameComponent {

    public PlayerBullet(Resources resources, int objectResId, int crashResId) {
        super(resources, objectResId, crashResId);
    }

    @Override
    public void onDraw(Canvas canvas) {
        if (!isCrash) {
            action();
            if (checkOutOfScreen()){
                isCrash = true;
            }else {
                canvas.save();
                canvas.clipRect(coordX - objectWidth / 2, coordY - objectHeight / 2, coordX + objectWidth / 2, coordY + objectHeight / 2);
                canvas.drawBitmap(objectBitmap, coordX - objectWidth / 2, coordY - objectHeight / 2, paint);
                canvas.restore();
            }
        } else {
            onDestroy();
        }
    }

    @Override
    public void onDestroy() {
        if (objectBitmap != null && !objectBitmap.isRecycled()) {
            objectBitmap.recycle();
        }
    }

    @Override
    public void crash(GameComponent target) {
        if (isCrash || target.isCrash) return;
        if (coordX + objectWidth / 2 > target.coordX - target.objectWidth / 2 &&
                coordX - objectWidth / 2 < target.coordX + target.objectWidth / 2 &&
                coordY + objectHeight / 2 > target.coordY - target.objectHeight / 2 &&
                coordY - objectHeight / 2 < target.coordY + target.objectHeight / 2) {
            target.decreaseLife(power * 100 / target.defense);
            isCrash = true;
        }
    }

    @Override
    protected void action() {
        coordY -= 10;
    }

    @Override
    protected boolean checkOutOfScreen() {
        if (coordY + objectHeight / 2 < 0) {
            return true;
        } else {
            return false;
        }
    }
}
