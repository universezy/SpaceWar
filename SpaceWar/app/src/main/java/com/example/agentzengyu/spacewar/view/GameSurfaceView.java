package com.example.agentzengyu.spacewar.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.agentzengyu.spacewar.entity.set.PlayerData;
import com.example.agentzengyu.spacewar.entity.single.Level;

/**
 * Created by Agent ZengYu on 2017/7/27.
 */

public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private SurfaceHolder holder;
    private Thread thread;
    private boolean run = true;

    private long screenWidth, srceenHeight;
    protected Paint paint;
    protected Canvas canvas;
    private Bitmap bitmapLevel;

    private PlayerData playerData;
    private Level level;

    public GameSurfaceView(Context context) {
        super(context);
    }

    public void init(PlayerData playerData, Level level) {
        this.playerData = playerData;
        this.level = level;
        initLevelRes();
        initPlayerRes();
        initEnemyRes();
    }


    @Override
    public void run() {
        while (run) {

        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        holder.addCallback(this);
        screenWidth = getWidth();
        srceenHeight = getHeight();
        thread = new Thread(this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    private void initLevelRes() {

    }

    private void initPlayerRes() {

    }

    private void initEnemyRes() {

    }
}
