package com.zengyu.spacewar.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.zengyu.spacewar.game.controller.Accelerometer;
import com.zengyu.spacewar.game.controller.IProcess;
import com.zengyu.spacewar.game.controller.Scheduler;
import com.zengyu.spacewar.game.manager.RuntimeManager;
import com.zengyu.spacewar.game.bean.Player;

public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable, IProcess, Accelerometer.Callback {

    private static final String TAG = "GameSurfaceView";

    private Context mCtx;
    private SurfaceHolder mHolder;
    private final Thread mThread = new Thread(this);
    private Accelerometer mAccelerometer;
    private Scheduler mScheduler;
    private Canvas mCanvas;
    private Paint mPaint = new Paint();
    private Player mPlayer;
    @State
    private int mProcessState = State.PREPARE;
    private volatile boolean mRunning;

    public GameSurfaceView(Context context) {
        super(context);
        init(context);
    }

    public GameSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public GameSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mCtx = context;
        mHolder = getHolder();
        mHolder.addCallback(this);
        mAccelerometer = new Accelerometer(context, this);
        mScheduler = new Scheduler();
    }

    private void draw() {
        synchronized (mHolder) {
            try {
                mCanvas = mHolder.lockCanvas();
                if (mCanvas == null) return;
                mCanvas.drawColor(Color.BLACK);
                // Block
                RuntimeManager.getInstance().drawBlocks(mCanvas);
                // Enemy
                RuntimeManager.getInstance().drawEnemies(mCanvas);
                // Player
                RuntimeManager.getInstance().drawPlayer(mCanvas);
                // Collision
                RuntimeManager.getInstance().checkCollision();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                mHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }

    /******************************* SurfaceHolder.Callback *********************************/

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d(TAG, "surfaceCreated()");
        prepare();
        start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        RuntimeManager.getInstance().destroy();
    }

    /******************************* Runnable *********************************/

    @Override
    public void run() {
        mRunning = true;
        mProcessState = State.RUNNING;
        mScheduler.start();
        while (mRunning) {
            draw();
        }
    }

    /******************************* IProcess *********************************/

    @Override
    public void prepare() {
        Log.d(TAG, "prepare()");
        mPaint.setAntiAlias(true);
        Point border = new Point();
        border.x = getMeasuredWidth();
        border.y = getMeasuredHeight();
        RuntimeManager.getInstance().init(border);
        mPlayer = RuntimeManager.getInstance().getPlayer();
        mAccelerometer.register();
    }

    @Override
    public void start() {
        Log.d(TAG, "start()");
        mThread.start();
    }

    @Override
    public void pause() {
        Log.d(TAG, "pause()");
        if (!mRunning) return;
        try {
            mScheduler.pause();
            mThread.wait();
            mProcessState = State.PAUSE;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void resume() {
        Log.d(TAG, "resume()");
        if (!mRunning) return;
        mThread.notify();
        mScheduler.resume();
        mProcessState = State.RUNNING;
    }

    @Override
    public void restart() {
        Log.d(TAG, "restart()");
        if (!mRunning) return;

    }

    @Override
    public void stop() {
        Log.d(TAG, "stop()");
        if (!mRunning) return;
        mRunning = false;
        mScheduler.stop();
        mAccelerometer.unregister();
        mProcessState = State.STOP;
    }

    @Override
    public int getProcessState() {
        return mProcessState;
    }

    /******************************* Accelerometer.Callback *********************************/

    @Override
    public void onChanged(float acceleratedX, float acceleratedY) {
        if (mPlayer != null && mRunning) {
            mPlayer.updateVelocity(acceleratedX, acceleratedY);
        }
    }
}
