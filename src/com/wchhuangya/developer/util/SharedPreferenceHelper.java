/**
 * Copyright @2014 Obsessed - Studio gsww All rights reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何* 渠道使用、修改源代码.
 * 日期 2014-11-18 下午4:23:36
 */
package com.wchhuangya.developer.util;

import java.util.Iterator;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.wchhuangya.developer.core.DeveloperApplication;

/**
 * SharedPreference助手类
 * @company WSuperman
 * @project developer
 * @author wchhuangya
 * @date 2014-11-18 下午4:23:36	
 * @class com.wchhuangya.developer.util.SharedPreferenceHelper
 *
 */
@SuppressWarnings("rawtypes")
public class SharedPreferenceHelper {
	
	/**
	 * 根据activity和获取模式得到与当前activity相关的sharedpreference对象
	 * @param activity	-	当前activity
	 * @param mode		-	获取的模式
	 * @return			-	sharedpreference对象
	 */
	public static SharedPreferences getSP(Activity activity, int mode){
		return activity.getPreferences(mode);
	}
	/**
	 * 根据sharedpreference文件名称获取sharedpreference对象
	 * @param spName	-	sharedpreference文件名称
	 * @param mode		-	获取方式
	 * @return			-	sharedpreference对象
	 */
	public static SharedPreferences getSP(String spName, int mode){
		return DeveloperApplication.getmContext().getSharedPreferences(spName, Context.MODE_PRIVATE);
	}
	/** 
	 * 根据sharedpreference文件名称获取文件内容
	 * @param fileName	-	要获取的sharedpreference文件名称
	 * @param mode		-	获取方式
	 * @return	-	Map
	 */
	public static Map getPres(String fileName, int mode){
		return getSP(fileName, mode).getAll();
	}
	/** 
	 * 根据activity和获取模式得到与当前activity相关的sharedpreference对象
	 * @param fileName	-	当前的activity
	 * @param mode		-	获取方式
	 * @return	-	Map
	 */
	public static Map getPres(Activity activity, int mode){
		return getSP(activity, mode).getAll();
	}
	/** 
	 * 保存当前activity的配置 
	 * @param activity	-	当前activity
	 * @param map		-	要保存的内容
	 * @param mode		-	模式
	 * @return			-	保存操作是否成功，true-成功；false-失败；
	 */
	public static boolean savePres(Activity activity, Map<String, String> map, int mode){
		try {
			if(map != null && map.size() > 0){
				SharedPreferences sp = getSP(activity, mode);
				SharedPreferences.Editor editor = sp.edit();
				for(Iterator it = map.entrySet().iterator(); it.hasNext();){
					Map.Entry e = (Map.Entry) it.next();
					editor.putString(e.getKey().toString(), e.getValue().toString());
				}
				return editor.commit();
			}
		} catch (Exception e) {
			LogHelper.printDebugLog(e.getMessage());
		}
		return false;
	}
	/**
	 * 根据文件名称和配置数组保存配置
	 * @param map		-	要保存值的Map
	 * @param fileName	-	文件名称
	 * @param mode		-	保存的模式
	 * @return			-	保存操作是否成功，true-成功；false-失败；
	 */
	public static boolean savePres(Map<String, Object> map, String fileName, int mode){
		try {
			if(map != null && map.size() > 0) {
				SharedPreferences sp = DeveloperApplication.getmContext()
						.getSharedPreferences(fileName, mode);
				SharedPreferences.Editor editor = sp.edit();
				for(Iterator it = map.entrySet().iterator(); it.hasNext();){
					Map.Entry e = (Map.Entry) it.next();
					editor.putString(e.getKey().toString(), e.getValue().toString());
				}
				return editor.commit();
			}
		} catch (Exception e) {
			LogHelper.printDebugLog(e.getMessage());
		}
		return false;
	}
	/** 根据key和文件名称删除初始化数据 */
	protected void removeInitParams(String key, String fileName) {
		Context context = DeveloperApplication.getmContext();
		if (key != null && !"".equals(key)) {
			SharedPreferences settings = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
			SharedPreferences.Editor edit = settings.edit();
			edit.remove(key);
			edit.commit();
		}
		context = null;
	}
}
