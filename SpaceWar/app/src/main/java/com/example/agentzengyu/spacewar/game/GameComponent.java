package com.example.agentzengyu.spacewar.game;

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
    //X坐标
    protected float coordX;
    //Y坐标
    protected float coordY;
    //生命值
    protected float life = 0;
    //防御值
    protected float defense = 0;
    //攻击力
    protected float power = 0;
    //移动速度
    protected float velocity = 0;
    //宽
    protected float objectWidth;
    //高
    protected float objectHeight;
    //坠机宽
    protected float crashWidth;
    //坠机高
    protected float crashHeight;
    //屏幕宽
    protected float screenWidth;
    //屏幕高
    protected float screenHeight;
    //画笔
    protected Paint paint;
    //图片
    protected Bitmap objectBitmap;
    //坠毁图片
    protected Bitmap crashBitmap;
    //是否坠毁
    public boolean isCrash = false;
    //坠毁持续次数
    protected int crashTimes = 50;

    private GameComponent() {

    }

    /**
     * 构造方法
     *
     * @param resources
     * @param objectResId
     * @param crashResId
     */
    public GameComponent(Resources resources, int objectResId, int crashResId) {
        paint = new Paint();
        paint.setAntiAlias(true);
        if (objectResId != 0) {
            objectBitmap = BitmapFactory.decodeResource(resources, objectResId);
            objectWidth = objectBitmap.getWidth();
            objectHeight = objectBitmap.getHeight();
        }
        if (crashResId != 0) {
            crashBitmap = BitmapFactory.decodeResource(resources, crashResId);
            crashWidth = crashBitmap.getWidth();
            crashHeight = crashBitmap.getHeight();
        }
    }

    /**
     * 设置屏幕尺寸
     *
     * @param screenWidth
     * @param screenHeight
     */
    public void setScreenSize(float screenWidth, float screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    /**
     * 设置参数
     *
     * @param life
     * @param defense
     * @param power
     * @param velocity
     */
    public void setParams(float life, float defense, float power, float velocity) {
        this.life = life;
        this.defense = defense;
        this.power = power;
        this.velocity = velocity;
    }

    /**
     * 绘图
     *
     * @param canvas
     */
    public abstract void onDraw(Canvas canvas);

    /**
     * 销毁
     */
    public abstract void onDestroy();

    /**
     * 碰撞
     *
     * @param target
     * @return
     */
    public abstract void crash(GameComponent target);

    /**
     * 减血
     *
     * @param attack
     */
    public void decreaseLife(float attack) {
        life -= attack;
    }

    /**
     * 动作
     */
    protected abstract void action();

    /**
     * 超出屏幕
     *
     * @return
     */
    protected abstract boolean isOutOfScreen();
}
