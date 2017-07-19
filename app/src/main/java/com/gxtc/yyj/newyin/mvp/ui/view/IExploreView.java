package com.gxtc.yyj.newyin.mvp.ui.view;

import com.gxtc.yyj.newyin.mvp.model.bean.ExploreBean;

import java.util.List;

/**
 * Created by Jam on 2017/7/18.
 * ExploreFragment的接口类 用于对UI进行一些操作
 */

public interface IExploreView extends IBaseView{
    void onResponse(int reqType, List<ExploreBean.ResultsBean> result);//刷新完成提供数据刷新UI
}
