package com.yqjr.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.xxzhy.okhttputils.OkHttpUtils;
import com.xxzhy.okhttputils.cookie.CookieJarImpl;
import com.xxzhy.okhttputils.cookie.store.PersistentCookieStore;
import com.xxzhy.okhttputils.https.HttpsUtils;
import com.xxzhy.okhttputils.log.LoggerInterceptor;
import com.yqjr.utils.service.OkHttp;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * 初始化网络请求
 */
public class OkHttpInit {
    private static final long CONNECTTIMEOUT = 10000;
    private static final long READTIMEOUT = 10000;
    public static Class mActivity;
    private static volatile OkHttpInit mOkHttpInit;
    private Context mContext;

    OkHttpInit(Context mContext) {
        this.mContext = mContext;
    }

     static OkHttpInit init(Context mContext) {
        if (mOkHttpInit == null) {
            mOkHttpInit = new OkHttpInit(mContext);
        }
        return mOkHttpInit;
    }

    /**
     * okHttp初始化
     *
     * @param isTag          是否开启请求日志
     * @param tagName        日志名称
     * @param connectTimeout 连接时间 毫秒值
     * @param readTimeout    超时时间 毫秒值
     * @param activity       401跳转的activity
     */
    public void initOkhttpUtils( boolean isTag, String tagName, long connectTimeout, long readTimeout, Class<? extends Activity> activity,String token) {
        if (token == null) {
            token = "";
        }
        OkHttp.TOKEN = token;
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
        CookieJarImpl cookieJar = new CookieJarImpl(new PersistentCookieStore(mContext));
        OkHttpClient okHttpClient;
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (isTag) {
            builder.addInterceptor(new LoggerInterceptor(tagName));
        }
        okHttpClient = builder.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .connectTimeout(connectTimeout < 10000 ? CONNECTTIMEOUT : connectTimeout, TimeUnit.MILLISECONDS)
                .readTimeout(readTimeout < 10000 ? READTIMEOUT : readTimeout, TimeUnit.MILLISECONDS)
                .cookieJar(cookieJar)//cookie保活
                .build();

        OkHttpUtils.initClient(okHttpClient);
        mActivity = activity;
    }


}
