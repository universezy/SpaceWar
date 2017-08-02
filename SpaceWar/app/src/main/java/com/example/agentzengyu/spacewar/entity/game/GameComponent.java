package com.example.agentzengyu.spacewar.entity.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Agent ZengYu on 2017/7/28.
 */

/**
 * 游戏组件
 */
abstract public class GameComponent {
    protected float coordX;
    protected float coordY;
    protected float life = 0;
    protected float defense = 0;
    protected float power = 0;
    protected float velocity = 0;
    protected float objectWidth;
    protected float objectHeight;
    protected float screenWidth;
    protected float screenHeight;
    protected Paint paint;
    protected Bitmap objectBitmap;
    protected Bitmap crashBitmap;

    public GameComponent(Resources resources, int objectResId, int crashResId){
        paint= new Paint();
        if (objectResId!=0){
            objectBitmap = BitmapFactory.decodeResource(resources,objectResId);
            objectWidth = objectBitmap.getWidth();
        }
        if (crashResId!=0){
            crashBitmap = BitmapFactory.decodeResource(resources,crashResId);
            objectHeight =  objectBitmap.getHeight();
        }
    }

    public void setScreenSize(float screenWidth,float screenHeight){
        this.screenWidth   = screenWidth;
        this.screenHeight = screenHeight;
    }

    public void setParams(float life,float defense,float power,float velocity){
        this.life = life;
        this.defense = defense;
        this.power = power;
        this.velocity = velocity;
    }

    public abstract void onDraw(Canvas canvas);

    public abstract  void onDestroy();

    public abstract  boolean crash(GameComponent target);

    protected abstract void action();

    public void setCoordX(float coordX) {
        this.coordX = coordX;
    }

    public void setCoordY(float coordY) {
        this.coordY = coordY;
    }

    public float getCoordX() {
        return coordX;
    }

    public float getCoordY() {
        return coordY;
    }
}
