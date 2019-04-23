package com.zhy.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.Toast;

import com.xxzhy.okhttputils.OkHttpUtils;
import com.yqjr.utils.base.BaseIView;
import com.yqjr.utils.base.BaseIViewFile;
import com.yqjr.utils.base.BaseIViewString;
import com.yqjr.utils.base.BaseIpresenterCompl;
import com.yqjr.utils.service.OkHttp;
import com.yqjr.utils.service.PostJsonBuilder;
import com.yqjr.utils.service.StringJsonCallBack;

import java.util.Map;

import okhttp3.Call;

public class IpresenterCompl extends BaseIpresenterCompl {
    public IpresenterCompl(BaseIViewString baseIViewString) {
        this.baseIViewString = baseIViewString;
    }

    public IpresenterCompl(BaseIViewFile baseIViewFile) {
        this.baseIViewFile = baseIViewFile;
    }

    public IpresenterCompl(BaseIViewString baseIViewString, BaseIViewFile baseIViewFile) {
        this.baseIViewString = baseIViewString;
        this.baseIViewFile = baseIViewFile;
    }

    @Override
    public void doData(Map map) {
        super.doData(map);
    }


    public void postJson(Context mContext, String url, Map map, final int position) {
        boolean netState = getNetState(mContext);
        if (!netState) {
            baseIViewString.result(false, null, position);
            return;
        }
        OkHttp.postJson().url(url)
                .json(map).build().execute(new StringJsonCallBack() {
            @Override
            public void onResponse(String response, int id) {
                super.onResponse(response, id);
                baseIViewString.result(true, response, position);
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                super.onError(call, e, id);
                baseIViewString.result(false, null, position);
            }
        });
    }

    public void getString(String url, Map map) {

        OkHttp.get().url(url)
                .params(map).build().execute(new StringJsonCallBack() {
            @Override
            public void onResponse(String response, int id) {
                super.onResponse(response, id);
                baseIViewString.result(true, response, 0);
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                super.onError(call, e, id);
                baseIViewString.result(false, null, 0);
            }
        });
    }

    public void postString(String url, Map map) {
        OkHttp.post().url(url)
                .params(map).build().execute(new StringJsonCallBack() {
            @Override
            public void onResponse(String response, int id) {
                super.onResponse(response, id);

                baseIViewString.result(true, response, 0);
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                super.onError(call, e, id);
                baseIViewString.result(false, null, 0);
            }
        });
    }

    private boolean getNetState(Context mContext) {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(MainActivity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isAvailable())
            return true;
        else {
            return false;
        }

    }
}
