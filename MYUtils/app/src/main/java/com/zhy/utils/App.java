package com.zhy.utils;

import android.app.Application;
import android.content.Intent;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.yqjr.utils.OkHttpInit;
import com.yqjr.utils.Utils;
import com.yqjr.utils.service.OkHttp;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        getHelper();
        Utils.initUtils(getApplicationContext());

        Utils.initUtils(this).initSQLite().initHelper(mHelper);
    }

    private volatile DataBaseHelper mHelper;

    public OrmLiteSqliteOpenHelper getHelper() {
        if (mHelper == null) {
            mHelper = OpenHelperManager.getHelper(this, DataBaseHelper.class);
        }
        return mHelper;
    }
}
