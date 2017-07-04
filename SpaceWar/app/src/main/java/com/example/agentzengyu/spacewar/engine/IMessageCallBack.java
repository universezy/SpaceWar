package com.example.agentzengyu.spacewar.engine;

/**
 * Created by Agent ZengYu on 2017/6/29.
 */

/**
 * 引擎接口
 */
public interface IMessageCallBack {
    /**
     * 通知初始化信息
     *
     * @param message 消息
     * @param status  状态
     */
    void notifyInitMsg(String message, boolean status);

    /**
     * 通知进度信息
     *
     * @param message
     */
    void notifyProgressMsg(String message);


}
