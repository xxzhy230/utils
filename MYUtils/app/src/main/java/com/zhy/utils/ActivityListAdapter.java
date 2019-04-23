package com.zhy.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 客户端全部
 */
public class ActivityListAdapter extends BaseAdapter {

    private Context contexts;
    private List<TimerBean.DataBean.ListBean> mListData;
    private LayoutInflater inflater;

    private SparseArray<CountDownTimer> countDownCounters;
    private long bLong;


    public ActivityListAdapter(Context context, List<TimerBean.DataBean.ListBean> mListData) {
        this.contexts = context;
        inflater = LayoutInflater.from(contexts);
        this.mListData = mListData;
        this.countDownCounters = new SparseArray<>();
    }

    public void updateView(List<TimerBean.DataBean.ListBean> mListData) {
        this.mListData = mListData;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Object getItem(int position) {
        return mListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(contexts).inflate(R.layout.item_list_activity, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        int mainorder = mListData.get(position).getMainorder();

        if (mainorder == 20) {//匹配中
            holder.ll_siyue.setVisibility(View.GONE);
            holder.ll_suyue.setVisibility(View.VISIBLE);
            holder.ll_suyue_1.setVisibility(View.VISIBLE);
            holder.ll_suyue_2.setVisibility(View.GONE);
            holder.tv_suyue_title.setText("需求已发布，正在通知周围的艺人...");
            holder.tv_suyue_statue.setText("匹配中");
            holder.item_kehuduan_title_su.setText(mListData.get(position).getServices());
            holder.item_kehuduan_dizhi_su.setText(mListData.get(position).getAddressname());
            holder.item_kehuduan_yuetime_su.setText(mListData.get(position).getTimestr());
            holder.item_kehuduan_yuetime2_su.setText(mListData.get(position).getDuration() / 60 / 60 + "小时");
            CountDownTimer countDownTimer = countDownCounters.get(holder.tv_daojishi.hashCode());
            if (countDownTimer != null) {
                countDownTimer.cancel();
            }

            long timer = mListData.get(position).getCreate_time();
            long timers = timer + 120 * 60;
            timers = timers - (System.currentTimeMillis() / 1000);
            Object tag = holder.tv_daojishi.getTag(R.id.tag_position_id);
            if (tag != null) {
                bLong = (long) tag;
            } else {
                bLong = timers;
            }
            if (timers > 0) {
                countDownTimer = new CountDownTimer(bLong * 1000, 1000) {
                    public void onTick(long millisUntilFinished) {
                        System.out.println("position :  "+ position + "时间  : " + millisUntilFinished);
                        holder.tv_daojishi.setTag(R.id.tag_position_id, millisUntilFinished);
                        holder.tv_daojishi.setText(formatTime(millisUntilFinished));
                    }

                    public void onFinish() {
                        holder.tv_daojishi.setText("00:00:00");
                        holder.tv_vip_pipei.setText("倒计时结束后将有 VIP客服优先为您匹配");
                    }
                }.start();
                countDownCounters.put(holder.tv_daojishi.hashCode(), countDownTimer);
            } else {
                holder.tv_daojishi.setVisibility(View.GONE);
                holder.tv_vip_pipei.setText("VIP 客服正在人工为您匹配中。。。请稍候");
            }


            holder.item_kehuduan_button.setText("取消订单");
            holder.item_kehuduan_button_two.setVisibility(View.GONE);
        }

        return convertView;
    }


    class ViewHolder {
        TextView item_kehuduan_title, item_kehuduan_daijie, item_kehuduan_name, item_kehuduan_dizhi, tv_suyue_title, tv_suyue_statue,
                item_kehuduan_yuetime, item_kehuduan_money, item_kehuduan_cyj, item_kehuduan_yuetime2, item_kehuduan_age,
                item_kehuduan_time, item_kehuduan_button, item_kehuduan_button_two, item_kehuduan_title_su, item_kehuduan_dizhi_su, item_kehuduan_yuetime_su, item_kehuduan_yuetime2_su, tv_daojishi, tv_vip_pipei, tv_heads_more;
        ImageView item_kehuduan_face;
        LinearLayout ll_kehuduan_money, lll_birs, item_kehuduan_fuwurenzheng, ll_suyue, ll_siyue, ll_suyue_1, ll_suyue_2;
        ImageView item_kehuduan_imgnannv;
        RecyclerView recycy_suyue_heads;
        public  ViewHolder(View convertView){
            item_kehuduan_face = (ImageView) convertView.findViewById(R.id.item_kehuduan_face);
            item_kehuduan_title = (TextView) convertView.findViewById(R.id.item_kehuduan_title);
           item_kehuduan_title_su = (TextView) convertView.findViewById(R.id.item_kehuduan_title_su);
           item_kehuduan_yuetime_su = (TextView) convertView.findViewById(R.id.item_kehuduan_yuetime_su);
           item_kehuduan_yuetime2_su = (TextView) convertView.findViewById(R.id.item_kehuduan_yuetime2_su);
           tv_daojishi = (TextView) convertView.findViewById(R.id.tv_daojishi);
           item_kehuduan_daijie = (TextView) convertView.findViewById(R.id.item_kehuduan_daijie);item_kehuduan_dizhi_su = (TextView) convertView.findViewById(R.id.item_kehuduan_dizhi_su);
             tv_suyue_statue = (TextView) convertView.findViewById(R.id.tv_suyue_statue);
           tv_suyue_title = (TextView) convertView.findViewById(R.id.tv_suyue_title);
             item_kehuduan_name = (TextView) convertView.findViewById(R.id.item_kehuduan_name);
           item_kehuduan_fuwurenzheng = (LinearLayout) convertView.findViewById(R.id.item_kehuduan_fuwurenzheng);
           item_kehuduan_dizhi = (TextView) convertView.findViewById(R.id.item_kehuduan_dizhi);
           item_kehuduan_yuetime = (TextView) convertView.findViewById(R.id.item_kehuduan_yuetime);
           item_kehuduan_yuetime2 = (TextView) convertView.findViewById(R.id.item_kehuduan_yuetime2);
           item_kehuduan_money = (TextView) convertView.findViewById(R.id.item_kehuduan_money);
           item_kehuduan_time = (TextView) convertView.findViewById(R.id.item_kehuduan_time);
           tv_vip_pipei = (TextView) convertView.findViewById(R.id.tv_vip_pipei);
           item_kehuduan_button = (TextView) convertView.findViewById(R.id.item_kehuduan_button);
           item_kehuduan_button_two = (TextView) convertView.findViewById(R.id.item_kehuduan_button_two);
           item_kehuduan_age = (TextView) convertView.findViewById(R.id.item_kehuduan_age);
           tv_heads_more = (TextView) convertView.findViewById(R.id.tv_heads_more);
           recycy_suyue_heads = (RecyclerView) convertView.findViewById(R.id.recycy_suyue_heads);
           ll_kehuduan_money = (LinearLayout) convertView.findViewById(R.id.ll_kehuduan_money);
           item_kehuduan_imgnannv = (ImageView) convertView.findViewById(R.id.item_kehuduan_imgnannv);
           lll_birs = (LinearLayout) convertView.findViewById(R.id.lll_birs);
           ll_suyue = (LinearLayout) convertView.findViewById(R.id.ll_suyue);
           ll_siyue = (LinearLayout) convertView.findViewById(R.id.ll_siyue);
           ll_suyue_2 = (LinearLayout) convertView.findViewById(R.id.ll_suyue_2);
           ll_suyue_1 = (LinearLayout) convertView.findViewById(R.id.ll_suyue_1);
        }
    }


    private long minutes;
    private long seconds;
    private long diff;


    private long dsss_minutes;
    private long dsss_seconds;
    private long dsss;


    /**
     * 得到时间差
     */
    private void getTime(String finacntime) {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sDateFormat.format(new Date());
        System.out.println("现在时间：" + date);
        try {
            Date d1 = df.parse(finacntime);
            Date d2 = df.parse(date);
            diff = d1.getTime() - d2.getTime();//这样得到的差值是微秒级别
            minutes = (diff) / (1000 * 60);
            seconds = (diff - minutes * (1000 * 60)) / (1000);
//			tender_pay.setText(""+days+"天"+hours+"小时"+minutes+"分"+seconds+"秒");
//            holder.tv_daojishi.setText(getTimeShort(minutes + ":" + seconds));
            System.out.println("现在时间：diff " + diff);
            System.out.println("" + minutes + "分" + seconds + "秒");
        } catch (Exception e) {
        }
    }

    /**
     * 获得要显示的时间
     */
    private void getShowTime() {
        minutes = (diff) / (1000 * 60);
        seconds = (diff - minutes * (1000 * 60)) / (1000);
//		tender_pay.setText(""+days+"天"+hours+"小时"+minutes+"分"+seconds+"秒");
//        holder.tv_daojishi.setText(getTimeShort(minutes + ":" + seconds));
    }

    private void getShowTime1() {
        dsss_minutes = (dsss) / (1000 * 60);
        dsss_seconds = (dsss - dsss_minutes * (1000 * 60)) / (1000);
//		tender_pay.setText(""+days+"天"+hours+"小时"+minutes+"分"+seconds+"秒");
//        holder.item_kehuduan_button_two.setText("取消订单\n" + getTimeShort(dsss_minutes + ":" + dsss_seconds));
    }

    /**
     * 获取时间 小时:分;秒 HH:mm:ss
     *
     * @return
     */
    public static String getTimeShort(String currentTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");//小写的mm表示的是分钟
        Date date;
        try {
            date = sdf.parse(currentTime);
            String dateString = sdf.format(date);
            return dateString;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";

    }

    /*
     * 毫秒转化时分秒毫秒
     */
    public static String formatTime(Long ms) {
        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        Long day = ms / dd;
        Long hour = (ms - day * dd) / hh;
        Long minute = (ms - day * dd - hour * hh) / mi;
        Long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        Long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        StringBuffer sb = new StringBuffer();
        if (day > 0) {
            sb.append(day + "天");
        }
        if (hour > 0) {
            sb.append(hour + "小时");
        }
        if (minute > 0) {
            sb.append(minute + "分");
        }
        if (second > 0) {
            sb.append(second + "秒");
        }
//        if (milliSecond > 0) {
//            sb.append(milliSecond + "毫秒");
//        }
        return sb.toString();
    }
}
