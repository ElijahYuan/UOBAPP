package com.example.uobapp.network;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.List;

import com.example.uobapp.MainActivity;
import com.example.uobapp.db.DBHelper;

import android.os.Handler;
import android.os.Message;
import android.text.style.ReplacementSpan;
import android.widget.Toast;

public class ImportHelper implements Runnable {

	private static final String SERVER_IP = "192.168.17.128";
	private static final int SERVER_PORT = 12300;
	private static final int CORRECT = 0;
	
	private Handler handler = new Handler(){
		 @Override
		 public void handleMessage(Message msg){
		 super.handleMessage(msg);
		 }
	};
	
	private String sid;
	private String password;
	private DBHelper dbHelper;
	
	public void setSid(String sid) {
		this.sid = sid;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setDBHelper(DBHelper dbHelper) {
		this.dbHelper = dbHelper;
	}
	
	public void run() {
		this.importTimetable(sid, password);
		handler.sendEmptyMessage(0);
	}
	
	private void importTimetable(String sid, String password) {
		Socket socket = null;
		
		try{
			SocketAddress isa = new InetSocketAddress(SERVER_IP, SERVER_PORT);
			socket = new Socket();
			socket.connect(isa, 3000);
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(sid+"^"+password);
			oos.flush();
			
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			int response = ois.readInt();
			
			if(response==CORRECT) {
				List timetable = (List)ois.readObject();
				this.dbHelper.importTimetable(timetable);
				//Toast.makeText(MainActivity.this, "Personal Timetable Imported", 1000).show();
			} else {
				//Toast.makeText(MainActivity.this, "Invalid ID or Password", 1000).show();
			}
			this.dbHelper.close();
			socket.close();			
		}catch (Exception e) {
			try {
				socket.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
}
