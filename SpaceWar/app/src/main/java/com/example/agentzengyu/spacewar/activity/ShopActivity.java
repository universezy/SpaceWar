package com.example.agentzengyu.spacewar.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.agentzengyu.spacewar.R;
import com.example.agentzengyu.spacewar.application.SpaceWarApp;
import com.example.agentzengyu.spacewar.fragment.ShipFragment;
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
    private ShipFragment shipFragment;

    private TextView mTvMoney;
    private CircleImageView mCivWeapon, mCivShip, mCivReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_shop);
        initFragment();
        initVariable();
        initView();
    }

    /**
     * 初始化布局
     */
    private void initView() {
        mTvMoney = (TextView) findViewById(R.id.tvMoney);
        mTvMoney.setText("$" + app.getPlayerData().getMoney());
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
            case R.id.civShip:
                transaction.replace(R.id.flContainer, shipFragment);
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
