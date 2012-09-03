package com.schriek.rtmpdump;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class HelpFragment extends Fragment {

	private ListView list;
	
	private static String[] usage = {
			"--help|-h               Prints this help screen.\n",
			"--rtmp|-r url           URL (e.g. rtmp://host[:port]/path)\n",
			"--host|-n hostname      Overrides the hostname in the rtmp url\n",
			"--port|-c port          Overrides the port in the rtmp url\n",
			"--socks|-S host:port    Use the specified SOCKS proxy\n",
			"--protocol|-l num       Overrides the protocol in the rtmp url (0 - RTMP, 2 - RTMPE)\n",
			"--playpath|-y path      Overrides the playpath parsed from rtmp url\n",
			"--playlist|-Y           Set playlist before playing\n",
			"--swfUrl|-s url         URL to player swf file\n",
			"--tcUrl|-t url          URL to played stream (default: \"rtmp://host[:port]/app\")\n",
			"--pageUrl|-p url        Web URL of played programme\n",
			"--app|-a app            Name of target app on server\n",
			"--swfhash|-w hexstring  SHA256 hash of the decompressed SWF file (32 bytes)\n",
			"--swfsize|-x num        Size of the decompressed SWF file, required for SWFVerification\n",
			"--swfVfy|-W url         URL to player swf file, compute hash/size automatically\n",
			"--swfAge|-X days        Number of days to use cached SWF hash before refreshing\n",
			"--auth|-u string        Authentication string to be appended to the connect string\n",
			"--conn|-C type:data     Arbitrary AMF data to be appended to the connect string\n B:boolean(0|1), S:string, N:number, O:object-flag(0|1),\n Z:(null), NB:name:boolean, NS:name:string, NN:name:number\n",
			"--flashVer|-f string    Flash version string (default: \"%s\")\n",
			"--live|-v               Save a live stream, no --resume (seeking) of live streams possible\n",
			"--subscribe|-d string   Stream name to subscribe to (otherwise defaults to playpath if live is specifed)\n",
			"--realtime|-R           Don't attempt to speed up download via the Pause/Unpause BUFX hack\n",
			"--flv|-o string         FLV output file name, if the file name is - print stream to stdout\n",
			"--resume|-e             Resume a partial RTMP download\n",
			"--timeout|-m num        Timeout connection num seconds (default: %u)\n",
			"--start|-A num          Start at num seconds into stream (not valid when using --live)\n",
			"--stop|-B num           Stop at num seconds into stream\n",
			"--token|-T key          Key for SecureToken response\n",
			"--jtv|-j JSON           Authentication token for Justin.tv legacy servers\n",
			"--hashes|-#             Display progress with hashes, not with the byte counter\n",
			"--buffer|-b             Buffer time in milliseconds (default: %u)\n",
			"--skip|-k num           Skip num keyframes when looking for last keyframe to resume from. Useful if resume fails (default: %d)\n\n",
			"--quiet|-q              Suppresses all command output.\n",
			"--verbose|-V            Verbose command output.\n",
			"--debug|-z              Debug level command output.\n",
			"If you don't pass parameters for swfUrl, pageUrl, or auth these properties will not be included in the connect " };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (container == null) {
			return null;
		}

		View helpfragment = inflater.inflate(R.layout.helpfragment, container,
				false);

		list = (ListView) helpfragment.findViewById(R.id.listView1);

		list.setAdapter(new ListAdapter(getActivity(), usage));

		return helpfragment;
	}

	public void onResult(List<String> result) {
		// TODO Auto-generated method stub
		// System.out.println(result.size());
		// ]\list.setAdapter(new ListAdapter(getActivity(), result));
	}

}
