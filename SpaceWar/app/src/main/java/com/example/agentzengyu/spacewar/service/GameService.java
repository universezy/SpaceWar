package com.example.agentzengyu.spacewar.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.agentzengyu.spacewar.application.SpaceWarApp;

/**
 * 游戏进程服务
 */
public class GameService extends Service {
    private SpaceWarApp app = null;

    public GameService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
