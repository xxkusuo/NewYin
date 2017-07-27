package com.gxtc.yyj.newyin.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gxtc.yyj.newyin.R;
import com.gxtc.yyj.newyin.mvp.model.bean.ExploreBean;
import com.gxtc.yyj.newyin.mvp.ui.holder.ExploreImageHolder;

import java.util.List;

/**
 * Created by Jam on 2017/7/25.
 */

public class ExploreImageAdapter extends BaseQuickAdapter<ExploreBean.StatusesBean.PicUrlsBeanX,ExploreImageHolder> {
    public ExploreImageAdapter(@Nullable List<ExploreBean.StatusesBean.PicUrlsBeanX> data) {
        super(R.layout.item_explore_image, data);
    }

    @Override
    protected void convert(ExploreImageHolder helper, ExploreBean.StatusesBean.PicUrlsBeanX picUrlsBeanX) {
        Glide.with(mContext).load(picUrlsBeanX.getThumbnailPic()).into(helper.mImageView);
    }
}
