package com.yqjr.utils.service;

import android.net.Uri;


import com.xxzhy.okhttputils.builder.GetBuilder;
import com.xxzhy.okhttputils.request.GetRequest;
import com.xxzhy.okhttputils.request.RequestCall;
import com.yqjr.utils.Utils;
import com.yqjr.utils.spUtils.SPUtils;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;


/**
 * Created by zhanghongyu on 2018/6/20.
 */

public class GetStrBuilder extends GetBuilder {
    @Override
    public RequestCall build() {
        if (params != null) {
            url = appendParams(url, params);
        }

        return new GetRequest(url, tag, params, headers, id).build();
    }

    public GetBuilder url(String url) {
        this.url = url;
        GetBuilder builder = addHeader();
        return builder;
    }

    protected String appendParams(String url, Map<String, String> params) {
        if (url == null || params == null || params.isEmpty()) {
            return url;
        }
        Uri.Builder builder = Uri.parse(url).buildUpon();
        Set<String> keys = params.keySet();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            builder.appendQueryParameter(key, params.get(key));
        }
        return builder.build().toString();
    }

    private GetBuilder addHeader() {
        if (this.headers == null) {
            headers = new LinkedHashMap<>();

        }
        headers.put("v_version", Utils.getVersionName());
        headers.put("v_device", "Android");
        headers.put(OkHttp.TOKEN, SPUtils.getToken() == null?"":SPUtils.getToken());
        return this;
    }

    @Override
    public GetBuilder params(Map<String, String> params) {
        this.params = params;
        return this;
    }

    @Override
    public GetBuilder addParams(String key, String val) {
        if (this.params == null) {
            params = new LinkedHashMap<>();
        }
        params.put(key, val);
        return this;
    }

}
