package com.example.a1;

import java.util.List;

import android.R.integer;
import android.R.interpolator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ListSelect extends BaseAdapter {    
private LayoutInflater inflater;    
private List<String> items_text = null; 
public int cur_pos = 0;// ��ǰ��ʾ��һ�� 

public ListSelect(Context context,List<String> list) {    
inflater = (LayoutInflater) context    
.getSystemService(Context.LAYOUT_INFLATER_SERVICE);    
items_text = list;
}    
    
@Override   
public int getCount() {    
return items_text.size();    
}    
    
@Override   
public String getItem(int position) {    
return items_text.get(position);  
}    
    
@Override   
public long getItemId(int position) {    
return position;    
}    
    
@Override   
public View getView(int position, View convertView, ViewGroup parent) {    
//Log.e("TEST", "refresh once");    
convertView = inflater.inflate(R.layout.listinfo, null, false);    
ImageView img = (ImageView) convertView    
.findViewById(R.id.infoimg);// ������ʾͼƬ    
TextView tv = (TextView) convertView    
.findViewById(R.id.infotxt);// ��ʾ����    
tv.setText(items_text.get(position));    
//Log.e("TEST", Integer.toString(position)+","+ Integer.toString(cur_pos));   
if (position == cur_pos) {// �����ǰ���о���ListView��ѡ�е�һ�У��͸�����ʾ��ʽ    
convertView.setBackgroundColor(Color.LTGRAY);// �������еı���ɫ    
tv.setTextColor(Color.RED);// ����������ɫ    
}    
return convertView;    
}    
}    
   