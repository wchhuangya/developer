/**
 * Copyright @2014 Obsessed - Studio gsww All rights reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何* 渠道使用、修改源代码.
 * 日期 2014-12-16 上午11:41:39
 */
package com.wchhuangya.developer.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 示例数据库的数据库操作类
 * @company gsww
 * @project developer
 * @author wchhuangya
 * @date 2014-12-16 上午11:41:39	
 * @class com.wchhuangya.developer.db.DBDatabase
 *
 */
public class DBDatabase extends SQLiteOpenHelper {
	
	//private sql
	/** 数据库版本。TODO 如果修改了数据库表的schema，必须修改该版本号。 */
	public static final int DATABASE_VERSION = 1;
	/** 示例数据库的表名 */
	public static final String DATABASE_NAME = "sample.db";

	public DBDatabase(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

	//public static final String 
}
