package com.example.agentzengyu.spacewar.adapter;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.agentzengyu.spacewar.R;
import com.example.agentzengyu.spacewar.activity.GameActivity;
import com.example.agentzengyu.spacewar.activity.MenuActivity;
import com.example.agentzengyu.spacewar.activity.SettingActivity;
import com.example.agentzengyu.spacewar.activity.ShopActivity;
import com.example.agentzengyu.spacewar.application.Config;
import com.example.agentzengyu.spacewar.view.CircleImageView;

/**
 * Created by Agent ZengYu on 2017/6/20.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {
    private MenuActivity context;
    private LayoutInflater inflater;
    private Resources resources;
    private int[] image;

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
        holder.getMcivMenu().setImageResource(image[position % image.length]);
        holder.getMcivMenu().setTag(position % image.length);
    }

    @Override
    public int getItemCount() {
        return image.length;
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
        image = new int[]{R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round};
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView mcivMenu;

        public MenuViewHolder(View itemView) {
            super(itemView);
            mcivMenu = (CircleImageView) itemView.findViewById(R.id.civMenu);
            mcivMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch ((int) v.getTag()) {
                        case Config.GAME:
                            Intent intentGame = new Intent(context, GameActivity.class);
                            context.startActivity(intentGame);
                            context.overridePendingTransition(R.anim.welcome_in,R.anim.welcome_out);
                            break;
                        case Config.SHOP:
                            Intent intentShop = new Intent(context, ShopActivity.class);
                            context.startActivity(intentShop);
                            context.overridePendingTransition(R.anim.welcome_in,R.anim.welcome_out);
                            break;
                        case Config.SETTING:
                            Intent intentSetting = new Intent(context, SettingActivity.class);
                            context.startActivity(intentSetting);
                            context.overridePendingTransition(R.anim.welcome_in,R.anim.welcome_out);
                            break;
                        default:
                            break;
                    }
                }
            });
        }

        public CircleImageView getMcivMenu() {
            return mcivMenu;
        }
    }
}
