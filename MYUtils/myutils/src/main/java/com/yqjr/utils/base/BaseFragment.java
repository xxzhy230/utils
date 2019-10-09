package com.yqjr.utils.base;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yqjr.utils.R;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import butterknife.ButterKnife;
import pl.droidsonroids.gif.GifDrawable;

public abstract class BaseFragment extends Fragment {

    private GifDrawable gifDrawable;
    private Dialog pg;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = getResourceView();
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView(savedInstanceState);
        onClickEvent();
    }

    //获取布局文件
    protected abstract View getResourceView();

    public abstract void onClickEvent();
    //初始化view
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        super.onDestroyView();
    }
    public void showProgress(boolean isCancel) {
        try {
            gifDrawable = new GifDrawable(getResources(), R.mipmap.loading);
            pg = new Dialog(getActivity(), R.style.myDialog);
            View view = View.inflate(getActivity(), R.layout.dialog_loading, null);
            pg.setContentView(view);
            pg.setCanceledOnTouchOutside(isCancel);// 按返回键不能退出
            pg.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    gifDrawable.stop();
                    dismissProgress();
                }
            });
            if (!pg.isShowing()) {
                gifDrawable.start();
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

