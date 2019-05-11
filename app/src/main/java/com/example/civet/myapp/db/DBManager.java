package com.example.civet.myapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.civet.myapp.Util.L;
import com.example.civet.myapp.bean.Consumption;

import java.util.ArrayList;
import java.util.List;

public class DBManager {

    private SQLiteDatabase database;

    public DBManager(Context context) {
        database = (new SQLiteDbHelper(context)).getWritableDatabase();
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }

    public  List<Consumption> getConsumptionList() {
        return queryForList(new DBManager.RowMapper<Consumption>() {
            @Override
            public Consumption mapRow(Cursor cursor, int index) {
                Consumption consumption = new Consumption();
                consumption.setTag(cursor.getString(cursor.getColumnIndex("tag")));
                consumption.setTime(cursor.getLong(cursor.getColumnIndex("time")));
                consumption.setClassification(cursor.getString(cursor.getColumnIndex("classification")));
                consumption.setMoney(cursor.getFloat(cursor.getColumnIndex("money")));
                return consumption;
            }
        }, "SELECT * FROM consumptions;", null);
    }

    public boolean insertConsumption(Consumption consumption) {
        ContentValues values = new ContentValues();
        values.put("time", consumption.getTime());
        values.put("money", consumption.getMoney());
        values.put("tag", consumption.getTag());
        values.put("classification", consumption.getClassification());
        if (database == null) {
            return false;
        } else {
            return database.insert("consumptions", null, values) != -1;
        }
    }

    /**
     * @return consumption数量
     */
    public int getConsumptionSize() {
        int size = 0;
        Cursor cursor;
        cursor = database.rawQuery("SELECT count(*) as count FROM consumptions", null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                size = cursor.getInt(cursor.getColumnIndex("count"));
            }
            cursor.close();
        }
        return size;
    }

    public interface RowMapper<T> {
        /**
         * @param cursor 游标
         * @param index  下标索引
         * @return
         */
        T mapRow(Cursor cursor, int index);
    }

    private <T> List<T> queryForList(RowMapper<T> rowMapper, String sql, String[] selectionArgs) {
        Cursor cursor = null;
        List<T> list = new ArrayList<T>();
        try {
            cursor = database.rawQuery(sql, selectionArgs);
            while (cursor.moveToNext()) {
                list.add(rowMapper.mapRow(cursor, cursor.getPosition()));
            }
        } catch (Exception e) {
            L.e("SQL", e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return list;
    }
}
