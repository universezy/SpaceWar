package com.example.agentzengyu.spacewar.application;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import com.example.agentzengyu.spacewar.entity.BasicData;
import com.example.agentzengyu.spacewar.entity.User;
import com.example.agentzengyu.spacewar.service.SpaceWarService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Agent ZengYu on 2017/6/20.
 */

/**
 * 应用
 */
public class SpaceWarApp extends Application {
    private List<Activity> activities = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();
    private SpaceWarService service = null;
    private User user = new User();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public User getUser() {
        return user;
    }

    public void addActivity(Activity activity) {
        if (!activities.contains(activity)) {
            activities.add(activity);
        }
    }

    public void removeAtivity(Activity activity) {
        if (activities.contains(activity)) {
            activity.finish();
            activities.remove(activity);
        }
    }

    public Activity getActivity(Class activityClass) {
        for (Activity activity : activities) {
            if (activity.getClass().equals(activityClass)) {
                return activity;
            }
        }
        return null;
    }

    public void addFragment(Fragment fragment) {
        if (!fragments.contains(fragment)) {
            fragments.add(fragment);
        }
    }

    public Fragment getFragment(Class fragmentClass) {
        for (Fragment fragment : fragments) {
            if (fragment.getClass().equals(fragmentClass)) {
                return fragment;
            }
        }
        return null;
    }

    public void setService(SpaceWarService service) {
        this.service = service;
    }

    public SpaceWarService getService() {
        return service;
    }
}
