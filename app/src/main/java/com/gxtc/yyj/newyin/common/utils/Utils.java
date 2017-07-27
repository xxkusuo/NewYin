package com.gxtc.yyj.newyin.common.utils;

import android.annotation.TargetApi;
import android.os.Build;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 工具类，封装常用的一些方法
 *
 * @author Jam
 */
public class Utils {
    private static final String TAG = "Utils";

    /**
     * 查找一个布局里的所有的按钮并设置点击事件
     *
     * @param rootView
     * @param listener
     */
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    public static void findButtonAndSetOnClickListener(View rootView,
                                                       OnClickListener listener) {
        if (rootView instanceof ViewGroup) {
            ViewGroup parent = (ViewGroup) rootView;
            for (int i = 0; i < parent.getChildCount(); i++) {
                View child = parent.getChildAt(i);
                // 如果是按钮设置点击事件
                if (child instanceof Button || child instanceof ImageButton) {
                    // 没有设置过监听器才设置
                    if (!child.hasOnClickListeners())
                        child.setOnClickListener(listener); // 设置点击事件
                }
                if (child instanceof ViewGroup) {
                    findButtonAndSetOnClickListener(child, listener);
                }
            }
        }
    }

    /**
     * 格式化日期显示
     */
    public static String formatDate(long date) {
        if (DateUtils.isToday(date)) {
            return DateFormat.format("今天 kk:mm", date).toString();
        } else {
            return DateFormat.format("yyyy/MM/dd kk:mm", date).toString();
        }
    }

    /**
     * 格式化金额显示
     */
    public static String formatAmount(double amount) {
        NumberFormat format = NumberFormat.getCurrencyInstance();
        format.setMaximumFractionDigits(1);     // 金额只保存一位有效数字
        return format.format(amount);
    }


    /**
     * 匹配ip地址的正则表达式
     */
    private static final String IP_REGEXP =
            "(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9" +
                    "])\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\" +
                    ".(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\." +
                    "(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])";

    /**
     * 替换一个url中的ip地址 <br/>
     * 例如：<br/>
     * url为： http://127.0.0.1:8080/jinquan/1.jpg <br/>
     * 调用 replaceIp(url, "192.168.1.1")后，<br/>
     * 则得到新的url为： http://192.168.1.1:8080/jinquan/1.jpg
     */
    public static String replaceIp(String url, String ip) {
        // 匹配ip的正则表达式
        return url.replaceAll(IP_REGEXP, ip);
    }


    /**
     * 每六位描述一个字节
     * @author zhouzhian
     */

    /**
     * 字符串编码成Unicode编码
     */
    public static String encode(String src) throws Exception {
        char c;
        StringBuilder str = new StringBuilder();
        int intAsc;
        String strHex;
        for (int i = 0; i < src.length(); i++) {
            c = src.charAt(i);
            intAsc = (int) c;
            strHex = Integer.toHexString(intAsc);
            if (intAsc > 128)
                str.append("\\u" + strHex);
            else
                str.append("\\u00" + strHex); // 低位在前面补00
        }
        return str.toString();
    }

    /**
     * Unicode解码成字符串
     *
     * @param src
     * @return
     */
    public static String decode(String src) {
        int t = src.length() / 6;
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < t; i++) {
            String s = src.substring(i * 6, (i + 1) * 6); // 每6位描述一个字节
            // 高位需要补上00再转
            String s1 = s.substring(2, 4) + "00";
            // 低位直接转
            String s2 = s.substring(4);
            // 将16进制的string转为int
            int n = Integer.valueOf(s1, 16) + Integer.valueOf(s2, 16);
            // 将int转换为字符
            char[] chars = Character.toChars(n);
            str.append(new String(chars));
        }
        return str.toString();
    }

    /*-----新浪微博工具-----*/

    /**
     * 格式化来源source
     */

    public static String formatSource(String sourceStr) {
        if (!TextUtils.isEmpty(sourceStr)) {
            String source = sourceStr.substring(sourceStr.indexOf("<") + 1, sourceStr.lastIndexOf(">"));
            return source;
        }
        return null;
    }

    /**
     * Thu Jul 27 14:38:03 +0800 2017 初始时间格式
     * 格式化时间显示
     *
     * @param timeStr 时间字符串
     * @return
     */
    public static String formatTime(String timeStr) {
        if (!TextUtils.isEmpty(timeStr)) {
            SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy");
            try {
                Date date = format.parse(timeStr.trim());
                long time = date.getTime();//得到传入的时间
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd HH:mm");
                String dayStr = dateFormat.format(date);
                String[] split = dayStr.split(" ");
                String partDay = split[0];
                String partTime = split[1];
                long currentTimeMillis = System.currentTimeMillis();//获取当前时间比对
                //判断是否处于同一天
                int hour = (int) ((currentTimeMillis - time) / 1000 / 60 / 60);
                if (hour < 24) {
                    //处于同一天 计算是否在1个小时之内
                    float l = (((currentTimeMillis - time) - time) * 1f / 1000 / 60 / 60);
                    if (l < 1) {
                        return (int) (l * 60 + 0.5f) + "分钟前";//在1小时内就直接显示多少分钟
                    } else {
                        return partDay;//否则显示时今天多少点发布的
                    }
                } else {
                    if (hour < 72) {
                        if (hour < 48) {
                                return "昨天 " + partTime;
                        } else {
                                return "前天 " + partTime;
                        }
                    } else {
                        return dayStr;
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            return "";
        }
        return "";
    }

}











