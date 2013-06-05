package com.example.uobapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE TimeTable ("+
				"id INTEGER PRIMARY KEY AUTOINCREMENT,"+
				"lecture TEXT NOT NULL,"+
				"lecturer TEXT,"+
				"weekDay NUMERIC,"+
				"timeFrom TEXT,"+
				"timeTo TEXT,"+
				"location TEXT,"+
				"note TEXT"+
				");"
		);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS TimeTable;");
		this.onCreate(db);
	}
	

}
