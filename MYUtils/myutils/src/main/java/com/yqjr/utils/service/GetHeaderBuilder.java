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
 * Created by zhy on 15/12/14.
 */
public class GetHeaderBuilder extends GetBuilder {
    @Override
    public RequestCall build() {
        if (params != null) {
            url = appendParams(url, params);
        }

        return new GetRequest(url, tag, params, addHeader(), id).build();
    }

    private Map<String, String> addHeader() {
        if (this.headers == null) {
            headers = new LinkedHashMap<>();
        }


        headers.put("v_version", Utils.getVersionName());
        headers.put("v_device", "Android");
        headers.put("Authorization", SPUtils.getString("token") == null ? "" : SPUtils.getString("token"));
        return headers;
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


    @Override
    public GetHeaderBuilder params(Map<String, String> params) {
        this.params = params;
        return this;
    }

    @Override
    public GetHeaderBuilder addParams(String key, String val) {
        if (this.params == null) {
            params = new LinkedHashMap<>();
        }
        params.put(key, val);
        return this;
    }


}
