package com.zengyu.spacewar.game.bean;

import com.zengyu.spacewar.game.model.BlockModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Block extends AbsBean {
    private static final int VELOCITY = 1;

    public Block(int x, int y, BlockModel model) {
        init(x, y, model);
        direction = Direction.DOWNWARD;
    }

    private void init(int x, int y, BlockModel model) {
        this.x = x;
        this.y = y;
        src = model.getSrc();
        velocityY = model.getVelocity();
        update();
    }

    @Override
    protected void updateCoordinate() {
        y += velocityY;
        if (y > border.y + height) {
            valid = false;
        }
    }

    public void from(int x, int y, BlockModel model) {
        init(x, y, model);
    }
}
