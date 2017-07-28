package com.gxtc.yyj.newyin.mvp.greendao.operator;

import com.google.gson.Gson;
import com.gxtc.yyj.newyin.mvp.greendao.bean.Explore;
import com.gxtc.yyj.newyin.mvp.greendao.dao.ExploreDao;
import com.gxtc.yyj.newyin.mvp.model.bean.ExploreBean;

import org.greenrobot.greendao.async.AsyncOperationListener;

/**
 * Created by Jam on 2017/7/28.
 */

public class ExploreDB extends BaseDB {
    private static ExploreDao mExploreDao = DBUtil.getInstance().getExploreDao();

    /**
     * 保存主页数据
     *
     * @param exploreBean 主页bean
     */
    public static void saveExploreData(ExploreBean exploreBean) {
        Gson gson = new Gson();
        String ad = gson.toJson(exploreBean.getAd());
        String advertises = gson.toJson(exploreBean.getAdvertises());
        String statuses = gson.toJson(exploreBean.getStatuses());
        final Explore explore = new Explore(
                ad,
                advertises,
                exploreBean.getHasUnread(),
                exploreBean.isHasVisible(),
                exploreBean.getInterval(),
                exploreBean.getMaxId(),
                exploreBean.getNextCursor(),
                exploreBean.getPreviousCursor(),
                exploreBean.getSinceId(),
                statuses,
                exploreBean.getTotalNumber(),
                exploreBean.getUveBlank());
        mAsyncSession.runInTx(new Runnable() {
            @Override
            public void run() {
                mExploreDao.saveInTx(explore);
            }
        });
    }


    /**
     * 异步读取保存的主页数据
     *
     * @param asyncListener 异步回调接口
     * @return Explore
     */
    public static void readExploreData(AsyncOperationListener asyncListener) {
        if (asyncListener == null) return;
        mAsyncSession.setListenerMainThread(asyncListener);//查询结束之后返回主线程
        mAsyncSession.queryList(mExploreDao
                .queryBuilder()
                .build()
        );
    }


    /**
     * 同步读取保存的主页数据
     *
     * @return Explore
     */
    public static Explore readExploreData() {
        return mExploreDao.queryBuilder().build().unique();
    }
}
