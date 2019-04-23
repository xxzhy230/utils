package com.yqjr.utils.utils;

import android.content.Context;
import android.text.TextUtils;

import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;

/**
 * Created by ZhangHongyu
 * 16/9/5 01:20
 * 描述
 */
public class StringUtils {
    public static boolean isNotEmpty(String str) {
        if (str == null) return false;
        if (str.length() == 0) return false;
        return true;
    }

    public static boolean isEmpty(String str) {
        if (str == null) return true;
        if (str.length() == 0) return true;
        return false;
    }

    public static String getHideName(String name) {
        if (isEmpty(name)) return "";
        String first = name.substring(0, 1);
        String xs = "";
        for (int i = 0; i < name.length() - 1; i++) {
            xs += "*";
        }
        return first + xs;
    }


    public static String getPhone(String pNumber) {
        if (!TextUtils.isEmpty(pNumber) && pNumber.length() > 6) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < pNumber.length(); i++) {
                char c = pNumber.charAt(i);
                if (i >= 3 && i <= 6) {
                    sb.append('*');
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        } else {
            return "";
        }
    }

    /**
     * dp转px
     *
     * @param context
     * @param dipValue
     * @return
     */
    public static int dp2px(Context context, int dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * double 保留两位小数
     *
     * @param dou
     * @return
     */
    public static String double2String(double dou) {
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        return decimalFormat.format(dou);
    }

    /**
     * 千分符
     *
     * @param text
     * @return
     */
    public static String fmtMicrometer(String text) {
        if (TextUtils.isEmpty(text)) {
            return "0.00";
        }
        DecimalFormat df = null;
        if (text.indexOf(".") > 0) {
            if (text.length() - text.indexOf(".") - 1 == 0) {
                df = new DecimalFormat("###,##0.00");
            } else if (text.length() - text.indexOf(".") - 1 == 1) {
                df = new DecimalFormat("###,##0.00");
            } else {
                df = new DecimalFormat("###,##0.00");
            }
        } else {
            df = new DecimalFormat("###,##0.00");
        }
        double number = 0.00;
        try {
            number = Double.parseDouble(text);
        } catch (Exception e) {
            number = 0.00;
        }
        return df.format(number);
    }

    /**
     * 手机号码中间隐藏
     * @param phone
     * @return
     */
    public static String phoneChange(String phone) {
        return phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    /**
     * 获取年
     * @return
     */
    public static int getYear(){
        Calendar cd = Calendar.getInstance();
        return  cd.get(Calendar.YEAR);
    }
    /**
     * 获取月
     * @return
     */
    public static int getMonth(){
        Calendar cd = Calendar.getInstance();
        return  cd.get(Calendar.MONTH)+1;
    }
    /**
     * 获取日
     * @return
     */
    public static int getDay(){
        Calendar cd = Calendar.getInstance();
        return  cd.get(Calendar.DATE);
    }
    /**
     * 获取时
     * @return
     */
    public static int getHour(){
        Calendar cd = Calendar.getInstance();
        return  cd.get(Calendar.HOUR);
    }
    /**
     * 获取分
     * @return
     */
    public static int getMinute() {
        Calendar cd = Calendar.getInstance();
        return cd.get(Calendar.MINUTE);
    }

    /**
     * @param plainText 明文
     * @return 32位密文
     */
    public static String md5(String plainText) {
        MessageDigest messageDigest = null;
        StringBuffer md5StrBuff = new StringBuffer();
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(plainText.getBytes("UTF-8"));
            byte[] byteArray = messageDigest.digest();
            for (int i = 0; i < byteArray.length; i++) {
                if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                    md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
                else
                    md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return md5StrBuff.toString().toUpperCase();//字母大写
    }




    private static String CHARSET = "UTF-8";
    /**
     * 得到时间戳
     *
     * @return
     */
    public static String getTimestamp() {

        return String.valueOf(System.currentTimeMillis());
    }

    /**
     * 字符串加签并转md5
     * @param map
     * @param key
     * @return
     */
    public static String getSign(Map<String, Object> map, String key) {
        ArrayList<String> list = new ArrayList<String>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() != "") {
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }
        int size = list.size();
        String[] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);//输出： [a, C, z]
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(arrayToSort[i]);
        }
        String result = sb.toString();
        result += "key=" + key;
        result = md5(result).toUpperCase();
        return result;
    }
}
