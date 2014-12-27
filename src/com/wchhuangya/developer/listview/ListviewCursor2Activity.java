/**
 * Copyright @2014 Obsessed - Studio gsww All rights reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何* 渠道使用、修改源代码.
 * 日期 2014-12-27 上午9:17:08
 */
package com.wchhuangya.developer.listview;

import com.wchhuangya.developer.util.LogHelper;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

/**
 * 演示列表数据来源于数组的ListView，使用SimpleAdapter
 * 
 * @company WSuperman
 * @project developer
 * @author wchhuangya
 * @date 2014-12-27 上午9:17:08	
 * @class com.wchhuangya.developer.listview.ListviewCursor2Activity
 *
 */
@SuppressWarnings("deprecation")
public class ListviewCursor2Activity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//获取指向所有电话的游标
		Cursor c = getContentResolver().query(Phone.CONTENT_URI, PHONE_PROJECTION, 
				null, null, null);
		startManagingCursor(c);
		
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, 
				android.R.layout.simple_list_item_2, c, 
				new String[]{Phone.TYPE, Phone.NUMBER}, 
				new int[]{android.R.id.text1, android.R.id.text2});
        //用于将数据绑定到视图的绑定器.设置为 null，可以删除既存的绑定器.
        adapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
        	//注意：使用setViewValue中的View获取ID时，获取的是在适配器（Adapter）中TO数组里指定的ID
            public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                for(int i = 0; i < cursor.getColumnCount(); i++)
                	LogHelper.printDebugLog(cursor.getString(i) + "|||" + i);
                LogHelper.printDebugLog("===========================");
                //如果该列数据不是一个TYPE，让适配器自己处理这次数据绑定
                if (columnIndex != COLUMN_TYPE) {
                    return false;
                }
                int type = cursor.getInt(COLUMN_TYPE);
                String label = null;
                //如果是自定义类型，那么获取自定义的标签
                if (type == Phone.TYPE_CUSTOM) {
                    label = cursor.getString(COLUMN_LABEL);
                }
                //获取可读的字符串
                String text = (String) Phone.getTypeLabel(getResources(), type, label);
                //设置文本
                ((TextView) view).setText(text);
                return true;
            }
        });
		setListAdapter(adapter);
	}
	/** 要查询的字段 */
    private static final String[] PHONE_PROJECTION = new String[] {
        Phone._ID,
        Phone.TYPE,
        Phone.LABEL,
        Phone.NUMBER
    };
    /**  */
    private static final int COLUMN_TYPE = 1;;
    /**  */
    private static final int COLUMN_LABEL = 2;
}
