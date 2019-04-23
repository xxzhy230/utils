package com.yqjr.utils.service;


import android.app.Activity;
import android.content.Intent;

import com.xxzhy.okhttputils.callback.Callback;
import com.xxzhy.okhttputils.callback.StringCallback;
import com.yqjr.utils.OkHttpInit;
import com.yqjr.utils.Utils;
import com.yqjr.utils.base.AppManager;

import okhttp3.Call;
import okhttp3.Response;

public class StringJsonCallBack extends StringCallback {

    @Override
    public boolean validateReponse(Response response, int id) {
        int code = response.code();
        if (code == 502) {
            return false;
        } else if (code == 401) {
            AppManager.getAppManager().finishAllActivity();
            // TODO: 2019/1/24   跳转到登录界面
            Class classes = OkHttpInit.mActivity;
            if (Activity.class.isInstance(classes)) {
                return false;
            }

            Intent intent = new Intent();
            System.out.println("类名   : " + classes.getName());
            intent.setClassName(Utils.mContext, classes.getName());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Utils.mContext.startActivity(intent);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onError(Call call, Exception e, int i) {

    }

    @Override
    public void onResponse(String s, int i) {

    }


}
