package com.example.agentzengyu.spacewar.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.agentzengyu.spacewar.R;
import com.example.agentzengyu.spacewar.activity.ShopActivity;
import com.example.agentzengyu.spacewar.adapter.ShopAdapter;
import com.example.agentzengyu.spacewar.application.SpaceWarApp;

/**
 * 商店武器界面
 */
public class WeaponFragment extends Fragment {
    private SpaceWarApp app = null;
    private LinearLayoutManager manager;
    private ShopAdapter adapter;
    private RecyclerView recyclerView;

    public WeaponFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weapon, null);
        app = (SpaceWarApp) getActivity().getApplication();
        initVariable();
        initView(view);
        return view;
    }

    /**
     * 初始化变量
     */
    private void initVariable() {
        app = (SpaceWarApp) getActivity().getApplication();
        manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        adapter = new ShopAdapter((ShopActivity) (app.getActivity(ShopActivity.class)));
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
    }
}
