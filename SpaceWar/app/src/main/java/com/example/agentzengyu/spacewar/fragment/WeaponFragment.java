package com.example.agentzengyu.spacewar.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.agentzengyu.spacewar.R;

/**
 * 商店武器界面
 */
public class WeaponFragment extends ArticleFragment {
    private ImageView mIvPower, mIvSpeed, mIvLaser;

    public WeaponFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weapon, null);
        initVariable();
        initView(view);
        shiftArticle(app.getPlayerData().getPower(), app.getArticleLibrary().getPowers());
        return view;
    }

    @Override
    public void setCallback(IUpdateMoney updateMoney) {
        super.setCallback(updateMoney);
    }

    @Override
    protected void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.rvWeapon);
        super.initView(view);

        mIvPower = (ImageView) view.findViewById(R.id.ivPower);
        mIvPower.setOnClickListener(this);
        mIvSpeed = (ImageView) view.findViewById(R.id.ivSpeed);
        mIvSpeed.setOnClickListener(this);
        mIvLaser = (ImageView) view.findViewById(R.id.ivLaser);
        mIvLaser.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.ivPower:
                shiftArticle(app.getPlayerData().getPower(), app.getArticleLibrary().getPowers());
                break;
            case R.id.ivSpeed:
                shiftArticle(app.getPlayerData().getSpeed(), app.getArticleLibrary().getSpeeds());
                break;
            case R.id.ivLaser:
                shiftArticle(app.getPlayerData().getLaser(), app.getArticleLibrary().getLasers());
                break;
            default:
                break;
        }
    }
}
