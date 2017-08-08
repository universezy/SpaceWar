package com.example.agentzengyu.spacewar.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.example.agentzengyu.spacewar.R;

/**
 * 设置界面
 */
public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_setting);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        overridePendingTransition(0, R.anim.activity_out);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
