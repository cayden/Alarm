/**
 * ActionIntentService.java
 * Copyright (C) 2015
 * All right reserved. 2015-2-27
 */
package com.cayden.activity;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 *  
 * @author cuiran
 * @version 1.0.0
 */
public class ActionIntentService extends IntentService {
	private static final String TAG = "ActionIntentService";
	public ActionIntentService() {
		super(TAG);
	}

	 /* (non-Javadoc)
     * @see android.app.IntentService#onCreate()
     */
    @Override
    public void onCreate() {
        Log.i(TAG, "=>onCreate");
        super.onCreate();
    }

    @Override
	public IBinder onBind(Intent intent) {
		return super.onBind(intent);
	}

	@Override
	public void onStart(Intent intent, int startId) {
		int type=intent.getIntExtra("type", -1);
		Toast.makeText(this, "闹钟时间到了 ActionIntentService！type="+type, Toast.LENGTH_SHORT).show();
		super.onStart(intent, startId);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		return super.onStartCommand(intent, flags, startId);
	}

	/* (non-Javadoc)
     * @see android.app.IntentService#onDestroy()
     */
    @Override
    public void onDestroy() {
        Log.i(TAG, "=>onDestroy");
        Toast.makeText(this, " ActionIntentService  onDestroy！", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
	@Override
	protected void onHandleIntent(Intent arg0) {
		 Log.i(TAG, "=>onHandleIntent");
		 Log.i(TAG, "IntentService 线程："+Thread.currentThread().getId());
	     try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		 
	}

}
