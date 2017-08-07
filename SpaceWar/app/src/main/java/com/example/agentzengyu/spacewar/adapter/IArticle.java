package com.example.agentzengyu.spacewar.adapter;

import com.example.agentzengyu.spacewar.entity.single.Article;

/**
 * Created by Agent ZengYu on 2017/8/7.
 */

/**
 * 商店回调
 */
public interface IArticle {
    /**
     * 选中商品
     * @param article
     */
    void select(Article article);
}
