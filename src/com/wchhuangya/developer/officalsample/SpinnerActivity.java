/**
 * Copyright @2014 Obsessed - Studio gsww All rights reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何* 渠道使用、修改源代码.
 * 日期 2014-12-16 下午3:52:55
 */
package com.wchhuangya.developer.officalsample;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.wchhuangya.developer.R;
import com.wchhuangya.developer.common.Constants;
import com.wchhuangya.developer.core.BaseActivity;
import com.wchhuangya.developer.util.LogHelper;
import com.wchhuangya.developer.util.SharedPreferenceHelper;

/**
 * spinner用法演示
 * @company gsww
 * @project developer
 * @author wchhuangya
 * @date 2014-12-16 下午3:52:55	
 * @class com.wchhuangya.developer.officalsample.SpinnerActivity
 *
 */
public class SpinnerActivity extends BaseActivity {
	/** spinner使用的适配器 */
	private ArrayAdapter<CharSequence> mAdapter;
	/** spinner当前显示元素的位置 */
	private int mPos;
	/** spinner当前显示元素的内容 */
	private String mSelection;
    /** 应用首次安装后spinner的默认显示位置 */
	private static final int DEFAULT_POSITION = 2;
    /** 存储位置时使用的key值 */
    private static final String POS_KEY = "pos";
    /** 存储选项时使用的key值 */
    private static final String SELECTION_KEY = "selection";

	/**
	 * 初始化Activity
	 * 1. 设置Activity的布局
	 * 2. 从字符资源文件里读取spinner要显示的内容
	 * 3. 实例化一个回调监听来处理spinner的选择事件
	 * 注意：onCreate()方法里包含的注释代码用于测试调用spinner失败的情况。
	 * @see com.wchhuangya.developer.core.BaseActivity#onCreate(android.os.Bundle)
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.spinner);
		
		activity = this;
		Spinner spinner = (Spinner)findViewById(R.id.spinner_spr);
		
		// 为spinner创建一个用于显示行星列表的适配器。列表显示的值在字符文件里定义的，是一个数组。
		mAdapter = ArrayAdapter.createFromResource(activity, R.array.spinner_plants, 
				android.R.layout.simple_spinner_item);
		//把适配器绑定到spinner上
		spinner.setAdapter(mAdapter);
		//创建一个监听器，当用户选择spinner中的某项时触发
		OnItemSelectedListener listener = new myListener(activity, mAdapter);
		spinner.setOnItemSelectedListener(listener);
		
		/*
		 * 去掉下一行的注释，就可以做spinner的先决条件测试。
		 * 因为spinner的选择监听没有实现，因此，该测试将失败。
         */
         // spinner.setOnItemSelectedListener(null);
	}
	/**
	 * 实现了OnItemSelectedListener接口的回调监听类。
	 * @company gsww
	 * @project developer
	 * @author wchhuangya
	 * @date 2014-12-17 下午3:47:43	
	 * @class com.wchhuangya.developer.officalsample.myListener
	 */
	@SuppressWarnings("unused")
	private class myListener implements OnItemSelectedListener{
		
		ArrayAdapter<CharSequence> mLocalAdapter;
        Activity mLocalContext;

        /**
         *  构造器。用于实例化一个监听对象。
         *  @param c - 显示spinner的activity
         *  @param ad - 控制spinner的适配器
         */
        public myListener(Activity c, ArrayAdapter<CharSequence> ad) {
        	this.mLocalContext = c;
        	this.mLocalAdapter = ad;
        }

        /**
         * 当用户选择spinner的某项时，该方法被回调链调用。
         * @param parent - 监听所绑定的AdapterView
         * @param view - mLocalAdapter里被选项的位置，从0开始
         * @param position - View里被选项的索引，从0开始
         * @param id
         */
		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			SpinnerActivity.this.mPos = position;
			SpinnerActivity.this.mSelection = parent.getItemAtPosition(position).toString();
            TextView resultText = (TextView)findViewById(R.id.spinner_result_tv);
            resultText.setText(SpinnerActivity.this.mSelection);
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			//该方法啥都不做
		}
	}
	/**
	 * 用于恢复当前spinner的状态（存储选择了哪个元素和被选择元素的值）
	 * 因为当activity启动时-即使是activity隐藏后重新显示-onResume()方法总会被调用，因此，该方法是最佳存储状态的方法。
	 * 先从一个preferences文件里尝试读取状态。如果没有读取到，就假设该应用初次安装，就做一个实例化的操作。
	 * 无论如何，都会让spinner以原来的位置显示。
	 * @see com.wchhuangya.developer.core.BaseActivity#onResume()
	 */
	@Override
	protected void onResume() {
		//必须首先调用父类的实现
		super.onResume();
		
		//尝试从preferences文件中读取数据。如果找不到文件，把状态设置为指定的初始值
		if(!readStoredState())
			initDefaultPos();
		
		//把spinner设置为当前值
		Spinner spinner = (Spinner)findViewById(R.id.spinner_spr);
		spinner.setSelection(mPos);
	}
	/**
	 * 存储spinner的当前状态（也就是记录哪个选项被选中和被选中选项的值）。
	 * 因为当activity即将隐藏时-即使是activity即将被销毁-onPause()方法总会被调用，因此，该方法是存储数据的最佳方法。
     * 尝试把状态写入preferences文件中。写入操作如果失败，会通知用户。
	 * @see com.wchhuangya.developer.core.BaseActivity#onPause()
	 */
	@Override
	protected void onPause() {
		//必须首先调用父类的实现
		super.onPause();
		
		if(!storeState())
			Toast.makeText(this, "spinner状态定稿失败!", Toast.LENGTH_LONG).show();
	}
	/**
	 * 设置spinner的默认位置
	 */
	private void initDefaultPos(){
		mPos = DEFAULT_POSITION;
	}
	/**
	 * 从sharedpreferences文件中读取spinner上次的位置和值
	 * @return - 如果原来没有存储过spinner的状态，返回false；如果存储过，给类变量赋值并返回true
	 */
	private boolean readStoredState(){
		
		/*
		 * 偏好存储于SharedPreferences文件中，抽象类SharedPreferences的实现是一个包含了哈希map的文件。
		 * 应用的所有实例都共享着该类文件的相同实例，这就意味着应用的所有实例都会共享同样的偏好设置
         */

        //获取应用的SharedPreferences对象
		SharedPreferences sp = SharedPreferenceHelper.getSP(Constants.SPINNER_FILE_NAME, 
				Context.MODE_PRIVATE);
		
		mPos = sp.getInt(POS_KEY, DEFAULT_POSITION);
		mSelection = sp.getString(SELECTION_KEY, "");
		
		return sp.contains(POS_KEY);
	}
	/**
	 * 存储spinner的状态
	 * @return - 存储成功，返回true；存储失败，返回false
	 */
	private boolean storeState(){
		try {
			SharedPreferences sp = SharedPreferenceHelper.getSP(Constants.SPINNER_FILE_NAME, 
					Context.MODE_PRIVATE);
			SharedPreferences.Editor editor = sp.edit();
			editor.putInt(POS_KEY, mPos);
			editor.putString(SELECTION_KEY, mSelection);
			return editor.commit();
		} catch (Exception e) {
			LogHelper.printDebugLog(e.getMessage());
		}
		return false;
	}
}
