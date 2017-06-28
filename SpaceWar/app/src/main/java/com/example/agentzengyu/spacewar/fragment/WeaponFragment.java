package com.example.agentzengyu.spacewar.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.agentzengyu.spacewar.R;
import com.example.agentzengyu.spacewar.adapter.ShopAdapter;
import com.example.agentzengyu.spacewar.application.SpaceWarApp;
import com.example.agentzengyu.spacewar.entity.single.ShopItem;

import java.util.ArrayList;

/**
 * 商店武器界面
 */
public class WeaponFragment extends Fragment implements View.OnClickListener {
    private SpaceWarApp app = null;
    private LinearLayoutManager manager;
    private ShopAdapter adapter;
    private RecyclerView recyclerView;
    private ImageView mIvPower, mIvSpeed, mIvRange, mIvNuclear;
    private ArrayList<ShopItem> userItem = new ArrayList<>();
    private ArrayList<ShopItem> shopItems = new ArrayList<>();
    private int currentPosition = 1;

    public WeaponFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weapon, null);
        app = (SpaceWarApp) getActivity().getApplication();
        initVariable();
        initView(view);
        shift(0);
        return view;
    }

    /**
     * 初始化变量
     */
    private void initVariable() {
        app = (SpaceWarApp) getActivity().getApplication();
        manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        adapter = new ShopAdapter(getActivity(), userItem, shopItems);
    }

    /**
     * 初始化布局
     *
     * @param view
     */
    private void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.rvWeapon);
        new PagerSnapHelper().attachToRecyclerView(recyclerView);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        recyclerView.scrollToPosition(0);

        mIvPower = (ImageView) view.findViewById(R.id.ivPower);
        mIvPower.setOnClickListener(this);
        mIvSpeed = (ImageView) view.findViewById(R.id.ivSpeed);
        mIvSpeed.setOnClickListener(this);
        mIvRange = (ImageView) view.findViewById(R.id.ivRange);
        mIvRange.setOnClickListener(this);
        mIvNuclear = (ImageView) view.findViewById(R.id.ivNuclear);
        mIvNuclear.setOnClickListener(this);
    }

    /**
     * 切换商品类型
     *
     * @param position 类型下标
     */
    private void shift(int position) {
        if (position == currentPosition) return;
        userItem.clear();
        shopItems.clear();
        switch (position) {
            case 0:
                userItem.add(app.getPlayerData().getPower());
                shopItems.addAll(app.getShopData().getPowers());
                break;
            case 1:
                userItem.add(app.getPlayerData().getSpeed());
                shopItems.addAll(app.getShopData().getSpeeds());
                break;
            case 2:
                userItem.add(app.getPlayerData().getRange());
                shopItems.addAll(app.getShopData().getRanges());
                break;
            case 3:
                userItem.add(app.getPlayerData().getBomb());
                shopItems.addAll(app.getShopData().getBombs());
                break;
            default:
                break;
        }
        adapter.notifyDataSetChanged();
        currentPosition = position;
        recyclerView.scrollToPosition(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivPower:
                shift(0);
                break;
            case R.id.ivSpeed:
                shift(1);
                break;
            case R.id.ivRange:
                shift(2);
                break;
            case R.id.ivNuclear:
                shift(3);
                break;
            default:
                break;
        }
    }
}
