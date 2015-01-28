/**
 * Copyright @2014 Obsessed - Studio gsww All rights reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何* 渠道使用、修改源代码.
 * 日期 2014-8-22 下午9:06:58
 */
package com.wchhuangya.developer;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.wchhuangya.developer.alarm.AlarmAddActivity;
import com.wchhuangya.developer.animator.CrossfadingActivity;
import com.wchhuangya.developer.animator.PosAlphaActivity;
import com.wchhuangya.developer.contacts.UserProfileActivity;
import com.wchhuangya.developer.core.BaseActivity;
import com.wchhuangya.developer.fragment.TrainingFragment;
import com.wchhuangya.developer.listview.ListviewArrayActivity;
import com.wchhuangya.developer.listview.ListviewCursor2Activity;
import com.wchhuangya.developer.listview.ListviewCursorActivity;
import com.wchhuangya.developer.listview.ListviewCustomActivity;
import com.wchhuangya.developer.listview.ListviewExpandActivity;
import com.wchhuangya.developer.listview.ListviewSeparatorsActivity;
import com.wchhuangya.developer.officalsample.SpinnerActivity;
import com.wchhuangya.developer.savedata.DatabaseActivity;
import com.wchhuangya.developer.savedata.FileSaveActivity;
import com.wchhuangya.developer.savedata.SharedPreferencesActivity;
import com.wchhuangya.developer.test.TestActivityManagerActivity;
import com.wchhuangya.developer.umeng.FeedbackActivity;

/**
 * 程序入口
 * @company gsww
 * @project developer
 * @author wchhuangya
 * @date 2014-8-22 下午9:06:58	
 * @class com.wchhuangya.developer.MainActivity
 *
 */
public class MainActivity extends BaseActivity {
	/** 当前列表显示的数据 */
	private List<Map<String, Object>> mCurList;
	/** 所有列表数据的集合 */
	private Map<String, List<Map<String, Object>>> mAllMap = new LinkedHashMap<String, List<Map<String,Object>>>();
	/** 展示数据的列表 */
	private ListView mListView;
	/** 列表使用的适配器 */
	private SimpleAdapter mAdapter;
	/** 后退堆栈 */
	private Stack<String> mBackStack = new Stack<String>();
	/** 标识当前是否正在退出 */
	private boolean mIsExit = false;
	/** 标识当前是否有退出的任务 */
	private boolean mIsHasTask = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		init();
	}
	/**
	 * 初始化界面的方法
	 */
	private void init(){
		getData();
		
		mBackStack.push("root");
		mCurList = getCurList();
		
		mListView = (ListView)findViewById(android.R.id.list);
		fillList();
	}
	/**
	 * 初始化ListView的方法
	 */
	private void fillList(){
		mAdapter = new SimpleAdapter(this, mCurList, R.layout.main_list_item, 
				new String[]{"title"}, new int[]{R.id.main_list_item_title});
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {
			@SuppressWarnings("rawtypes")
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Map<String, Object> map = mCurList.get(position);
				if(Boolean.valueOf(map.get("hasChild").toString())){//有子列表
					String sign = map.get("activity").toString();
					mCurList = mAllMap.get(sign);
					mBackStack.push(sign);
					fillList();
				} else {//没有子列表
					if(map.get("activity") == null){
						Toast.makeText(MainActivity.this, "功能正在建设中,敬请期待...", Toast.LENGTH_LONG).show();
					} else {
						intent = new Intent(MainActivity.this, (Class) map.get("activity"));
						startActivity(intent);
					}
				}
			}
		});
	}
	/**
	 * 获取当前显示内容的List的方法
	 * @return
	 */
	private List<Map<String, Object>> getCurList(){
		return mAllMap.get(mBackStack.peek());
	}
	/**
	 * 向完整的List里添加数据
	 */
	private void getData(){
		getRootData("root");
		getPushData("push");
		getTrainingData("training");
		getSaveData("savedata");
		getAPIGuidesData("guides");
		getGuidesContactsData("guidescontacts");
		getOfficalSampleData("sample");
		getListviewData("listview");
		getTestData("test");
	}
	
	@Override
	public void onBackPressed() {
		if(mBackStack.size() > 1){//如果当前的后退堆栈有数据，弹出栈顶后显示新栈顶的数据
			mBackStack.pop();
			mCurList = getCurList();
			fillList();
		} else {//按两次后退键退出应用
			if(mIsHasTask){
    			quit();
    		} else {
    			if(!mIsExit){
    				mIsExit = true;
    				mIsHasTask = true;
    				Toast.makeText(MainActivity.this, "再按一次后退键退出!", Toast.LENGTH_LONG).show();
    				new Handler().postDelayed(new Runnable() {
						public void run() {
							mIsExit = false;
							mIsHasTask = false;
						}
					}, 2000);
    			}
    		}
		}
	}
	/**
	 * 获取根目录的数据
	 * @param sign - 要保存数据的map的键
	 */
	private void getRootData(String sign){
		List<Map<String,Object>> singleList = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		
		map.put("title", getResources().getString(R.string.offical_training));
		map.put("activity", "training");
		map.put("hasChild", true);
		singleList.add(map);
		
		map = new LinkedHashMap<String, Object>();
		map.put("title", getResources().getString(R.string.msg_push));
		map.put("activity", "push");
		map.put("hasChild", true);
		singleList.add(map);
		
		map = new LinkedHashMap<String, Object>();
		map.put("title", getResources().getString(R.string.api_guides));
		map.put("activity", "guides");
		map.put("hasChild", true);
		singleList.add(map);

		map = new LinkedHashMap<String, Object>();
		map.put("title", getResources().getString(R.string.offical_sample));
		map.put("activity", "sample");
		map.put("hasChild", true);
		singleList.add(map);

		map = new LinkedHashMap<String, Object>();
		map.put("title", getResources().getString(R.string.test));
		map.put("activity", "test");
		map.put("hasChild", true);
		singleList.add(map);
		
		map = new LinkedHashMap<String, Object>();
		map.put("title", getResources().getString(R.string.feed_back));
		map.put("activity", FeedbackActivity.class);
		map.put("hasChild", false);
		singleList.add(map);
		
		mAllMap.put(sign, singleList);
	}
	/**
	 * 获取官方Training的数据
	 * @param sign - 要保存数据的map的键
	 */
	private void getTrainingData(String sign){
		List<Map<String,Object>> singleList = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		
		map.put("title", getResources().getString(R.string.training_fragment));
		map.put("activity", TrainingFragment.class);
		map.put("hasChild", false);
		singleList.add(map);
		
		map = new LinkedHashMap<String, Object>();		
		map.put("title", getResources().getString(R.string.animation_cross_fading));
		map.put("activity", CrossfadingActivity.class);
		map.put("hasChild", false);
		singleList.add(map);
		
		map = new LinkedHashMap<String, Object>();		
		map.put("title", getResources().getString(R.string.training_save_data));
		map.put("activity", "savedata");
		map.put("hasChild", true);
		singleList.add(map);
		
		mAllMap.put(sign, singleList);
	}
	/**
	 * 获取官方Training的数据
	 * @param sign - 要保存数据的map的键
	 */
	private void getSaveData(String sign){
		List<Map<String,Object>> singleList = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		
		map.put("title", getResources().getString(R.string.training_shared_preference));
		map.put("activity", SharedPreferencesActivity.class);
		map.put("hasChild", false);
		singleList.add(map);
		
		map = new LinkedHashMap<String, Object>();
		map.put("title", getResources().getString(R.string.training_file_save));
		map.put("activity", FileSaveActivity.class);
		map.put("hasChild", false);
		singleList.add(map);
		
		map = new LinkedHashMap<String, Object>();
		map.put("title", getResources().getString(R.string.training_database));
		map.put("activity", DatabaseActivity.class);
		map.put("hasChild", false);
		singleList.add(map);
		
		mAllMap.put(sign, singleList);
	}
	/**
	 * 获取消息推送的数据
	 * @param sign - 要保存数据的map的键
	 */
	private void getPushData(String sign){
		List<Map<String,Object>> singleList = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		
		map.put("title", getResources().getString(R.string.push_jig));
		map.put("activity", null);
		map.put("hasChild", false);
		singleList.add(map);
		
		map = new LinkedHashMap<String, Object>();
		map.put("title", getResources().getString(R.string.push_xinge));
		map.put("activity", null);
		map.put("hasChild", false);
		singleList.add(map);
		
		mAllMap.put(sign, singleList);
	}
	/**
	 * 获官方API Guide的数据
	 * @param sign - 要保存数据的map的键
	 */
	private void getAPIGuidesData(String sign){
		List<Map<String,Object>> singleList = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		
		map.put("title", getResources().getString(R.string.guides_alarm_set));
		map.put("activity", AlarmAddActivity.class);
		map.put("hasChild", false);
		singleList.add(map);
		
		map = new LinkedHashMap<String, Object>();		
		map.put("title", getResources().getString(R.string.guides_animator_pos_alpha));
		map.put("activity", PosAlphaActivity.class);
		map.put("hasChild", false);
		singleList.add(map);
		
		map = new LinkedHashMap<String, Object>();		
		map.put("title", getResources().getString(R.string.guides_contacts));
		map.put("activity", "guidescontacts");
		map.put("hasChild", true);
		singleList.add(map);
		
		mAllMap.put(sign, singleList);
	}
	/**
	 * 获官方API Guide中联系人的数据
	 * @param sign - 要保存数据的map的键
	 */
	private void getGuidesContactsData(String sign){
		List<Map<String,Object>> singleList = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		
		map.put("title", getResources().getString(R.string.guides_user_profile));
		map.put("activity", UserProfileActivity.class);
		map.put("hasChild", false);
		singleList.add(map);
		
		mAllMap.put(sign, singleList);
	}
	/**
	 * 获官方API Guide中联系人的数据
	 * @param sign - 要保存数据的map的键
	 */
	private void getOfficalSampleData(String sign){
		List<Map<String,Object>> singleList = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		
		map.put("title", getResources().getString(R.string.sample_spinner));
		map.put("activity", SpinnerActivity.class);
		map.put("hasChild", false);
		singleList.add(map);
		
		map = new LinkedHashMap<String, Object>();
		map.put("title", getResources().getString(R.string.sample_listview));
		map.put("activity", "listview");
		map.put("hasChild", true);
		singleList.add(map);
		
		mAllMap.put(sign, singleList);
	}
	/**
	 * 获官方API Guide中联系人的数据
	 * @param sign - 要保存数据的map的键
	 */
	private void getListviewData(String sign){
		List<Map<String,Object>> singleList = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		
		map.put("title", getResources().getString(R.string.listview_array));
		map.put("activity", ListviewArrayActivity.class);
		map.put("hasChild", false);
		singleList.add(map);
		
		map = new LinkedHashMap<String, Object>();
		map.put("title", getResources().getString(R.string.listview_cursor));
		map.put("activity", ListviewCursorActivity.class);
		map.put("hasChild", false);
		singleList.add(map);
		
		map = new LinkedHashMap<String, Object>();
		map.put("title", getResources().getString(R.string.listview_cursor2));
		map.put("activity", ListviewCursor2Activity.class);
		map.put("hasChild", false);
		singleList.add(map);
		
		map = new LinkedHashMap<String, Object>();
		map.put("title", getResources().getString(R.string.listview_custom));
		map.put("activity", ListviewCustomActivity.class);
		map.put("hasChild", false);
		singleList.add(map);
		
		map = new LinkedHashMap<String, Object>();
		map.put("title", getResources().getString(R.string.listview_separators));
		map.put("activity", ListviewSeparatorsActivity.class);
		map.put("hasChild", false);
		singleList.add(map);
		
		map = new LinkedHashMap<String, Object>();
		map.put("title", getResources().getString(R.string.listview_expandable));
		map.put("activity", ListviewExpandActivity.class);
		map.put("hasChild", false);
		singleList.add(map);
		
		mAllMap.put(sign, singleList);
	}
	/**
	 * 获取测试数据
	 * @param sign - 要保存数据的map的键
	 */
	private void getTestData(String sign){
		List<Map<String,Object>> singleList = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		
		map.put("title", getResources().getString(R.string.test_activitymanager));
		map.put("activity", TestActivityManagerActivity.class);
		map.put("hasChild", false);
		singleList.add(map);
		
		mAllMap.put(sign, singleList);
	}
}
