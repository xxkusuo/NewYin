package com.gxtc.yyj.newyin.mvp.greendao.operator;

import android.content.Context;

import com.gxtc.yyj.newyin.common.constant.Const;
import com.gxtc.yyj.newyin.mvp.greendao.dao.DaoMaster;
import com.gxtc.yyj.newyin.mvp.greendao.dao.DaoSession;
import com.gxtc.yyj.newyin.mvp.greendao.dao.ExploreDao;
import com.gxtc.yyj.newyin.mvp.greendao.dao.UserDao;

import org.greenrobot.greendao.async.AsyncSession;

/**
 * Created by Jam on 2017/7/28.
 */

public class DBUtil {
    private static DBUtil mInstance;
    private DaoSession mDaoSession;
    private static Context mContext;

    private DBUtil() {
    }

    /**
     * 得到本类实例
     *
     * @return
     */
    public static DBUtil getInstance() {
        return mInstance;
    }

    /**
     * Application中初始化
     *
     * @param context
     */
    public static void init(Context context) {
        initDB(context);
    }

    //初始化操作工具类
    private static void initDB(Context context) {
        mContext = context;
        //实例化
        if (mInstance == null) {
            synchronized (DBUtil.class) {
                if (mInstance == null) {
                    mInstance = new DBUtil();
                }
            }
        }
    }

    /**
     * 获取DaoSession对象
     *
     * @return
     */
    public DaoSession getDaoSession() {
        if (mDaoSession == null) {
            synchronized (DBUtil.class) {
                if (mDaoSession == null) {
                    //初始化操作对象
                    DaoMaster daoMaster = new DaoMaster(new GreenDaoOpenHelper(mContext, Const.DB_NAME, null).getWritableDb());
                    mDaoSession = daoMaster.newSession();
                }
            }
        }
        return mDaoSession;
    }

    /**
     * 得到 异步Session
     *
     * @return AsyncSession
     */
    public AsyncSession getAsyncSession() {
        return getDaoSession().startAsyncSession();
    }


    /**
     * 得到主页数据的操作DAO
     *
     * @return ExploreDao
     */
    public ExploreDao getExploreDao() {
        return getDaoSession().getExploreDao();
    }


    /**
     * 得到用户数据的操作DAO
     *
     * @return UserDao
     */
    public UserDao getUserDao() {
        return getDaoSession().getUserDao();
    }

}
