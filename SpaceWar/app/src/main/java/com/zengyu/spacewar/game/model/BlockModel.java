package com.zengyu.spacewar.game.model;

import com.zengyu.spacewar.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BlockModel implements IModel{
    @Src
    private int src;
    @Velocity
    private int velocity;
    @Hp
    private int hp;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Src {
        int BLOCK1 = R.drawable.ic_block1;
        int BLOCK2 = R.drawable.ic_block2;
        int BLOCK3 = R.drawable.ic_block3;
        int BLOCK4 = R.drawable.ic_block4;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Velocity {
        int VELOCITY1 = 1;
        int VELOCITY2 = 2;
        int VELOCITY3 = 3;
        int VELOCITY4 = 4;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Hp {
        int HP1 = 500;
        int HP2 = 1000;
        int HP3 = 1500;
        int HP4 = 2000;
    }
}
