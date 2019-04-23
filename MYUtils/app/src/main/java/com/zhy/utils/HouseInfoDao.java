package com.zhy.utils;


import android.content.ContentValues;
import android.content.Context;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.support.ConnectionSource;


import net.sqlcipher.database.SQLiteDatabase;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;


public class HouseInfoDao extends BaseDaoImpl<HouseInfo, String> {

    /**
     * 构造函数
     *
     * @param helper
     */
    public HouseInfoDao(DataBaseHelper helper) throws SQLException {
        super(helper.getConnectionSource(), HouseInfo.class);
    }

    /**
     * 构造函数
     *
     * @param connectionSource
     * @param dataClass
     */
    public HouseInfoDao(ConnectionSource connectionSource,
                        Class<HouseInfo> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

//
//    public List<HouseInfo> queryDevList() throws SQLException {
//        QueryBuilder<HouseInfo, String> queryBuilder = queryBuilder();
//        queryBuilder.where().eq("userId",BLApplcation.getmBLUserInfoUnits().getUserid())
//                .or().isNull("userId");
//        return query(queryBuilder.prepare());
//    }
//
//    public List<HouseInfo> queryDevList(String houseId) throws SQLException {
//        Where<HouseInfo, String> houseInfoStringWhere = queryBuilder().where()
//                .eq("houseId", houseId).and()
//                .eq("userId",BLApplcation.getmBLUserInfoUnits().getUserid());
//        return query(houseInfoStringWhere.prepare());
//    }
//
//
//
//
    public void insertData(final List<HouseInfo> list) throws SQLException {
        TransactionManager.callInTransaction(connectionSource, new Callable<Void>() {

            @Override
            public Void call() throws Exception {
                for (int i = 0; i < list.size(); i++) {
                    CreateOrUpdateStatus orUpdate = createOrUpdate(list.get(i));
                }
                return null;
            }
        });
    }

    public long insertRawData(Context mContext, final HouseInfo houseInfo) throws SQLException {
        CreateOrUpdateStatus orUpdate = createOrUpdate(houseInfo);
//        DataBaseHelper databaseHelper = new DataBaseHelper(mContext);
//        SQLiteDatabase db = databaseHelper.getWritableDatabase("test");
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("houseName", houseInfo.getHouseName());
//        contentValues.put("userId","1234567");
//        long zidingyitable = db.insert("houseTable", null, contentValues);
        return orUpdate.getNumLinesChanged();
    }
//
//
//    /**
//     * 删除设备
//     *
//     * @param deviceInfo 需要删除的设备信息
//     * @throws SQLException
//     */
//    public int deleteDevice(BLDeviceInfo deviceInfo) throws SQLException {
//        QueryBuilder<HouseInfo, String> queryBuilder = queryBuilder();
//        Where<HouseInfo, String> where = queryBuilder.where();
//        where.eq("pdid", deviceInfo.getDid());
//        List<HouseInfo> devList = query(queryBuilder.prepare());
//
//        for (HouseInfo subDevInfo : devList) {
//            deleteById(subDevInfo.getDid());
//        }
//
//        return deleteById(deviceInfo.getDid());
//    }
//
//    /**
//     * 删除设备
//     *
//     * @throws SQLException
//     */
//    public int deleteDevice(Context mContext, int id) throws SQLException {
//
//        DatabaseHelper databaseHelper = new DatabaseHelper(mContext);
//        SQLiteDatabase db = databaseHelper.getWritableDatabase("test");
//        int zidingyitable = db.delete("zidingyitable", "_id=?", new String[]{id + ""});
//        return zidingyitable;
//    }
//
//
//
//    public int updataHouseName(String houseName, int where) {
//        UpdateBuilder<HouseInfo, String> houseInfoStringUpdateBuilder = updateBuilder();
//        try {
//            houseInfoStringUpdateBuilder.updateColumnValue("houseName", houseName);
//            houseInfoStringUpdateBuilder.where().eq("id", where);
//            int update = houseInfoStringUpdateBuilder.update();
//            return update;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return -1;
//        }
//    }
//    public int updataHousestate(String state, String where) {
//        UpdateBuilder<HouseInfo, String> houseInfoStringUpdateBuilder = updateBuilder();
//        try {
//            houseInfoStringUpdateBuilder.updateColumnValue("state", state);
//            houseInfoStringUpdateBuilder.where().eq("id", where);
//            int update = houseInfoStringUpdateBuilder.update();
//            return update;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return -1;
//        }
//    }
//    public int updataHousestate(String where) {
//        UpdateBuilder<HouseInfo, String> houseInfoStringUpdateBuilder = updateBuilder();
//        try {
//            houseInfoStringUpdateBuilder.updateColumnValue("state", 0);
//            houseInfoStringUpdateBuilder.where().eq("did", where);
//            int update = houseInfoStringUpdateBuilder.update();
//            return update;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return -1;
//        }
//    }
}
