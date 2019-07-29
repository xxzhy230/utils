package com.zhy.utils;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.view.View;

import android.widget.ListView;
import android.widget.Toast;

import com.yqjr.superviseapp.utils.ext.Klog;
import com.yqjr.utils.base.BaseActivity;
import com.yqjr.utils.base.BaseIViewString;
import com.yqjr.utils.flyBanner.FlyBanner;
import com.yqjr.utils.utils.GPSUtils;

import com.yqjr.utils.wheel.WheelViewUtils;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends BaseActivity implements BaseIViewString {

    private View view;
    private FlyBanner fb_banner;

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initFindView() {
        fb_banner = findViewById(R.id.fb_banner);
        fbBanner();
        GPSUtils gpsUtils = new GPSUtils(this);
//        String localCity = GPSUtils.getLocalCity();
//        String addressStr = GPSUtils.getAddressStr();
//        Klog.Companion.d("localCity : " + localCity);
//        Klog.Companion.d("addressStr : " + addressStr);

    }


    @Override
    public void onClickEvent() {

    }

    @Override
    public View getView() {
        view = View.inflate(this, R.layout.activity_main, null);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void initView() {
        initFindView();
    }

    public void button(View view) {
        List<String> mList = new ArrayList<>();
        mList.add("吉林省");
        mList.add("长春市");
        mList.add("高新区");
        mList.add("天盛名都");
        mList.add("8栋三单元");
        WheelViewUtils.init(this).setItemVisible(5).setCancelTitle("取消").setCancelColor(R.color.color_555)
                .setTextSizeCenter(20).setTextSizeOuter(14).setTextColorOuter(R.color.color_555).setTextColorCenter(R.color.colorAccent)
                .setCancelTextSize(18).setConfirmTextSize(18).setConfirmColor(R.color.colorPrimary).setConfirmTitle("确定")
                .setData(mList, 1).setLineColor(R.color.colorPrimaryDark).setOnSubmitListener(new WheelViewUtils.OnSubmitListener() {


            @Override
            public void onSubmit(int type, String content, int position) {

            }
        }).build().show();


//        WheelViewTimeUtils.init(this).setItemVisible(5).setCancelTitle("取消").setCancelColor(R.color.color_555)
//                .setTitleBg(R.drawable.white_bg)
//                .setTextSizeCenter(20).setTextSizeOuter(14).setTextColorOuter(R.color.color_555).setTextColorCenter(R.color.colorAccent)
//                .setCancelTextSize(18).setConfirmTextSize(18).setConfirmColor(R.color.colorPrimary).setConfirmTitle("确定")
//                .setTimeData(1,"2009","12","12").setLineColor(R.color.colorPrimaryDark).setOnSubmitListener(new WheelViewTimeUtils.OnSubmitDateListener() {
//            @Override
//            public void onSubmit(int type, String content) {
//
//            }
//        }).build().show();
    }

    @Override
    public void result(boolean state, String json, int position) {
        System.out.println("状态  : " + state);
        System.out.println("json  : " + json);
        LoginModel loginModel = new LoginModel();
//        loginModel.gsonData(json);


    }

    private void fbBanner() {
        List mList = new ArrayList();
        mList.add("http://www.pptok.com/wp-content/uploads/2012/08/xunguang-4.jpg");
        mList.add("http://pic33.photophoto.cn/20141022/0019032438899352_b.jpg");
        mList.add("http://img.bimg.126.net/photo/ZZ5EGyuUCp9hBPk6_s4Ehg==/5727171351132208489.jpg");
        mList.add("http://img5.duitang.com/uploads/item/201411/07/20141107164412_v284V.jpeg");
        fb_banner.setImagesUrl(mList);
    }


}
