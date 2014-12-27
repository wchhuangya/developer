/**
 * Copyright @2014 Obsessed - Studio gsww All rights reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何* 渠道使用、修改源代码.
 * 日期 2014-8-22 下午9:11:06
 */
package com.wchhuangya.developer.core;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.umeng.fb.FeedbackAgent;
import com.wchhuangya.developer.R;
import com.wchhuangya.developer.component.crouton.Crouton;
import com.wchhuangya.developer.component.crouton.Style;
import com.wchhuangya.developer.dialog.TipsDialog;
import com.wchhuangya.developer.util.CompleteQuit;

/**
 * <p>
 * Activity的基类
 * </p>
 * @company gsww
 * @project developer
 * @author wchhuangya
 * @date 2014-8-22 下午9:11:06	
 * @class com.wchhuangya.developer.core.BaseActivity
 *
 */
@SuppressWarnings("deprecation")
public class BaseActivity extends Activity {
	/*************全局开始*************/
	/** Activity对象 */
	protected Activity activity;
	/** Intent对象 */
	protected Intent intent;
	/** 顶部布局左边的按钮 */
	protected Button commonTopLeftBtn;
	/** 顶部布局右边的按钮 */
	protected Button commonTopRightBtn;
	/** 顶部布局中间的文本 */
	protected TextView commonTopMiddleTV;
	/** 对话框变量 */
	protected TipsDialog tipsDialog;
	/** 获取当前屏幕的宽度 */
	protected int screenWidth;
	/** 对话框控件 */
	protected ProgressDialog progressDialog;
	/*************全局结束*************/
	
	/*************友盟开始*************/
	/** 友盟反馈 */
	protected FeedbackAgent fbAgent;
	/*************友盟结束*************/

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}
	
	@Override
	protected void onStop() {
		super.onStop();
	}
	
	@Override
	protected void onDestroy() {
		Crouton.cancelAllCroutons();
		super.onDestroy();
	}
	/**
	 * 完全退出应用的方法
	 */
	protected void quit() {
		try {
			if (Integer.parseInt(android.os.Build.VERSION.SDK) > 7) {
				CompleteQuit.getInstance().exitAll(true);
			} else {
				CompleteQuit.getInstance().exitAll(true);
				ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
				am.restartPackage(getPackageName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 初始化顶部工具栏的方法
	 * @param title	-	工具栏要显示的标题
	 */
	protected void initTopTitle(String title){
		commonTopMiddleTV = (TextView)findViewById(R.id.common_top_lmr_middle_tv);
		if(!TextUtils.isEmpty(title))
			commonTopMiddleTV.setText(title);
		commonTopLeftBtn = (Button)findViewById(R.id.common_top_lmr_left_btn);
		commonTopLeftBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		commonTopRightBtn = (Button)findViewById(R.id.common_top_lmr_right_btn);
	}
	/**
	 * 显示键盘
	 */
	protected void showInputMethod() {
		InputMethodManager inputManager = (InputMethodManager) getApplication()
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		inputManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
	}
	/**
	 * 设置dialog，传入参数并显示出来 
	 * @param message 提示内容
	 * @param grivate 显示的位置
	 * @param btnConfirm 确认button
	 * @param btnCancle 取消button
	 * @param width 宽度
	 * @param layout 导入之定义提示框
	 * @param txtId textview控件对象
	 * @param anim 动画效果
	 */
	protected TipsDialog setDialog(String message, int grivate, Button btnConfirm,
			Button btnCancle, int width, int layout, int txtId, int anim) {
		tipsDialog = TipsDialog.creatTipsDialog(getApplicationContext(), width, layout, grivate,
				anim);
		tipsDialog.show();
		return tipsDialog;
	}
	/**
	 * 计算当前屏幕的宽度（像素），结果保存在类成员变量width员
	 */
	protected void calculateScreenWidth(){
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		Rect rect = new Rect();
		View view = getWindow().getDecorView();
		view.getWindowVisibleDisplayFrame(rect);
		screenWidth = dm.widthPixels;
	}
	/**
	 * createCrouton:根据传入的resId，创建一个Crouton动画对象，并依赖resId的位置显示提示动画. <br/> 
	 * @author wchhuangya
	 * @param activity	-	Activity对象
	 * @param msg		-	要显示的消息
	 * @param style		-	提示的类型，警告：Style.ALERT，确认：Style.CONFITM，提示：Style.INFO，
	 * 	自定义：new Style.Builder().setBackgroundColorValue(背景颜色值).build();背景颜色值说明：示例：0xffff4444
	 * @param resId		-	提示所依赖的ViewGroup的ID，必须得传
	 * @return
	 */
	protected void showCrouton(Activity activity, String msg, Style style, int resId){
		Crouton.makeText(activity, msg, style, resId).show();
	}
}
