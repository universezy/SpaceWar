package com.zengyu.spacewar.game.bean;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import com.zengyu.spacewar.game.manager.BitmapManager;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import lombok.Data;

@Data
public abstract class AbsBean {
    protected Paint paint;
    protected Point border;
    protected Bitmap bitmap;
    protected int width;
    protected int height;
    protected int x;
    protected int y;
    protected int velocityX = 0;
    protected int velocityY = 0;
    protected int src;
    protected int damage;
    protected int hp;
    @Direction
    protected int direction = Direction.UPWARD;
    public boolean visible = true;
    public boolean alive = true;

    public AbsBean(Point border) {
        this.border = border;
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    void initGraphic() {
        bitmap = BitmapManager.getInstance().getSrc(src);
        width = bitmap.getWidth();
        height = bitmap.getHeight();
    }

    protected boolean updateCoordinate() {
        if (alive) {
            return true;
        } else {
            visible = false;
            return false;
        }
    }

    public boolean draw(Canvas canvas) {
        if (!visible) return false;
        updateCoordinate();
        canvas.save();
        Rect beanRect = getBeanRect();
        canvas.clipRect(beanRect);
        canvas.drawBitmap(getBitmap(), beanRect.left, beanRect.top, paint);
        canvas.restore();
        return true;
    }

    public void activate() {
        visible = true;
        alive = true;
    }

    private Rect getBeanRect() {
        int left = x - width / 2;
        int top = y - height / 2;
        int right = left + width;
        int bottom = top + height;
        return new Rect(left, top, right, bottom);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Direction {
        int UPWARD = 0;
        int DOWNWARD = 1;
    }
}
