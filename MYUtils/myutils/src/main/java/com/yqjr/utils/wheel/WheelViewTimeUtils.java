package com.yqjr.utils.wheel;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yqjr.utils.R;
import com.yqjr.utils.utils.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class WheelViewTimeUtils {
    private int itemsVisible = 7;
    private int textSizeCenter = 18;
    private int textSizeOuter = 13;
    private int textColorOuter= R.color.gray;
    private int textColorCenter= R.color.blue;
    private int titleLineColor= R.color.line_bg;
    private int cancelColor =  R.color.gray;
    private int confirmColor= R.color.blue;
    private int titleColor = R.color.black;
    private int backgroundColor = R.color.white;
    private int lineColor = R.color.white;
    private int titleBg = R.color.white;
    private boolean titleLineVisible = true;
    private String title = "";
    private String canceltitle = "取消";

    private String confirmtitle = "确定";
    private int cancelTextSize = 18;
    private int confirmTextSize = 18;
    private int titleTextSize = 20;
    private static int monthIndex;
    private static BottomDialog bottomDialog;
    private static OnSubmitDateListener onSubmitListener;
    private static WheelViewTimeUtils wheelViewUtils;
    private Context mContext;
    private View outerView1;

    private WheelViewTimeUtils(Context mContext) {
        this.mContext = mContext;
    }

    public WheelViewTimeUtils setOnSubmitListener(OnSubmitDateListener onSubmitListener) {
        onSubmitListener = onSubmitListener;
        return this;
    }


    public static WheelViewTimeUtils init(Context mContext) {
        if (wheelViewUtils == null) {
            wheelViewUtils = new WheelViewTimeUtils(mContext);
        }
        return wheelViewUtils;
    }

    public WheelViewTimeUtils setTitle(String title) {
        this.title = title;
        return this;
    }

    public WheelViewTimeUtils setCancelTitle(String canceltitle) {
        this.canceltitle = canceltitle;
        return this;
    }

    public WheelViewTimeUtils setTitleBg(int titleBg) {
        this.titleBg = titleBg;
        return this;
    }

    public WheelViewTimeUtils setConfirmTitle(String confirmtitle) {
        this.confirmtitle = confirmtitle;
        return this;
    }

    public WheelViewTimeUtils setTitleColor(int titleColor) {
        this.titleColor = titleColor;
        return this;
    }

    public WheelViewTimeUtils setCancelColor(int cancelColor) {
        this.cancelColor = cancelColor;
        return this;
    }

    public WheelViewTimeUtils setConfirmColor(int confirmColor) {
        this.confirmColor = confirmColor;
        return this;
    }

    public WheelViewTimeUtils setLineColor(int lineColor) {
        this.lineColor = lineColor;
        return this;
    }

    public WheelViewTimeUtils setItemVisible(int itemsVisible) {
        this.itemsVisible = itemsVisible;
        return this;
    }

    public WheelViewTimeUtils setTextSizeCenter(int textSizeCenter) {
        this.textSizeCenter = textSizeCenter;
        return this;
    }

    public WheelViewTimeUtils setTextSizeOuter(int textSizeOuter) {
        this.textSizeOuter = textSizeOuter;
        return this;
    }

    public WheelViewTimeUtils setCancelTextSize(int cancelTextSize) {
        this.cancelTextSize = cancelTextSize;
        return this;
    }

    public WheelViewTimeUtils setConfirmTextSize(int confirmTextSize) {
        this.confirmTextSize = confirmTextSize;
        return this;
    }

    public WheelViewTimeUtils setTitleTextSize(int titleTextSize) {
        this.titleTextSize = titleTextSize;
        return this;
    }

    public WheelViewTimeUtils setTextColorOuter(int textColorOuter) {
        this.textColorOuter = textColorOuter;
        return this;
    }

    public WheelViewTimeUtils setTextColorCenter(int textColorCenter) {
        this.textColorCenter = textColorCenter;
        return this;
    }

    public WheelViewTimeUtils setTitleLineColor(int titleLineColor) {
        this.titleLineColor = titleLineColor;
        return this;
    }

    public WheelViewTimeUtils setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }


    public WheelViewTimeUtils setTimeData(final int type, String years, String months, String days) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        if (TextUtils.isEmpty(years)) {
            years = year + "";
        }
        int month = cal.get(Calendar.MONTH) + 1;
        if (TextUtils.isEmpty(months)) {
            months = month + "";
        }
        int day = cal.get(Calendar.DATE) + 1;
        if (TextUtils.isEmpty(days)) {
            days = day + "";
        }
        outerView1 = LayoutInflater.from(mContext).inflate(R.layout.utils_dialog_date_wheelview, null);
        //年滚轮
        final WheelView wv1 = (WheelView) outerView1.findViewById(R.id.wv1);
        //月滚轮
        final WheelView wv2 = (WheelView) outerView1.findViewById(R.id.wv2);
        //日滚轮
        final WheelView wv3 = (WheelView) outerView1.findViewById(R.id.wv3);
        wv1.setTextColorAndTextSize(itemsVisible, textSizeCenter, textSizeOuter, mContext.getResources().getColor(textColorOuter), mContext.getResources().getColor(textColorCenter), mContext.getResources().getColor(lineColor));
        wv2.setTextColorAndTextSize(itemsVisible, textSizeCenter, textSizeOuter, mContext.getResources().getColor(textColorOuter), mContext.getResources().getColor(textColorCenter), mContext.getResources().getColor(lineColor));
        wv3.setTextColorAndTextSize(itemsVisible, textSizeCenter, textSizeOuter, mContext.getResources().getColor(textColorOuter), mContext.getResources().getColor(textColorCenter), mContext.getResources().getColor(lineColor));
        RelativeLayout rlBg = outerView1.findViewById(R.id.topbar);
        if (titleBg != 0) {
            rlBg.setBackgroundResource(titleBg);
        }
        TextView tvTitle = outerView1.findViewById(R.id.tv_title);
        View vLine = outerView1.findViewById(R.id.v_line);
        if (titleLineVisible) {
            vLine.setVisibility(View.VISIBLE);
        } else {
            vLine.setVisibility(View.GONE);
        }
        LinearLayout llBg = outerView1.findViewById(R.id.ll_bg);
        vLine.setBackgroundResource(titleLineColor);
        llBg.setBackgroundResource(backgroundColor);
        TextView tv_ok = (TextView) outerView1.findViewById(R.id.tv_ok);
        TextView tv_cancel = (TextView) outerView1.findViewById(R.id.tv_cancel);
        tvTitle.setText(title);
        tv_cancel.setText(canceltitle);
        tv_ok.setText(confirmtitle);
        tv_cancel.setTextSize(cancelTextSize);
        tvTitle.setTextSize(titleTextSize);
        tv_ok.setTextSize(confirmTextSize);
        tvTitle.setTextColor(mContext.getResources().getColor(titleColor));
        tv_ok.setTextColor(mContext.getResources().getColor(confirmColor));
        tv_cancel.setTextColor(mContext.getResources().getColor(cancelColor));
        Calendar ca = Calendar.getInstance();
        ca.setTime(new Date());
        int newYear = ca.get(Calendar.YEAR);
        List<String> yearsList;
        if (newYear > Integer.parseInt(years)) {
            yearsList = Common.yearList(Integer.parseInt(years), 30);
        } else {
            yearsList = Common.yearList(30);
        }
        monthIndex = Integer.parseInt(months) - 1;
        wv1.setItems(yearsList, Common.getYear(30, Integer.parseInt(years)));
        wv2.setItems(Common.monthList(), monthIndex);
        wv3.setItems(Common.dayList(years, months), Integer.parseInt(days) - 1);
        wv1.setOnItemSelectedListener(new WheelView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int selectedIndex, String item) {
                String yearItem = wv1.getSelectedItem();
                String monthItem = wv2.getSelectedItem();
                yearItem = yearItem.substring(0, yearItem.length() - 1);
                monthItem = monthItem.substring(0, monthItem.length() - 1);
                wv2.setItems(Common.monthList(), monthIndex);
                if (monthItem.equals("02")) {
                    if (Integer.parseInt(yearItem) % 4 == 0 && Integer.parseInt(yearItem) % 100 != 0 || Integer.parseInt(yearItem) % 400 == 0) {
                        wv3.setItems(Common.dayList(yearItem, monthItem), 0);
                    }
                }
            }
        });
        wv2.setOnItemSelectedListener(new WheelView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(int index, String item) {
                String yearItem = wv1.getSelectedItem();
                String monthItem = wv2.getSelectedItem();
                yearItem = yearItem.substring(0, yearItem.length() - 1);
                monthItem = monthItem.substring(0, monthItem.length() - 1);
                monthIndex = index;
                if (monthItem.equals("2")) {
                    if (Integer.parseInt(yearItem) % 4 == 0 && Integer.parseInt(yearItem) % 100 != 0 || Integer.parseInt(yearItem) % 400 == 0) {
                        wv3.setItems(Common.dayList(yearItem, monthItem), 0);
                    }
                } else {
                    wv3.setItems(Common.dayList(yearItem, monthItem), 0);
                }

            }
        });


        //点击确定
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                bottomDialog.dismiss();
                String mSelectDate = wv1.getSelectedItem();
                String mSelectHour = wv2.getSelectedItem();
                String mSelectMin = wv3.getSelectedItem();
                mSelectDate = mSelectDate.substring(0, mSelectDate.length() - 1);
                mSelectHour = mSelectHour.substring(0, mSelectHour.length() - 1);
                mSelectMin = mSelectMin.substring(0, mSelectMin.length() - 1);
                String pickUpDate = mSelectDate + "-" + mSelectHour + "-" + mSelectMin;

                if (onSubmitListener != null) {
                    onSubmitListener.onSubmit(type, pickUpDate);
                }
            }
        });
        //点击取消
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                bottomDialog.dismiss();
            }
        });
        //防止弹出两个窗口
        if (bottomDialog != null && bottomDialog.isShowing()) {
            return this;
        }

        return this;
    }

    public BottomDialog build() {
        if (outerView1 == null) {
            new Throwable("请最后调用后build()方法");
        }
        //防止弹出两个窗口
        if (bottomDialog != null && bottomDialog.isShowing()) {
            return bottomDialog;
        }
        bottomDialog = new BottomDialog(mContext, R.style.ActionSheetDialogStyle);
        //将布局设置给Dialog
        bottomDialog.setContentView(outerView1);
        return bottomDialog;

    }

    public void show() {
        bottomDialog.show();//显示对话框
    }


    public interface OnSubmitDateListener {
        /**
         * @param type    类型
         * @param content 选中的内容
         */
        void onSubmit(int type, String content);
    }


}
