package com.example.agentzengyu.spacewar.engine;

/**
 * Created by Agent ZengYu on 2017/6/29.
 */

/**
 * 状态接口
 */
public interface IStatus {
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
