package com.example.agentzengyu.spacewar.application;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import com.example.agentzengyu.spacewar.entity.PlayerData;
import com.example.agentzengyu.spacewar.service.SpaceWarService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Agent ZengYu on 2017/6/20.
 */

/**
 * 应用类，全局管理
 */
public class SpaceWarApp extends Application {
    private List<Activity> activities = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();
    private SpaceWarService service = null;
    private PlayerData playerData = new PlayerData();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public PlayerData getPlayerData() {
        return playerData;
    }

    /**
     * 添加活动
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        if (!activities.contains(activity)) {
            activities.add(activity);
        }
    }

    /**
     * 销毁活动
     *
     * @param activity
     */
    public void removeAtivity(Activity activity) {
        if (activities.contains(activity)) {
            activity.finish();
            activities.remove(activity);
        }
    }

    /**
     * 获取活动对象
     *
     * @param activityClass
     * @return
     */
    public Activity getActivity(Class activityClass) {
        for (Activity activity : activities) {
            if (activity.getClass().equals(activityClass)) {
                return activity;
            }
        }
        return null;
    }

    /**
     * 添加碎片
     *
     * @param fragment
     */
    public void addFragment(Fragment fragment) {
        if (!fragments.contains(fragment)) {
            fragments.add(fragment);
        }
    }

    /**
     * 获取碎片对象
     *
     * @param fragmentClass
     * @return
     */
    public Fragment getFragment(Class fragmentClass) {
        for (Fragment fragment : fragments) {
            if (fragment.getClass().equals(fragmentClass)) {
                return fragment;
            }
        }
        return null;
    }

    /**
     * 设置服务
     *
     * @param service
     */
    public void setService(SpaceWarService service) {
        this.service = service;
    }

    /**
     * 获取服务对象
     *
     * @return
     */
    public SpaceWarService getService() {
        return service;
    }
}
