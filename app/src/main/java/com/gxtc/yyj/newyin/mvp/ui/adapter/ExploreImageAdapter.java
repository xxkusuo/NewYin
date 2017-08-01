package com.gxtc.yyj.newyin.mvp.ui.adapter;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gxtc.yyj.newyin.R;
import com.gxtc.yyj.newyin.common.utils.Global;
import com.gxtc.yyj.newyin.mvp.model.bean.ExploreBean;
import com.gxtc.yyj.newyin.mvp.ui.holder.ExploreImageHolder;

import java.util.List;

/**
 * Created by Jam on 2017/7/25.
 */

public class ExploreImageAdapter extends BaseQuickAdapter<ExploreBean.StatusesBean.PicUrlsBeanX, ExploreImageHolder> {
    public ExploreImageAdapter(@Nullable List<ExploreBean.StatusesBean.PicUrlsBeanX> data) {
        super(R.layout.item_explore_image, data);
    }

    @Override
    protected void convert(final ExploreImageHolder helper, ExploreBean.StatusesBean.PicUrlsBeanX picUrlsBeanX) {
        final RequestOptions requestOptions = RequestOptions
                .placeholderOf(R.drawable.bg_place_holder)
                .error(R.drawable.bg_place_holder);
        Glide.with(mContext)
                .asBitmap()
                .load(picUrlsBeanX.getThumbnailPic())
//                .apply(requestOptions)
//                .into(helper.mImageView);
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        int width = resource.getWidth();
                        int height = resource.getHeight();
                        int resizeWidth = width;
                        int resizeHeight = height;
                        if (height < 100) {
                            resizeWidth = (int) (Global.mScreenWidth / 3 + 0.5f);
                            resizeHeight = (int) (Global.mScreenWidth / 3 * height / width + 0.5f);
                        }else {
                            resizeHeight = 100;
                            resizeWidth = (int) (Global.mScreenWidth / 3 + 0.5f);
                        }
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) helper.mImageView.getLayoutParams();
                        layoutParams.height = resizeHeight;
                        layoutParams.width = resizeWidth;
                        helper.mImageView.setImageBitmap(resource);
                    }
                });
    }
}
