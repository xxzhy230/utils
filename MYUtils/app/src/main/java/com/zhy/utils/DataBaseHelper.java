package com.zhy.utils;

import android.content.Context;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import net.sqlcipher.database.SQLiteDatabase;

public class DataBaseHelper extends OrmLiteSqliteOpenHelper {
    /**
     * 数据库名称
     **/
    private static final String DATABASE_NAME = "appsdk.db";

    /**
     * 数据库版本
     **/
    private static final int DATABASE_VERSION = 1;

    public DataBaseHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION, getKey());
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, HouseInfo.class);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {

    }



    private static String getKey() {
        return "test";
    }
}
