/**
 * Copyright @2014 Obsessed - Studio gsww All rights reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何* 渠道使用、修改源代码.
 * 日期 2014-8-27 下午2:03:58
 */
package com.wchhuangya.developer.util;

import android.text.TextUtils;

/**
 * <p>
 * 字符串帮助类
 * </p>
 * @company WSuperman
 * @project developer
 * @author wchhuangya
 * @date 2014-8-27 下午2:03:58	
 * @class com.wchhuangya.developer.util.StringHelper
 *
 */
public class StringHelper {

	/**
	 * 拼凑字符串，格式key：value，...，key：value，...
	 * 要求：key数组和value数组长度必须一致
	 * @param key
	 * @param values
	 * @return
	 */
	public static String getStr(String[] key, String[] value){
		if(key != null && value != null && key.length == value.length){
			String res = "";
			for(int i = 0; i < key.length; i++){
				res += key[i] + "：" + value[i] + "，";
			}
			if(!TextUtils.isEmpty(res))
				res = res.substring(0, res.length() - 1);
			return res;
		} else 
			return null;
	}
}
