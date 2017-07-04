package com.example.agentzengyu.spacewar.loader;

/**
 * Created by Agent ZengYu on 2017/6/21.
 */

/**
 * 加载器回调
 */
public interface ILoaderCallback {
    /**
     * 开始
     *
     * @param message 提示信息
     */
    void onStart(String message);

    /**
     * 过程
     *
     * @param progress 进度
     */
    void onProcess(int progress);

    /**
     * 成功
     *
     * @param result 结果
     */
    void onSuccess(String result);

    /**
     * 失败
     *
     * @param result 结果
     * @param e      异常
     */
    void onFailure(String result, Exception e);
}
