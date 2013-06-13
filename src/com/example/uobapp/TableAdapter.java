package com.example.uobapp;

import java.util.List;  

import android.content.Context;  
import android.graphics.Color;  
import android.view.Gravity;  
import android.view.View;  
import android.view.ViewGroup;  
import android.widget.BaseAdapter;  
import android.widget.LinearLayout;  
import android.widget.TextView;  
public class TableAdapter extends BaseAdapter {  
    private Context context;  
    private List<TableRow> table;  
    public TableAdapter(Context context, List<TableRow> table) {  
        this.context = context;  
        this.table = table;  
    }  
    public int getCount() {  
        return table.size();  
    }  
    public long getItemId(int position) {  
        return position;
    }  
    public TableRow getItem(int position) {  
        return table.get(position);  
    }  
    public View getView(int position, View convertView, ViewGroup parent) {  
        TableRow tableRow = table.get(position);  
        return new TableRowView(this.context, tableRow);  
    }
    /** 
     * TableRowView
     */  
    class TableRowView extends LinearLayout {  
        public TableRowView(Context context, TableRow tableRow) {  
            super(context);  
              
            this.setOrientation(LinearLayout.HORIZONTAL);
            for (int i = 0; i < tableRow.getSize(); i++) {//add cells to rows
                TableCell tableCell = tableRow.getCellValue(i);
                
                //Set space upon width and height of cell
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(tableCell.width, tableCell.height);
                layoutParams.setMargins(0, 0, 1, 1);//Create border
                
                TextView textCell = new TextView(context);  
                textCell.setLines(1);  
                textCell.setGravity(Gravity.CENTER);
                textCell.setText(String.valueOf(tableCell.value));  
                addView(textCell, layoutParams);  
                
                //for content
                if (tableCell.type == TableCell.EMPTY) {
                    textCell.setBackgroundColor(Color.parseColor("#ADD8E6")); //#F5FFFA£¬#006400£¬#6B8E23£¬#FF4500
                }
                //for title
                if(tableCell.type == TableCell.TITLE) {
                    textCell.setBackgroundColor(Color.parseColor("#87CEEB"));
                }
                //for time
                if(tableCell.type == TableCell.TIME) {
                    textCell.setBackgroundColor(Color.parseColor("#AFEEEE"));
                }
                if(tableCell.type == TableCell.SHOW) {
                	textCell.setBackgroundColor(Color.parseColor("#FFB6C1"));
                }
            }  
            this.setBackgroundColor(Color.WHITE);//white background, for making border  
        }  
    }
    
    /** 
     * TableRow 
     */  
    static public class TableRow {  
        public TableCell[] cell;  
        public TableRow(TableCell[] cell) {  
            this.cell = cell;  
        }  
        public int getSize() {  
            return cell.length;  
        }  
        public TableCell getCellValue(int index) {  
            if (index >= cell.length)  
                return null;  
            return cell[index];  
        }  
    }  
    /** 
     * TableCell
     */  
    static public class TableCell {  
        static public final int EMPTY = 0;  
        static public final int TITLE = 1;
        static public final int TIME = 2;
        static public final int SHOW = 3;
        public Object value;  
        public int width;  
        public int height;  
        private int type;
        public TableCell(Object value, int width, int height, int type) {  
            this.value = value;  
            this.width = width;  
            this.height = height;  
            this.type = type;  
        }  
    }  
}  
