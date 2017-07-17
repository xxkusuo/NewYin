package com.gxtc.yyj.newyin.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gxtc.yyj.newyin.common.utils.Global;
import com.gxtc.yyj.newyin.common.utils.Utils;

/**
 * Fragment基类，所有的Fragment都需要继承此类。
 * 封装查看子控件，设置监听器，初始化数据
 *
 * @author Jam
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {

	/** 管理Fragment的Activity */
	public BaseActivity mActivity;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 初始化Activity对象
		mActivity = (BaseActivity)getActivity();
	}

	/** Fragment显示的布局 */
	public View mRoot;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		if (mRoot == null) {
			mRoot = inflater.inflate(getLayoutRes(), null);

			// 查找布局中的所有的button(ImageButton),并设置点击事件
			Utils.findButtonAndSetOnClickListener(mRoot, this);
			
			initView();
			initListener();
			initData();
			
		} else {
			// 解除mRoot与其父控件的关系
			unbindWidthParent(mRoot);
		}
		
		return mRoot;
	}

	protected abstract int getLayoutRes();

	protected abstract void initView();

	protected void initListener(){};

	protected void initData(){}



	/**
	 * 解除父子控件关系
	 * 
	 * @param view
	 */
	public void unbindWidthParent(View view) {
		ViewGroup parent = (ViewGroup) view.getParent();
		if (parent != null) {
			parent.removeView(view);
		}
	}

	/** 查找子控件，可以省略强转 */
	public <T> T findView(int id) {
		T view = (T) mRoot.findViewById(id);
		return view;
	}

	
	@Override
	public void onClick(View v) {
	}
	
	public void showToast(String text) {
		Global.showToast(text);
	}

}

