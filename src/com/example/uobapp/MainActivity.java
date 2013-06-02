package com.example.uobapp;

import com.example.uobapp2.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	private LinearLayout task = null;
	private LinearLayout accounts = null;
	private LinearLayout sended = null;
	private LinearLayout content = null;
	
	private TextView taskText = null;
	private TextView accountsText = null;
	private TextView sendedText = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}

	private void init(){
		task = (LinearLayout)findViewById(R.id.task);
		accounts = (LinearLayout)findViewById(R.id.accounts);
		sended = (LinearLayout)findViewById(R.id.sended);
		content = (LinearLayout)findViewById(R.id.content);
		
		taskText = (TextView)findViewById(R.id.taskText);
		accountsText = (TextView)findViewById(R.id.accountsText);
		sendedText = (TextView)findViewById(R.id.sendedText);
		
		LayoutInflater factory = LayoutInflater.from(this);
		View taskView = factory.inflate(R.layout.task, null);
		View accountsView = factory.inflate(R.layout.accounts, null);
		View sendedView = factory.inflate(R.layout.sended, null);
		
		content.addView(taskView);
		task.setOnClickListener(new TabListener(task, taskText, taskView));
		accounts.setOnClickListener(new TabListener(accounts, accountsText, accountsView));
		sended.setOnClickListener(new TabListener(sended, sendedText, sendedView));
	}
	/**
	 * TabPanel 
	 * @author chengmingwei
	 *
	 */
	class TabListener implements OnClickListener{
		LinearLayout currLayout;
		TextView tv;
		View subView;
		
		
		
		public TabListener(LinearLayout currLayout, TextView tv, View subView) {
			this.currLayout = currLayout;
			this.tv = tv;
			this.subView = subView;
		}

		@Override
		public void onClick(View v) {
			reset();
			active();
		}

		private void reset(){
			LinearLayout[] layouts = {task,accounts,sended};
			TextView[] tvs = {taskText,accountsText,sendedText};
			for(int i=0; i<3; i++){
				tvs[i].setTextColor(getResources().getColor(R.color.green));
			}
			content.removeAllViews();
		}
		
		private void active(){
			tv.setTextColor(getResources().getColor(R.color.red));
			content.addView(subView);
		}
	}

}
