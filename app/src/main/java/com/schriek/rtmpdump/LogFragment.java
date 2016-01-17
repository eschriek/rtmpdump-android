package com.schriek.rtmpdump;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class LogFragment extends Fragment {

	private ListView list;
	private static AppendingAdapter adapter;

	public LogFragment() {}

	public LogFragment(Context con) {
		adapter = new AppendingAdapter(con);
	}
	
	public static void Append(String s) {
		if(adapter == null) {
			return;
		}
		
		adapter.add(s);
		adapter.notifyDataSetChanged();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (container == null) {
			return null;
		}
		String dir = Environment.getExternalStorageDirectory()
				.getAbsolutePath();
		
		Rtmpdump dump = new Rtmpdump();
		dump.parseString("rtmpdump -r rtmp://owned.fc.llnwd.net:1935/own3duslive-live -a owned -f WIN 11,1,102,62 -W http://static.ec.own3d.tv/player/Own3dPlayerV3_07.swf -p http://www.own3d.tv/live/10588 --live -y 2dd-dota_10588 -o "+ dir + "/test.flv");
		
		View commandfragment = inflater.inflate(R.layout.commandfragment,
				container, false);

		list = (ListView) commandfragment.findViewById(R.id.commandLog);

		list.setAdapter(adapter);

		return commandfragment;
	}
}
