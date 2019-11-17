package com.zengyu.spacewar.game.manager;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.LruCache;

import java.util.Optional;

public class BitmapManager {
    private static final String TAG = "BitmapManager";

    private static final int CACHE_SIZE = 32;
    private static volatile BitmapManager sInstance = null;
    private final LruCache<Integer, Bitmap> mCache = new LruCache<>(CACHE_SIZE);
    private Context mCtx;
    private Resources mRes;

    private BitmapManager() {
    }

    public static BitmapManager getInstance() {
        if (sInstance == null) {
            synchronized (BitmapManager.class) {
                if (sInstance == null) {
                    sInstance = new BitmapManager();
                }
            }
        }
        return sInstance;
    }

    public void init(Context context) {
        mCtx = context;
        mRes = context.getResources();
    }

    public void destroy() {
        mCache.evictAll();
    }

    public Bitmap getSrc(int src) {
        return Optional.ofNullable(mCache.get(src)).orElseGet(() -> createSrc(src));
    }

    private Bitmap createSrc(int src) {
        Bitmap bitmap;
        Drawable drawable = ContextCompat.getDrawable(mCtx, src);
        if (drawable instanceof BitmapDrawable) {
            bitmap = BitmapFactory.decodeResource(mRes, src);
        } else if (drawable instanceof VectorDrawable) {
            bitmap = getBitmap((VectorDrawable) drawable);
        } else {
            throw new IllegalArgumentException("unsupported drawable type");
        }
        if (bitmap != null) {
            mCache.put(src, bitmap);
        }
        return bitmap;
    }

    private Bitmap getBitmap(VectorDrawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }
}
