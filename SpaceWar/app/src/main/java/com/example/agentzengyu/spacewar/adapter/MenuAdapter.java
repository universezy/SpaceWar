package com.example.agentzengyu.spacewar.adapter;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.agentzengyu.spacewar.R;
import com.example.agentzengyu.spacewar.activity.GameActivity;
import com.example.agentzengyu.spacewar.activity.MainActivity;
import com.example.agentzengyu.spacewar.activity.MenuActivity;
import com.example.agentzengyu.spacewar.activity.SettingActivity;
import com.example.agentzengyu.spacewar.activity.ShopActivity;
import com.example.agentzengyu.spacewar.application.Config;

/**
 * Created by Agent ZengYu on 2017/6/20.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {
    private MenuActivity context;
    private LayoutInflater inflater;
    private Resources resources;
    private int[] images;
    private String[] titles;

    public MenuAdapter(MenuActivity context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        resources = context.getResources();
        setData();
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_menu, parent, false);
        MenuViewHolder holder = new MenuViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MenuViewHolder holder, int position) {
        holder.getmIvMenu().setImageResource(images[position % images.length]);
        holder.getmBtnEnter().setTag(position % titles.length);
        holder.getmBtnEnter().setText(titles[position % titles.length]);
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    @Override
    public void onViewAttachedToWindow(MenuViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        View view = holder.itemView;
        Animation animation = AnimationUtils.loadAnimation(view.getContext(), R.anim.welcome_in);
        view.startAnimation(animation);
    }

    @Override
    public void onViewDetachedFromWindow(MenuViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        View view = holder.itemView;
        Animation animation = AnimationUtils.loadAnimation(view.getContext(), R.anim.welcome_out);
        view.startAnimation(animation);
    }

    private void setData() {
        images = new int[]{R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round,R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round};
        titles = new String[]{resources.getString(R.string.menu_info),resources.getString(R.string.menu_game), resources.getString(R.string.menu_shop), resources.getString(R.string.menu_setting)};
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mIvMenu;
        private Button mBtnEnter;

        public MenuViewHolder(View itemView) {
            super(itemView);
            mIvMenu = (ImageView) itemView.findViewById(R.id.ivMenu);
            mBtnEnter = (Button) itemView.findViewById(R.id.btnEnter);
            mBtnEnter.setOnClickListener(this);
        }

        public ImageView getmIvMenu() {
            return mIvMenu;
        }

        public Button getmBtnEnter() {
            return mBtnEnter;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnEnter:
                    switch ((int) v.getTag()) {
                        case Config.INFO:
                            Intent intentInfo = new Intent(context, MainActivity.class);
                            context.startActivity(intentInfo);
                            context.overridePendingTransition(R.anim.welcome_in, R.anim.welcome_out);
                            break;
                        case Config.GAME:
                            Intent intentGame = new Intent(context, GameActivity.class);
                            context.startActivity(intentGame);
                            context.overridePendingTransition(R.anim.welcome_in, R.anim.welcome_out);
                            break;
                        case Config.SHOP:
                            Intent intentShop = new Intent(context, ShopActivity.class);
                            context.startActivity(intentShop);
                            context.overridePendingTransition(R.anim.welcome_in, R.anim.welcome_out);
                            break;
                        case Config.SETTING:
                            Intent intentSetting = new Intent(context, SettingActivity.class);
                            context.startActivity(intentSetting);
                            context.overridePendingTransition(R.anim.welcome_in, R.anim.welcome_out);
                            break;
                        default:
                            break;
                    }
                default:
                    break;
            }
        }
    }
}
