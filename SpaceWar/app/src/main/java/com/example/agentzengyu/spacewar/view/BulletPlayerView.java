package com.example.agentzengyu.spacewar.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.agentzengyu.spacewar.R;
import com.example.agentzengyu.spacewar.entity.single.Bullet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Agent ZengYu on 2017/7/5.
 */

/**
 * 玩家子弹视图
 */
public class BulletPlayerView extends View {
    private int bulletColor = Color.parseColor("#000000");
    private Paint paintBullet;
    private Bitmap bitmap;
    private float screenWidth = 0;
    private float screenHeight = 0;
    private float bitmapHeight = 0;
    private float moveWidth = 0;
    private float moveHeight = 0;
    private boolean init = false;
    private List<Bullet> bullets = Collections.synchronizedList(new ArrayList<Bullet>());

    public BulletPlayerView(Context context) {
        super(context);
        init();
    }

    public BulletPlayerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BulletPlayerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!init) {
            screenWidth = getWidth();
            screenHeight = getHeight();
            moveWidth = screenWidth;
            moveHeight = screenHeight - bitmapHeight;
            init = true;
        }

        for (Bullet bullet : bullets) {
            float bulletX = bullet.getX() * moveWidth / 1000;
            float bulletY = bullet.getY() * moveHeight / 1000;
            canvas.drawCircle(bulletX, bulletY, bullet.getRange() / 5, paintBullet);
        }
    }

    /**
     * 初始化
     */
    private void init() {
        int normalId = R.mipmap.ic_launcher_round;
        bitmap = BitmapFactory.decodeResource(getResources(), normalId);
        bitmapHeight = bitmap.getHeight();
        paintBullet = new Paint();
        paintBullet.setColor(bulletColor);
    }

    /**
     * 设置子弹
     *
     * @param bullets
     */
    public synchronized void setBullets(List<Bullet> bullets) {
        this.bullets.clear();
        this.bullets.addAll(bullets);
        invalidate();
    }
}
