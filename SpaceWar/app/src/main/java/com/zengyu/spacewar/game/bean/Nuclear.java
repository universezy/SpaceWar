package com.zengyu.spacewar.game.bean;

public class Nuclear extends AbsBean {
    private static final int VELOCITY = 6;

    public Nuclear(Player player) {
        init(player);
        src = player.getNuclearSrc();
    }

    private void init(Player player) {
        x = player.getX();
        y = player.getY();
    }

    @Override
    protected void updateCoordinate() {
        y -= VELOCITY;
        if (y + height < 0) {
            valid = false;
        }
    }

    public void from(Player player) {
        init(player);
    }
}
