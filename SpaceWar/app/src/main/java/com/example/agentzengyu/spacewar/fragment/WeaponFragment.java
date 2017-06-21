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
import com.example.agentzengyu.spacewar.entity.ShopItem;

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
        manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        adapter = new ShopAdapter(getActivity(), userItem,shopItems);
    }

    /**
     * 初始化布局
     *
     * @param view
     */
    private void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.rvSpaceship);
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

    private void shift(int position) {
        if (position == currentPosition) return;
        shopItems.clear();
        switch (position) {
            case 0:
                shopItems.addAll(app.getService().getData().getPowers());
                break;
            case 1:
                shopItems.addAll(app.getService().getData().getSpeeds());
                break;
            case 2:
                shopItems.addAll(app.getService().getData().getRanges());
                break;
            case 3:
                shopItems.addAll(app.getService().getData().getNuclears());
                break;
            default:
                break;
        }
        adapter.notifyDataSetChanged();
        currentPosition = position;
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
