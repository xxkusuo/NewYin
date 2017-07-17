package com.gxtc.yyj.newyin.mvp.model;

import okhttp3.Call;

/**
 * Created by Jam on 2017/7/11.
 */

public interface Callback {
    void error(Call call, Exception e, int id);
}
