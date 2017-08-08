package com.example.agentzengyu.spacewar.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.agentzengyu.spacewar.R;
import com.example.agentzengyu.spacewar.application.SpaceWarApp;
import com.example.agentzengyu.spacewar.fragment.IUpdatePlayer;
import com.example.agentzengyu.spacewar.fragment.ShipFragment;
import com.example.agentzengyu.spacewar.fragment.WeaponFragment;
import com.example.agentzengyu.spacewar.view.CircleImageView;

/**
 * 商店界面
 */
public class ArticleActivity extends AppCompatActivity implements View.OnClickListener ,IUpdatePlayer {
    private SpaceWarApp app = null;
    private FragmentManager fragmentManager;
    private WeaponFragment weaponFragment;
    private ShipFragment shipFragment;
    private IUpdatePlayer updateMoney;

    private TextView mTvMoney;
    private CircleImageView mCivWeapon, mCivShip, mCivReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_article);
        initFragment();
        initVariable();
        initView();
        onFragment(weaponFragment);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        overridePendingTransition(0, R.anim.activity_out);
    }

    @Override
    public void onBackPressed() {

    }

    /**
     * 初始化布局
     */
    private void initView() {
        mTvMoney = (TextView) findViewById(R.id.tvMoney);
        mTvMoney.setText("$" + app.getPlayerData().getPlayer().getMoney());
        mCivWeapon = (CircleImageView) findViewById(R.id.civWeapon);
        mCivWeapon.setOnClickListener(this);
        mCivShip = (CircleImageView) findViewById(R.id.civShip);
        mCivShip.setOnClickListener(this);
        mCivReturn = (CircleImageView) findViewById(R.id.civReturn);
        mCivReturn.setOnClickListener(this);
    }

    /**
     * 初始化碎片
     */
    private void initFragment() {
        shipFragment = new ShipFragment();
        shipFragment.setCallback(this);
        weaponFragment = new WeaponFragment();
        weaponFragment.setCallback(this);
    }

    /**
     * 初始化变量
     */
    private void initVariable() {
        app = (SpaceWarApp) getApplication();
        fragmentManager = getSupportFragmentManager();
    }

    private void onFragment(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.flContainer, fragment);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.civWeapon:
                onFragment(weaponFragment);
                break;
            case R.id.civShip:
                onFragment(shipFragment);
                break;
            case R.id.civReturn:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void update() {
        app.getPlayerDao().update(app.getPlayerData());
        mTvMoney.setText("$" + app.getPlayerData().getPlayer().getMoney());
    }
}
