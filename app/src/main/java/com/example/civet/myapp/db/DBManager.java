package com.example.civet.myapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.civet.myapp.bean.Consumption;

import java.util.List;

public class DBManager {
    private static SQLiteDatabase database;

    public static SQLiteDatabase getDB(Context context) {
        if (database == null) {
            database = new SQLiteDbHelper(context).getWritableDatabase();
        }
        return database;
    }

    public static boolean insertConsumption(Consumption consumption, Context context) {
        SQLiteDatabase db = getDB(context);
        ContentValues values = new ContentValues();
        values.put("time", consumption.getTime());
        values.put("money", consumption.getMoney());
        values.put("tag", consumption.getTag());
        values.put("classification", consumption.getClassification());
        if (db == null) {
            return false;
        } else {
            return db.insert("consumptions", null, values) != -1;
        }
    }

    public static int getConsumptionSize(Context context) {
        SQLiteDatabase db = getDB(context);
        int size = 0;
        Cursor cursor;
        cursor = db.rawQuery("SELECT count(*) as count FROM consumptions", null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                size = cursor.getInt(cursor.getColumnIndex("count"));
            }
        }
        return size;
    }
}
