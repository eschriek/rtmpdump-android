package com.schriek.rtmpdump;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {

	private Context mContext;
	private String[] items;

	public ListAdapter(Context con, String[] items) {
		mContext = con;
		this.items = items;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return items.length;
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return items[position];
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		TextView text = new TextView(mContext);
		text.setText(items[position]);
		
		return text;
	}

}
