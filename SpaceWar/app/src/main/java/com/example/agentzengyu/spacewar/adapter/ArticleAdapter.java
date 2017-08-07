package com.example.agentzengyu.spacewar.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.agentzengyu.spacewar.R;
import com.example.agentzengyu.spacewar.entity.single.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Agent ZengYu on 2017/6/20.
 */

/**
 * 商店适配器
 */
public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ShopViewHolder> {
    private LayoutInflater inflater;
    private IArticle iArticle = null;
    private List<Article> articles = new ArrayList<>();

    public ArticleAdapter(Activity context,IArticle iArticle) {
        inflater = LayoutInflater.from(context);
        this.iArticle = iArticle;
    }

    @Override
    public ShopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_article, parent, false);
        ShopViewHolder holder = new ShopViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ShopViewHolder holder, int position) {
        holder.getIvPicture().setImageResource(articles.get(0).getImage());
        holder.getTvUpgradedName().setText(articles.get(position).getName());
        holder.getTvUpgradedLevel().setText(String.valueOf(articles.get(position).getGrade()));
        holder.getTvUpgradedValue().setText(String.valueOf(articles.get(position).getValue()));
        iArticle.select(articles.get(0));
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    /**
     * 更新
     *
     * @param articles
     */
    public void update(List<Article> articles) {
        this.articles.clear();
        this.articles.addAll(articles);
        notifyDataSetChanged();
    }

    /**
     * 商店布局容器
     */
    public class ShopViewHolder extends RecyclerView.ViewHolder {
        private ImageView mIvPicture;
        private TextView mTvUpgradedLevel, mTvUpgradedName, mTvUpgradedValue;

        public ShopViewHolder(View itemView) {
            super(itemView);
            mIvPicture = (ImageView) itemView.findViewById(R.id.ivPicture);
            mTvUpgradedLevel = (TextView) itemView.findViewById(R.id.tvUpgradedLevel);
            mTvUpgradedName = (TextView) itemView.findViewById(R.id.tvUpgradedName);
            mTvUpgradedValue = (TextView) itemView.findViewById(R.id.tvUpgradedValue);
        }

        public ImageView getIvPicture() {
            return mIvPicture;
        }

        public TextView getTvUpgradedLevel() {
            return mTvUpgradedLevel;
        }

        public TextView getTvUpgradedName() {
            return mTvUpgradedName;
        }

        public TextView getTvUpgradedValue() {
            return mTvUpgradedValue;
        }
    }
}
