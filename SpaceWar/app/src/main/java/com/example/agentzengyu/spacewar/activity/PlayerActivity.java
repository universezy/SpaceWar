package com.example.agentzengyu.spacewar.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.agentzengyu.spacewar.R;
import com.example.agentzengyu.spacewar.application.SpaceWarApp;
import com.example.agentzengyu.spacewar.entity.single.Article;
import com.example.agentzengyu.spacewar.view.CircleImageView;

/**
 * 玩家信息界面
 */
public class PlayerActivity extends AppCompatActivity implements View.OnClickListener {
    private SpaceWarApp app = null;

    private TextView mTvPlayer, mTvMoney;
    private Button mBtnPlay;
    private CircleImageView mCivLife, mCivDefense, mCivVelocity, mCivShield;
    private CircleImageView mCivPower, mCivSpeed, mCivLaser,mCivReturn;

    private PopupWindow popupWindowArticle, popupWindowPlayer;
    private ImageView mIvArticle;
    private TextView mTvName, mTvLevel, mTvValue,mTvOld;
    private EditText mEtNew;
    private Button mBtnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_player);
        app = (SpaceWarApp) getApplication();
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        overridePendingTransition(0, R.anim.activity_out);
    }

    @Override
    public void onBackPressed() {

    }

    /**
     * 初始化布局
     */
    private void initView() {
        mTvPlayer = (TextView) findViewById(R.id.tvPlayer);
        mTvPlayer.setText(app.getPlayerData().getPlayer().getName());
        mTvPlayer.setOnClickListener(this);
        mTvMoney = (TextView) findViewById(R.id.tvMoney);
        mTvMoney.setText("$" + app.getPlayerData().getPlayer().getMoney());
        mBtnPlay = (Button) findViewById(R.id.btnPlay);
        mBtnPlay.setOnClickListener(this);
        mCivLife = (CircleImageView) findViewById(R.id.civLife);
        mCivLife.setImageResource(app.getPlayerData().getLife().getImage());
        mCivLife.setOnClickListener(this);
        mCivDefense = (CircleImageView) findViewById(R.id.civDefense);
        mCivDefense.setImageResource(app.getPlayerData().getDefense().getImage());
        mCivDefense.setOnClickListener(this);
        mCivVelocity = (CircleImageView) findViewById(R.id.civVelocity);
        mCivVelocity.setImageResource(app.getPlayerData().getVelocity().getImage());
        mCivVelocity.setOnClickListener(this);
        mCivShield = (CircleImageView) findViewById(R.id.civShield);
        mCivShield.setImageResource(app.getPlayerData().getShield().getImage());
        mCivShield.setOnClickListener(this);
        mCivPower = (CircleImageView) findViewById(R.id.civPower);
        mCivPower.setImageResource(app.getPlayerData().getPower().getImage());
        mCivPower.setOnClickListener(this);
        mCivSpeed = (CircleImageView) findViewById(R.id.civSpeed);
        mCivSpeed.setImageResource(app.getPlayerData().getSpeed().getImage());
        mCivSpeed.setOnClickListener(this);
        mCivLaser = (CircleImageView) findViewById(R.id.civLaser);
        mCivLaser.setImageResource(app.getPlayerData().getLaser().getImage());
        mCivLaser.setOnClickListener(this);
        mCivReturn = (CircleImageView)findViewById(R.id.civReturn);
        mCivReturn.setOnClickListener(this);

        popupWindowArticle = new PopupWindow(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindowArticle.setBackgroundDrawable(new BitmapDrawable());
        popupWindowArticle.setFocusable(true);
        popupWindowArticle.setTouchable(true);
        popupWindowArticle.setOutsideTouchable(true);

        popupWindowPlayer = new PopupWindow(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindowPlayer.setBackgroundDrawable(new BitmapDrawable());
        popupWindowPlayer.setFocusable(true);
        popupWindowPlayer.setTouchable(true);
        popupWindowPlayer.setOutsideTouchable(true);
    }

    /**
     * 属性详情
     *
     * @param article 属性
     */
    private void showArticle(Article article) {
        View view = getLayoutInflater().inflate(R.layout.popupwindow_article, null);
        mIvArticle = (ImageView) view.findViewById(R.id.ivArticle);
        mIvArticle.setImageResource(article.getImage());
        mTvName = (TextView) view.findViewById(R.id.tvName);
        mTvName.setText(article.getName());
        mTvLevel = (TextView) view.findViewById(R.id.tvLevel);
        mTvLevel.setText(String.valueOf(article.getGrade()));
        mTvValue = (TextView) view.findViewById(R.id.tvValue);
        mTvValue.setText(String.valueOf(article.getValue()));

        popupWindowArticle.setContentView(view);
        popupWindowArticle.showAtLocation(mTvMoney, Gravity.CENTER, 0, 0);
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.alpha = 0.2f;
        getWindow().setAttributes(layoutParams);
        popupWindowArticle.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
                layoutParams.alpha = 1f;
                getWindow().setAttributes(layoutParams);
            }
        });
    }

    /**
     * 修改用户名
     */
    private void modifyUserName(){
        View view = getLayoutInflater().inflate(R.layout.popupwindow_player, null);
        mTvOld =(TextView) view.findViewById(R.id.tvOld);
        mTvOld.setText(app.getPlayerData().getPlayer().getName());
        mEtNew = (EditText)view.findViewById(R.id.evNew);
        mBtnSave = (Button) view.findViewById(R.id.btnSave);
        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = mEtNew.getText().toString().trim();
                app.getPlayerData().getPlayer().setName(newName);
                app.getPlayerDao().update(app.getPlayerData());
                mTvPlayer.setText(app.getPlayerData().getPlayer().getName());
                popupWindowPlayer.dismiss();
            }
        });

        popupWindowPlayer.setContentView(view);
        popupWindowPlayer.showAtLocation(mTvMoney, Gravity.CENTER, 0, 0);
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.alpha = 0.2f;
        getWindow().setAttributes(layoutParams);
        popupWindowPlayer.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
                layoutParams.alpha = 1f;
                getWindow().setAttributes(layoutParams);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvPlayer:
                modifyUserName();
                break;
            case R.id.civLife:
                showArticle(app.getPlayerData().getLife());
                break;
            case R.id.civDefense:
                showArticle(app.getPlayerData().getDefense());
                break;
            case R.id.civVelocity:
                showArticle(app.getPlayerData().getVelocity());
                break;
            case R.id.civShield:
                showArticle(app.getPlayerData().getShield());
                break;
            case R.id.civPower:
                showArticle(app.getPlayerData().getPower());
                break;
            case R.id.civSpeed:
                showArticle(app.getPlayerData().getSpeed());
                break;
            case R.id.civLaser:
                showArticle(app.getPlayerData().getLaser());
                break;
            case R.id.civReturn:
                finish();
                break;
            case R.id.btnPlay:
                Intent intent = new Intent(PlayerActivity.this, LevelActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                break;
            default:
                break;
        }
    }
}
