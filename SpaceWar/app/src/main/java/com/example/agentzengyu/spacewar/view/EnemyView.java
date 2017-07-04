package com.example.agentzengyu.spacewar.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.agentzengyu.spacewar.entity.single.Bullet;

import java.util.ArrayList;

/**
 * Created by Agent ZengYu on 2017/6/29.
 */

public class EnemyView extends View {
    private ArrayList<Bullet> bullets = null;


    public EnemyView(Context context) {
        super(context);
    }

    public EnemyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public EnemyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


    }
}
