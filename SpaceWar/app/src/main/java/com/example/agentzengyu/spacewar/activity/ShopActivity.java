package com.example.agentzengyu.spacewar.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.agentzengyu.spacewar.R;
import com.example.agentzengyu.spacewar.application.SpaceWarApp;
import com.example.agentzengyu.spacewar.fragment.SpaceshipFragment;
import com.example.agentzengyu.spacewar.fragment.WeaponFragment;
import com.example.agentzengyu.spacewar.view.CircleImageView;

/**
 * 商店界面
 */
public class ShopActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = getClass().getName();
    private SpaceWarApp app = null;
    private FragmentManager fragmentManager;
    private WeaponFragment weaponFragment;
    private SpaceshipFragment spaceshipFragment;

    private TextView mTvMoney;
    private CircleImageView mCivWeapon, mCivSpaceship, mCivReturn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        initView();
        initFragment();
        initVariable();
    }

    /**
     * 初始化布局
     */
    private void initView() {
        mTvMoney = (TextView) findViewById(R.id.tvMoney);
        mCivWeapon = (CircleImageView) findViewById(R.id.civWeapon);
        mCivSpaceship = (CircleImageView) findViewById(R.id.civSpaceship);
        mCivReturn = (CircleImageView) findViewById(R.id.civReturn);
    }

    /**
     * 初始化碎片
     */
    private void initFragment() {
        spaceshipFragment = new SpaceshipFragment();
        weaponFragment = new WeaponFragment();
    }

    /**
     * 初始化变量
     */
    private void initVariable() {
        app = (SpaceWarApp) getApplication();
        fragmentManager = getSupportFragmentManager();
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (v.getId()) {
            case R.id.civWeapon:
                transaction.replace(R.id.flContainer, weaponFragment);
                transaction.commit();
                break;
            case R.id.civSpaceship:
                transaction.replace(R.id.flContainer, spaceshipFragment);
                transaction.commit();
                break;
            case R.id.civReturn:
                finish();
                break;
            default:
                break;
        }
    }
}
