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
    private Article article;
    private List<Article> articles = new ArrayList<>();

    public ArticleAdapter(Activity context, IArticle iArticle) {
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
        holder.getIvPicture().setImageResource(articles.get(position).getImage());
        holder.getTvUpgradedName().setText(articles.get(position).getName());
        holder.getTvUpgradedLevel().setText(String.valueOf(articles.get(position).getGrade()));
        holder.getTvUpgradedValue().setText(String.valueOf(articles.get(position).getValue()));
        holder.getTvPrice().setText("" + articles.get(position).getPrice());
        holder.getBtnUpgrade().setTag(articles.get(position));
        if (article == null) return;
        if (article.getGrade() < articles.get(position).getGrade()) {
            holder.getIvPrice().setImageResource(R.mipmap.ic_launcher);
            holder.getBtnUpgrade().setClickable(true);
        } else {
            holder.getIvPrice().setImageResource(R.mipmap.ic_launcher_round);
            holder.getBtnUpgrade().setClickable(false);
        }
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
    public void update(Article article, List<Article> articles) {
        this.article = article;
        this.articles.clear();
        this.articles.addAll(articles);
        notifyDataSetChanged();
    }

    /**
     * 商店布局容器
     */
    public class ShopViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mIvPicture, mIvPrice;
        private TextView mTvUpgradedLevel, mTvUpgradedName, mTvUpgradedValue, mTvPrice;
        private Button mBtnUpgrade;

        public ShopViewHolder(View itemView) {
            super(itemView);
            mIvPicture = (ImageView) itemView.findViewById(R.id.ivPicture);
            mIvPrice = (ImageView) itemView.findViewById(R.id.ivPrice);
            mTvUpgradedLevel = (TextView) itemView.findViewById(R.id.tvUpgradedLevel);
            mTvUpgradedName = (TextView) itemView.findViewById(R.id.tvUpgradedName);
            mTvUpgradedValue = (TextView) itemView.findViewById(R.id.tvUpgradedValue);
            mTvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
            mBtnUpgrade = (Button) itemView.findViewById(R.id.btnUpgrade);
            mBtnUpgrade.setOnClickListener(this);
        }

        public ImageView getIvPicture() {
            return mIvPicture;
        }

        public ImageView getIvPrice() {
            return mIvPrice;
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

        public TextView getTvPrice() {
            return mTvPrice;
        }

        public Button getBtnUpgrade() {
            return mBtnUpgrade;
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btnUpgrade:
                    iArticle.select((Article) mBtnUpgrade.getTag());
                    break;
                default:
                    break;
            }
        }
    }
}
