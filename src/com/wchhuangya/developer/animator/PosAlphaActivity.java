/**
 * Copyright @2014 Obsessed - Studio gsww All rights reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何* 渠道使用、修改源代码.
 * 日期 2014-11-24 下午1:23:27
 */
package com.wchhuangya.developer.animator;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.wchhuangya.developer.R;
import com.wchhuangya.developer.core.BaseActivity;

/**
 * 改变位置、alpha值的动画示例
 * @company gsww
 * @project developer
 * @author wchhuangya
 * @date 2014-11-24 下午1:23:27	
 * @class com.wchhuangya.developer.animation.PosAlphaActivity
 *
 */
@SuppressLint("NewApi")
public class PosAlphaActivity extends BaseActivity {
	private Button mButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pos_alpha);
		
		activity = this;

		AnimatorSet set = (AnimatorSet)AnimatorInflater.loadAnimator(activity, R.animator.pos_alpha);
		set.setTarget(((TextView)findViewById(R.id.pos_alpha_tv)));
		final AnimatorSet as = set;
		
		mButton = (Button)findViewById(R.id.pos_alpha_btn);
		mButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				as.start();
			}
		});
	}
}
