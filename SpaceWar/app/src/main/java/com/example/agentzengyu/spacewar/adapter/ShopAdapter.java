package com.example.agentzengyu.spacewar.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.agentzengyu.spacewar.R;
import com.example.agentzengyu.spacewar.entity.single.ShopItem;

import java.util.List;

/**
 * Created by Agent ZengYu on 2017/6/20.
 */

/**
 * 商店适配器
 */
public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ShopViewHolder> {
    private LayoutInflater inflater;
    private List<ShopItem> userItem = null;
    private List<ShopItem> shopItems = null;
    private int[] upgrade = null;

    public ShopAdapter(Activity context, List<ShopItem> userItem, List<ShopItem> shopItems) {
        this.userItem = userItem;
        this.shopItems = shopItems;
        inflater = LayoutInflater.from(context);
        setData();
    }

    @Override
    public ShopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_shop, parent, false);
        ShopViewHolder holder = new ShopViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ShopViewHolder holder, int position) {
        if (userItem.size() == 1) {
//            holder.getmIvPlayer().setImageResource(userItem.get(0).getImage());
            holder.getmIvPlayer().setImageResource(R.mipmap.ic_launcher_round);
            holder.getmTvPlayerName().setText(userItem.get(0).getName());
            holder.getmTvPlayerLevel().setText(String.valueOf(userItem.get(0).getLevel()));
            holder.getmTvPlayerValue().setText(String.valueOf(userItem.get(0).getValue()));
            if (userItem.get(0).getLevel() < shopItems.get(position).getLevel()) {
                holder.getmIvUpgrade().setImageResource(upgrade[0]);
                holder.getmBtnUpgrade().setClickable(true);
            } else {
                holder.getmIvUpgrade().setImageResource(upgrade[1]);
                holder.getmBtnUpgrade().setClickable(false);
            }
        }
//        holder.getmIvUpgraded().setImageResource(shopItems.get(position).getImage());
        holder.getmIvUpgraded().setImageResource(R.mipmap.ic_launcher_round);
        holder.getmTvUpgradedName().setText(shopItems.get(position).getName());
        holder.getmTvUpgradedLevel().setText(String.valueOf(shopItems.get(position).getLevel()));
        holder.getmTvUpgradedValue().setText(String.valueOf(shopItems.get(position).getValue()));
        holder.getmTvUpgradedFee().setText(String.valueOf(shopItems.get(position).getFee()));
    }

    @Override
    public int getItemCount() {
        return shopItems.size();
    }

    /**
     * 设置数据
     */
    private void setData() {
        upgrade = new int[]{R.mipmap.ic_launcher_round, R.mipmap.ic_launcher};
    }

    /**
     * 商店布局容器
     */
    public class ShopViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mIvPlayer, mIvUpgraded, mIvUpgrade;
        private TextView mTvUpgradedFee;
        private TextView mTvPlayerLevel, mTvPlayerName, mTvPlayerValue;
        private TextView mTvUpgradedLevel, mTvUpgradedName, mTvUpgradedValue;
        private Button mBtnUpgrade;

        public ShopViewHolder(View itemView) {
            super(itemView);
            mTvUpgradedFee = (TextView) itemView.findViewById(R.id.tvUpgradedFee);
            mIvUpgrade = (ImageView) itemView.findViewById(R.id.ivUpgrade);

            mIvPlayer = (ImageView) itemView.findViewById(R.id.ivPlayer);
            mTvPlayerLevel = (TextView) itemView.findViewById(R.id.tvPlayerLevel);
            mTvPlayerName = (TextView) itemView.findViewById(R.id.tvPlayerName);
            mTvPlayerValue = (TextView) itemView.findViewById(R.id.tvPlayerValue);

            mIvUpgraded = (ImageView) itemView.findViewById(R.id.ivUpgraded);
            mTvUpgradedLevel = (TextView) itemView.findViewById(R.id.tvUpgradedLevel);
            mTvUpgradedName = (TextView) itemView.findViewById(R.id.tvUpgradedName);
            mTvUpgradedValue = (TextView) itemView.findViewById(R.id.tvUpgradedValue);

            mBtnUpgrade = (Button) itemView.findViewById(R.id.btnUpgrade);
            mBtnUpgrade.setOnClickListener(this);
        }

        public TextView getmTvUpgradedFee() {
            return mTvUpgradedFee;
        }

        public ImageView getmIvUpgrade() {
            return mIvUpgrade;
        }

        public ImageView getmIvPlayer() {
            return mIvPlayer;
        }

        public TextView getmTvPlayerLevel() {
            return mTvPlayerLevel;
        }

        public TextView getmTvPlayerName() {
            return mTvPlayerName;
        }

        public TextView getmTvPlayerValue() {
            return mTvPlayerValue;
        }

        public ImageView getmIvUpgraded() {
            return mIvUpgraded;
        }

        public TextView getmTvUpgradedLevel() {
            return mTvUpgradedLevel;
        }

        public TextView getmTvUpgradedName() {
            return mTvUpgradedName;
        }

        public TextView getmTvUpgradedValue() {
            return mTvUpgradedValue;
        }

        public Button getmBtnUpgrade() {
            return mBtnUpgrade;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnUpgrade:

                    break;
                default:
                    break;
            }
        }
    }
}
