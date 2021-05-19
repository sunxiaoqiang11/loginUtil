package com.example.logutil;

import android.util.Log;

public class logDebug {
    public static final String TAG = "这是我的电脑呀";

    public static void d(String message) {
        Log.d(TAG, message);
    }
}
