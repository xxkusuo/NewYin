package com.gxtc.yyj.newyin.common.utils;

import android.util.Log;

public class L {
    private static final boolean DEBUG = true;

    public static void e(String tag, String msg) {
        if (DEBUG) {
            Log.e(tag, msg);
        }
    }
}