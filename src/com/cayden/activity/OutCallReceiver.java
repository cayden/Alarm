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
		   // ͬ����������޸���εĵ绰���룬����������
          String phone = getResultData();//�õ���ε绰
          Log.d("OutCallReceiver", "==========onReceive========"+phone);
          setResultData(null); //����绰���㲥������ϵͳ�Ľ����ߺ���Ϊ�绰Ϊnull��ȡ���绰�δ�
          
         // setResultData(��12593��+ phone);//�ڵ绰ǰ�����12593

	}

}
