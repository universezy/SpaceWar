package com.example.agentzengyu.spacewar.engine;

/**
 * Created by Agent ZengYu on 2017/7/3.
 */

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Vibrator;

/**
 * 音乐播放器
 */
public class MusicPlayer {
    private static MusicPlayer instance = null;
    private MediaPlayer mediaPlayer = null;
    private Context context = null;
    private Vibrator vibrator = null;
    private long[] pattern = {100, 200};

    private MusicPlayer(Context context) {
        this.context = context;
        if (vibrator == null) {
            vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        }
    }

    public static MusicPlayer getInstance(Context context) {
        if (instance == null) {
            synchronized (MusicPlayer.class) {
                if (instance == null) {
                    instance = new MusicPlayer(context);
                }
            }
        }
        return instance;
    }

    /**
     * 初始化
     *
     * @param musicSource 音乐资源
     * @return 提示信息
     */
    public String init(int musicSource) {
        mediaPlayer = MediaPlayer.create(context, musicSource);
        mediaPlayer.reset();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                onStart();
            }
        });
        return "Loading music successful.";
    }

    /**
     * 开始
     */
    public void onStart() {
        mediaPlayer.start();
    }

    /**
     * 暂停
     */
    public void onPause() {
        mediaPlayer.pause();
    }

    /**
     * 停止
     */
    public void onStop() {
        mediaPlayer.stop();
    }

    /**
     * 震动
     */
    public void onVibrator() {
        vibrator.vibrate(pattern, -1);
    }
}
