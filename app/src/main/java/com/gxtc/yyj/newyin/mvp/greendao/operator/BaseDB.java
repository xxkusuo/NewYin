package com.gxtc.yyj.newyin.mvp.greendao.operator;

import org.greenrobot.greendao.async.AsyncSession;

/**
 * Created by Jam on 2017/7/28.
 */

public class BaseDB {
    protected static AsyncSession mAsyncSession = DBUtil.getInstance().getAsyncSession();
}
