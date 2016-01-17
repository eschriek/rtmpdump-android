package com.schriek.rtmpdump;

import android.util.Log;

public class callBack {
	
	public static void testCallback(String s) {
		Log.i("Test", s);

		LogFragment.Append(s);
	}
}
