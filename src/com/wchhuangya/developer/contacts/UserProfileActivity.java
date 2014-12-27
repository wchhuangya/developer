/**
 * Copyright @2014 Obsessed - Studio gsww All rights reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何* 渠道使用、修改源代码.
 * 日期 2014-11-30 下午9:07:13
 */
package com.wchhuangya.developer.contacts;

import android.R.integer;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract.Profile;
import android.text.Html;
import android.widget.TextView;
import android.widget.Toast;

import com.wchhuangya.developer.R;
import com.wchhuangya.developer.core.BaseActivity;

/**
 * 联系人表中的用户配置
 * ContactsContract.Contacts 表里仅仅只有一行包含了设备用户的配置数据。这一行的数据描述了设备的用户而不是用户的某个联系人。
 * @company WSuperman
 * @project developer
 * @author wchhuangya
 * @date 2014-11-30 下午9:07:13	
 * @class com.wchhuangya.developer.contacts.UserProfileActivity
 *
 */
@SuppressLint("NewApi")
public class UserProfileActivity extends BaseActivity {
	/** 用户配置的数据库游标 */
	private Cursor mProfileCursor;
	/** 显示信息的文本控件 */
	private TextView mShowTV;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_profile);
		
		activity = this;
		mShowTV = (TextView)findViewById(R.id.user_profile_tv);
		
		new getUserProfileAsync().execute("");
	}
	
	private class getUserProfileAsync extends AsyncTask<String, integer, Boolean>{

		@Override
		protected void onPreExecute() {
			progressDialog = ProgressDialog.show(activity, "", "正在加载数据,请稍候...", true, false);
		}

		@Override
		protected Boolean doInBackground(String... params) {
			try {
				String[] mProjection = new String[]{
						Profile._ID,
						Profile.DISPLAY_NAME_PRIMARY,
						Profile.LOOKUP_KEY,
						Profile.PHOTO_THUMBNAIL_URI
				};
				mProfileCursor = getContentResolver().query(Profile.CONTENT_URI, mProjection
						, null, null, null);
			} catch(Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			try {
				if(result){
					if(mProfileCursor != null){
						while (mProfileCursor.moveToNext()) {
							int id = mProfileCursor.getInt(mProfileCursor.getColumnIndex(Profile._ID));
							String name = mProfileCursor.getString(
									mProfileCursor.getColumnIndex(Profile.DISPLAY_NAME_PRIMARY));
							String lookUp = mProfileCursor.getString(
									mProfileCursor.getColumnIndex(Profile.LOOKUP_KEY));
							String uri = mProfileCursor.getString(
									mProfileCursor.getColumnIndex(Profile.PHOTO_THUMBNAIL_URI));
							mShowTV.setText(Html.fromHtml(id + "<br/>" + name + "<br/>" 
									+ lookUp + "<br/>" + uri));
						}
					} else {
						Toast.makeText(activity, "获取用户配置失败!", Toast.LENGTH_LONG).show();
					}
				} else {
					Toast.makeText(activity, "获取用户配置失败!", Toast.LENGTH_LONG).show();
				}
			} catch (Exception e) {
				e.printStackTrace();
				Toast.makeText(activity, "获取用户配置失败!", Toast.LENGTH_LONG).show();
			} finally {
				if(progressDialog != null)
					progressDialog.dismiss();
			}
		}
	}
}
