/**
 * Copyright @2014 Obsessed - Studio gsww All rights reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何* 渠道使用、修改源代码.
 * 日期 2014-11-25 上午11:54:36
 */
package com.wchhuangya.developer.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.TextView;

import com.wchhuangya.developer.R;

/**
 * 自定义对话框。分为三种进入方式：从顶部向下，从底部向上，中间；
 * @company gsww
 * @project developer
 * @author wchhuangya
 * @date 2014-11-25 上午11:54:36	
 * @class com.wchhuangya.developer.dialog.TipsDialog
 *
 */
public class TipsDialog extends Dialog {
	@SuppressWarnings("unused")
	private Context mContext = null;
	private static TipsDialog mTipsDialog = null;

	public TipsDialog(Context context) {
		super(context);
		this.mContext = context;
	}

	public TipsDialog(Context context, int theme) {
		super(context, theme);
	}
	/**
	 * @param context activity上下文
	 * @param message 提示内容
	 * @param mwidth dialog宽度
	 * @param layout dialog布局文件
	 * @return
	 */
	public static TipsDialog creatTipsDialog(Context context, String message,
			int mwidth, int layout) {
		mTipsDialog = new TipsDialog(context, R.style.tipsDialog_style);
		mTipsDialog.setContentView(layout);
		Window w = mTipsDialog.getWindow();
		w.setWindowAnimations(R.style.tipsAnimation); // 添加动画
		w.setGravity(Gravity.BOTTOM); // 此处可以设置dialog显示的位置
		LayoutParams lay = mTipsDialog.getWindow().getAttributes();// dialog显示的宽度
		lay.width = mwidth;
		TextView tvText = (TextView) mTipsDialog.findViewById(R.id.tvText);
		tvText.setText(message);
		return mTipsDialog;
	}

	/**
	 * @param context activity上下文
	 * @param message 提示内容
	 * @param mwidth dialog宽度
	 * @param layout dialog布局文件
	 * @param grivate dialog位置
	 * @return
	 */
	public static TipsDialog creatTipsDialog(Context context,
			int mwidth, int layout, int grivate, int anim) {
		mTipsDialog = new TipsDialog(context, R.style.tipsDialog_style);
		mTipsDialog.setContentView(layout);
		Window w = mTipsDialog.getWindow();
		w.setWindowAnimations(anim); // 添加动画
		w.setGravity(grivate); // 此处可以设置dialog显示的位置
		LayoutParams lay = mTipsDialog.getWindow().getAttributes();// dialog显示的宽度
		lay.width = mwidth;
		return mTipsDialog;
	}

	/**
	 * @param context activity上下文
	 * @param mwidth dialog宽度
	 * @param layout dialog布局文件
	 * @return
	 */
	public static TipsDialog creatTipsDialog(Context context, int mwidth,
			int layout) {
		mTipsDialog = new TipsDialog(context, R.style.tipsDialog_style);
		mTipsDialog.setContentView(layout);
		Window w = mTipsDialog.getWindow();
		w.setWindowAnimations(R.style.tipsAnimation); // 添加动画
		w.setGravity(Gravity.BOTTOM); // 此处可以设置dialog显示的位置
		LayoutParams lay = mTipsDialog.getWindow().getAttributes();// dialog显示的宽度
		lay.width = mwidth;
		return mTipsDialog;
	}
}
