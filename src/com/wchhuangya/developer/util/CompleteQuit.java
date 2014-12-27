/**
 * Copyright @2014 Obsessed - Studio gsww All rights reserved.
 * JavaԴ����,δ����ɽ�ֹ�κ��ˡ��κ���֯ͨ���κ�* ����ʹ�á��޸�Դ����.
 * ���� 2014-1-12 ����11:35:59
 */
package com.wchhuangya.developer.util;

import java.util.Stack;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;

/**
 * <p>
 * 完全退出帮助类
 * </p>
 * @company gsww
 * @project AndroidStudy
 * @author wchhuangya
 * @date 2014-1-12 11:35:59	
 * @class com.ch.wchhuangya.util.CompleteQuit
 *
 */
public class CompleteQuit extends Application {

	private Stack<Activity> activityStack;
	private static CompleteQuit instance;

	private CompleteQuit() {
	}

	// 单例模式中获取唯一的CompleteQuit实例
	public static CompleteQuit getInstance() {
		if (null == instance) {
			instance = new CompleteQuit();
		}
		return instance;

	}

	// 添加Activity到容器中
	public void pushActivity(Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack<Activity>();
		}
		activityStack.add(activity);
	}

	// 退出栈中所有Activity(唯一列外)
	@SuppressWarnings("rawtypes")
	public void exitAllButOne(Class cls) {
		while (true) {
			Activity activity = currentActivity();
			if (activity == null) {
				break;
			}
			if (activity.getClass().equals(cls)) {
				break;
			}
			popActivity(activity);
		}
	}

	// 退出栈中所有Activity
	public void exitAll(boolean exit) {
		while (true) {
			Activity activity = currentActivity();
			if (activity == null) {
				break;
			}
			popActivity(activity);
		}
		if (exit) {
			System.exit(0);
		}
	}

	// 获得当前栈顶Activity
	public Activity currentActivity() {
		Activity activity = null;
		if (activityStack != null && !activityStack.empty())
			activity = activityStack.lastElement();
		return activity;
	}

	// 退出栈顶Activity
	public void popActivity(Activity activity) {
		if (activity != null) {
			// 在从自定义集合中取出当前Activity时，也进行了Activity的关闭操作
			activity.finish();
			activityStack.remove(activity);
			activity = null;
		}
	}

	// 退出客户端
	public void exitApp(final Context mContext) {
		new AlertDialog.Builder(mContext).setTitle("提示ʾ")
				.setIcon(android.R.drawable.ic_menu_revert)
				.setMessage("您确定要退出吗?")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						quit(mContext);
					}
				}).setNegativeButton("取消", null).show();
	}
	@SuppressWarnings("deprecation")
	protected void quit(Context mContext) {
		try {
			if (Integer.parseInt(android.os.Build.VERSION.SDK) > 7) {
				getInstance().exitAll(true);
			} else {
				getInstance().exitAll(true);
				ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
				am.restartPackage(getPackageName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}