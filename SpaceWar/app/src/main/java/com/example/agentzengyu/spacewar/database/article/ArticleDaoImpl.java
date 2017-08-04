package com.example.agentzengyu.spacewar.database.article;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.agentzengyu.spacewar.application.Constant;
import com.example.agentzengyu.spacewar.entity.basic.set.ArticleLibrary;
import com.example.agentzengyu.spacewar.entity.basic.single.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Agent ZengYu on 2017/7/13.
 */

/**
 * 商品数据库调用接口
 */
public class ArticleDaoImpl implements ArticleDao {
    private static ArticleDaoImpl instance = null;
    private ArticleHelper helper = null;
    private SQLiteDatabase database = null;

    private ArticleDaoImpl(Context context) {
        if (helper == null) {
            helper = new ArticleHelper(context, Constant.Database.Article.DatabaseName, null, 1);
            database = helper.getWritableDatabase();
        }
    }

    public static ArticleDaoImpl getInstance(Context context) {
        if (instance == null) {
            synchronized (ArticleDaoImpl.class) {
                if (instance == null) {
                    instance = new ArticleDaoImpl(context);
                }
            }
        }
        return instance;
    }

    @Override
    public void insert(@Constant.Database.Article.TableName String tableName, Article article) {
        ContentValues values = new ContentValues();
        values.put(Constant.Database.Article.ColumnName.NAME, article.getName());
        values.put(Constant.Database.Article.ColumnName.IMAGE, article.getImage());
        values.put(Constant.Database.Article.ColumnName.VALUE, article.getValue());
        values.put(Constant.Database.Article.ColumnName.GRADE, article.getGrade());
        values.put(Constant.Database.Article.ColumnName.PRICE, article.getPrice());
        database.insert(tableName, null, values);
    }

    @Override
    public void update(@Constant.Database.Article.TableName String tableName, Article article) {
        ContentValues values = new ContentValues();
        values.put(Constant.Database.Article.ColumnName.NAME, article.getName());
        values.put(Constant.Database.Article.ColumnName.IMAGE, article.getImage());
        values.put(Constant.Database.Article.ColumnName.VALUE, article.getValue());
        values.put(Constant.Database.Article.ColumnName.GRADE, article.getGrade());
        values.put(Constant.Database.Article.ColumnName.PRICE, article.getPrice());
        String[] whereArgs = new String[]{String.valueOf(article.getGrade())};
        database.update(tableName, values, Constant.Database.Article.ColumnName.GRADE + "=?", whereArgs);
    }

    @Override
    public void delete(@Constant.Database.Article.TableName String tableName, Article article) {
        String[] whereArgs = new String[]{String.valueOf(article.getName())};
        database.delete(tableName, Constant.Database.Article.ColumnName.NAME + "=?", whereArgs);
    }

    @Override
    public ArticleLibrary findAll() {
        ArticleLibrary library = new ArticleLibrary();
        if (library.setLives(findEachTable(Constant.Database.Article.TableName.LIFE)) &&
                library.setDefenses(findEachTable(Constant.Database.Article.TableName.DEFENSE)) &&
                library.setVelocities(findEachTable(Constant.Database.Article.TableName.VELOCITY)) &&
                library.setShields(findEachTable(Constant.Database.Article.TableName.SHIELD)) &&
                library.setPowers(findEachTable(Constant.Database.Article.TableName.POWER)) &&
                library.setSpeeds(findEachTable(Constant.Database.Article.TableName.SPEED)) &&
                library.setLasers(findEachTable(Constant.Database.Article.TableName.LASER))) {
            return library;
        }
        return null;
    }

    @Override
    public void close() {
        if (database != null) {
            database.close();
        }
    }

    @Override
    public void destroy(@Constant.Database.Article.TableName String tableName) {
        String sql = "drop table " + tableName;
        database.execSQL(sql);
    }

    /**
     * 查找每张表的数据
     *
     * @param tableName 表名
     * @return
     */
    private List<Article> findEachTable(@Constant.Database.Article.TableName String tableName) {
        List<Article> articles = null;
        Cursor cursor = database.query(tableName, null, null, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            articles = new ArrayList<>();
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(Constant.Database.Article.ColumnName.NAME));
                int image = cursor.getInt(cursor.getColumnIndex(Constant.Database.Article.ColumnName.IMAGE));
                int value = cursor.getInt(cursor.getColumnIndex(Constant.Database.Article.ColumnName.VALUE));
                int level = cursor.getInt(cursor.getColumnIndex(Constant.Database.Article.ColumnName.GRADE));
                int price = cursor.getInt(cursor.getColumnIndex(Constant.Database.Article.ColumnName.PRICE));
                if (!"".equals(name) && image > 0 && value > 0 && level >= 0 && price > 0) {
                    Article article = new Article(name, image, value, level, price);
                    articles.add(article);
                }
            }
        }
        return articles;
    }
}
