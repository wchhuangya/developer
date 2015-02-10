/**
 * 项目  developer 
 * 创建时间  2015-1-28 下午2:36:08 
 * Copyright (c) 2015, developer All rights reserved.
 * 机器猫公司 专有/保密源代码,未经许可禁止任何人通过任何* 渠道使用、修改源代码.
 */

package com.wchhuangya.developer.test;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.wchhuangya.developer.R;
import com.wchhuangya.developer.core.BaseActivity;
import com.wchhuangya.developer.util.ActivityManagerHelper;
import com.wchhuangya.developer.util.LogHelper;


/**
 * 类名: TestActivityManager <br/> 
 * 功能: 测试ActivityManager类功能 <br/> 
 * 创建日期: 2015-1-28 下午2:36:08 <br/> 
 *
 * @author wchya
 * @version V1.0
 * @since Jdk 1.6
 * @see       
 */
public class TestActivityManagerActivity extends BaseActivity {
	private TextView firstTV;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_activitymanager);
		
		init();
		getRunningTasks();
		ActivityManagerHelper.isAppForeground(this);
	}
	/**
	 * init:初始化控件的方法
	 * @author wchya
	 */
	private void init(){
		firstTV = (TextView)findViewById(R.id.activitymanager_cur_tasks);
	}
	
	private void getRunningTasks(){
		 ActivityManager am = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
		 List<RunningTaskInfo> list = am.getRunningTasks(100);
		 for(RunningTaskInfo info : list){
			 LogHelper.printDebugLog("baseActivity:" + info.baseActivity.getClassName());
			 firstTV.append("baseActivity:" + info.baseActivity.getClassName() + "  ||  ");
			 LogHelper.printDebugLog("description:" + info.description);
			 firstTV.append("description:" + info.description + "  ||  ");
			 LogHelper.printDebugLog("id" + info.id);
			 firstTV.append("id:" + info.id + "  ||  ");
			 LogHelper.printDebugLog("numActivities:" + info.numActivities);
			 firstTV.append("numActivities:" + info.numActivities + "  ||  ");
			 LogHelper.printDebugLog("numRunning:" + info.numRunning);
			 firstTV.append("numRunning:" + info.numRunning + "  ||  ");
			 LogHelper.printDebugLog("topActivity:" + info.topActivity.getClassName());
			 firstTV.append("topActivity:" + info.topActivity + "  ||  ");
			 LogHelper.printDebugLog("describeContents:" + info.describeContents());
			 firstTV.append("describeContents:" + info.describeContents() + "\n\n");
		 }
	} 
}
