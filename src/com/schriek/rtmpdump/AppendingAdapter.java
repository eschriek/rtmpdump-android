package com.schriek.rtmpdump;

import java.util.Vector;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AppendingAdapter extends BaseAdapter {

	private Context mContext;
	private Vector<String> items = new Vector<String>();

	public void add(String s) {
		items.add(s);

	}

	public AppendingAdapter(Context con) {
		mContext = con;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return items.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		TextView text = new TextView(mContext);
		text.setTextColor(Color.BLACK);
		text.setText(items.get(position));

		return text;
	}

}
