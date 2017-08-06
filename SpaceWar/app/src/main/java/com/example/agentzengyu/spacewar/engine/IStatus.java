package com.example.agentzengyu.spacewar.engine;

/**
 * Created by Agent ZengYu on 2017/6/29.
 */

import com.example.agentzengyu.spacewar.entity.single.Level;
import com.example.agentzengyu.spacewar.view.GameSurfaceView;

/**
 * 状态接口
 */
public interface IStatus {
    /**
     * 准备
     * @param level
     * @param gameSurfaceView
     */
    void onPrepare(Level level,GameSurfaceView gameSurfaceView);

    /**
     * 开始
     */
    void onStart();

    /**
     * 暂停
     */
    void onPause();

    /**
     * 继续
     */
    void onContinue();

    /**
     * 结束
     */
    void onStop();
}
