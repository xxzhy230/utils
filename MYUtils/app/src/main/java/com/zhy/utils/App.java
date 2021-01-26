package com.zhy.utils;

import android.app.Application;
import android.content.Intent;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.yqjr.utils.OkHttpInit;
import com.yqjr.utils.Utils;
import com.yqjr.utils.service.OkHttp;

import java.lang.reflect.Field;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        getHelper();
        Utils.initUtils(getApplicationContext()).initHttp(true, "000", 5000l, 5000l, null);
//        init(OkHttpInit.okHttpClient);
        Utils.initUtils(this);
    }

    private volatile DataBaseHelper mHelper;

    public OrmLiteSqliteOpenHelper getHelper() {
        if (mHelper == null) {
            mHelper = OpenHelperManager.getHelper(this, DataBaseHelper.class);
        }
        return mHelper;
    }


    private void init(OkHttpClient okHttpClient) {
        HostnameVerifier hv1 = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };

        //通过反射拿到okhttpClient 对象，给sslParams的SSLSocketFactory赋值
        String workerClassName = "okhttp3.OkHttpClient";

        try {
            Class workerClass = Class.forName(workerClassName);
            Field hostnameVerifier = workerClass.getDeclaredField("hostnameVerifier");
            hostnameVerifier.setAccessible(true);
            hostnameVerifier.set(okHttpClient, hv1);
            Field sslSocketFactory = workerClass.getDeclaredField("sslSocketFactory");
            sslSocketFactory.setAccessible(true);
            sslSocketFactory.set(okHttpClient, SSLSocketFactoryUtils.getSSLSocketFactory(this));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
