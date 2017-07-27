package com.gxtc.yyj.newyin.mvp.model.net;

import com.gxtc.yyj.newyin.mvp.model.Callback;
import com.gxtc.yyj.newyin.mvp.model.bean.ExploreBean;

/**
 * Created by Jam on 2017/7/18.
 */

public class CommonProtocol extends BaseProtocol {

    /**
     * 获取首页微博
     *
     * @param accessToken token
     * @param count       每页的数量
     * @param page        请求第几页
     * @param baseApp     是否获取当前应用的数据
     * @param feature     过滤类型
     * @param trimUser    返回值中user字段开关
     * @param callback    回调
     * @param reqType     请求类型
     */
    public void getExplore(
            String accessToken,
            int count,
            int page,
            int baseApp,
            int feature,
            int trimUser,
            Callback<ExploreBean> callback,
            int reqType) {
        execute(getIHttpService().getExplore(accessToken, count, page, baseApp, feature, trimUser), callback, reqType);
    }

}
