/**
 * ActionService.java
 * Copyright (C) 2015
 * All right reserved. 2015-2-2
 */
package com.cayden.activity;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

/**
 *  
 * @author cuiran
 * @version 1.0.0
 */
public class ActionService extends Service {

	/* (non-Javadoc)
	 * @see android.app.Service#onBind(android.content.Intent)
	 */
	@Override
	public IBinder onBind(Intent arg0) {
		
		return null;
	}

	@Override
	public void onStart(Intent intent, int startId) {
		Toast.makeText(this, "闹钟时间到了 ActionService！", Toast.LENGTH_SHORT).show();
		super.onStart(intent, startId);
		
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
	
		return super.onStartCommand(intent, flags, startId);
	}

}
