package com.example.agentzengyu.spacewar.engine;

/**
 * Created by Agent ZengYu on 2017/6/29.
 */

import com.example.agentzengyu.spacewar.entity.single.MapItem;

/**
 * 状态操作接口
 */
public interface IStatusHandle {
    /**
     * 准备
     */
    void onPrepare(MapItem mapItem);

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
