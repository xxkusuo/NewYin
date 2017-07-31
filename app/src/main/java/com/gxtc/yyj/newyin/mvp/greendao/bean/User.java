package com.gxtc.yyj.newyin.mvp.greendao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Jam on 2017/7/31.
 */
@Entity
public class User {
    private String userInfo;

    @Generated(hash = 924027712)
    public User(String userInfo) {
        this.userInfo = userInfo;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public String getUserInfo() {
        return this.userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

   
}
