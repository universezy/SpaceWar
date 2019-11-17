package com.zengyu.spacewar.game.bean;

import android.graphics.Point;

import com.zengyu.spacewar.game.model.IModel;

public abstract class AbsModelBean<T extends IModel> extends AbsBean implements Destructible {
    public AbsModelBean(Point border) {
        super(border);
    }

    public void init(int x, int y, T t) {
        this.x = x;
        this.y = y;
        src = t.getSrc();
        initGraphic();
    }

    @Override
    public void decreaseHp(int damage) {
        hp -= damage;
        if (hp <= 0) {
            alive = false;
        }
    }
}
