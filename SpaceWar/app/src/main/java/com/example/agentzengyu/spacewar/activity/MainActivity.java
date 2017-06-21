package com.example.agentzengyu.spacewar.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.agentzengyu.spacewar.R;

/**
 * 玩家信息界面
 */
public class MainActivity extends AppCompatActivity {
    private final String TAG = getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
