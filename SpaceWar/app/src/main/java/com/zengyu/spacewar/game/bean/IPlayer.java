package com.zengyu.spacewar.game.bean;

public interface IPlayer {
    void updateVelocity(float acceleratedX, float acceleratedY);

    void setShieldState(boolean useShield);

    void setLaserState(boolean useLaser);
}
