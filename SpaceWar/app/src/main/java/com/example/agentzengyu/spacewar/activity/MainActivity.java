package com.example.agentzengyu.spacewar.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.agentzengyu.spacewar.R;
import com.example.agentzengyu.spacewar.application.SpaceWarApp;
import com.example.agentzengyu.spacewar.entity.set.ResourceMap;
import com.example.agentzengyu.spacewar.entity.single.ShopItem;
import com.example.agentzengyu.spacewar.view.CircleImageView;

/**
 * 玩家信息界面
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private SpaceWarApp app = null;
    private ResourceMap resourceMap = null;

    private TextView mTvUser, mTvMoney;
    private Button mBtnPlay;
    private CircleImageView mCivLife, mCivDefense, mCivAgility, mCivShield;
    private CircleImageView mCivPower, mCivSpeed, mCivRange, mCivLaser;

    private PopupWindow popupWindow;
    private ImageView mIvPlayer;
    private TextView mTvName, mTvLevel, mTvValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        app = (SpaceWarApp) getApplication();
        resourceMap = ResourceMap.getInstance();
        if (resourceMap==null){
            Log.e("resourceMap","null");
        }

        initView();
    }

    /**
     * 初始化布局
     */
    private void initView() {
        mTvUser = (TextView) findViewById(R.id.tvUser);
        mTvUser.setText(app.getPlayerData().getInfo().getName());
        mTvMoney = (TextView) findViewById(R.id.tvMoney);
        mTvMoney.setText("$" + app.getPlayerData().getInfo().getMoney());
        mBtnPlay = (Button) findViewById(R.id.btnPlay);
        mBtnPlay.setOnClickListener(this);
        mCivLife = (CircleImageView) findViewById(R.id.civLife);
        mCivLife.setImageResource((int) resourceMap.getShopImageMap().get(app.getPlayerData().getLife().getImage()));
        mCivLife.setOnClickListener(this);
        mCivDefense = (CircleImageView) findViewById(R.id.civDefense);
        mCivDefense.setImageResource((int) resourceMap.getShopImageMap().get(app.getPlayerData().getDefense().getImage()));
        mCivDefense.setOnClickListener(this);
        mCivAgility = (CircleImageView) findViewById(R.id.civAgility);
        mCivAgility.setImageResource((int) resourceMap.getShopImageMap().get(app.getPlayerData().getAgility().getImage()));
        mCivAgility.setOnClickListener(this);
        mCivShield = (CircleImageView) findViewById(R.id.civShield);
        mCivShield.setImageResource((int) resourceMap.getShopImageMap().get(app.getPlayerData().getShield().getImage()));
        mCivShield.setOnClickListener(this);
        mCivPower = (CircleImageView) findViewById(R.id.civPower);
        mCivPower.setImageResource((int) resourceMap.getShopImageMap().get(app.getPlayerData().getPower().getImage()));
        mCivPower.setOnClickListener(this);
        mCivSpeed = (CircleImageView) findViewById(R.id.civSpeed);
        mCivSpeed.setImageResource((int) resourceMap.getShopImageMap().get(app.getPlayerData().getSpeed().getImage()));
        mCivSpeed.setOnClickListener(this);
        mCivRange = (CircleImageView) findViewById(R.id.civRange);
        mCivRange.setImageResource((int) resourceMap.getShopImageMap().get(app.getPlayerData().getRange().getImage()));
        mCivRange.setOnClickListener(this);
        mCivLaser = (CircleImageView) findViewById(R.id.civLaser);
        mCivLaser.setImageResource((int) resourceMap.getShopImageMap().get(app.getPlayerData().getLaser().getImage()));
        mCivLaser.setOnClickListener(this);

        popupWindow = new PopupWindow(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
    }

    /**
     * 属性详情
     *
     * @param item 属性
     */
    private void showDetail(ShopItem item) {
        View view = getLayoutInflater().inflate(R.layout.popupwindow_main, null);
        mIvPlayer = (ImageView) view.findViewById(R.id.ivPlayer);
        mIvPlayer.setImageResource((int) resourceMap.getShopImageMap().get(item.getImage()));
        mTvName = (TextView) view.findViewById(R.id.tvName);
        mTvLevel = (TextView) view.findViewById(R.id.tvLevel);
        mTvValue = (TextView) view.findViewById(R.id.tvValue);

        popupWindow.setContentView(view);
        popupWindow.showAtLocation(mTvMoney, Gravity.CENTER, 0, 0);
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.alpha = 0.2f;
        getWindow().setAttributes(layoutParams);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
                layoutParams.alpha = 1f;
                getWindow().setAttributes(layoutParams);
            }
        });
        mIvPlayer.setImageResource((int) resourceMap.getShopImageMap().get(item.getImage()));
        mTvName.setText(item.getName());
        mTvLevel.setText(String.valueOf(item.getLevel()));
        mTvValue.setText(String.valueOf(item.getValue()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.welcome_in, R.anim.welcome_out);
                break;
            default:
                break;
        }
    }
}
