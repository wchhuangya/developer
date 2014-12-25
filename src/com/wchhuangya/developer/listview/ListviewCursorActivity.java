/**
 * Copyright @2014 Obsessed - Studio gsww All rights reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何* 渠道使用、修改源代码.
 * 日期 2014-12-24 下午2:06:16
 */
package com.wchhuangya.developer.listview;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;

/**
 * 演示列表数据来源于数组的ListView
 * @company gsww
 * @project developer
 * @author wchhuangya
 * @date 2014-12-24 下午2:06:16	
 * @class com.wchhuangya.developer.listview.ListviewCursorActivity
 *
 */
public class ListviewCursorActivity extends ListActivity {

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//获取指向所有人员的数据库游标
		Cursor cursor = getContentResolver().query(Contacts.CONTENT_URI, 
				PROJECTION, null, null, null);
		
		ListAdapter adapter = new SimpleCursorAdapter(this,
                // 使用显示一个TextView的布局模板
                android.R.layout.simple_list_item_1,
                // 把游标塞给数据库
                cursor,
                // 与查询结果里的名称列做匹配
                new String[] {Contacts.DISPLAY_NAME},
                // 用于显示名称的布局模板里的TextView的ID
                new int[] {android.R.id.text1});
        setListAdapter(adapter);
	}
	
	private static final String[] PROJECTION = new String[]{
		Contacts._ID,
		Contacts.DISPLAY_NAME
	};
}
