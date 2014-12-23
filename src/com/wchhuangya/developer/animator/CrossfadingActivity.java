/**
 * Copyright @2014 Obsessed - Studio gsww All rights reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何* 渠道使用、修改源代码.
 * 日期 2014-9-9 上午10:24:24
 */
package com.wchhuangya.developer.animator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.view.View;

import com.wchhuangya.developer.R;
import com.wchhuangya.developer.core.BaseActivity;

/**
 * <p>
 * 官方Training-淡入淡出
 * </p>
 * @company gsww
 * @project developer
 * @author wchhuangya
 * @date 2014-9-9 上午10:24:24	
 * @class com.wchhuangya.developer.animation.CrossfadingActivity
 *
 */
public class CrossfadingActivity extends BaseActivity {
	private View mContentView;//显示内容的view
	private View mLoadingView;//显示加载动画的view
	private int mShortAnimationDuration;//动画持续的时间

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.animation_cross_fading);
		
		mContentView = findViewById(R.id.cross_fading_sc);
		mLoadingView = findViewById(R.id.cross_fading_loading);
		
		mContentView.setVisibility(View.GONE);
		
		mShortAnimationDuration = 5000;//getResources().getInteger(android.R.integer.config_shortAnimTime);
		
		crossFade();
	}
	
	private void crossFade(){
		//设置内容view的透明度为0%，但是保持它可见，这样的话，在整个动画期间，它就是可见的（但完全透明）
		mContentView.setAlpha(0f);
		mContentView.setVisibility(View.VISIBLE);
		
		//让content的透明度动态的添加到100%，并清除给它所设置的所有动画监听
		mContentView.animate().alpha(1f).setDuration(mShortAnimationDuration).setListener(null);
		
		//动态的改变loading view的透明度为0%。
		//在这个动画结束后，设置它的可见性为NONE，这是一种最优化的处理（这样做了它就不会参与布局的变化了）
		mLoadingView.animate().alpha(0f).setDuration(2500)
			.setListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					mLoadingView.setVisibility(View.GONE);
				}
			});
	}
}
