package com.zhy.utils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.yqjr.utils.wheel.WheelViewUtils;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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


    private void fbBanner() {
        List mList = new ArrayList();
        mList.add("http://www.pptok.com/wp-content/uploads/2012/08/xunguang-4.jpg");
        mList.add("http://pic33.photophoto.cn/20141022/0019032438899352_b.jpg");
        mList.add("http://img.bimg.126.net/photo/ZZ5EGyuUCp9hBPk6_s4Ehg==/5727171351132208489.jpg");
        mList.add("http://img5.duitang.com/uploads/item/201411/07/20141107164412_v284V.jpeg");
    }


}
