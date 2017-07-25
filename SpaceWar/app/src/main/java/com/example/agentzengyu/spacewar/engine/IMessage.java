package com.example.agentzengyu.spacewar.engine;

/**
 * Created by Agent ZengYu on 2017/6/29.
 */

/**
 * 消息回调
 */
public interface IMessage {
    /**
     * 通知初始化消息
     *
     * @param message 消息
     * @param status  状态
     */
    void notifyInitMsg(String message, boolean status);

    /**
     * 通知游戏消息
     *
     * @param message 消息
     */
    void notifyGameMsg(String message);
}
