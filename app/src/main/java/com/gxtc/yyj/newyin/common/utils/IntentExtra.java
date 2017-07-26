package com.gxtc.yyj.newyin.common.utils;

import android.os.Parcelable;

import java.io.Serializable;
import java.lang.reflect.Type;

/**
 * Created by Jam on 2017/7/26.
 * Activity之间跳转传递的数据extra的实体类
 */

public class IntentExtra {

    private String name;
    private Object value;
    private Type mType;

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    public IntentExtra(String name, boolean value) {
        this.name = name;
        this.value = value;
    }

    public IntentExtra(String name, CharSequence charSequence) {
        this.name = name;
        this.value = charSequence;
    }

    public IntentExtra(String name, int valueInt) {
        this.name = name;
        this.value = valueInt;
    }

    public IntentExtra(String name, float valueFloat) {
        this.name = name;
        this.value = valueFloat;
    }

    public IntentExtra(String name, Parcelable valueParcelable) {
        this.name = name;
        this.value = valueParcelable;
    }

    public IntentExtra(String name, Serializable valueSerialize) {

        this.name = name;
        this.value = valueSerialize;
    }

    public IntentExtra(String name, String valueStr) {
        this.name = name;
        this.value = valueStr;
    }

    public IntentExtra(String name, double valueDouble) {

        this.name = name;
        this.value = valueDouble;
    }

    public IntentExtra(String name, long valueLong) {

        this.name = name;
        this.value = valueLong;
    }
}
