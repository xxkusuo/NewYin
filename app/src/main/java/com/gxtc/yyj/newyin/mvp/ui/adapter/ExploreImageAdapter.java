package com.gxtc.yyj.newyin.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gxtc.yyj.newyin.R;
import com.gxtc.yyj.newyin.mvp.ui.holder.ExploreImageHolder;

import java.util.List;

/**
 * Created by Jam on 2017/7/25.
 */

public class ExploreImageAdapter extends BaseQuickAdapter<String,ExploreImageHolder> {
    public ExploreImageAdapter(@Nullable List<String> data) {
        super(R.layout.item_explore_image, data);
    }

    @Override
    protected void convert(ExploreImageHolder helper, String item) {
        Glide.with(mContext).load(item).into(helper.mImageView);
    }
}
