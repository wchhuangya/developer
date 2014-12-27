/**
 * Copyright @2014 Obsessed - Studio gsww All rights reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何* 渠道使用、修改源代码.
 * 日期 2014-8-27 下午4:22:27
 */
package com.wchhuangya.developer.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;

/**
 * manifest文件的助手类
 * @company WSuperman
 * @project developer
 * @author wchhuangya
 * @date 2014-8-27 下午4:22:27	
 * @class com.wchhuangya.developer.util.ManifestHelper
 *
 */
public class ManifestHelper {

	/**
	 * 根据key值获取manifest里的meta-data值
	 * @param context	-	应用上下文
	 * @param metaKey	-	key值
	 * @return			-	如果参数传递有误或找不到，返回null
	 */
	public static String getMetaValue(Context context, String metaKey) {
        Bundle metaData = null;
        String apiKey = null;
        if (context == null || metaKey == null) {
            return null;
        }
        try {
            ApplicationInfo ai = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA);
            if (null != ai) {
                metaData = ai.metaData;
            }
            if (null != metaData) {
                apiKey = metaData.getString(metaKey);
            }
        } catch (NameNotFoundException e) {

        }
        return apiKey;
    }
}
