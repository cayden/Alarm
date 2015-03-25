/**
 * ActionActivity.java
 * Copyright (C) 2015
 * All right reserved. 2015-2-2
 */
package com.cayden.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

/**
 *  
 * @author cuiran
 * @version 1.0.0
 */
public class ActionActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Button button = new Button(this);
		button.setText("我是由AlarmManager启动的Activity");
		
		setContentView(button);
		
		
	}
	
}
