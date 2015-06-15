package com.gooker.dfg.adapter;

import java.util.Random;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBServices extends SQLiteOpenHelper {
	private final static int DATABASE_VERSION = 1;
	private final static String DATABASE_NAME = "test.db";

	public DBServices(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql = "CREATE TABLE[t_test] (" + "[_id] AUTOINC,[name] VARCHAR(20) NOT NULL ON CONFLICT FAIL,CONSTTARINT [sqlite_autoindex_t_test_1] PRIMARY KEY ([_id])";
		db.execSQL(sql);
		// 向t_test 中插入20 条记录
		String s = "";
		Random random = new Random();
		for (int i = 0; i < 20; i++) {
			char c = (char) (97 + random.nextInt(26));
			s += c;
		}
		db.execSQL("insert into t_test(name) values(?)", new Object[] { s });
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	public Cursor query(String sql, String[] args) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(sql, args);
		return cursor;
	}

}
