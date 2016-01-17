package com.schriek.rtmpdump;

import java.lang.reflect.Method;

import android.util.Log;

public class Rtmpdump {

	private final String TAG = "Rtmpdump";

	public Rtmpdump() {
		LoadLib();
	}

	public void parseString(String str) {
		String[] split = str.split(" ");

		run(split);
	}

	public void testHelpOutput() {
		run(new String[] { "rtmpdump", "-h" });
	}

	protected void LoadLib() {

		try {
			System.loadLibrary("rtmpdump");
		} catch (UnsatisfiedLinkError e) {
			Log.e(TAG, e.getMessage());
		}
	}
	
	private native void testNative();

	public native void run(String[] args);
}
