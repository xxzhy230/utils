package com.yqjr.utils.service;

import com.xxzhy.okhttputils.OkHttpUtils;
import com.xxzhy.okhttputils.builder.GetBuilder;
import com.xxzhy.okhttputils.builder.HeadBuilder;
import com.xxzhy.okhttputils.builder.OtherRequestBuilder;
import com.xxzhy.okhttputils.builder.PostFileBuilder;
import com.xxzhy.okhttputils.builder.PostFormBuilder;
import com.xxzhy.okhttputils.builder.PostStringBuilder;
import com.xxzhy.okhttputils.callback.Callback;
import com.xxzhy.okhttputils.request.RequestCall;
import com.xxzhy.okhttputils.utils.Platform;


import java.io.IOException;
import java.util.concurrent.Executor;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by zhanghongyu on 2018/6/20.
 */

public class OkHttp extends OkHttpUtils {
    public static String TOKEN;

    private volatile static OkHttpUtils mInstance;
    private OkHttpClient mOkHttpClient;
    private Platform mPlatform;

    public OkHttp(OkHttpClient okHttpClient) {
        super(okHttpClient);
        if (okHttpClient == null)
        {
            mOkHttpClient = new OkHttpClient();
        } else
        {
            mOkHttpClient = okHttpClient;
        }

        mPlatform = Platform.get();
    }


    public static OkHttpUtils initClient(OkHttpClient okHttpClient)
    {
        if (mInstance == null)
        {
            synchronized (OkHttpUtils.class)
            {
                if (mInstance == null)
                {
                    mInstance = new OkHttpUtils(okHttpClient);
                }
            }
        }

        return mInstance;
    }

    public static OkHttpUtils getInstance()
    {
        return initClient(null);
    }


    public Executor getDelivery()
    {
        return mPlatform.defaultCallbackExecutor();
    }

    public OkHttpClient getOkHttpClient()
    {
        return mOkHttpClient;
    }

    public static GetBuilder get()
    {
        return new GetStrBuilder();
    }

//    public static PostStringBuilder postString()
//    {
//        return new PostStringBuilder();
//    }
//    public static PostStrBuilder postStr()
//    {
//        return new PostStrBuilder();
//    }

    public static PostFileBuilder postFile()
    {
        return new PostFileBuilder();
    }

    public static PostFormBuilder post()
    {
        return new PostFormBuilder();
    }
    public static PostJsonBuilder postJson()
    {
        return new PostJsonBuilder();
    }
//    public static OtherRequestBuilder put()
//    {
//        return new OtherRequestBuilder(OkHttpUtils.METHOD.PUT);
//    }
//
//    public static HeadBuilder head()
//    {
//        return new HeadBuilder();
//    }

//    public static OtherRequestBuilder delete()
//    {
//        return new OtherRequestBuilder(OkHttpUtils.METHOD.DELETE);
//    }
//
//    public static OtherRequestBuilder patch()
//    {
//        return new OtherRequestBuilder(OkHttpUtils.METHOD.PATCH);
//    }

    public void execute(final RequestCall requestCall, Callback callback)
    {
        if (callback == null)
            callback = Callback.CALLBACK_DEFAULT;
        final Callback finalCallback = callback;
        final int id = requestCall.getOkHttpRequest().getId();
        requestCall.getCall().enqueue(new okhttp3.Callback()
        {
            @Override
            public void onFailure(Call call, final IOException e)
            {
                sendFailResultCallback(call, e, finalCallback, id);
            }

            @Override
            public void onResponse(final Call call, final Response response)
            {
                try
                {
                    if (call.isCanceled())
                    {
                        sendFailResultCallback(call, new IOException("Canceled!"), finalCallback, id);
                        return;
                    }

                    if (!finalCallback.validateReponse(response, id))
                    {
                        mySendFailResultCallback(response,call, new IOException("request failed , reponse's code is : " + response.code()), finalCallback, id);
                        return;
                    }

                    Object o = finalCallback.parseNetworkResponse(response, id);
                    sendSuccessResultCallback(o, finalCallback, id);
                } catch (Exception e)
                {
                    sendFailResultCallback(call, e, finalCallback, id);
                } finally
                {
                    if (response.body() != null)
                        response.body().close();
                }

            }
        });
    }


    public void sendFailResultCallback(final Call call, final Exception e, final Callback callback, final int id)
    {
        if (callback == null) return;

        mPlatform.execute(new Runnable()
        {
            @Override
            public void run()
            {
                callback.onError(call, e, id);
                callback.onAfter(id);
            }
        });
    }

    public void mySendFailResultCallback(Response response, final Call call, final Exception e, final Callback callback, final int id)
    {
        if (callback == null) return;

        mPlatform.execute(new Runnable()
        {
            @Override
            public void run()
            {
                callback.onError(call, e, id);
                callback.onAfter(id);
            }
        });
    }
    public void sendSuccessResultCallback(final Object object, final Callback callback, final int id)
    {
        if (callback == null) return;
        mPlatform.execute(new Runnable()
        {
            @Override
            public void run()
            {
                callback.onResponse(object, id);
                callback.onAfter(id);
            }
        });
    }

    public void cancelTag(Object tag)
    {
        for (Call call : mOkHttpClient.dispatcher().queuedCalls())
        {
            if (tag.equals(call.request().tag()))
            {
                call.cancel();
            }
        }
        for (Call call : mOkHttpClient.dispatcher().runningCalls())
        {
            if (tag.equals(call.request().tag()))
            {
                call.cancel();
            }
        }
    }

    public static class METHOD
    {
        public static final String HEAD = "HEAD";
        public static final String DELETE = "DELETE";
        public static final String PUT = "PUT";
        public static final String PATCH = "PATCH";
    }
}
