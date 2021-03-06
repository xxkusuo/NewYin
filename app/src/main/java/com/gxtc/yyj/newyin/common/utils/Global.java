package com.gxtc.yyj.newyin.common.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.lang.reflect.Field;

/**
 * 全局公共类, 封装：屏幕宽高获取，单位转换，主线程运行等
 *
 * @author Jam
 */
public class Global {

    public static Context mContext;

    public static float mDensity;
    public static float mScreenWidth;
    public static float mScreenHeight;

    public static void init(Context context) {
        mContext = context;
        initScreenSize();
    }

    private static void initScreenSize() {
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        mDensity = dm.density;
        mScreenHeight = dm.heightPixels;
        mScreenWidth = dm.widthPixels;
    }

    public static int dp2px(int dp) {
        return (int) (dp * mDensity);
    }

    public static int px2dp(int px) {
        return (int) (px / mDensity);
    }

    public static View inflate(int layoutResID, ViewGroup parent) {
        return LayoutInflater.from(mContext).inflate(layoutResID, parent, false);
    }

    public static View inflate(int layoutResID) {
        return inflate(layoutResID, null);
    }

    private static Handler mHandler = new Handler(Looper.getMainLooper());

    public static Handler getMainHandler() {
        return mHandler;
    }

    /**
     * 判断当前线程是否是主线程
     *
     * @return true表示当前是在主线程中运行
     */
    public static boolean isUIThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    public static void runOnUIThread(Runnable run) {
        if (isUIThread()) {
            run.run();
        } else {
            mHandler.post(run);
        }
    }

    private static Toast mToast;

    /**
     * 可以在子线程中调用
     *
     * @param msg toast内容
     */
    public static void showToast(final String msg) {
        runOnUIThread(new Runnable() {
            @Override
            public void run() {
                if (mToast == null) {
                    mToast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
                }
                mToast.setText(msg);
                mToast.show();
            }
        });
    }

    public static String getString(int stringId) {
        return mContext.getResources().getString(stringId);
    }

    public static int getColor(int colorId) {
        return mContext.getResources().getColor(colorId);
    }


    //=============沉侵式==(begin)=================
    private static View mStatusBarView;

    /**
     * 设置全屏沉侵式效果
     */
    public static void setNoStatusBarFullMode(Activity activity) {
        // sdk 4.4
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

            if (mStatusBarView != null) {
                ViewGroup root = (ViewGroup) activity.findViewById(android.R.id.content);
                root.removeView(mStatusBarView);
            }
            return;
        }

        // sdk 5.x
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.setStatusBarColor(Color.TRANSPARENT);
            return;
        }
    }

    /**
     * 设置控件的paddingTop, 使它不被StatusBar覆盖
     */
    public static void setStatusBarPadding(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // marginTop： 状态栏的高度
            int marginTop = getStatusBarHeight(view.getContext());
            view.setPadding(view.getPaddingLeft(), marginTop,
                    view.getPaddingRight(), view.getPaddingBottom());
            return;
        }
    }

    /**
     * 通过反射的方式获取状态栏高度，
     * 一般为24dp，有些可能较特殊，所以需要反射动态获取
     */
    private static int getStatusBarHeight(Context context) {
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object obj = clazz.newInstance();
            Field field = clazz.getField("status_bar_height");
            int id = Integer.parseInt(field.get(obj).toString());
            return context.getResources().getDimensionPixelSize(id);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-------无法获取到状态栏高度");
        }
        return dp2px(24);
    }

    public static void setStatusBarColor(Activity activity, int statusColor) {
        // sdk 4.4
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                ViewGroup root = (ViewGroup) activity.findViewById(android.R.id.content);
                if (mStatusBarView == null) {
                    mStatusBarView = new View(activity);
                    mStatusBarView.setBackgroundColor(statusColor);
                } else {
                    // 先解除父子控件关系，否则重复把一个控件多次
                    // 添加到其它父控件中会出错
                    ViewParent parent = mStatusBarView.getParent();
                    if (parent != null) {
                        ViewGroup viewGroup = (ViewGroup) parent;
                        if (viewGroup != null)
                            viewGroup.removeView(mStatusBarView);
                    }
                }
                ViewGroup.LayoutParams param = new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        getStatusBarHeight(activity));
                root.addView(mStatusBarView, param);
            }
            return;
        }

        // sdk 5.x
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(statusColor);
            return;
        }
    }
    //=============沉侵式==(end)=================

    /**
     * 跳转Activity
     *
     * @param context       上下文
     * @param clazz         目的类
     * @param finishPresent 是否结束当前Activity
     */
    public static void startIntent(Context context, Class clazz, boolean finishPresent) {
        startIntent(context, clazz, null, finishPresent, 0);
    }

    public static void startIntent(Context context, Class clazz) {
        startIntent(context, clazz, false);
    }

    public static void startIntentDelay(Context context, Class clazz, boolean finishPresent, long delayMillis) {
        startIntent(context, clazz, null, finishPresent, delayMillis);
    }

    /**
     * 带Bundle数据的跳转
     *
     * @param context       上下文
     * @param clazz         目的类
     * @param bundle        数据
     * @param finishPresent 是否结束当前activity
     */
    public static void startIntent(Context context, Class clazz, Bundle bundle, boolean finishPresent, long delayMillis) {
        Intent intent = new Intent();
        if (context instanceof Application) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        intent.setClass(context, clazz);
        startActivity(context, intent, bundle, delayMillis, finishPresent);
    }

    /**
     * 跳转activity
     *
     * @param context       Context上下文
     * @param intent        意图
     * @param bundle        数据
     * @param delayMillis   延时时间
     * @param finishPresent 是否结束当前activity
     */
    public static void startActivity(final Context context, final Intent intent, final Bundle bundle, long delayMillis, final boolean finishPresent) {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (bundle == null) {
                    context.startActivity(intent);
                } else {
                    context.startActivity(intent, bundle);
                }
                if (finishPresent) {
                    if (context instanceof Activity) ((Activity) context).finish();
                }
            }
        }, delayMillis);
    }

    public static void startIntent(Context context, Class clazz, Bundle bundle) {
        startIntent(context, clazz, bundle, false, 0);
    }

    public static void startIntentWithExtra(Context context, Class clazz, IntentExtra value) {
        Intent intent = new Intent();
        if (context instanceof Application) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        intent.setClass(context, clazz);
    }
}
