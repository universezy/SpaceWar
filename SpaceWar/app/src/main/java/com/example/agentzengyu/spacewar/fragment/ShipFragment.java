package com.example.agentzengyu.spacewar.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.agentzengyu.spacewar.R;

/**
 * 商店战舰界面
 */
public class ShipFragment extends ArticleFragment {
    private ImageView mIvLife, mIvVelocity, mIvDefense, mIvShield;

    public ShipFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ship, null);
        initVariable();
        initView(view);
        shiftArticle(app.getPlayerData().getLife(), app.getArticleLibrary().getLives());
        return view;
    }

    @Override
    public void setCallback(IUpdatePlayer updateMoney) {
        super.setCallback(updateMoney);
    }

    /**
     * 初始化布局
     *
     * @param view
     */
    protected void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.rvShip);
        super.initView(view);

        mIvLife = (ImageView) view.findViewById(R.id.ivLife);
        mIvLife.setOnClickListener(this);
        mIvVelocity = (ImageView) view.findViewById(R.id.ivVelocity);
        mIvVelocity.setOnClickListener(this);
        mIvDefense = (ImageView) view.findViewById(R.id.ivDefense);
        mIvDefense.setOnClickListener(this);
        mIvShield = (ImageView) view.findViewById(R.id.ivShield);
        mIvShield.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.ivLife:
                shiftArticle(app.getPlayerData().getLife(), app.getArticleLibrary().getLives());
                break;
            case R.id.ivDefense:
                shiftArticle(app.getPlayerData().getDefense(), app.getArticleLibrary().getDefenses());
                break;
            case R.id.ivVelocity:
                shiftArticle(app.getPlayerData().getVelocity(), app.getArticleLibrary().getVelocities());
                break;
            case R.id.ivShield:
                shiftArticle(app.getPlayerData().getShield(), app.getArticleLibrary().getShields());
                break;
            default:
                break;
        }
    }
}
