/**
 * Copyright @2014 Obsessed - Studio gsww All rights reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何* 渠道使用、修改源代码.
 * 日期 2014-11-13 下午2:08:10
 */
package com.wchhuangya.developer.alarm;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wchhuangya.developer.R;
import com.wchhuangya.developer.core.BaseActivity;
import com.wchhuangya.developer.util.SharedPreferenceHelper;

/**
 * 闹钟设置类
 * @company gsww
 * @project developer
 * @author wchhuangya
 * @date 2014-11-13 下午2:08:10	
 * @class com.wchhuangya.developer.alarm.AlarmAddActivity
 *
 */
public class AlarmAddActivity extends BaseActivity implements OnClickListener {
	/** 重复的相对布局 */
	private RelativeLayout mRepeatRL;
	/** 重复副标题 */
	private TextView mRepeatSubTV;
	/** 重复一次的线性布局 */
	private LinearLayout mRepeatOneLL;
	/** 重复一次的文本 */
	private TextView mRepeatOneTV;
	/** 每天重复的线性布局 */
	private LinearLayout mRepeatEverydayLL;
	/** 每天的文本 */
	private TextView mRepeatEverydayTV;
	/** 周一到周五的线性布局 */
	private LinearLayout mRepeatMonToFriLL;
	/** 周一到周五的文本 */
	private TextView mRepeatMonToFriTV;
	/** 自定义的线性布局 */
	private LinearLayout mRepeatCustomLL;
	/** 自定义的文本 */
	private TextView mRepeatCustomTV;
	/** 振动的相对布局 */
	private RelativeLayout mVibrationRL;
	/** 振动的CheckBox */
	private CheckBox mVibrateCB;
	/** 备注的相对布局 */
	private RelativeLayout mRemarkRL;
	/** 备注副标题 */
	private TextView mRemarkSubTV;
	/** 备注弹窗的输入框 */
	private EditText mRemarkET;
	/** 备注弹窗的取消按钮 */
	private Button mRemarkCancelBtn;
	/** 备注弹窗的确认按钮 */
	private Button mRemarkOKBtn;
	/** 弹出框对象 */
	private PopupWindow mPopupWindow;
	
	/** 记录重复的类型。0：重复一次；1：每天重复；2：周一到周五；3：自定义； */
	private int mRepeatType = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alarm_add);
		
		activity = this;
		init();
		calculateScreenWidth();
	}
	
	private void init(){
		initTopTitle(getResources().getString(R.string.guides_alarm_add));
		
		commonTopLeftBtn.setText(getResources().getString(R.string.common_cancel));
		commonTopRightBtn.setText(getResources().getString(R.string.common_ok));
		
		mRepeatRL = (RelativeLayout)findViewById(R.id.alarm_add_repeat_rl);
		mRepeatSubTV = (TextView)findViewById(R.id.alarm_add_repeat_sub_tv);
		mVibrationRL = (RelativeLayout)findViewById(R.id.alarm_add_vibration_rl);
		mVibrateCB = (CheckBox)findViewById(R.id.alarm_vibration_cb);
		mRemarkRL = (RelativeLayout)findViewById(R.id.alarm_add_remark_rl);
		mRemarkSubTV = (TextView)findViewById(R.id.alarm_add_remark_sub_tv);
		
		mRepeatRL.setOnTouchListener(touchListener);
		mVibrationRL.setOnTouchListener(touchListener);
		mRemarkRL.setOnTouchListener(touchListener);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("开关1", "1");
		map.put("开关2", "0");
		map.put("开关3", "1");
		SharedPreferenceHelper.savePres(activity, map, Context.MODE_PRIVATE);
		Toast.makeText(activity, 
				SharedPreferenceHelper.getPres(activity, Context.MODE_PRIVATE).toString(), Toast.LENGTH_LONG)
				.show();
	}
	/**
	 * 相对布局touch事件的匿名内部类
	 */
	private OnTouchListener touchListener = new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN://按下
				v.setBackgroundResource(R.drawable.alarm_line_pressed);
				break;
			case MotionEvent.ACTION_UP://抬起
				v.setBackgroundResource(R.drawable.alarm_line_normal);
				//Button btnCancle = null, btnConfirm = null;
				switch (v.getId()) {
				case R.id.alarm_add_repeat_rl://点击重复布局
					/*setDialog("", Gravity.BOTTOM, btnConfirm, btnCancle, 
							screenWidth, R.layout.alarm_repeat_window, 
							R.id.tvText, R.style.tipsAnimation);*/
					showPopWindow(1);
					break;
				case R.id.alarm_repeat_window_one_ll://点击重复一次的布局
					mRepeatType = 0;
					mRepeatSubTV.setText(getResources().getString(R.string.alarm_repeat_window_one));
					mPopupWindow.dismiss();
					break;
				case R.id.alarm_repeat_window_everyday_ll://点击每天重复的布局
					mRepeatType = 1;
					mRepeatSubTV.setText(getResources().getString(R.string.alarm_repeat_window_everyday));
					mPopupWindow.dismiss();
					break;
				case R.id.alarm_repeat_window_mon_to_fir_ll://点击周一到周五的布局
					mRepeatType = 2;
					mRepeatSubTV.setText(getResources().getString(R.string.alarm_repeat_window_mon_to_fir));
					mPopupWindow.dismiss();
					break;
				case R.id.alarm_repeat_window_custom_ll://点击自定义的布局
					mRepeatType = 3;
					mRepeatSubTV.setText(getResources().getString(R.string.alarm_repeat_window_custom));
					mPopupWindow.dismiss();
					break;
				case R.id.alarm_add_vibration_rl://点击振动布局
					if(mVibrateCB.isChecked())
						mVibrateCB.setChecked(false);
					else
						mVibrateCB.setChecked(true);
					break;
				case R.id.alarm_add_remark_rl://点击备注布局
					showPopWindow(3);
					break;
				}
				break;
			}
			return true;
		}
	};
	/**
	 * 从屏幕底部弹出菜单的动画
	 * @param type		-	1. 重复；2. 自定义；3. 备注；
	 */
	private void showPopWindow(int type){
		View view = null;
		switch (type) {
		case 1:
			view = repeatWindow();
			break;
		case 2:
			
			break;
		case 3:
			view = remarkWindow();
			break;
		}
		
		if(view != null){
			mPopupWindow = new PopupWindow(this);;
	
			mPopupWindow.setFocusable(true); // 设置PopupWindow可获得焦点
			mPopupWindow.setTouchable(true); // 设置PopupWindow可触摸
			mPopupWindow.setOutsideTouchable(true); // 设置非PopupWindow区域可触摸
	
			mPopupWindow.setContentView(view);
			
			mPopupWindow.setWidth(LayoutParams.MATCH_PARENT);
			mPopupWindow.setHeight(LayoutParams.WRAP_CONTENT);
			
			mPopupWindow.setAnimationStyle(R.style.common_pop_from_bottom);	//设置 popupWindow 动画样式
	
	        //实例化一个ColorDrawable颜色为半透明  
	        ColorDrawable dw = new ColorDrawable(0x80555555);  
	        //设置SelectPicPopupWindow弹出窗体的背景  
	        mPopupWindow.setBackgroundDrawable(dw);  
	
			mPopupWindow.showAtLocation(mRepeatRL, Gravity.BOTTOM, 0, 0);
			
			final View vv = view; 
			view.setOnTouchListener(new OnTouchListener() {  
	            public boolean onTouch(View v, MotionEvent event) {  
	                 
	                int height = vv.findViewById(R.id.alarm_window_ll).getTop();  
	                int y = (int) event.getY();  
	                if(event.getAction()==MotionEvent.ACTION_UP){  
	                    if(y<height){  
	                    	mPopupWindow.dismiss();  
	                    }  
	                }                 
	                return true;  
	            }  
	        });  
			
			if(type == 3){
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						showInputMethod();
					}
				}, 200);
			}			
	
			mPopupWindow.update();
		}
	}
	/**
	 * 弹出的重复前的准备
	 * @return	-	重复布局
	 */
	private View repeatWindow(){
		View view = View.inflate(this, R.layout.alarm_repeat_window, null);
		mRepeatOneLL = (LinearLayout)view.findViewById(R.id.alarm_repeat_window_one_ll);
		mRepeatOneTV = (TextView)view.findViewById(R.id.alarm_repeat_window_one_tv);
		mRepeatEverydayLL = (LinearLayout)view.findViewById(R.id.alarm_repeat_window_everyday_ll);
		mRepeatEverydayTV = (TextView)view.findViewById(R.id.alarm_repeat_window_everyday_tv);
		mRepeatMonToFriLL = (LinearLayout)view.findViewById(R.id.alarm_repeat_window_mon_to_fir_ll);
		mRepeatMonToFriTV = (TextView)view.findViewById(R.id.alarm_repeat_window_mon_to_fir_tv);
		mRepeatCustomLL = (LinearLayout)view.findViewById(R.id.alarm_repeat_window_custom_ll);
		mRepeatCustomTV = (TextView)view.findViewById(R.id.alarm_repeat_window_custom_tv);
		
		setLeftDrawable();
		mRepeatOneLL.setOnTouchListener(touchListener);
		mRepeatEverydayLL.setOnTouchListener(touchListener);
		mRepeatMonToFriLL.setOnTouchListener(touchListener);
		mRepeatCustomLL.setOnTouchListener(touchListener);
		
		return view;
	}
	/**
	 * 设置重复布局文字前的图片
	 */
	private void setLeftDrawable(){
		mRepeatOneTV.setTextColor(getResources().getColor(R.color.common_main_title));
		mRepeatEverydayTV.setTextColor(getResources().getColor(R.color.common_main_title));
		mRepeatMonToFriTV.setTextColor(getResources().getColor(R.color.common_main_title));
		mRepeatCustomTV.setTextColor(getResources().getColor(R.color.common_main_title));
		
		Drawable drawable = getResources().getDrawable(R.drawable.alarm_repeat_current_arrow);
		drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
		
		switch (mRepeatType) {
		case 0://重复一次
			mRepeatOneTV.setCompoundDrawables(drawable, null, null, null);
			mRepeatOneTV.setCompoundDrawablePadding(10);
			mRepeatOneTV.setTextColor(getResources().getColor(R.color.alarm_repeat_window_current));
			break;
		case 1://每天重复
			mRepeatEverydayTV.setCompoundDrawables(drawable, null, null, null);
			mRepeatEverydayTV.setCompoundDrawablePadding(10);
			mRepeatEverydayTV.setTextColor(getResources().getColor(R.color.alarm_repeat_window_current));
			break;
		case 2://周一到周五
			mRepeatMonToFriTV.setCompoundDrawables(drawable, null, null, null);
			mRepeatMonToFriTV.setCompoundDrawablePadding(10);
			mRepeatMonToFriTV.setTextColor(getResources().getColor(R.color.alarm_repeat_window_current));
			break;
		case 3://自定义
			mRepeatCustomTV.setCompoundDrawables(drawable, null, null, null);
			mRepeatCustomTV.setCompoundDrawablePadding(10);
			mRepeatCustomTV.setTextColor(getResources().getColor(R.color.alarm_repeat_window_current));
			break;
		}
	}
	/**
	 * 弹出的闹钟备注前的准备
	 * @return	-	闹钟备注布局
	 */
	private View remarkWindow(){
		View view = View.inflate(this, R.layout.alarm_remark_window, null);
		mRemarkET = (EditText)view.findViewById(R.id.alarm_remark_window_et);
		mRemarkCancelBtn = (Button)view.findViewById(R.id.alarm_remark_window_cancel_btn);
		mRemarkOKBtn = (Button)view.findViewById(R.id.alarm_remark_window_ok_btn);
		
		String remark = mRemarkSubTV.getText().toString().trim();
		mRemarkET.setText(remark.equals(getResources().getString(R.string.alarm_remark_sub)) ? "" : remark);
		mRemarkCancelBtn.setOnClickListener(this);
		mRemarkOKBtn.setOnClickListener(this);
		
		return view;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.alarm_remark_window_cancel_btn:
			mPopupWindow.dismiss();
			break;
		case R.id.alarm_remark_window_ok_btn:
			mRemarkSubTV.setText(mRemarkET.getText().toString().trim());
			mPopupWindow.dismiss();
			break;
		}
	}
}
