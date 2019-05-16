package com.yqjr.utils.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * @Description: 自定义Toast
 */
public class ToastUtils {
    /**
     * 短时间显示
     *
     * @param cxt
     * @param content
     * @author lvgg
     * @date 2014-5-16
     */
    public static void toastShort(Context cxt, String content) {
        Toast.makeText(cxt, content, Toast.LENGTH_SHORT).show();
    }

    /**
     * @param cxt
     * @param resource 资源
     */
    public static void toastShort(Context cxt, int resource) {
        Toast.makeText(cxt, resource, Toast.LENGTH_SHORT).show();
    }

    /**
     * 位于屏幕顶端
     *
     * @param cxt
     * @param content
     */
    public static void toastTop(Context cxt, String content) {
        Toast toast = Toast.makeText(cxt, content, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.show();
    }

    /**
     * 位于屏幕顶端
     *
     * @param cxt
     * @param content
     */
    public static void toastTop(Context cxt, int content) {
        Toast toast = Toast.makeText(cxt, content, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.show();
    }

    /**
     * 长时间显示
     *
     * @param cxt
     * @param content
     * @author lvgg
     * @date 2014-5-16
     */
    public static void toastLong(Context cxt, String content) {
        Toast.makeText(cxt, content, Toast.LENGTH_LONG).show();
    }

    /**
     * 利用string 中的资源文件
     *
     * @param cxt
     * @param resource
     * @author lvgg
     * @date 2014-5-16
     */
    public static void toastLong(Context cxt, int resource) {
        Toast.makeText(cxt, resource, Toast.LENGTH_LONG).show();
    }

    /**
     * 位于屏幕中央
     *
     * @param cxt
     * @param content
     * @author lvgg
     * @date 2014-5-16
     */
    public static void toastCenter(Context cxt, String content) {
        Toast toast = Toast.makeText(cxt, content, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void toastCenter(Context cxt, int content) {
        Toast toast = Toast.makeText(cxt, content, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
