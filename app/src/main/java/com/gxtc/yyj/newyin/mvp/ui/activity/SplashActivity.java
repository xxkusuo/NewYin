package com.gxtc.yyj.newyin.mvp.ui.activity;

import android.content.Intent;

import com.gxtc.yyj.newyin.R;
import com.gxtc.yyj.newyin.common.base.BaseActivity;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

/**
 * Created by Jam on 2017/7/26.
 * 启动页
 */

public class SplashActivity extends BaseActivity {
    private static final String TAG = "SplashActivity";
    private SsoHandler mSsoHandler;
    private Oauth2AccessToken mAccessToken;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        mAccessToken = AccessTokenKeeper.readAccessToken(this);
        if (!mAccessToken.isSessionValid() || mAccessToken.getExpiresTime() < System.currentTimeMillis()) {
            mSsoHandler = new SsoHandler(this);
            mSsoHandler.authorize(new WbAuthListener() {
                @Override
                public void onSuccess(Oauth2AccessToken oauth2AccessToken) {
                    mAccessToken = oauth2AccessToken;
                    if (mAccessToken.isSessionValid()) {
                        AccessTokenKeeper.writeAccessToken(SplashActivity.this, mAccessToken);
                        showToast("授权成功");
                        startIntent(MainActivity.class,true);
                    }
                }

                @Override
                public void cancel() {
                    showToast("取消授权");
                }

                @Override
                public void onFailure(WbConnectErrorMessage wbConnectErrorMessage) {
                    showToast(wbConnectErrorMessage.getErrorMessage());
                }
            });
        } else {
            startIntent(MainActivity.class, true);
        }
    }


    /**
     * 当 SSO 授权 Activity 退出时，该函数被调用。
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // SSO 授权回调
        // 重要：发起 SSO 登陆的 Activity 必须重写 onActivityResults
        if (mSsoHandler != null) {
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }

    }
}
