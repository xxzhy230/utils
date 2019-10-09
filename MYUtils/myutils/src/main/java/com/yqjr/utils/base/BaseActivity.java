package com.yqjr.utils.base;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;

import com.yqjr.utils.R;
import com.yqjr.utils.Utils;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;


public abstract class BaseActivity extends FragmentActivity {

    private GifDrawable gifDrawable;
    private Dialog pg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onBeforeSetContentView();
        setContentView(getView());
        View vIncludeBar = findViewById(R.id.v_include_bar);
        if (vIncludeBar != null) {
            ViewGroup.LayoutParams layoutParams = vIncludeBar.getLayoutParams();
            layoutParams.height = Utils.stateHeight;
            vIncludeBar.setLayoutParams(layoutParams);
        }
        onClickEvent();
        initView();
        changeNet();
    }


    public abstract void onClickEvent();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        dismissProgress();
    }

    //获取布局文件
    public abstract View getView();

    //初始化view
    protected abstract void initView();

    //实现网络状态变化
    protected abstract void changeNet();

    /**
     * 设置layout前配置
     */
    public void onBeforeSetContentView() {
        AppManager.getAppManager().addActivity(this);
        // 设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void showProgress(boolean isCancel) {
        try {
            if (pg == null) {
                View view = View.inflate(this, R.layout.dialog_loading, null);
                pg = new Dialog(this, R.style.myDialog);
                pg.setContentView(view);
                gifDrawable = new GifDrawable(getResources(), R.mipmap.loading);
                pg.setCanceledOnTouchOutside(isCancel);// 按返回键不能退出
                pg.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        gifDrawable.stop();
                        dismissProgress();
                    }
                });
            }

            if (!pg.isShowing()) {
                gifDrawable.start();
                pg.setCancelable(isCancel);
                pg.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * 进度条消失
     */
    public void dismissProgress() {
        if (pg != null) {
            pg.dismiss();
            pg = null;
        }
    }
}
