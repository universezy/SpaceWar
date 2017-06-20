package com.example.agentzengyu.spacewar.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.agentzengyu.spacewar.R;

/**
 * 游戏主界面
 */
public class GameActivity extends AppCompatActivity {
    private final String TAG = getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }
}
