package com.gxtc.yyj.newyin.mvp.model.bean;

import java.io.Serializable;

/**
 * Created by Jam on 2017/7/26.
 */

public class AccessTokenBean implements Serializable{

    /**
     * mAccessToken : ACCESS_TOKEN
     * mExpiresIn : 1234
     * mRemindIn : 798114
     * uid : 12341234
     */

    private String mAccessToken;
    private int mExpiresIn;
    private String mRemindIn;
    private String uid;

    public String getAccessToken() {
        return mAccessToken;
    }

    public void setAccessToken(String accessToken) {
        this.mAccessToken = accessToken;
    }

    public int getExpiresIn() {
        return mExpiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.mExpiresIn = expiresIn;
    }

    public String getRemindIn() {
        return mRemindIn;
    }

    public void setRemindIn(String remindIn) {
        this.mRemindIn = remindIn;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
