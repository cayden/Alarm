/**
 * OutCallReceiver.java
 * Copyright (C) 2015
 * All right reserved. 2015-5-7
 */
package com.cayden.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 *  
 * @author cuiran
 * @version 1.0.0
 */
public class OutCallReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		Log.d("OutCallReceiver", "==========onReceive========");
		   // 同样如果你想修改外拔的电话号码，可以这样做
          String phone = getResultData();//得到外拔电话
          Log.d("OutCallReceiver", "==========onReceive========"+phone);
          setResultData(null); //清除电话，广播被传给系统的接收者后，因为电话为null，取消电话拔打
          
         // setResultData(“12593”+ phone);//在电话前面加上12593

	}

}
