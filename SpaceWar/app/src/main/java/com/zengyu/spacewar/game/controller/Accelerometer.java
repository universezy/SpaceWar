package com.zengyu.spacewar.game.controller;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.annotation.NonNull;

public class Accelerometer implements SensorEventListener {

    private static final String TAG = "Accelerometer";

    private static final float GX_BASE = 7.5F;
    private static final float GX_OFFSET_START = 0.1F;
    private static final float GX_OFFSET_END = 1.5F;
    private static final float GX_MAX_END = GX_BASE + GX_OFFSET_END;
    private static final float GX_MAX_START = GX_BASE + GX_OFFSET_START;
    private static final float GX_MIN_START = GX_BASE - GX_OFFSET_START;
    private static final float GX_MIN_END = GX_BASE - GX_OFFSET_END;

    private static final float GY_BASE = 0;
    private static final float GY_OFFSET_START = 0.05F;
    private static final float GY_OFFSET_END = 1.55F;
    private static final float GY_MAX_END = GY_BASE + GY_OFFSET_END;
    private static final float GY_MAX_START = GY_BASE + GY_OFFSET_START;
    private static final float GY_MIN_START = GY_BASE - GY_OFFSET_START;
    private static final float GY_MIN_END = GY_BASE - GY_OFFSET_END;

    private final Callback mCallback;
    private final SensorManager mSensorManager;
    private final Sensor mSensor;

    public Accelerometer(Context context, @NonNull Callback callback) {
        mCallback = callback;
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    public void register() {
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_GAME);
    }

    public void unregister() {
        mSensorManager.unregisterListener(this);
    }

    private void calculate(float gX, float gY, float gZ) {
        float acceleratedX;
        float acceleratedY;

        if (Float.compare(gY, GY_MAX_END) > 0) {
            acceleratedX = GY_MAX_END - GY_MAX_START;
        } else if (Float.compare(gY, GY_MAX_START) > 0) {
            acceleratedX = gY - GY_MAX_START;
        } else if (Float.compare(gY, GY_MIN_START) > 0) {
            acceleratedX = 0;
        } else if (Float.compare(gY, GY_MIN_END) > 0) {
            acceleratedX = gY - GY_MIN_START;
        } else {
            acceleratedX = GY_MIN_END - GY_MIN_START;
        }

        if (Float.compare(gX, GX_MAX_END) > 0) {
            acceleratedY = GX_MAX_END - GX_MAX_START;
        } else if (Float.compare(gX, GX_MAX_START) > 0) {
            acceleratedY = gX - GX_MAX_START;
        } else if (Float.compare(gX, GX_MIN_START) > 0) {
            acceleratedY = 0;
        } else if (Float.compare(gX, GX_MIN_END) > 0) {
            acceleratedY = gX - GX_MIN_START;
        } else {
            acceleratedY = GX_MIN_END - GX_MIN_START;
        }

        mCallback.onChanged(acceleratedX, acceleratedY);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        final float gX = event.values[SensorManager.DATA_X];
        final float gY = event.values[SensorManager.DATA_Y];
        final float gZ = event.values[SensorManager.DATA_Z];
        calculate(gX, gY, gZ);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public interface Callback {
        void onChanged(float acceleratedX, float acceleratedY);
    }
}
