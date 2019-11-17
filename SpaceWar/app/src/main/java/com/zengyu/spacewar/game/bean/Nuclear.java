package com.zengyu.spacewar.game.bean;

import android.graphics.Point;

public class Nuclear extends AbsEntityBean<Player> {
    private static final int VELOCITY = 6;

    public Nuclear(Point border) {
        super(border);
    }

    @Override
    public void init(Player player) {
        x = player.getX();
        y = player.getY();
        src = player.getNuclearSrc();
        initGraphic();
    }

    @Override
    protected boolean updateCoordinate() {
        if (!super.updateCoordinate()) return false;
        y -= VELOCITY;
        if (y + height < 0) {
            visible = false;
        }
        return true;
    }
}
