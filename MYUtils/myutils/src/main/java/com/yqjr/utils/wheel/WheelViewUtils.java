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

public class WheelViewUtils {
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
    private static BottomDialog bottomDialog;
    private static OnSubmitListener onSubmitListener;
    private static WheelViewUtils wheelViewUtils;
    private Context mContext;
    private View outerView1;

    private WheelViewUtils(Context mContext) {
        this.mContext = mContext;
    }

    public WheelViewUtils setOnSubmitListener(OnSubmitListener onSubmitListener) {
        this.onSubmitListener = onSubmitListener;
        return this;
    }

    public static WheelViewUtils init(Context mContext) {
        if (wheelViewUtils == null) {
            wheelViewUtils = new WheelViewUtils(mContext);
        }
        return wheelViewUtils;
    }

    public WheelViewUtils setTitle(String title) {
        this.title = title;
        return this;
    }

    public WheelViewUtils setCancelTitle(String canceltitle) {
        this.canceltitle = canceltitle;
        return this;
    }

    public WheelViewUtils setTitleBg(int titleBg) {
        this.titleBg = titleBg;
        return this;
    }

    public WheelViewUtils setConfirmTitle(String confirmtitle) {
        this.confirmtitle = confirmtitle;
        return this;
    }

    public WheelViewUtils setTitleColor(int titleColor) {
        this.titleColor = titleColor;
        return this;
    }

    public WheelViewUtils setCancelColor(int cancelColor) {
        this.cancelColor = cancelColor;
        return this;
    }

    public WheelViewUtils setConfirmColor(int confirmColor) {
        this.confirmColor = confirmColor;
        return this;
    }

    public WheelViewUtils setLineColor(int lineColor) {
        this.lineColor = lineColor;
        return this;
    }

    public WheelViewUtils setItemVisible(int itemsVisible) {
        this.itemsVisible = itemsVisible;
        return this;
    }

    public WheelViewUtils setTextSizeCenter(int textSizeCenter) {
        this.textSizeCenter = textSizeCenter;
        return this;
    }

    public WheelViewUtils setTextSizeOuter(int textSizeOuter) {
        this.textSizeOuter = textSizeOuter;
        return this;
    }

    public WheelViewUtils setCancelTextSize(int cancelTextSize) {
        this.cancelTextSize = cancelTextSize;
        return this;
    }

    public WheelViewUtils setConfirmTextSize(int confirmTextSize) {
        this.confirmTextSize = confirmTextSize;
        return this;
    }

    public WheelViewUtils setTitleTextSize(int titleTextSize) {
        this.titleTextSize = titleTextSize;
        return this;
    }

    public WheelViewUtils setTextColorOuter(int textColorOuter) {
        this.textColorOuter = textColorOuter;
        return this;
    }

    public WheelViewUtils setTextColorCenter(int textColorCenter) {
        this.textColorCenter = textColorCenter;
        return this;
    }

    public WheelViewUtils setTitleLineColor(int titleLineColor) {
        this.titleLineColor = titleLineColor;
        return this;
    }

    public WheelViewUtils setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public WheelViewUtils setData(List<String> mList, final int type) {
        outerView1 = LayoutInflater.from(mContext).inflate(R.layout.utils_dialog_one_wheelview, null);
        RelativeLayout rlBg = outerView1.findViewById(R.id.topbar);
        if (titleBg != 0) {
            rlBg.setBackgroundResource(titleBg);
        }
        View vLine = outerView1.findViewById(R.id.v_line);
        if (titleLineVisible) {
            vLine.setVisibility(View.VISIBLE);
        } else {
            vLine.setVisibility(View.GONE);
        }
        LinearLayout llBg = outerView1.findViewById(R.id.ll_bg);
        vLine.setBackgroundResource(titleLineColor);
        llBg.setBackgroundResource(backgroundColor);
        final WheelView wv1 = (WheelView) outerView1.findViewById(R.id.wv1);

        wv1.setTextColorAndTextSize(itemsVisible, textSizeCenter, textSizeOuter, mContext.getResources().getColor(textColorOuter), mContext.getResources().getColor(textColorCenter), mContext.getResources().getColor(lineColor));
        wv1.setItems(mList, 0);
        TextView tv_ok = (TextView) outerView1.findViewById(R.id.tv_ok);
        TextView tv_cancel = (TextView) outerView1.findViewById(R.id.tv_cancel);
        TextView tv_title = (TextView) outerView1.findViewById(R.id.tv_title);
        tv_title.setText(title);
        tv_cancel.setText(canceltitle);
        tv_ok.setText(confirmtitle);
        tv_cancel.setTextSize(cancelTextSize);
        tv_title.setTextSize(titleTextSize);
        tv_ok.setTextSize(confirmTextSize);
        tv_title.setTextColor(mContext.getResources().getColor(titleColor));
        tv_ok.setTextColor(mContext.getResources().getColor(confirmColor));
        tv_cancel.setTextColor(mContext.getResources().getColor(cancelColor));
        //点击确定
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                bottomDialog.dismiss();
                String nTimeLimit = wv1.getSelectedItem();
                int selectedPosition = wv1.getSelectedPosition();
                if (onSubmitListener != null) {
                    onSubmitListener.onSubmit(type, nTimeLimit, selectedPosition);
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

    public interface OnSubmitListener {
        /**
         * @param type    类型
         * @param content 选中的内容
         */
        void onSubmit(int type, String content, int position);
    }


}
