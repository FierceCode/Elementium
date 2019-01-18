package com.fiercecode.elementium.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DbHelper extends SQLiteOpenHelper
{	
	public static final String DB_NAME = "elementium.db";
	public static final int DB_VERSION = 2;
	public static final String TABLE_NAME = "elements";
	public static final String C_ATOMIC_NUM = BaseColumns._ID;
	public static final String C_SYMBOL = "symbol";
	public static final String C_NAME = "name";
	public static final String C_PHASE = "phase";
	public static final String C_CATEGORY = "category";
	public static final String C_ATOMIC_WEIGHT = "atomic_weight";
	public static final String C_BOILING_POINT = "boiling_point";
	public static final String C_MELTING_POINT = "melting_point";
	public static final String C_DENSITY = "density";
	public static final String C_DENSITY_TAG = "density_tag";
	
	public DbHelper(Context context)
	{
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		String sql_create = String
				.format("CREATE TABLE %s (%s int primary key, %s TEXT, %s TEXT, %s TEXT, " +
						"%s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT)",
						TABLE_NAME, C_ATOMIC_NUM, C_SYMBOL, C_NAME, C_PHASE, 
						C_CATEGORY, C_ATOMIC_WEIGHT, C_BOILING_POINT, C_MELTING_POINT, 
						C_DENSITY, C_DENSITY_TAG);

		db.execSQL(sql_create);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2)
	{
		// TODO Auto-generated method stub
	}
}
