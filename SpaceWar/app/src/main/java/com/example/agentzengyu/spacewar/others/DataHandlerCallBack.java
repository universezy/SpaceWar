package com.example.agentzengyu.spacewar.others;

/**
 * Created by Agent ZengYu on 2017/6/21.
 */

/**
 * 数据处理类回调接口
 */
public interface DataHandlerCallBack {
    void onStart(String message);

    void onProcess(int progress);

    void onSuccess(String result);

    void onFailure(String result, Exception e);
}
