package com.example.agentzengyu.spacewar.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.agentzengyu.spacewar.R;
import com.example.agentzengyu.spacewar.entity.single.Enemy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Agent ZengYu on 2017/6/29.
 */

/**
 * 敌人位置视图
 */
public class LocationEnemyView extends View {
    private Paint paint;
    private List<Enemy> enemys = new ArrayList<>();
    private float screenWidth = 0;
    private float screenHeight = 0;
    private float bitmapWidth = 0;
    private float bitmapHeight = 0;
    private float moveWidth = 0;
    private float moveHeight = 0;
    private boolean init = false;

    public LocationEnemyView(Context context) {
        super(context);
        init();
    }

    public LocationEnemyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LocationEnemyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!init) {
            screenWidth = getWidth();
            screenHeight = getHeight();
            moveWidth = screenWidth;
            moveHeight = screenHeight + bitmapHeight;
            init = true;
        }

        synchronized (enemys) {
            for (Enemy enemy : enemys) {
                float pixelX = enemy.getX() * moveWidth / 1000 - bitmapWidth / 2;
                float pixelY = enemy.getY() * moveHeight / 1000 - bitmapHeight;
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), enemy.getImage());
                canvas.drawBitmap(bitmap, pixelX, pixelY, paint);
            }
        }
    }

    private void init() {
        int standardId = R.mipmap.ic_launcher;
        Bitmap bitmapStandard = BitmapFactory.decodeResource(getResources(), standardId);
        bitmapWidth = bitmapStandard.getWidth();
        bitmapHeight = bitmapStandard.getHeight();
        paint = new Paint();
    }

    public synchronized void setList(List<Enemy> items) {
        this.enemys.clear();
        this.enemys.addAll(items);
        invalidate();
    }
}
