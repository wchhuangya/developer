/**
 * Copyright @2014 Obsessed - Studio gsww All rights reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何* 渠道使用、修改源代码.
 * 日期 2014-12-9 下午4:53:21
 */
package com.wchhuangya.developer.util;

import com.wchhuangya.developer.common.Constants;

import android.text.TextUtils;
import android.util.Log;

/**
 * 日志助手类
 * @company WSuperman
 * @project developer
 * @author wchhuangya
 * @date 2014-12-9 下午4:53:21	
 * @class com.wchhuangya.developer.util.LogHelper
 *
 */
public class LogHelper {

	/**
	 * 打印调试日志。参数不能为空。
	 * @param msg	-	要打印的日志信息
	 */
	public static void printDebugLog(String msg){
		if(!TextUtils.isEmpty(msg))
			if(Constants.LOG_PRINT_SWITCH)
				Log.d(Constants.LOG_TAG, msg);
	}
}
