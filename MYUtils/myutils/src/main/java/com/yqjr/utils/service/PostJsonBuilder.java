package com.yqjr.utils.service;

import com.google.gson.Gson;
import com.xxzhy.okhttputils.builder.OkHttpRequestBuilder;
import com.xxzhy.okhttputils.request.PostStringRequest;
import com.xxzhy.okhttputils.request.RequestCall;
import com.yqjr.utils.Utils;
import com.yqjr.utils.spUtils.SPUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.MediaType;

/**
 * Created by zhanghongyu on 2018/6/20.
 */

public class PostJsonBuilder extends OkHttpRequestBuilder<PostJsonBuilder> {
    public String json;
    public String url;
    public Object tag = "";
    public Map<String, String> headers;
    public Map<String, String> params = new HashMap<>();


    public PostJsonBuilder url(String url) {
        this.url = url;
        PostJsonBuilder postJsonBuilder = addHeader();
        return postJsonBuilder;
    }

    public PostJsonBuilder json(Map content) {
        Gson gson = new Gson();
        this.json = gson.toJson(content);
        return this;
    }

    private PostJsonBuilder addHeader() {
        if (this.headers == null) {
            headers = new LinkedHashMap<>();
        }
        headers.put("v_version", Utils.getVersionName());
        headers.put("v_device", "Android");
        headers.put(OkHttp.TOKEN, SPUtils.getToken() == null ? "" : SPUtils.getToken());
        return this;
    }

    public PostJsonBuilder tag(Object tag) {
        this.tag = tag;
        return this;
    }

    @Override
    public RequestCall build() {
        return new PostStringRequest(url, tag, params, headers, json, MediaType.parse("application/json; charset=utf-8"), id).build();
    }
}
