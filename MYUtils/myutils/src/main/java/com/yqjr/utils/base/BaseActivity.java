package com.yqjr.utils.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;

import com.yqjr.utils.R;
import com.yqjr.utils.Utils;


public abstract class BaseActivity extends FragmentActivity {

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
        initView();
        onClickEvent();
    }

    public abstract void onClickEvent();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
    }

    //获取布局文件
    public abstract View getView();

    //初始化view
    protected abstract void initView();


    /**
     * 设置layout前配置
     */
    public void onBeforeSetContentView() {
        AppManager.getAppManager().addActivity(this);
        // 设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }
}
