/**
 * Copyright @2014 Obsessed - Studio gsww All rights reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何* 渠道使用、修改源代码.
 * 日期 2014-8-25 下午9:55:10
 */
package com.wchhuangya.developer.core;

import android.app.Application;
import android.content.Context;


/**
 * <p>
 * 全局的应用类
 * </p>
 * @company gsww
 * @project developer
 * @author wchhuangya
 * @date 2014-8-25 下午9:55:10	
 * @class com.wchhuangya.developer.core.DeveloperApplication
 *
 */
public class DeveloperApplication extends Application {
	/** 全局的上下文变量 */
	private static Context mContext;

	@Override
	public void onCreate() {
		super.onCreate();
		
		mContext = getApplicationContext();
	}

	public static Context getmContext() {
		return mContext;
	}

	public static void setmContext(Context mContext) {
		DeveloperApplication.mContext = mContext;
	}
}
