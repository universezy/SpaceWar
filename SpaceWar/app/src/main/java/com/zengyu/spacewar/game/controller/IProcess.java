package com.zengyu.spacewar.game.controller;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface IProcess {

    void prepare();

    void start();

    void pause();

    void resume();

    void restart();

    void stop();

    @State
    int getProcessState();

    @Retention(RetentionPolicy.SOURCE)
    @interface State {
        int PREPARE = 0;
        int RUNNING = 1;
        int PAUSE = 2;
        int STOP = 3;
    }
}
