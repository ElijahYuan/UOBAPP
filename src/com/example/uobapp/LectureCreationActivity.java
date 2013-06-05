package com.example.uobapp;

import com.example.uobapp2.R;
import com.example.uobapp.db.DBHelper;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.support.v4.app.NavUtils;

public class LectureCreationActivity extends Activity {

	private SQLiteDatabase db;
	private DBHelper dbHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lecture_creation);
		
		((TimePicker) findViewById(R.id.timePicker_from)).setIs24HourView(true);
		((TimePicker) findViewById(R.id.timePicker_to)).setIs24HourView(true);
		
		dbHelper = new DBHelper(this, "Timetable", null, 1);
		db = dbHelper.getWritableDatabase();
		
		// Show the Up button in the action bar.
		setupActionBar();
	}
	
	public void saveNewLecture(View view) {
		String lectureName = ((EditText) findViewById(R.id.editText_lectureName)).getText().toString();
		String lecturerName = ((EditText) findViewById(R.id.editText_lecturerName)).getText().toString();
		int weekDay = Integer.parseInt(((EditText) findViewById(R.id.editText_weekDay)).getText().toString());
		String timeFrom = ((TimePicker) findViewById(R.id.timePicker_from)).getCurrentHour()+":"+
						((TimePicker) findViewById(R.id.timePicker_from)).getCurrentMinute();
		String timeTo = ((TimePicker) findViewById(R.id.timePicker_to)).getCurrentHour()+":"+
						((TimePicker) findViewById(R.id.timePicker_to)).getCurrentMinute();
		String location = ((EditText) findViewById(R.id.editText_location)).getText().toString();
		String note = ((EditText) findViewById(R.id.editText_note)).getText().toString();
		note = note.equals("")?"null":note;
		
		this.db.execSQL("INSERT INTO TimeTable(lecture,lecturer,weekDay,timeFrom,timeTo,location,note) values('"
				+lectureName+"','"
				+lecturerName+"','"
				+weekDay+"','"
				+timeFrom+"','"
				+timeTo+"','"
				+location+"','"
				+note
				+"')");
		
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lecture_creation, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
