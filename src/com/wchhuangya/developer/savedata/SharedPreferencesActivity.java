/**
 * Copyright @2014 Obsessed - Studio gsww All rights reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何* 渠道使用、修改源代码.
 * 日期 2014-12-4 上午11:21:43
 */
package com.wchhuangya.developer.savedata;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.wchhuangya.developer.R;
import com.wchhuangya.developer.core.BaseActivity;
import com.wchhuangya.developer.util.SharedPreferenceHelper;

/**
 * SharedPreference用法演示
 * @company WSuperman
 * @project developer
 * @author wchhuangya
 * @date 2014-12-4 上午11:21:43	
 * @class com.wchhuangya.developer.savedata.SharedPreferencesActivity
 *
 */
@SuppressWarnings("rawtypes")
public class SharedPreferencesActivity extends BaseActivity {
	/** 用户显示当前配置的TextView */
	private TextView mCurTV;
	/** 第一个复选框 */
	private CheckBox mFirstCB;
	/** 第二个复选框 */
	private CheckBox mSecondCB;
	/** 第三个复选框 */
	private CheckBox mThirdCB;
	/** 设置按钮 */
	private Button mSetBtn;
	
	/** sharedpreference操作的模式，0-清除；1-设置； */
	private int mSetMode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shared_preference);
		
		activity = this;
		init();
	}
	
	private void init(){
		mCurTV = (TextView)findViewById(R.id.shared_preference_cur_tv);
		mFirstCB = (CheckBox)findViewById(R.id.shared_preference_switch1_cb);
		mSecondCB = (CheckBox)findViewById(R.id.shared_preference_switch2_cb);
		mThirdCB = (CheckBox)findViewById(R.id.shared_preference_switch3_cb);
		mFirstCB.setOnCheckedChangeListener(changeListener);
		mSecondCB.setOnCheckedChangeListener(changeListener);
		mThirdCB.setOnCheckedChangeListener(changeListener);
		mSetBtn = (Button)findViewById(R.id.shared_preference_set_btn);
		mSetBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				switch (mSetMode) {
				case 0:
					mSetMode = 1;
					break;
				case 1:
					mSetMode = 0;
					break;
				}
				setSharedPreferencesOfActivity();
			}
		});
		
		Map map = getSharedPreferencesOfActivity(Context.MODE_PRIVATE);
		if(map.size() < 1) {
			mSetMode = 0;
			mSetBtn.setText(getResources().getString(R.string.shared_preference_add));
			mCurTV.setText(getResources().getString(R.string.shared_preference_no_settings));
		} else {
			mSetMode = 1;
			mSetBtn.setText(getResources().getString(R.string.shared_preference_clear));
			mFirstCB.setChecked(getCBState(map.get("开关1").toString()));
			mSecondCB.setChecked(getCBState(map.get("开关2").toString()));
			mThirdCB.setChecked(getCBState(map.get("开关3").toString()));
			mCurTV.setText(map.toString());
		}
	}
	/**
	 * 获取activity私有的sharedpreferences配置
	 * @param mode	-	获取的模式
	 * @return		-	配置
	 */
	private Map getSharedPreferencesOfActivity(int mode){
		return SharedPreferenceHelper.getPres(activity, mode);
	}
	/**
	 * 设置activity私有的sharedpreferences配置
	 * @param mode	-	0-清除；1-重置；
	 */
	private void setSharedPreferencesOfActivity(){
		switch (mSetMode) {
		case 0:
			mSetBtn.setText(getResources().getString(R.string.shared_preference_add));
			mCurTV.setText(getResources().getString(R.string.shared_preference_no_settings));
			break;
		case 1:
			mSetBtn.setText(getResources().getString(R.string.shared_preference_clear));
			setSaveShowData();
			break;
		}
	}
	/**
	 * 返回CheckBox的状态
	 * @param cb	-	要获取状态的CheckBox
	 * @return		-	选中为1；没有选中为0；
	 */
	private String getCBState(CheckBox cb){
		return cb.isChecked() ? "1" : "0";
	}
	/**
	 * 设置CheckBox的状态
	 * @param state	-	从配置文件中读取的状态
	 * @return	-	true为选中；false为没有选中；
	 */
	private boolean getCBState(String state){
		return state.equals("1") ? true : false;
	}
	/**
	 * checkbox选中状态改变监听事件
	 */
	private OnCheckedChangeListener changeListener = new OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			setSaveShowData();
		}
	};
	/**
	 * 设置、保存并显示配置
	 */
	private void setSaveShowData(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("开关1", getCBState(mFirstCB));
		map.put("开关2", getCBState(mSecondCB));
		map.put("开关3", getCBState(mThirdCB));
		SharedPreferenceHelper.savePres(activity, map, Context.MODE_PRIVATE);
		mCurTV.setText(SharedPreferenceHelper.getPres(activity, Context.MODE_PRIVATE).toString());
	}
}
