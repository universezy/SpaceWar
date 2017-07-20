package com.example.agentzengyu.spacewar.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.agentzengyu.spacewar.R;
import com.example.agentzengyu.spacewar.application.SpaceWarApp;
import com.example.agentzengyu.spacewar.entity.single.Article;
import com.example.agentzengyu.spacewar.view.CircleImageView;

/**
 * 玩家信息界面
 */
public class UserActivity extends AppCompatActivity implements View.OnClickListener {
    private SpaceWarApp app = null;

    private TextView mTvUser, mTvMoney;
    private Button mBtnPlay;
    private CircleImageView mCivLife, mCivDefense, mCivAgility, mCivShield;
    private CircleImageView mCivPower, mCivSpeed, mCivRange, mCivLaser;

    private PopupWindow popupWindowShow,popupWindowUser;
    private ImageView mIvShop;
    private TextView mTvName, mTvLevel, mTvValue,mTvOld;
    private EditText mEtNew;
    private Button mBtnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user);
        app = (SpaceWarApp) getApplication();
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        overridePendingTransition(0, R.anim.activity_out);
    }

    /**
     * 初始化布局
     */
    private void initView() {
        mTvUser = (TextView) findViewById(R.id.tvUser);
        mTvUser.setText(app.getPlayerData().getInfo().getName());
        mTvUser.setOnClickListener(this);
        mTvMoney = (TextView) findViewById(R.id.tvMoney);
        mTvMoney.setText("$" + app.getPlayerData().getInfo().getMoney());
        mBtnPlay = (Button) findViewById(R.id.btnPlay);
        mBtnPlay.setOnClickListener(this);
        mCivLife = (CircleImageView) findViewById(R.id.civLife);
        mCivLife.setImageResource(app.getPlayerData().getLife().getImage());
        mCivLife.setOnClickListener(this);
        mCivDefense = (CircleImageView) findViewById(R.id.civDefense);
        mCivDefense.setImageResource(app.getPlayerData().getDefense().getImage());
        mCivDefense.setOnClickListener(this);
        mCivAgility = (CircleImageView) findViewById(R.id.civAgility);
        mCivAgility.setImageResource(app.getPlayerData().getAgility().getImage());
        mCivAgility.setOnClickListener(this);
        mCivShield = (CircleImageView) findViewById(R.id.civShield);
        mCivShield.setImageResource(app.getPlayerData().getShield().getImage());
        mCivShield.setOnClickListener(this);
        mCivPower = (CircleImageView) findViewById(R.id.civPower);
        mCivPower.setImageResource(app.getPlayerData().getPower().getImage());
        mCivPower.setOnClickListener(this);
        mCivSpeed = (CircleImageView) findViewById(R.id.civSpeed);
        mCivSpeed.setImageResource(app.getPlayerData().getSpeed().getImage());
        mCivSpeed.setOnClickListener(this);
        mCivRange = (CircleImageView) findViewById(R.id.civRange);
        mCivRange.setImageResource(app.getPlayerData().getRange().getImage());
        mCivRange.setOnClickListener(this);
        mCivLaser = (CircleImageView) findViewById(R.id.civLaser);
        mCivLaser.setImageResource(app.getPlayerData().getLaser().getImage());
        mCivLaser.setOnClickListener(this);

        popupWindowShow = new PopupWindow(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindowShow.setBackgroundDrawable(new BitmapDrawable());
        popupWindowShow.setFocusable(true);
        popupWindowShow.setTouchable(true);
        popupWindowShow.setOutsideTouchable(true);

        popupWindowUser = new PopupWindow(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindowUser.setBackgroundDrawable(new BitmapDrawable());
        popupWindowUser.setFocusable(true);
        popupWindowUser.setTouchable(true);
        popupWindowUser.setOutsideTouchable(true);
    }

    /**
     * 属性详情
     *
     * @param item 属性
     */
    private void showDetail(Article item) {
        View view = getLayoutInflater().inflate(R.layout.popupwindow_main, null);
        mIvShop = (ImageView) view.findViewById(R.id.ivPlayer);
        mIvShop.setImageResource(item.getImage());
        mTvName = (TextView) view.findViewById(R.id.tvName);
        mTvName.setText(item.getName());
        mTvLevel = (TextView) view.findViewById(R.id.tvLevel);
        mTvLevel.setText(String.valueOf(item.getLevel()));
        mTvValue = (TextView) view.findViewById(R.id.tvValue);
        mTvValue.setText(String.valueOf(item.getValue()));

        popupWindowShow.setContentView(view);
        popupWindowShow.showAtLocation(mTvMoney, Gravity.CENTER, 0, 0);
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.alpha = 0.2f;
        getWindow().setAttributes(layoutParams);
        popupWindowShow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
                layoutParams.alpha = 1f;
                getWindow().setAttributes(layoutParams);
            }
        });
    }

    /**
     * 修改用户名
     */
    private void modifyUserName(){
        View view = getLayoutInflater().inflate(R.layout.popupwindow_user, null);
        mTvOld =(TextView) view.findViewById(R.id.tvOld);
        mTvOld.setText(app.getPlayerData().getInfo().getName());
        mEtNew = (EditText)view.findViewById(R.id.evNew);
        mBtnSave = (Button) view.findViewById(R.id.btnSave);
        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = mEtNew.getText().toString().trim();
                app.getPlayerData().getInfo().setName(newName);
                app.getPlayerDao().update(app.getPlayerData());
                mTvUser.setText(app.getPlayerData().getInfo().getName());
                popupWindowUser.dismiss();
            }
        });

        popupWindowUser.setContentView(view);
        popupWindowUser.showAtLocation(mTvMoney, Gravity.CENTER, 0, 0);
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.alpha = 0.2f;
        getWindow().setAttributes(layoutParams);
        popupWindowUser.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
                layoutParams.alpha = 1f;
                getWindow().setAttributes(layoutParams);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvUser:
                modifyUserName();
                break;
            case R.id.civLife:
                showDetail(app.getPlayerData().getLife());
                break;
            case R.id.civDefense:
                showDetail(app.getPlayerData().getDefense());
                break;
            case R.id.civAgility:
                showDetail(app.getPlayerData().getAgility());
                break;
            case R.id.civShield:
                showDetail(app.getPlayerData().getShield());
                break;
            case R.id.civPower:
                showDetail(app.getPlayerData().getPower());
                break;
            case R.id.civSpeed:
                showDetail(app.getPlayerData().getSpeed());
                break;
            case R.id.civRange:
                showDetail(app.getPlayerData().getRange());
                break;
            case R.id.civLaser:
                showDetail(app.getPlayerData().getLaser());
                break;
            case R.id.btnPlay:
                Intent intent = new Intent(UserActivity.this, MapActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                break;
            default:
                break;
        }
    }
}
