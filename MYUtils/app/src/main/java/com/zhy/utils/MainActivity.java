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
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;

import android.widget.ListView;
import android.widget.Toast;

import com.yqjr.utils.base.BaseActivity;
import com.yqjr.utils.base.BaseIViewString;
import com.yqjr.utils.flyBanner.FlyBanner;
import com.yqjr.utils.utils.StatusBarUtil;
import com.yqjr.utils.utils.StringUtils;
import com.yqjr.utils.wheel.WheelViewUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class MainActivity extends BaseActivity implements BaseIViewString {
    private static final int BAIDU_READ_PHONE_STATE = 100;//定位权限请求
    private static final int PRIVATE_CODE = 1315;//开启GPS权限
    static final String[] LOCATIONGPS = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_PHONE_STATE};

    ListView lvTimer;
    private View view;
    private FlyBanner fb_banner;

    private void initFindView() {
        fb_banner = findViewById(R.id.fb_banner);
        fbBanner();
//
//        try {
//            HouseInfoDao houseInfos = new HouseInfoDao((DataBaseHelper)Utils.initUtils(this).helper);
//            HouseInfo houseInfo = new HouseInfo();
//            houseInfo.setDid("12345");
//            houseInfo.setHouseName("nha");
//            houseInfo.setPdid("12345");
//            houseInfo.setSeclet(false);
//            houseInfo.setUserId("1");
//            houseInfos.insertRawData(this,houseInfo);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        showGPSContacts();
        IpresenterCompl iPresenter = new IpresenterCompl(this);
        Map map = new HashMap();
        map.put("username", "18888888888");
        map.put("sysTime", System.currentTimeMillis() + "");
        map.put("password", StringUtils.md5(StringUtils.md5("123abc") + "zkr2019"));
        String zkr2019 = StringUtils.getSign(map, "zkr2019");
        map.put("sign", zkr2019);
        String urls = "https://test.17ebank.com:9137/api/login/authenticateforsign";
//        iPresenter.postJson(this, urls, map, 0);
//        List<Long> mList = new ArrayList<>();
//        for (int i = 0; i < 30; i++) {
//            mList.add(1000000l + i * 100000 );
//        }

//        TimerAdapter timerAdapter = new TimerAdapter(list);
//        lvTimer.setAdapter(timerAdapter);
//        new VGUtil(lvTimer, new SingleAdapter<Long>(this, mList, R.layout.item_timer) {
//
//            @Override
//            public void onBindViewHolder(ViewGroup viewGroup,ViewHolder viewHolder, final Long aLong, final int i) {
//                TextView tv_send_code = viewHolder.getView(R.id.tv_timer);
////               new CountDownUtil(tv_send_code,aLong,1).start();
//            }
//        }).bind();

//        lvTimer.setAdapter(new CommonAdapter<Long>(this,mList, R.layout.item_timer) {
//
//            @Override
//            public void convert(com.mcxtzhang.commonadapter.lvgv.ViewHolder holder, Long aLong, int position) {
//                TextView tv_send_code = holder.getView(R.id.tv_timer);
//               new CountDownUtil(tv_send_code,aLong,1).start();
//            }
//        });

    }


    @Override
    public void onClickEvent() {

    }

    @Override
    public View getView() {
//        StatusBarUtil.setStatusBarMode(this,true,R.color.white);
        StatusBarUtil.fullScreen(this);
        view = View.inflate(this, R.layout.activity_main, null);
        return view;
    }

    @Override
    protected void initView() {
        initFindView();
    }

    public void button(View view) {
        List<String> mList = new ArrayList<>();
        mList.add("1");
        mList.add("1");
        mList.add("1");
        mList.add("1");
        WheelViewUtils.init(this).showWheelDateDialog(1, "生日", "year", "months", "days", new WheelViewUtils.OnSubmitDateListener() {
            @Override
            public void onSubmit(int type, String content) {

            }
        });

        WheelViewUtils.init(this)
                .setTitleBar("", R.color.black, R.color.white, R.color.color_555, R.color.confirm)
                .setTextColorAndTextSize(5, 14, 15, 0xff00ff00, 0xffff0000, 0xff0000ff)
                .showWheelOneDialog(mList, 1, new WheelViewUtils.OnSubmitOneListener() {

                    @Override
                    public void onSubmit(int type, String content, int position) {

                    }
                });
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

    /**
     * 检测GPS、位置权限是否开启
     */
    public void showGPSContacts() {
        LocationManager lm = (LocationManager) this.getSystemService(this.LOCATION_SERVICE);
        boolean ok = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        System.out.println("====ok======  : " + ok);
        if (ok) {//开了定位服务
            if (Build.VERSION.SDK_INT >= 23) { //判断是否为android6.0系统版本，如果是，需要动态添加权限
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        != PERMISSION_GRANTED) {// 没有权限，申请权限。
                    ActivityCompat.requestPermissions(this, LOCATIONGPS,
                            BAIDU_READ_PHONE_STATE);
                } else {
                    System.out.println("====ok======---------  : " + ok);
                    getLocation();//getLocation为定位方法
                }
            } else {
                System.out.println("====ok======----+++++++++-----  : " + ok);
                getLocation();//getLocation为定位方法
            }
        } else {
            Toast.makeText(this, "系统检测到未开启GPS定位服务,请开启", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivityForResult(intent, PRIVATE_CODE);
        }
    }

    /**
     * 获取具体位置的经纬度
     */
    private void getLocation() {
        // 获取位置管理服务
        LocationManager locationManager;
        String serviceName = Context.LOCATION_SERVICE;
        locationManager = (LocationManager) this.getSystemService(serviceName);
        // 查找到服务信息
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);//高精度
        criteria.setAltitudeRequired(true);//包含高度信息
        criteria.setBearingRequired(true);//包含方位信息
        criteria.setSpeedRequired(true);//包含速度信息
        criteria.setCostAllowed(true);//允许付费
        criteria.setPowerRequirement(Criteria.POWER_HIGH);//高耗电

        String provider = locationManager.getBestProvider(criteria, true); // 获取GPS信息
        /**这段代码不需要深究，是locationManager.getLastKnownLocation(provider)自动生成的，不加会出错**/
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PERMISSION_GRANTED) {
            // TODO: Consider calling
            System.out.println("==========  : " );
            return;
        }
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER); // 通过GPS获取位置
        if (location == null) {
            System.out.println(getLngAndLatWithNetwork());
        }else{
            updateLocation(location);
        }

    }
    //从网络获取经纬度
    public String getLngAndLatWithNetwork() {
        double latitude = 0.0;
        double longitude = 0.0;
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, locationListener);
        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if (location != null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }
        return longitude + "," + latitude;
    }
    LocationListener locationListener = new LocationListener() {

        // Provider的状态在可用、暂时不可用和无服务三个状态直接切换时触发此函数
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        // Provider被enable时触发此函数，比如GPS被打开
        @Override
        public void onProviderEnabled(String provider) {

        }

        // Provider被disable时触发此函数，比如GPS被关闭
        @Override
        public void onProviderDisabled(String provider) {

        }

        //当坐标改变时触发此函数，如果Provider传进相同的坐标，它就不会被触发
        @Override
        public void onLocationChanged(Location location) {
        }
    };

    /**
     * 获取到当前位置的经纬度
     * @param location
     */
    private void updateLocation(Location location) {
        System.out.println("====-------adadsdaddsd--------======  : " );
        if (location != null) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            System.out.println("维度：" + latitude + "\n经度" + longitude);
        } else {
            System.out.println("无法获取到位置信息");
        }
    }
    /**
     * Android6.0申请权限的回调方法
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            // requestCode即所声明的权限获取码，在checkSelfPermission时传入
            case BAIDU_READ_PHONE_STATE:
                //如果用户取消，permissions可能为null.
                if (grantResults[0] == PERMISSION_GRANTED && grantResults.length > 0) {  //有权限
                    // 获取到权限，作相应处理
                    getLocation();
                } else {
                    showGPSContacts();
                }
                break;
            default:
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PRIVATE_CODE:
                getLocation();//getLocation为定位方法
                break;

        }
    }

}
