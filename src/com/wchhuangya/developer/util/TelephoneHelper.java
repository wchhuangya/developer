/**
 * Copyright @2014 Obsessed - Studio gsww All rights reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何* 渠道使用、修改源代码.
 * 日期 2014-8-27 下午4:31:28
 */
package com.wchhuangya.developer.util;

import com.wchhuangya.developer.core.DeveloperApplication;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

/**
 * 手机设备的助手类
 * PS：该类不能直接实例化，得使用Context.getSystemService(Context.TELEPHONY_SERVICE)方法来获得该类的引用。
 * 当手机状态发生变化时，系统会发出通知，也可以注册一个监听来接收这些通知。
 * @company gsww
 * @project developer
 * @author wchhuangya
 * @date 2014-8-27 下午4:31:28	
 * @class com.wchhuangya.developer.util.TelephoneHelper
 *
 */
public class TelephoneHelper {
	
	private static TelephonyManager tm = (TelephonyManager)DeveloperApplication.getmContext()
			.getSystemService(Context.TELEPHONY_SERVICE);

	/**
	 * 获取手机唯一的设备号。对于GSM手机来说，返回IMEI，对于CDMA手机来说返回MEID或ESN。
	 * @param context	-	应用上下文
	 * @return			-	获取成功返回设备号，获取失败返回空字符串；
	 */
	public static String getDeviceNumber(){
		String res = tm.getDeviceId();
		return TextUtils.isEmpty(res) ? "" : res;
	}
}
