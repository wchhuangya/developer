/**
 * Copyright @2014 Obsessed - Studio gsww All rights reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何* 渠道使用、修改源代码.
 * 日期 2014-8-22 下午10:44:34
 */
package com.wchhuangya.developer.umeng;

import android.os.Bundle;

import com.umeng.fb.FeedbackAgent;
import com.wchhuangya.developer.core.BaseActivity;

/**
 * <p>
 * 友盟的信息反馈页面
 * </p>
 * @company gsww
 * @project developer
 * @author wchhuangya
 * @date 2014-8-22 下午10:44:34	
 * @class com.wchhuangya.developer.umeng.FeedbackActivity
 *
 */
public class FeedbackActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(fbAgent == null)
			fbAgent = new FeedbackAgent(this);
		fbAgent.startFeedbackActivity();
		finish();
	}
}
