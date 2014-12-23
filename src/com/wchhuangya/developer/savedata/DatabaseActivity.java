/**
 * Copyright @2014 Obsessed - Studio gsww All rights reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何* 渠道使用、修改源代码.
 * 日期 2014-12-11 上午9:24:34
 */
package com.wchhuangya.developer.savedata;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.wchhuangya.developer.R;
import com.wchhuangya.developer.component.crouton.Style;
import com.wchhuangya.developer.core.BaseActivity;
import com.wchhuangya.developer.util.TelephoneHelper;

/**
 * 数据库存储演示类
 * @company gsww
 * @project developer
 * @author wchhuangya
 * @date 2014-12-11 上午9:24:34	
 * @class com.wchhuangya.developer.savedata.DatabaseActivity
 *
 */
public class DatabaseActivity extends BaseActivity {
	/** 显示当前设备号的TextView */
	private TextView mDeviceIdTV;
	/** 选择职业的EditText */
	private EditText mPostET;
	/** 选择年龄的EditText */
	private EditText mAgeET;
	/** 选择最有用模块的EditText */
	private EditText mUsefulET;
	/** 提交按钮 */
	private Button mSubmitBtn;
	/** 行业选择框 */
	private Spinner mPostSpr;
	/** 年龄段选择框 */
	private Spinner mAgeSpr;

	/** 存储行业分类的数组 */
	private String[] mPostArr;
	/** 记录当前选择的行业 */
	private String mPostCur;
	/** 存储不同年龄段的数组 */
	private String[] mAgeArr;
	/** 记录当前选择的年龄段 */
	private String mAgeCur;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.database);
		
		activity = this;
		init();
	}
	
	private void init(){
		mDeviceIdTV = (TextView)findViewById(R.id.database_device_id_tv);
		mPostET = (EditText)findViewById(R.id.database_post_et);
		mAgeET = (EditText)findViewById(R.id.database_age_et);
		mUsefulET = (EditText)findViewById(R.id.database_useful_et);
		mSubmitBtn = (Button)findViewById(R.id.database_submit_btn);
		mPostSpr = (Spinner)findViewById(R.id.database_post_spr);
		mAgeSpr = (Spinner)findViewById(R.id.database_age_spr);
		
		mDeviceIdTV.setText(TelephoneHelper.getDeviceNumber());
	    mPostArr = getResources().getStringArray(R.array.database_post_array);
	    mAgeArr = getResources().getStringArray(R.array.database_age_array);
		
	    mSubmitBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String post = mPostET.getText().toString().trim();
				String age = mAgeET.getText().toString().trim();
				String use = mUsefulET.getText().toString().trim();
				if(TextUtils.isEmpty(post) || 
						getResources().getString(R.string.database_post_default).equals(post))
					showCrouton(activity, "请选择行业!", Style.ALERT, R.id.database_post_fl);
				else if(TextUtils.isEmpty(age) || 
						getResources().getString(R.string.database_age_default).equals(age))
					showCrouton(activity, "请选择年龄段!", Style.ALERT, R.id.database_age_fl);
				else if(TextUtils.isEmpty(use))
					showCrouton(activity, "请填写最有用功能!", Style.ALERT, R.id.database_useful_fl);
			}
		});

	    ArrayAdapter<String> postAdapter = new ArrayAdapter<String>(activity, 
	    		android.R.layout.simple_spinner_item, mPostArr);
	    postAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    mPostSpr.setAdapter(postAdapter);
	    mPostSpr.setSelection(0, true);
	    mPostSpr.setOnItemSelectedListener(mPostListener);
	    mPostCur = getResources().getString(R.string.database_post_default);
	    mPostET.setText(mPostCur);
	    
	    ArrayAdapter<String> ageAdapter = new ArrayAdapter<String>(activity, 
	    		android.R.layout.simple_spinner_item, mAgeArr);
	    ageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    mAgeSpr.setAdapter(ageAdapter);
	    mAgeSpr.setSelection(0, true);
	    mAgeSpr.setOnItemSelectedListener(mAgeListener);
	    mAgeCur = getResources().getString(R.string.database_age_default);
	    mAgeET.setText(mAgeCur);
	}
	
	public void forClick(View view){
		switch (view.getId()) {
		case R.id.database_post_et:
		case R.id.database_post_iv:
			mPostSpr.performClick();
			break;
		case R.id.database_age_et:
		case R.id.database_age_iv:
			mAgeSpr.performClick();
			break;
		}
	}
	
	private OnItemSelectedListener mPostListener = new OnItemSelectedListener() {
		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			mPostCur = (String) parent.getItemAtPosition(position);
			mPostET.setText(mPostCur);
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
		}
	};
	
	private OnItemSelectedListener mAgeListener = new OnItemSelectedListener() {
		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			mAgeCur = (String) parent.getItemAtPosition(position);
			mAgeET.setText(mAgeCur);
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			Toast.makeText(activity, "sdfsdf", Toast.LENGTH_LONG).show();
		}
	};
}
