package com.yqjr.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;

import net.sqlcipher.database.SQLiteDatabase;

public class Utils {
    public static Context mContext;
    private static Utils utils;
    public OrmLiteSqliteOpenHelper helper;
    public static int stateHeight;

    public static Utils initUtils(Context context) {
        mContext = context;
        if (utils == null) {
            utils = new Utils();
        }
        int resourceId = mContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
        stateHeight = mContext.getResources().getDimensionPixelSize(resourceId);
        return utils;
    }

    //获取版本名
    public static String getVersionName() {
        return getPackageInfo(mContext).versionName;
    }

    //获取版本号
    public static int getVersionCode(Context context) {
        return getPackageInfo(context).versionCode;
    }

    //通过PackageInfo得到的想要启动的应用的包名
    private static PackageInfo getPackageInfo(Context context) {
        PackageInfo pInfo = null;
        try {
            //通过PackageManager可以得到PackageInfo
            PackageManager pManager = context.getPackageManager();
            pInfo = pManager.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);
            return pInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pInfo;
    }

    /**
     * 初始化数据库
     */
    public Utils initSQLite() {
        SQLiteDatabase.loadLibs(mContext);
        return utils;
    }

    /**
     * 初始化网络框架
     * @param istag   是否开启日志
     * @param tagName 日志名称
     * @param connectTimeout   连接时间
     * @param readTimeout     超时时间
     * @param classes    401 跳转的界面
     */
    public Utils initHttp(boolean istag, String tagName, long connectTimeout, long readTimeout, Class<Activity> classes,String token){
        OkHttpInit.init(mContext).initOkhttpUtils(istag, tagName, connectTimeout, readTimeout, classes,token);
        return utils;
    }

    /**
     * 初始化splite helper
     * @param ormLiteSqliteOpenHelper
     */
    public void initHelper(OrmLiteSqliteOpenHelper ormLiteSqliteOpenHelper){
       this.helper = ormLiteSqliteOpenHelper;
    }

    /**
     * 移除 helper
     */
    public void delHelper(){
       if (helper != null) {
           OpenHelperManager.releaseHelper();
           helper = null;
       }
    }
}
