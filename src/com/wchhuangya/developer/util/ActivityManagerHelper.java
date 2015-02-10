/**
 * 项目  developer 
 * 创建时间  2015-1-30 上午10:47:24 
 * Copyright (c) 2015, developer All rights reserved.
 * 机器猫公司 专有/保密源代码,未经许可禁止任何人通过任何* 渠道使用、修改源代码.
 */

package com.wchhuangya.developer.util;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;


/**
 * 类名: ActivityManagerHelper <br/> 
 * 功能:  <br/> 
 * 创建日期: 2015-1-30 上午10:47:24 <br/> 
 *
 * @author wchya
 * @version V1.0
 * @since Jdk 1.6
 * @see       
 *
 */
public class ActivityManagerHelper {

	public static boolean isAppForeground(Context context){
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> list = am.getRunningTasks(1);
		LogHelper.printDebugLog("1" + list.get(0).baseActivity.getPackageName());
		LogHelper.printDebugLog("2" + ManifestHelper.getMetaValue(context, "packagename"));
		LogHelper.printDebugLog("3" + context.getPackageName());
		return list.get(0).baseActivity.getPackageName().equals(context.getPackageName());
	}
}
