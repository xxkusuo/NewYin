package com.gxtc.yyj.newyin.mvp.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gxtc.yyj.newyin.R;
import com.gxtc.yyj.newyin.common.utils.Utils;
import com.gxtc.yyj.newyin.mvp.model.bean.ExploreBean;
import com.gxtc.yyj.newyin.mvp.ui.holder.ExploreHolder;

import java.util.List;

/**
 * Created by Jam on 2017/7/19.
 */

public class ExploreAdapter extends BaseQuickAdapter<ExploreBean.ResultsBean, ExploreHolder> {

    public ExploreAdapter(@Nullable List<ExploreBean.ResultsBean> data) {
        super(R.layout.item_explore, data);
    }

    @Override
    protected void convert(ExploreHolder holder, ExploreBean.ResultsBean result) {
        holder.tvAuthorName.setText(result.getWho());
        holder.tvPubSource.setText("来自 "+result.getSource());
        holder.tvPubType.setText("分类 "+result.getType());
        holder.tvPubContent.setText(result.getDesc());
        holder.tvPubTime.setText(Utils.formatTime(result.getPublishedAt()));
        holder.rvExploreImg.setLayoutManager(new GridLayoutManager(mContext,3));
        holder.rvExploreImg.setAdapter(new ExploreImageAdapter(result.getImages()));
    }

}
