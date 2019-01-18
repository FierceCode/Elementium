package com.fiercecode.elementium.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.fiercecode.elementium.entities.Elemental;

public class ElementDao
{
	DbHelper dbHelper;
	SQLiteDatabase db;
	List<Elemental> elementals;
	int num_rows = 0;

	private String[] allColumns = { DbHelper.C_ATOMIC_NUM, DbHelper.C_SYMBOL,
			DbHelper.C_NAME, DbHelper.C_PHASE, DbHelper.C_CATEGORY,
			DbHelper.C_ATOMIC_WEIGHT, DbHelper.C_BOILING_POINT,
			DbHelper.C_MELTING_POINT, DbHelper.C_DENSITY,
			DbHelper.C_DENSITY_TAG };

	public ElementDao(Context context)
	{
		this.dbHelper = new DbHelper(context);
		this.num_rows = 0;
	}

	public int getSize()
	{
		return this.num_rows;
	}
	
	public void open() throws SQLException
	{
		this.db = this.dbHelper.getWritableDatabase();
	}

	public void close()
	{
		this.dbHelper.close();
	}

	public void insertElement(final Elemental the_element)
	{
		ContentValues values = new ContentValues();
		{
			values.put(DbHelper.C_ATOMIC_NUM, the_element.getAtomicNum());
			values.put(DbHelper.C_SYMBOL, the_element.getSymbol());
			values.put(DbHelper.C_NAME, the_element.getName());
			values.put(DbHelper.C_PHASE, the_element.getPhase());
			values.put(DbHelper.C_CATEGORY, the_element.getCategory());
			values.put(DbHelper.C_ATOMIC_WEIGHT, the_element.getAtomicWeight());
			values.put(DbHelper.C_BOILING_POINT, the_element.getBoilingPoint());
			values.put(DbHelper.C_MELTING_POINT, the_element.getMeltingPoint());
			values.put(DbHelper.C_DENSITY, the_element.getDensity());
			values.put(DbHelper.C_DENSITY_TAG, the_element.getDensityTag());

		}
		this.db.insert(DbHelper.TABLE_NAME, null, values);
	}

	public void insertAllElements(List<Elemental> elements)
	{
		for (Elemental e : elements)
		{
			this.insertElement(e);
		}
	}

	public List<Elemental> getAllElements()
	{
		if (this.elementals == null || this.elementals.size() < this.num_rows)
		{
			this.elementals = new ArrayList<Elemental>();

			Cursor cursor = this.db.query(DbHelper.TABLE_NAME, this.allColumns,
					null, null, null, null, null, null);
			cursor.moveToFirst();

			this.num_rows = cursor.getCount();

			while (!cursor.isAfterLast())
			{
				Elemental element = cursorToElement(cursor);
				this.elementals.add(element);
				cursor.moveToNext();
			}
			cursor.close();
		}
		return this.elementals;
	}

	public Elemental cursorToElement(Cursor cursor)
	{
		Elemental element = new Elemental();
		element.setAtomicNum(cursor.getInt(0));
		element.setSymbol(cursor.getString(1));
		element.setName(cursor.getString(2));
		element.setPhase(cursor.getString(3));
		element.setCategory(cursor.getString(4));
		element.setAtomicWeight(cursor.getString(5));
		element.setBoilingPoint(cursor.getString(6));
		element.setMeltingPoint(cursor.getString(7));
		element.setDensity(cursor.getString(8));
		element.setDensityTag(cursor.getString(9));

		return element;
	}
}
