/**
 * 
 */
package com.cayden.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * 
 *  
 * @author cuiran
 * @version 1.0.0
 */
public class AlarmReceiver extends BroadcastReceiver {

	/* (non-Javadoc)
	 * @see android.content.BroadcastReceiver#onReceive(android.content.Context, android.content.Intent)
	 */
	@Override
	public void onReceive(Context arg0, Intent data) {
		Log.d(Alarm.TAG, "the time is up,start the alarm...");
		Toast.makeText(arg0, "闹钟时间到了 AlarmReceiver！", Toast.LENGTH_SHORT).show();
	}
}
