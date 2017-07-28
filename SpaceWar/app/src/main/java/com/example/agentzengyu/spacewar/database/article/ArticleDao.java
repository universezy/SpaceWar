package com.example.agentzengyu.spacewar.database.article;

import com.example.agentzengyu.spacewar.application.Constant;
import com.example.agentzengyu.spacewar.entity.basic.set.ArticleLibrary;
import com.example.agentzengyu.spacewar.entity.basic.single.Article;

/**
 * Created by Agent ZengYu on 2017/7/13.
 */

/**
 * 商品数据库接口
 */
public interface ArticleDao {
    /**
     * 插入一件商品数据
     *
     * @param tableName
     * @param article
     */
    void insert(@Constant.Database.Article.TableName String tableName, Article article);

    /**
     * 更新一件商品数据
     *
     * @param tableName
     * @param article
     */
    void update(@Constant.Database.Article.TableName String tableName, Article article);

    /**
     * 删除一件商品数据
     *
     * @param tableName
     * @param article
     */
    void delete(@Constant.Database.Article.TableName String tableName, Article article);

    /**
     * 查找全部商品数据
     *
     * @return
     */
    ArticleLibrary findAll();

    /**
     * 关闭连接
     */
    void close();

    /**
     * 销毁数据库
     *
     * @param tableName
     */
    void destroy(@Constant.Database.Enemy.TableName String tableName);
}
