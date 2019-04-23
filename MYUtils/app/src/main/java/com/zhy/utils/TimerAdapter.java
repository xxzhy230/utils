package com.zhy.utils;

import android.os.CountDownTimer;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yqjr.utils.utils.CountDownUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TimerAdapter extends BaseAdapter {
    private List<TimerBean.DataBean.ListBean> mList;
    private SparseArray<CountDownTimer> countDownCounters;
    private long bLong;

    public TimerAdapter(List<TimerBean.DataBean.ListBean> mList) {
        this.mList = mList;
        countDownCounters = new SparseArray<>();
    }

    /**
     * avtivity 销毁前释放资源
     */
    public void cancelAllTimers() {
        if (countDownCounters != null) {
            for (int i = 0, length = countDownCounters.size(); i < length; i++) {
                CountDownTimer cdt = countDownCounters.get(countDownCounters.keyAt(i));
                if (cdt != null) {
                    cdt.cancel();
                }
            }
        }
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.item_timer, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        System.out.println(position);
        TimerBean.DataBean.ListBean listBean = mList.get(position);
        long timer = listBean.getCreate_time();
        long timers = timer + 120 * 60;
        timers = timers - (System.currentTimeMillis() / 1000);
        CountDownTimer countDownTimer = countDownCounters.get(holder.tvTimer.hashCode());
        if (countDownTimer != null) {
            //将复用的倒计时清除
            countDownTimer.cancel();
        }
        /**
         * 设置tag   在ids.xml中定义id <item name="tag_position_id" type="id" />
         */
        Object tag = holder.tvTimer.getTag(R.id.tag_position_id);
        if (tag != null) {
            bLong = (long) tag;
        } else {
            bLong = timers;
        }
        if (bLong > 0) {
            countDownTimer = new CountDownTimer(bLong * 1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    holder.tvTimer.setTag(R.id.tag_position_id, millisUntilFinished);
                    holder.tvTimer.setText(formatTime(millisUntilFinished));
                }

                @Override
                public void onFinish() {
                    holder.tvTimer.setText("倒计时结束");
                }
            }.start();
            countDownCounters.put(holder.tvTimer.hashCode(), countDownTimer);
        } else {
            holder.tvTimer.setText("倒计时结束");
        }
        return convertView;
    }

    static class ViewHolder {
        TextView tvTimer;

        ViewHolder(View view) {
            tvTimer = view.findViewById(R.id.tv_timer);
//            ButterKnife.bind(this, view);
        }
    }

    /*
     * 毫秒转化
     */
    public static String formatTime(long ms) {

        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;
        int dd = hh * 24;

        long day = ms / dd;
        long hour = (ms - day * dd) / hh;
        long minute = (ms - day * dd - hour * hh) / mi;
        long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        String strDay = day < 10 ? "0" + day : "" + day; //天
        String strHour = hour < 10 ? "0" + hour : "" + hour;//小时
        String strMinute = minute < 10 ? "0" + minute : "" + minute;//分钟
        String strSecond = second < 10 ? "0" + second : "" + second;//秒
        String strMilliSecond = milliSecond < 10 ? "0" + milliSecond : "" + milliSecond;//毫秒
        strMilliSecond = milliSecond < 100 ? "0" + strMilliSecond : "" + strMilliSecond;

        return strMinute + " 分钟 " + strSecond + " 秒";
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
        if (milliSecond > 0) {
            sb.append(milliSecond + "毫秒");
        }
        return sb.toString();
    }

}
