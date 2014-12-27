/**
 * Copyright @2014 Obsessed - Studio gsww All rights reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何* 渠道使用、修改源代码.
 * 日期 2014-12-4 下午4:54:26
 */
package com.wchhuangya.developer.savedata;

import java.io.File;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wchhuangya.developer.R;
import com.wchhuangya.developer.core.BaseActivity;
import com.wchhuangya.developer.util.FileHelper;
import com.wchhuangya.developer.util.LogHelper;

/**
 * 文件保存操作类
 * @company WSuperman
 * @project developer
 * @author wchhuangya
 * @date 2014-12-4 下午4:54:26	
 * @class com.wchhuangya.developer.savedata.FileSaveActivity
 *
 */
public class FileSaveActivity extends BaseActivity {
	/** 显示内部存储-应用私有文件夹地址的TextView */
	private TextView mInternalDirTV;
	/** 显示内部存储-应用缓存文件夹地址的TextView */
	private TextView mCacheDirTV;
	/** 显示内部目录结果 */
	private TextView mInternalResTV;
	/** 显示缓存目录结果 */
	private TextView mCacheResTV;
	/** 显示外部存储-应用公用存储路径的TextView */
	private TextView mExternalPublicDirTV;
	/** 标识外部存储是否可读写 */
	private boolean isExternalStorageValiable;
	/** 显示外部公用下载目录结果 */
	private TextView mExternalPublicResTV;
	/** 显示外部存储-应用私有储路径的TextView */
	private TextView mExternalPrivateDirTV;
	/** 显示外部私有目录结果 */
	private TextView mExternalPrivateResTV;
	
	/** 公用外部下载目录路径 */
	private static final File EXTERNAL_PUBLIC_DOWNLOADS = 
			Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
	/** 应用外部私有目录路径 */
	private static File EXTERNAL_PRIVATE;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.file_save);
		
		activity = this;
		init();
	}
	
	private void init(){
		isExternalStorageValiable = FileHelper.isExternalStorageWritable();
		EXTERNAL_PRIVATE = getExternalFilesDir(null);
		
		mInternalDirTV = (TextView)findViewById(R.id.file_save_app_internal);
		mCacheDirTV = (TextView)findViewById(R.id.file_save_app_cache);
		mInternalResTV = (TextView)findViewById(R.id.file_save_internal_file_res_tv);
		mCacheResTV = (TextView)findViewById(R.id.file_save_cache_file_res_tv);
		mExternalPublicDirTV = (TextView)findViewById(R.id.file_save_external_public_tv);
		mExternalPublicResTV = (TextView)findViewById(R.id.file_save_external_public_res_tv);
		mExternalPrivateDirTV = (TextView)findViewById(R.id.file_save_external_private_tv);
		mExternalPrivateResTV = (TextView)findViewById(R.id.file_save_external_private_res_tv);
		
		mInternalDirTV.setText(getResources().getString(R.string.file_save_internal_dir) 
				+ getFilesDir().getPath());
		LogHelper.printDebugLog("应用内容存储的file目录：" + getFilesDir().getPath());
		mCacheDirTV.setText(getResources().getString(R.string.file_save_cache_dir) 
				+ getCacheDir().getPath());
		LogHelper.printDebugLog("应用内容存储的缓存目录：" + getCacheDir().getPath());
		if(isExternalStorageValiable){
			mExternalPublicDirTV.setText(getResources().getString(R.string.file_save_external_public_dir) 
					+ EXTERNAL_PUBLIC_DOWNLOADS.getPath());
			LogHelper.printDebugLog("外部存储上公用的下载目录：" + EXTERNAL_PUBLIC_DOWNLOADS.getPath());
			mExternalPrivateDirTV.setText(getResources().getString(R.string.file_save_external_private_dir) 
					+ EXTERNAL_PRIVATE.getPath());
			LogHelper.printDebugLog("应用在外部存储上的私有目录：" + EXTERNAL_PRIVATE.getPath());
		} else {
			mExternalPublicDirTV.setText(getResources().getString(R.string.file_save_external_unvaliable));
			mExternalPrivateDirTV.setText(getResources().getString(R.string.file_save_external_unvaliable));
		}
	}
	
	public void forClick(View view){
		switch (view.getId()) {
		case R.id.file_save_internal_list_btn://列出文件按钮
			File file = getFilesDir();
			String[] fileNames = file.list();
			if(fileNames == null)
				mInternalResTV.setText("内部目录获取失败!");
			else if(fileNames.length == 0)
				mInternalResTV.setText("内部目录下没有文件!");
			else {
				String res = "";
				for(String s : fileNames)
					res += s + "<br/>";
				mInternalResTV.setText(Html.fromHtml(res));
			}
			break;
		case R.id.file_save_internal_save_btn://保存文件按钮
			try {
				if(FileHelper.createFilesFile("sample.txt", Context.MODE_PRIVATE, "sample"))
					Toast.makeText(activity, "保存文件成功!", Toast.LENGTH_LONG).show();
				else
					Toast.makeText(activity, "保存文件失败!", Toast.LENGTH_LONG).show();
			} catch (Exception e) {
				e.printStackTrace();
				Toast.makeText(activity, "保存文件失败!", Toast.LENGTH_LONG).show();
			}
			break;
		case R.id.file_save_internal_clear_btn://清理文件按钮
			try {
				File file2 = new File(activity.getFilesDir(), "sample.txt");
				if(file2.exists()){
					if(file2.delete())
						Toast.makeText(activity, "删除文件成功!", Toast.LENGTH_LONG).show();
					else
						Toast.makeText(activity, "删除文件失败!", Toast.LENGTH_LONG).show();
				} else
					Toast.makeText(activity, "待删除的文件不存在!", Toast.LENGTH_LONG).show();
			} catch (Exception e) {
				Toast.makeText(activity, "删除文件失败!", Toast.LENGTH_LONG).show();
			}
			break;
		case R.id.file_save_cache_list_btn://列出文件按钮
			File filec = getCacheDir();
			String[] fileNamesc = filec.list();
			if(fileNamesc == null)
				mInternalResTV.setText("内部目录获取失败!");
			else if(fileNamesc.length == 0)
				mInternalResTV.setText("内部目录下没有文件!");
			else {
				String res = "";
				for(String s : fileNamesc)
					res += s + "<br/>";
				mCacheResTV.setText(Html.fromHtml(res));
			}
			break;
		case R.id.file_save_cache_save_btn://保存文件按钮
			try {
				if(FileHelper.createTempFile("develop", null, "develop cache"))
					Toast.makeText(activity, "保存文件成功!", Toast.LENGTH_LONG).show();
				else
					Toast.makeText(activity, "保存文件失败!", Toast.LENGTH_LONG).show();
			} catch (Exception e) {
				LogHelper.printDebugLog(e.getMessage());
				Toast.makeText(activity, "保存文件失败!", Toast.LENGTH_LONG).show();
			}
			break;
		case R.id.file_save_cache_clear_btn://清理文件按钮
			try {
				File[] fileList = FileHelper.getFileArray(activity.getCacheDir().getPath());
				if(fileList != null){
					boolean isDeleted = false;
					for(File f : fileList){
						if(f.getName().startsWith("develop"))
							if(!f.delete())
								Toast.makeText(activity, "删除" + f.getName() + "失败!", Toast.LENGTH_LONG).show();
							else
								isDeleted = true;
					}
					if(isDeleted)
						Toast.makeText(activity, "删除文件成功!", Toast.LENGTH_LONG).show();
				} else
					Toast.makeText(activity, "缓存文件夹下没有文件!", Toast.LENGTH_LONG).show();
			} catch (Exception e) {
				Toast.makeText(activity, "删除文件失败!", Toast.LENGTH_LONG).show();
			}
			break;
		case R.id.file_save_external_public_list_btn://列出文件按钮
			if(isExternalStorageValiable){
				String[] fileNamese = EXTERNAL_PUBLIC_DOWNLOADS.list();
				if(fileNamese == null)
					mExternalPublicResTV.setText("外部公用下载目录获取失败!");
				else if(fileNamese.length == 0)
					mExternalPublicResTV.setText("外部公用下载目录下没有文件!");
				else {
					String res = "";
					for(String s : fileNamese)
						res += s + "<br/>";
					mExternalPublicResTV.setText(Html.fromHtml(res));
				}
			} else
				Toast.makeText(activity, "外部存储不可用!", Toast.LENGTH_LONG).show();
			break;
		case R.id.file_save_external_public_save_btn://保存文件按钮
			try {
				if(FileHelper.createExternalFile(EXTERNAL_PUBLIC_DOWNLOADS.getPath(), 
						"downloads.txt", "downloads"))
					Toast.makeText(activity, "保存文件成功!", Toast.LENGTH_LONG).show();
				else
					Toast.makeText(activity, "保存文件失败!", Toast.LENGTH_LONG).show();
			} catch (Exception e) {
				LogHelper.printDebugLog(e.getMessage());
				Toast.makeText(activity, "保存文件失败!", Toast.LENGTH_LONG).show();
			}
			break;
		case R.id.file_save_external_public_clear_btn://清理文件按钮
			try {
				File[] fileList = FileHelper.getFileArray(EXTERNAL_PUBLIC_DOWNLOADS.getPath());
				if(fileList != null){
					boolean isDeleted = false;
					for(File f : fileList){
						if(f.getName().equals("downloads.txt"))
							if(!f.delete())
								Toast.makeText(activity, "删除" + f.getName() + "失败!", Toast.LENGTH_LONG).show();
							else
								isDeleted = true;
					}
					if(isDeleted)
						Toast.makeText(activity, "删除外部公用下载目录下的文件成功!", Toast.LENGTH_LONG).show();
				} else
					Toast.makeText(activity, "外部公用下载目录文件夹下没有文件!", Toast.LENGTH_LONG).show();
			} catch (Exception e) {
				Toast.makeText(activity, "删除外部公用下载目录下的文件失败!", Toast.LENGTH_LONG).show();
			}
			break;
		case R.id.file_save_external_private_list_btn://列出文件按钮
			if(isExternalStorageValiable){
				String[] fileNamese = EXTERNAL_PRIVATE.list();
				if(fileNamese == null)
					mExternalPrivateResTV.setText("外部私有目录获取失败!");
				else if(fileNamese.length == 0)
					mExternalPrivateResTV.setText("外部私有目录下没有文件!");
				else {
					String res = "";
					for(String s : fileNamese)
						res += s + "<br/>";
					mExternalPrivateResTV.setText(Html.fromHtml(res));
				}
			} else
				Toast.makeText(activity, "外部存储不可用!", Toast.LENGTH_LONG).show();
			break;
		case R.id.file_save_external_private_save_btn://保存文件按钮
			try {
				if(FileHelper.createExternalFile(EXTERNAL_PRIVATE.getPath(), 
						"private.txt", "private"))
					Toast.makeText(activity, "保存文件成功!", Toast.LENGTH_LONG).show();
				else
					Toast.makeText(activity, "保存文件失败!", Toast.LENGTH_LONG).show();
			} catch (Exception e) {
				LogHelper.printDebugLog(e.getMessage());
				Toast.makeText(activity, "保存文件失败!", Toast.LENGTH_LONG).show();
			}
			break;
		case R.id.file_save_external_private_clear_btn://清理文件按钮
			try {
				File[] fileList = FileHelper.getFileArray(EXTERNAL_PUBLIC_DOWNLOADS.getPath());
				if(fileList != null){
					boolean isDeleted = false;
					for(File f : fileList){
						if(f.getName().equals("downloads.txt"))
							if(!f.delete())
								Toast.makeText(activity, "删除" + f.getName() + "失败!", Toast.LENGTH_LONG).show();
							else
								isDeleted = true;
					}
					if(isDeleted)
						Toast.makeText(activity, "删除外部公用下载目录下的文件成功!", Toast.LENGTH_LONG).show();
				} else
					Toast.makeText(activity, "外部公用下载目录文件夹下没有文件!", Toast.LENGTH_LONG).show();
			} catch (Exception e) {
				Toast.makeText(activity, "删除外部公用下载目录下的文件失败!", Toast.LENGTH_LONG).show();
			}
			break;
		}
	}
}
