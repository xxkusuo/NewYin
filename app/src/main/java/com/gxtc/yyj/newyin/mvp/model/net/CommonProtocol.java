package com.gxtc.yyj.newyin.mvp.model.net;

import com.gxtc.yyj.newyin.mvp.model.Callback;
import com.gxtc.yyj.newyin.mvp.model.bean.ExploreBean;

/**
 * Created by Jam on 2017/7/18.
 */

public class CommonProtocol extends BaseProtocol {

    /**
     * 获取ExploreFragment数据
     *
     * @param resultSize 返回集合的大小
     * @param pageOffset 返回第几页的数据
     * @param callback   回调
     * @param reqType    请求类型
     */
    public void getExplore(int resultSize, int pageOffset, Callback<ExploreBean> callback, int reqType) {
        execute(getIHttpService().getExplore(resultSize, pageOffset), callback, reqType);
    }
}
