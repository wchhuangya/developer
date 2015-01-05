/**
 * Copyright @2015 Obsessed - Studio gsww All rights reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何* 渠道使用、修改源代码.
 * 日期 2015-1-2 下午6:15:14
 */
package com.wchhuangya.developer.listview;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * 演示ListView隐藏分隔线的用法
 * @company WSuperman
 * @project developer
 * @author wchhuangya
 * @date 2015-1-2 下午6:15:14	
 * @class com.wchhuangya.developer.listview.ListviewSeperatorActivity
 */
public class ListviewSeparatorsActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setListAdapter(new MyAdapter(this));
	}
	
	private class MyAdapter extends BaseAdapter{
		private Context mContext;
		
		public MyAdapter(Context context){
			mContext = context;
		}
		/**
		 * 标识访适配器的所有元素是否都是可用的。如果该方法返回的值随着时间的推移会发生变化，那就不能保证该方法会起作用。
		 * 如果该方法返回true，那就意味着适配器中所有的元素都是可选择和可点击的（没有分隔线）。
		 * @see android.widget.BaseAdapter#areAllItemsEnabled()
		 * @return
		 */
		@Override
		public boolean areAllItemsEnabled() {
			return false;
		}
		/**
		 * 如果指定位置的元素不是分隔线，该方法返回true。（分隔线是不能点击、不能选择的元素）。如果位置不可用，那么结果是不确定的。
		 * 在彻底失效的情况下会抛出ArrayIndexOutOfBoundsException的异常。
		 * @see android.widget.BaseAdapter#isEnabled(int)
		 * @param position 元素位置
		 * @return
		 */
		@Override
		public boolean isEnabled(int position) {
			return !mStrings[position].startsWith("-");
		}

		@Override
		public int getCount() {
			return mStrings.length;
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return (long)position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView tv = null;
			if(convertView == null){
				tv = (TextView) View.inflate
						(mContext, android.R.layout.simple_expandable_list_item_1, null);
			} else {
				tv = (TextView)convertView;
			}
			tv.setText(mStrings[position]);
			return tv;
		}
	}
    /** 用于显示数据的数组 */
    private String[] mStrings = {
            "----------",
            "----------",
            "Abbaye de Belloc",
            "Abbaye du Mont des Cats",
            "Abertam",
            "----------",
            "Abondance",
            "----------",
            "Ackawi",
            "Acorn",
            "Adelost",
            "Affidelice au Chablis",
            "Afuega'l Pitu",
            "Airag",
            "----------",
            "Airedale",
            "Aisy Cendre",
            "----------",
            "Allgauer Emmentaler",
            "Alverca",
            "Ambert",
            "American Cheese",
            "Ami du Chambertin",
            "----------",
            "----------",
            "Anejo Enchilado",
            "Anneau du Vic-Bilh",
            "Anthoriro",
            "----------",
            "----------"
    };
}
