package com.gxtc.yyj.newyin.mvp.greendao.operator;

import com.google.gson.Gson;
import com.gxtc.yyj.newyin.mvp.greendao.bean.User;
import com.gxtc.yyj.newyin.mvp.greendao.dao.UserDao;
import com.gxtc.yyj.newyin.mvp.model.bean.UserInfo;

import org.greenrobot.greendao.async.AsyncOperationListener;

/**
 * Created by Jam on 2017/7/31.
 */

public class UserDB extends BaseDB {
    private static UserDao mUserDao = DBUtil.getInstance().getUserDao();

    /**
     * 插入用户信息
     *
     * @param userInfo      用户信息
     * @param asyncListener 异步监听
     */
    public static void updateUserInfo(UserInfo userInfo, AsyncOperationListener asyncListener) {
        Gson gson = new Gson();
        final User user = new User(gson.toJson(userInfo));
        mAsyncSession.setListenerMainThread(asyncListener);
        mAsyncSession.runInTx(new Runnable() {
            @Override
            public void run() {
                mUserDao.deleteAll();
                mUserDao.save(user);
            }
        });
    }


    /**
     * 查询用户信息
     *
     * @return User
     */
    public static User readUserInfo() {
        return mUserDao.queryBuilder().build().unique();
    }
}
