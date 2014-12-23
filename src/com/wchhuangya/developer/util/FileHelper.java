/**
 * Copyright @2014 Obsessed - Studio gsww All rights reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何* 渠道使用、修改源代码.
 * 日期 2014-12-9 下午4:04:33
 */
package com.wchhuangya.developer.util;

import java.io.File;
import java.io.FileOutputStream;

import android.os.Environment;
import android.text.TextUtils;

import com.wchhuangya.developer.core.DeveloperApplication;

/**
 * 文件操作助手类
 * @company gsww
 * @project developer
 * @author wchhuangya
 * @date 2014-12-9 下午4:04:33	
 * @class com.wchhuangya.developer.util.FileHelper
 *
 */
public class FileHelper {

	/**
	 * 在/data/data/包名/cache/目录下创建临时文件
	 * @param prefix	-	临时文件的前缀
	 * @param suffix	-	临时文件的后缀
	 * @param content	-	临时文件中保存的文本信息
	 * @return			-	创建结果，true-成功；false-失败，失败的原因可能是内容为空，也可能是文件操作异常；
	 */
	public static boolean createTempFile(String prefix, String suffix, String content){
		boolean res = false;
		try {
			if(!TextUtils.isEmpty(content)){
				File file = File.createTempFile(prefix, suffix, 
						DeveloperApplication.getmContext().getCacheDir());
				FileOutputStream fos = new FileOutputStream(file);
				fos.write(content.getBytes());
				fos.close();
				res = true;
			}
		} catch (Exception e) {
			LogHelper.printDebugLog(e.getMessage());
		}
		return res;
	}
	/**
	 * 创建私有文件
	 * @param name		-	文件名称（带后缀）
	 * @param mode		-	操作模式
	 * @param content	-	文件里存储的内容
	 * @return			-	true-创建成功；false-创建失败；
	 */
	public static boolean createFilesFile(String name, int mode, String content){
		boolean res = false;
		try {
			FileOutputStream fos = DeveloperApplication.getmContext().openFileOutput(name, mode);
			fos.write(content.getBytes());
			fos.close();
			res = true;
		} catch (Exception e) {
			LogHelper.printDebugLog(e.getMessage());
		}
		return res;
	}
	/**
	 * 在外部存储里创建文件
	 * @param path		-	文件要保存的路径
	 * @param name		-	要保存的文件名称
	 * @param content	-	要保存的文件内容
	 * @return			-	文件是否创建成功
	 */
	public static boolean createExternalFile(String path, String name, String content){
		boolean res = false;
		try {
			File file = new File(path + File.separator + name); 
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(content.getBytes());
			fos.close();
			res = true;
		} catch (Exception e) {
			LogHelper.printDebugLog(e.getMessage());
		}
		return res;
	}
	/**
	 * 根据文件路径，获取该路径下的所有文件
	 * @param path	-	文件路径
	 * @return		-	null-抛出异常；File[]-路径下的文件数组；
	 */
	public static File[] getFileArray(String path){
		try {
			File file = new File(path);
			return file.listFiles();
		} catch (Exception e) {
			LogHelper.printDebugLog(e.getMessage());
		}
		return null;
	}
	/**
	 * 检查外部存储是否可读写
	 * @return	-	true-可读写；false-不可写，能不能读就不知道啦；
	 */
	public static boolean isExternalStorageWritable() {
	    String state = Environment.getExternalStorageState();
	    if (Environment.MEDIA_MOUNTED.equals(state)) {
	        return true;
	    }
	    return false;
	}
	/**
	 * 检查外部存储是否可读写（至少可读）
	 * @return	-	true-至少可读，或许可写；false-既不可读，也不可写；
	 */
	public static boolean isExternalStorageReadable() {
	    String state = Environment.getExternalStorageState();
	    if (Environment.MEDIA_MOUNTED.equals(state) ||
	        Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
	        return true;
	    }
	    return false;
	}
}
