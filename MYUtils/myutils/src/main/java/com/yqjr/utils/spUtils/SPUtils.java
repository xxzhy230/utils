package com.yqjr.utils.spUtils;

import android.content.Context;
import android.content.SharedPreferences;

import com.yqjr.utils.Utils;

/**
 * 本地文件存储工具类
 */
public class SPUtils {
    /**
     * 默认本地文件名称
     */
    public static String spName = "locationName";
    /**
     * 账号
     */
    private static final String KEY_USER_ACCOUNT = "account";
    /**
     * token
     */
    private static final String KEY_USER_TOKEN = "token";
    /**
     * 用户ID
     */
    private static final String KEY_USER_ID = "userId";

    /**
     * 获取默认文件中的存储
     *
     * @return
     */
    public static SharedPreferences getInstance() {
        return getSharedPreferences(spName);
    }

    /**
     * 根据存储文件名称获取存储字段
     *
     * @param spName
     * @return
     */
    public static SharedPreferences getInstance(String spName) {
        return getSharedPreferences(spName);
    }

    private static SharedPreferences getSharedPreferences(String spName) {
        return Utils.mContext.getSharedPreferences(spName, Context.MODE_PRIVATE);
    }

    /**
     * 根据字段获取存储的值
     *
     * @param key
     * @return
     */
    public static String getString(String key) {
        return getInstance().getString(key, null);
    }

    /**
     * 指定文件名称和字段查询
     *
     * @param key
     * @param spName 文件名称
     * @return
     */
    public static String getSpNameString(String spName, String key) {
        return getInstance(spName).getString(key, null);
    }


    /**
     * 根据字段获取存储的值
     *
     * @param key
     * @return
     */
    public static int getInt(String key) {
        return getInstance().getInt(key, -1);
    }

    /**
     * 指定文件名称和字段查询
     *
     * @param key
     * @param spName 文件名称
     * @return
     */
    public static int getSpNameInt(String spName, String key) {
        return getInstance(spName).getInt(key, -1);
    }

    /**
     * 根据字段获取存储的值
     *
     * @param key
     * @return
     */
    public static float getFloat(String key) {
        return getInstance().getFloat(key, -1);
    }

    /**
     * 指定文件名称和字段查询
     *
     * @param key
     * @param spName 文件名称
     * @return
     */
    public static float getSpNameFloat(String spName, String key) {
        return getInstance(spName).getFloat(key, -1);
    }

    /**
     * 根据字段获取存储的值
     *
     * @param key
     * @return
     */
    public static boolean getBoolean(String key) {
        return getInstance().getBoolean(key, false);
    }

    /**
     * 指定文件名称和字段查询
     *
     * @param key
     * @param spName 文件名称
     * @return
     */
    public static boolean getSpNameBoolean(String spName, String key) {
        return getInstance(spName).getBoolean(key, false);
    }

    /**
     * 根据字段获取存储的值
     *
     * @param key
     * @return
     */
    public static long getLong(String key) {
        return getInstance().getLong(key, -1);
    }

    /**
     * 指定文件名称和字段查询
     *
     * @param key
     * @param spName 文件名称
     * @return
     */
    public static long getSpNameLong(String spName, String key) {
        return getInstance(spName).getLong(key, -1);
    }

    public static String getToken() {
        String token = getString(KEY_USER_TOKEN);
        return token;
    }
    public static void putToken(String token) {
        putString(KEY_USER_TOKEN,token);
    }
    public static String getUserId() {
        String userId = getString(KEY_USER_ID);
        return userId;
    }
    public static void putUserId(String userId) {
        putString(KEY_USER_ID,userId);
    }

    public static void putString(String key, String value) {
        SharedPreferences.Editor editor = getInstance().edit();
        editor.putString(key, value);
        editor.commit();
    }
    public static void putSpNameString(String spName,String key, String value) {
        SharedPreferences.Editor editor = getInstance(spName).edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void putInt(String key, int value) {
        SharedPreferences.Editor editor = getInstance().edit();
        editor.putInt(key, value);
        editor.commit();
    }
    public static void putSpNameInt(String spName,String key, int value) {
        SharedPreferences.Editor editor = getInstance(spName).edit();
        editor.putInt(key, value);
        editor.commit();
    }


    public static void putLong(String key, long value) {
        SharedPreferences.Editor editor = getInstance().edit();
        editor.putLong(key, value);
        editor.commit();
    }
    public static void putSpNameLong(String spName,String key, long value) {
        SharedPreferences.Editor editor = getInstance(spName).edit();
        editor.putLong(key, value);
        editor.commit();
    }
    public static void putFloat(String key, float value) {
        SharedPreferences.Editor editor = getInstance().edit();
        editor.putFloat(key, value);
        editor.commit();
    }
    public static void putSpNameFloat(String spName,String key, float value) {
        SharedPreferences.Editor editor = getInstance(spName).edit();
        editor.putFloat(key, value);
        editor.commit();
    }
    public static void putBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = getInstance().edit();
        editor.putBoolean(key, value);
        editor.commit();
    }
    public static void putSpNameBoolean(String spName,String key, boolean value) {
        SharedPreferences.Editor editor = getInstance(spName).edit();
        editor.putBoolean(key, value);
        editor.commit();
    }
}
