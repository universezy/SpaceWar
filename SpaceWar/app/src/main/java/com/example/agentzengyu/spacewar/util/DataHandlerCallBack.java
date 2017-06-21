package com.example.agentzengyu.spacewar.util;

/**
 * Created by Agent ZengYu on 2017/6/21.
 */

public interface DataHandlerCallBack {
    void onStart(String s);

    void onSuccess(String s);

    void onFailure(String s,Exception e);
}
