package com.example.uobapp;

import java.util.ArrayList;

import com.example.uobapp.TableAdapter.TableCell;
import com.example.uobapp.TableAdapter.TableRow;
import com.example.uobapp.db.DBHelper;
import com.example.uobapp.entity.Lecture;
import com.example.uobapp.network.ImportHelper;
import com.example.uobapp.ui.CustomDialog;
import com.example.uobapp2.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

public class MainActivity extends Activity {
	
	private LinearLayout content = null;	
	private ListView contentListView;
	private DBHelper dbHelper;
	
	private static final int CUSTOM_DIALOG 	= 1;
	private static final int DEFAULT_DIALOG	= 2;
	
	private TableAdapter tableAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}
	
	//add new method newTimetable
	public void newTimetable(View view) {
		Intent intent = new Intent(this, LectureCreationActivity.class);
		startActivity(intent);
	}

	private void init(){
		
		this.dbHelper = new DBHelper(this, "Timetable", null, 1);
		
		content = (LinearLayout)findViewById(R.id.content);

		LayoutInflater factory = LayoutInflater.from(this);
		View taskView = factory.inflate(R.layout.activity_task, null);

		content.addView(taskView);
		
		Button btnImport = (Button)findViewById(R.id.btnImportTimetable);
		btnImport.setOnClickListener(new importClickEvent());
		
		this.drawTable(); 
	}
	
	private void drawTable() {
		
		this.contentListView = (ListView) this.findViewById(R.id.ListView_Content);  
        ArrayList<TableRow> table = new ArrayList<TableRow>();  
        TableCell[] titles = new TableCell[6];// 6 cells for every row
        @SuppressWarnings("deprecation")
		int width = this.getWindowManager().getDefaultDisplay().getWidth()/titles.length;
        
        // Assign title
        String[] titleString = {"Time","Mon","Tue","Wed","Thu","Fri"};
        for (int i = 0; i < titles.length; i++) {  
            titles[i] = new TableCell(titleString[i],   
                                    width+50,  
                                    30,
                                    TableCell.TITLE);  
        }
        table.add(new TableRow(titles));
        
        // add rows into table 
        for (int i = 9; i < 19; i++) {
        	// Values of rows
        	TableCell[] cells = new TableCell[6];// 6 cells for every row
            for (int j = 0; j < 6; j++) {
            	if(j==0){
            		cells[j] = new TableCell(i, width+50, 50, TableCell.TIME);  
            	} else {
            		Lecture lecture = dbHelper.searchLecture(i,i+1, j);
                	if(lecture!=null) {
                		String lectureName = lecture.getLecture();
                		if(lectureName.length()>17){
                			lectureName = lectureName.substring(0, 17);
                		}
                		cells[j] = new TableCell(lectureName, width+50, 50, TableCell.SHOW);
                	}else {
                		cells[j] = new TableCell("", width+50, 50, TableCell.EMPTY);  
    				}
            	}
            }
            table.add(new TableRow(cells));
        }
        
        
        this.tableAdapter = new TableAdapter(this, table);  
        contentListView.setAdapter(this.tableAdapter);
        //contentListView.setOnItemClickListener(new ItemClickEvent());
	}
	
	@Override
    public Dialog onCreateDialog(int dialogId) {
    	Dialog dialog = null;
    	switch (dialogId) {
	    	case CUSTOM_DIALOG :
	        	LayoutInflater layoutInflater = getLayoutInflater();
	        	final View dialogView = layoutInflater.inflate(R.layout.dialog_edittext,null);
	        	final EditText sidTxt = (EditText)dialogView.findViewById(R.id.sid);
				final EditText passwordTxt = (EditText)dialogView.findViewById(R.id.password);
				
				CustomDialog.Builder customBuilder = new CustomDialog.Builder(MainActivity.this);
				customBuilder.setContentView(dialogView);
				customBuilder.setTitle("Login");
				customBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					});
				customBuilder.setPositiveButton("Login", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
					    	String sid = sidTxt.getText().toString();
					    	String password = passwordTxt.getText().toString();
					    	if(!sid.equals("") && !password.equals("")) {
						    	ImportHelper importHelper = new ImportHelper();
						    	importHelper.setSid(sid);
						    	importHelper.setPassword(password);
						    	importHelper.setDBHelper(dbHelper);
						    	Thread thread = new Thread(importHelper);
						    	thread.start();
					    	}
							dialog.dismiss();
						}
					});
	            dialog = customBuilder.create();
    	}
    	return dialog;
    }
	
//	@SuppressLint("ShowToast")
//	class ItemClickEvent implements AdapterView.OnItemClickListener   {  
//        @SuppressWarnings("deprecation")
//		public void onItemClick(AdapterView<?> arg0, View view, int rowPosition, long arg3) {
//            Toast.makeText(MainActivity.this, "Select No."+rowPosition+" Row", 1000).show();
//            MainActivity.this.showDialog(CUSTOM_DIALOG);
//        }
//    }
	
	class importClickEvent implements OnClickListener   {
		public void onClick(View arg0) {
			MainActivity.this.showDialog(CUSTOM_DIALOG);	    	
		}
    }

}