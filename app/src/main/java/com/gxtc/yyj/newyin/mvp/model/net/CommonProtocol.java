package com.gxtc.yyj.newyin.mvp.model.net;

import com.gxtc.yyj.newyin.mvp.model.Callback;
import com.gxtc.yyj.newyin.mvp.model.bean.AccessTokenBean;
import com.gxtc.yyj.newyin.mvp.model.bean.AuthorizeBean;
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


    /**
     * 微博认证
     *
     * @param clientId    申请应用时分配的AppKey
     * @param redirectUri 授权回调地址
     * @param scope       申请scope权限所需参数
     * @param callback    回调
     * @param reqType     请求类型
     */
    public void authorize(String clientId, String redirectUri, String scope, Callback<AuthorizeBean> callback, int reqType) {
        execute(getIHttpService().authorize(clientId, redirectUri, scope), callback, reqType);
    }


    /**
     * 获取AccessToken
     *
     * @param clientId     申请应用时分配的AppKey
     * @param clientSecret 申请应用时分配的AppSecret
     * @param grantType    请求的类型，填写authorization_code
     * @param code         认证返回的code
     * @param redirectUri  授权回调地址
     * @param callback     回调
     * @param reqType      请求类型
     */
    public void accessToken(String clientId,
                            String clientSecret,
                            String grantType,
                            String code,
                            String redirectUri,
                            Callback<AccessTokenBean> callback,
                            int reqType) {
        execute(getIHttpService().accessToken(clientId, clientSecret, grantType, code, redirectUri), callback, reqType);
    }
}
