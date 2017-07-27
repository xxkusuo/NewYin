package com.gxtc.yyj.newyin.mvp.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.gxtc.yyj.newyin.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Jam on 2017/7/19.
 * Explore Fragment中的ViewHolder 用于绑定数据和视图
 */

public class ExploreHolder extends BaseViewHolder {
    public CircleImageView ivAuthorHeader;
    public TextView tvAuthorName;
    public TextView tvPubTime;
    public TextView tvPubSource;
    public TextView tvPubContent;
    public RecyclerView rvExploreImg;

    public ExploreHolder(View view) {
        super(view);
        ivAuthorHeader = (CircleImageView) itemView.findViewById(R.id.iv_author_header);
        tvAuthorName = (TextView) itemView.findViewById(R.id.tv_author_name);
        tvPubTime = (TextView) itemView.findViewById(R.id.tv_pub_time);
        tvPubSource = (TextView) itemView.findViewById(R.id.tv_pub_source);
        rvExploreImg = (RecyclerView) itemView.findViewById(R.id.rv_explore_img);
        tvPubContent = (TextView) itemView.findViewById(R.id.tv_pub_content);
    }
}
