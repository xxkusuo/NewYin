package com.gxtc.yyj.newyin.mvp.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gxtc.yyj.newyin.R;
import com.gxtc.yyj.newyin.common.utils.Utils;
import com.gxtc.yyj.newyin.mvp.model.bean.ExploreBean;
import com.gxtc.yyj.newyin.mvp.ui.holder.ExploreHolder;

import java.util.List;

/**
 * Created by Jam on 2017/7/19.
 */

public class ExploreAdapter extends BaseQuickAdapter<ExploreBean.StatusesBean, ExploreHolder> {

    public ExploreAdapter(@Nullable List<ExploreBean.StatusesBean> data) {
        super(R.layout.item_explore, data);
    }

    @Override
    protected void convert(ExploreHolder holder, ExploreBean.StatusesBean status) {
        ExploreBean.StatusesBean.UserBean user = status.getUser();
        Glide.with(mContext)
                .load(user.getAvatarLarge())
                .into(holder.ivAuthorHeader);
        holder.tvAuthorName.setText(user.getScreenName());
        holder.tvPubSource.setText("来自 " + Utils.formatSource(status.getSource()));
        holder.tvPubContent.setText(status.getText());
        holder.tvPubTime.setText(Utils.formatTime(status.getCreatedAt()));
        holder.rvExploreImg.setLayoutManager(new GridLayoutManager(mContext, 3));
        holder.rvExploreImg.setAdapter(new ExploreImageAdapter(status.getPicUrls()));
    }

}
