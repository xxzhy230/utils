package com.yqjr.utils.wheel;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.yqjr.utils.R;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class WheelViewUtils {
    private int itemsVisible = 7;
    private float textSizeCenter = 18;
    private float textSizeOuter = 13;
    private int textColorOuter = 0xffbbbbbb;
    private int textColorCenter = 0xff4d4d4d;
    private int lineColor = 0xffe6e6e6;

    private String title;
    private int titleColor;
    private int titleBarColor;
    private int cancelColor;
    private int confirmColor;


    private static int monthIndex;
    private static BottomDialog bottomDialog;
    private static OnSubmitDateListener onSubmitListener;
    private static OnSubmitOneListener onSubmitOneListener;
    private static WheelViewUtils wheelViewUtils;
    private Context mContext;

    private WheelViewUtils(Context mContext) {
        this.mContext = mContext;
    }

    public void setOnSubmitOneListener(OnSubmitOneListener onSubmitOneListener) {
        onSubmitOneListener = onSubmitOneListener;
    }

    public static OnSubmitDateListener getOnSubmitListener() {
        return onSubmitListener;
    }

    public void setOnSubmitListener(OnSubmitDateListener onSubmitListener) {
        onSubmitListener = onSubmitListener;
    }

    /**
     * 设置wheelView 样式
     *
     * @param itemsVisible    显示条目数量
     * @param textSizeCenter  中间字体大小
     * @param textSizeOuter   非中间字体大小
     * @param textColorOuter  非中间字体颜色
     * @param textColorCenter 中间字体颜色
     * @param lineColor       分割线颜色
     */
    public WheelViewUtils setTextColorAndTextSize(int itemsVisible, float textSizeCenter, float textSizeOuter,
                                                  int textColorOuter, int textColorCenter, int lineColor) {
        this.itemsVisible = itemsVisible;
        this.textSizeCenter = textSizeCenter;
        this.textSizeOuter = textSizeOuter;
        this.textColorOuter = textColorOuter;
        this.textColorCenter = textColorCenter;
        this.lineColor = lineColor;
        return wheelViewUtils;
    }

    /**
     * 设置状态栏颜色
     *
     * @param title
     * @param titleColor
     * @param titleBarColor
     * @param cancelColor
     * @param confirmColor
     * @return
     */
    public WheelViewUtils setTitleBar(String title, int titleColor, int titleBarColor, int cancelColor, int confirmColor) {
        this.title = title;
        this.titleColor = titleColor;
        this.titleBarColor = titleBarColor;
        this.cancelColor = cancelColor;
        this.confirmColor = confirmColor;
        return wheelViewUtils;
    }

    public static WheelViewUtils init(Context mContext) {
        if (wheelViewUtils == null) {
            wheelViewUtils = new WheelViewUtils(mContext);
        }
        return wheelViewUtils;
    }

    /**
     * 单列选择框
     *
     * @param type 类型
     */
    public void showWheelOneDialog(List<String> mList, final int type, final OnSubmitOneListener onSubmitOneListener) {
        View outerView1 = LayoutInflater.from(mContext).inflate(R.layout.utils_dialog_one_wheelview, null);
        RelativeLayout rlBg = outerView1.findViewById(R.id.topbar);
        if (titleBarColor != 0) {
            rlBg.setBackgroundColor(titleBarColor);
        }
        final WheelView wv1 = (WheelView) outerView1.findViewById(R.id.wv1);
        wv1.setTextColorAndTextSize(itemsVisible, textSizeCenter, textSizeOuter, textColorCenter, textColorOuter, lineColor);
        wv1.setItems(mList, 0);
        TextView tv_ok = (TextView) outerView1.findViewById(R.id.tv_ok);
        TextView tv_cancel = (TextView) outerView1.findViewById(R.id.tv_cancel);
        TextView tv_title = (TextView) outerView1.findViewById(R.id.tv_title);
        if (TextUtils.isEmpty(title)){
            tv_title.setText("");
        }else{
            tv_title.setText(title);
        }
        if (titleColor != 0) {
            tv_title.setTextColor(mContext.getResources().getColor(titleColor));
        }
        if (confirmColor != 0) {
            tv_ok.setTextColor(mContext.getResources().getColor(confirmColor));
        }
        if (cancelColor != 0) {
            tv_cancel.setTextColor(mContext.getResources().getColor(cancelColor));
        }
        //点击确定
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                bottomDialog.dismiss();
                String nTimeLimit = wv1.getSelectedItem();
                int selectedPosition = wv1.getSelectedPosition();
                if (onSubmitOneListener != null) {
                    onSubmitOneListener.onSubmit(type, nTimeLimit, selectedPosition);
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
            return;
        }
        bottomDialog = new BottomDialog(mContext, R.style.ActionSheetDialogStyle);
        //将布局设置给Dialog
        bottomDialog.setContentView(outerView1);
        bottomDialog.show();//显示对话框
    }


    /**
     * 适用于选择年月日
     *
     * @param type  类型
     * @param title 标题
     */
    public void showWheelDateDialog(final int type, String title, String years, String months, String days, final OnSubmitDateListener onSubmitListener) {
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
        View outerView1 = LayoutInflater.from(mContext).inflate(R.layout.utils_dialog_date_wheelview, null);
        //年滚轮
        final WheelView wv1 = (WheelView) outerView1.findViewById(R.id.wv1);
        //月滚轮
        final WheelView wv2 = (WheelView) outerView1.findViewById(R.id.wv2);
        //日滚轮
        final WheelView wv3 = (WheelView) outerView1.findViewById(R.id.wv3);
        TextView tvTitle = outerView1.findViewById(R.id.tv_title);
        if (type == 1) {
            tvTitle.setText(title);
        } else if (type == 2) {
            tvTitle.setText(title);
        } else if (type == 3) {
            tvTitle.setText(title);
        } else if (type == 4) {
            tvTitle.setText(title);
        }
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
//                wv3.setItems(Common.dayList(yearItem, monthItem), 0);
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

        TextView tv_ok = (TextView) outerView1.findViewById(R.id.tv_ok);
        TextView tv_cancel = (TextView) outerView1.findViewById(R.id.tv_cancel);
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
            return;
        }

        bottomDialog = new BottomDialog(mContext, R.style.ActionSheetDialogStyle);
        //将布局设置给Dialog
        bottomDialog.setContentView(outerView1);
        bottomDialog.show();//显示对话框
    }

    /**
     * 待完善    此方法无用
     * 两个集合不联动
     *
     * @param mContext
     * @param type     类型
     * @param title    标题
     */
    public void showWheelTwoDialog(Context mContext, List<String> leftList, final List<String> rightList, final int type, String title, final OnSubmitDateListener onSubmitListener) {

        View outerView1 = LayoutInflater.from(mContext).inflate(R.layout.utils_dialog_date_wheelview, null);
        final WheelView wv1 = (WheelView) outerView1.findViewById(R.id.wv1);
        final WheelView wv2 = (WheelView) outerView1.findViewById(R.id.wv2);
        TextView tvTitle = outerView1.findViewById(R.id.tv_title);
        if (type == 1) {
            tvTitle.setText(title);
        } else if (type == 2) {
            tvTitle.setText(title);
        } else if (type == 3) {
            tvTitle.setText(title);
        } else if (type == 4) {
            tvTitle.setText(title);
        }
        Calendar ca = Calendar.getInstance();
        ca.setTime(new Date());

        wv1.setItems(leftList, 0);
        wv2.setItems(rightList, 0);


        TextView tv_ok = (TextView) outerView1.findViewById(R.id.tv_ok);
        TextView tv_cancel = (TextView) outerView1.findViewById(R.id.tv_cancel);
        //点击确定
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                bottomDialog.dismiss();
                String mSelectDate = wv1.getSelectedItem();
                String mSelectHour = wv2.getSelectedItem();
                mSelectDate = mSelectDate.substring(0, mSelectDate.length() - 1);
                mSelectHour = mSelectHour.substring(0, mSelectHour.length() - 1);
                String pickUpDate = mSelectDate + "-" + mSelectHour;

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
            return;
        }

        bottomDialog = new BottomDialog(mContext, R.style.ActionSheetDialogStyle);
        //将布局设置给Dialog
        bottomDialog.setContentView(outerView1);
        bottomDialog.show();//显示对话框
    }


    public interface OnSubmitDateListener {
        /**
         * @param type    类型
         * @param content 选中的内容
         */
        void onSubmit(int type, String content);
    }

    public interface OnSubmitOneListener {
        /**
         * @param type    类型
         * @param content 选中的内容
         */
        void onSubmit(int type, String content, int position);
    }


}
