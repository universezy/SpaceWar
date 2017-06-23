package com.example.agentzengyu.spacewar.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.agentzengyu.spacewar.R;
import com.example.agentzengyu.spacewar.adapter.MenuAdapter;
import com.example.agentzengyu.spacewar.application.SpaceWarApp;

/**
 * 菜单界面
 */
public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = getClass().getName();
    private SpaceWarApp app = null;
    private LinearLayoutManager manager;
    private MenuAdapter adapter;
    private RecyclerView mRvMenu;
    private Button mBtnLeft, mBtnRight;
    private int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_memu);
        initVariable();
        initView();
        setButtonVisible();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        app.getService().stopSelf();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    /**
     * 初始化变量
     */
    private void initVariable() {
        app = (SpaceWarApp) getApplication();
        manager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }

            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        adapter = new MenuAdapter(this);
    }

    /**
     * 初始化布局
     */
    private void initView() {
        mRvMenu = (RecyclerView) findViewById(R.id.rvMenu);
        new PagerSnapHelper().attachToRecyclerView(mRvMenu);
        mRvMenu.setLayoutManager(manager);
        mRvMenu.setAdapter(adapter);
        mRvMenu.scrollToPosition(position);
        mBtnLeft = (Button) findViewById(R.id.btnLeft);
        mBtnLeft.setOnClickListener(this);
        mBtnRight = (Button) findViewById(R.id.btnRight);
        mBtnRight.setOnClickListener(this);
    }

    /**
     * 设置按钮可见性
     */
    private void setButtonVisible() {
        if (position == 0) {
            mBtnLeft.setVisibility(View.INVISIBLE);
            mBtnRight.setVisibility(View.VISIBLE);
        } else if (position == 3) {
            mBtnLeft.setVisibility(View.VISIBLE);
            mBtnRight.setVisibility(View.INVISIBLE);
        } else {
            mBtnRight.setVisibility(View.VISIBLE);
            mBtnLeft.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLeft:
                if (position > 0) {
                    position--;
                    mRvMenu.scrollToPosition(position);
                } else
                    return;
                setButtonVisible();
                break;
            case R.id.btnRight:
                if (position < 3) {
                    position++;
                    mRvMenu.scrollToPosition(position);
                } else
                    return;
                setButtonVisible();
                break;
            default:
                break;
        }
    }
}
