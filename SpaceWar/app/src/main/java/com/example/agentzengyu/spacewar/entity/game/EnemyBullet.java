package com.example.agentzengyu.spacewar.entity.game;

import android.content.res.Resources;
import android.graphics.Canvas;

/**
 * Created by Agent ZengYu on 2017/7/28.
 */

public class EnemyBullet extends GameObject {


    public EnemyBullet(Resources resources, int objectResId, int crashResId) {
        super(resources, objectResId, crashResId);
    }

    @Override
    public void onDraw(Canvas canvas) {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public boolean crash(GameObject target) {
        return false;
    }

    @Override
    protected void action() {

    }
}
