package com.example.civet.myapp.Util;

import com.example.civet.myapp.BuildConfig;

public class L {
    public static void d(String tag, String msg) {
        if (BuildConfig.IS_DEBUG) {
            android.util.Log.d(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (BuildConfig.IS_DEBUG) {
            android.util.Log.e(tag, msg);
        }
    }
}
