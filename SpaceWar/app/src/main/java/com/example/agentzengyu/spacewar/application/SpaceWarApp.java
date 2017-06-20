package com.example.agentzengyu.spacewar.application;

import android.app.Application;

import com.example.agentzengyu.spacewar.service.SpaceWarService;

/**
 * Created by Agent ZengYu on 2017/6/20.
 */

public class SpaceWarApp extends Application {
    private SpaceWarService service = null;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public void setService(SpaceWarService service) {
        this.service = service;
    }

    public SpaceWarService getService() {
        return service;
    }
}
