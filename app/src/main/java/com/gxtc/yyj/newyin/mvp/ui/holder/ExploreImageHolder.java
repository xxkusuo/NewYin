package com.gxtc.yyj.newyin.mvp.ui.holder;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.gxtc.yyj.newyin.R;

/**
 * Created by Jam on 2017/7/25.
 */

public class ExploreImageHolder extends BaseViewHolder {
    public ImageView mImageView;

    public ExploreImageHolder(View view) {
        super(view);
        mImageView = (ImageView) itemView.findViewById(R.id.iv_explore);
    }
}
