package com.example.agentzengyu.spacewar.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.agentzengyu.spacewar.application.SpaceWarApp;

public class SpaceWarService extends Service {
    private SpaceWarApp app = null;

    @Override
    public void onCreate() {
        super.onCreate();
        app = (SpaceWarApp) getApplication();
        app.setService(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new ServiceBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public class ServiceBinder extends Binder {
    }
}
