/**
 * Copyright @2014 Obsessed - Studio gsww All rights reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何* 渠道使用、修改源代码.
 * 日期 2014-12-23 下午4:53:57
 */
package com.wchhuangya.developer.listview;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

/**
 * 演示列表数据来源于数组的ListView
 * @company gsww
 * @project developer
 * @author wchhuangya
 * @date 2014-12-23 下午4:53:57	
 * @class com.wchhuangya.developer.listview.ListviewArrayActivity
 *
 */
public class ListviewArrayActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// 使用一个现成的ListAdapter，该适配器把数组里的字符串匹配到TextView上
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data));
		getListView().setTextFilterEnabled(true);
	}
	
	public static final String[] data = {"a第一项", "b第二项", "c第三项", "d第四项", "e第五项", "f第六项", 
		"g第七项", "h第八项", "i第九项", "j第十项", "k第十一项", "l第十二项", "m第十三项", "n第十四项"};
}
