package com.example.uobapp.db;

import java.util.ArrayList;

import com.example.uobapp.entity.Lecture;

import android.content.Context;
import android.database.Cursor;
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
	
	public ArrayList<Lecture> getLectures() {
		ArrayList<Lecture> lectures = new ArrayList<Lecture>();
		SQLiteDatabase db = this.getReadableDatabase();
		
		Cursor cursor = db.query("TimeTable", new String[]{"*"}, null, null, null, null, null);
		
		while(cursor.moveToNext()) {
			Lecture l = new Lecture(cursor.getInt(0), 
									cursor.getString(1), 
									cursor.getString(2), 
									cursor.getInt(3), 
									cursor.getString(4), 
									cursor.getString(5), 
									cursor.getString(6), 
									cursor.getString(7));
			lectures.add(l);
		}
		return lectures;
	}
	

}
