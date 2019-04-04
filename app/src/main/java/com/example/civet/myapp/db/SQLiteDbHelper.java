package com.example.civet.myapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteDbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "database.db";
    public static final int DB_VERSION = 1;
    public SQLiteDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /**
         * 创建消费、标签、分类三个表
         */
        db.execSQL("CREATE TABLE `classifications` ( " +
                "`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
                "`name` TEXT NOT NULL, " +
                "`times` INTEGER DEFAULT 0, " +
                "`priority` INTEGER DEFAULT 0 " +
                ")");
        db.execSQL("CREATE TABLE `tags` ( " +
                    "`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
                    "`name` TEXT NOT NULL, " +
                    "`classification` TEXT NOT NULL, " +
                    "`times` INTEGER DEFAULT 0, " +
                    "`priority` INTEGER DEFAULT 0, " +
                    "FOREIGN KEY(`classification`) REFERENCES `classifications`(`name`) " +
                ")");
        db.execSQL("CREATE TABLE `consumptions` ( " +
                "`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
                "`time` TEXT DEFAULT 0, " +
                "`money` REAL DEFAULT 0, " +
                "`tag` TEXT NOT NULL, " +
                "`classification` TEXT NOT NULL, " +
                "FOREIGN KEY(`tag`) REFERENCES `tags`(`name`), " +
                "FOREIGN KEY(`classification`) REFERENCES `classifications`(`name`) " +
                ")");
        /**
         * 给分类表写入初始值
         */
        db.execSQL("INSERT INTO classifications('name', 'times','priority') VALUES('吃喝', 0, 0)");
        db.execSQL("INSERT INTO classifications('name', 'times','priority') VALUES('交通', 0, 0)");
        db.execSQL("INSERT INTO classifications('name', 'times','priority') VALUES('娱乐', 0, 0)");
        /**
         * 给标签表写入初始值
         */
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
