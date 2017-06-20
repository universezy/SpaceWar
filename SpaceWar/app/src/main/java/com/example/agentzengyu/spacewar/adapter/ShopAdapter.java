package com.example.agentzengyu.spacewar.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.agentzengyu.spacewar.R;
import com.example.agentzengyu.spacewar.activity.ShopActivity;
import com.example.agentzengyu.spacewar.application.SpaceWarApp;

/**
 * Created by Agent ZengYu on 2017/6/20.
 */

/**
 * 商店适配器
 */
public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ShopViewHolder> {
    private SpaceWarApp app = null;
    private ShopActivity context;
    private LayoutInflater inflater;

    public ShopAdapter(ShopActivity context) {
        this.context = context;
        app = (SpaceWarApp) context.getApplication();
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
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    private void setData() {

    }

    /**
     * 商店布局容器
     */
    public class ShopViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mIvCurrent, mIvUpgraded, mIvUpgrad;
        private TextView mTvUpgradedFee;
        private TextView mTvCurrentLevel, mTvCurrentName, mTvCurrentDetail;
        private TextView mTvUpgradedLevel, mTvUpgradedName, mTvUpgradedDetail;
        private Button mBtnUpgrade;

        public ShopViewHolder(View itemView) {
            super(itemView);
            mTvUpgradedFee = (TextView) itemView.findViewById(R.id.tvUpgradedFee);
            mIvUpgrad = (ImageView) itemView.findViewById(R.id.ivUpgrade);

            mIvCurrent = (ImageView) itemView.findViewById(R.id.ivCurrent);
            mTvCurrentLevel = (TextView) itemView.findViewById(R.id.tvCurrentLevel);
            mTvCurrentName = (TextView) itemView.findViewById(R.id.tvCurrentName);
            mTvCurrentDetail = (TextView) itemView.findViewById(R.id.tvCurrentDetail);

            mIvUpgraded = (ImageView) itemView.findViewById(R.id.ivUpgraded);
            mTvUpgradedLevel = (TextView) itemView.findViewById(R.id.tvUpgradedLevel);
            mTvUpgradedName = (TextView) itemView.findViewById(R.id.tvUpgradedName);
            mTvUpgradedDetail = (TextView) itemView.findViewById(R.id.tvUpgradedDetail);

            mBtnUpgrade = (Button) itemView.findViewById(R.id.btnUpgrade);
            mBtnUpgrade.setOnClickListener(this);
        }

        public TextView getmTvUpgradedFee() {
            return mTvUpgradedFee;
        }

        public ImageView getmIvUpgrad() {
            return mIvUpgrad;
        }

        public ImageView getmIvCurrent() {
            return mIvCurrent;
        }

        public TextView getmTvCurrentLevel() {
            return mTvCurrentLevel;
        }

        public TextView getmTvCurrentName() {
            return mTvCurrentName;
        }

        public TextView getmTvCurrentDetail() {
            return mTvCurrentDetail;
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

        public TextView getmTvUpgradedDetail() {
            return mTvUpgradedDetail;
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
