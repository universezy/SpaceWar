package com.zengyu.spacewar.game.bean;

import android.graphics.Point;

public abstract class AbsEntityBean<T> extends AbsBean {
    public AbsEntityBean(Point border) {
        super(border);
    }

    public abstract void init(T t);
}
