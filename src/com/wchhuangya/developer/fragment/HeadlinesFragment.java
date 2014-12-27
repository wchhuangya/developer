/**
 * Copyright @2014 Obsessed - Studio gsww All rights reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何* 渠道使用、修改源代码.
 * 日期 2014-8-23 下午6:52:39
 */
package com.wchhuangya.developer.fragment;

import com.wchhuangya.developer.R;
import com.wchhuangya.developer.common.Constants;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * <p>
 * 官方Training里Fragment的例子--显示标题的fragment
 * </p>
 * @company WSuperman
 * @project developer
 * @author wchhuangya
 * @date 2014-8-23 下午6:52:39	
 * @class com.wchhuangya.developer.fragment.HeadlinesFragment
 *
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class HeadlinesFragment extends ListFragment {
	OnHeadlineSelectedListener mCallback;

	//宿主activity必须实现这个接口，这样的话，fragment就可以把消息发送给宿主activity了
	public interface OnHeadlineSelectedListener{
		/** 当一个列表元素被点击时，HeadlinesFragment就会调用这个方法 */
		public void onArticleSelected(int position);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Android版本在3.0以上或以下，采用的布局文件不同
		int layout = Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB ? 
				android.R.layout.simple_list_item_activated_1 : android.R.layout.simple_list_item_1;
		//使用预定义的数组为列表创建一个数组适配器
		setListAdapter(new ArrayAdapter<String>(getActivity(), layout, Constants.Headlines));
	}
	
	@Override
	public void onStart() {
		super.onStart();
		
		//如果当前是两个面板显示时，把ListView设置为高亮显示被选择的元素
		//在onStart方法里做这些是因为只有在这个点上，ListView才是可见的
		if(getFragmentManager().findFragmentById(R.id.article_fragment) != null)
			getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
		//确保宿主activity已经实现了接口，如果没有，抛出异常
		try {
			mCallback = (OnHeadlineSelectedListener)activity;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ClassCastException(activity.toString() + "必须实现OnHeadlineSelectedListener接口!");
		}
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
	}
}
