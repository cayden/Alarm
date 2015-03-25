/**
 * Lesson_10.java
 * Copyright (C) 2015
 * All right reserved. 2015-3-25
 */
package com.cayden.activity;

import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Audio;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.SeekBar;
import android.widget.TextView;
/**
 * Notification
 * @author Administrator
 *
 */
public class Lesson_10 extends Activity {
	
	//BaseNotification
	private Button bt01;
	
	//UpdateBaseNotification
	private Button bt02;
	
	//ClearBaseNotification
	private Button bt03;
	
	//MediaNotification
	private Button bt04;
	
	//ClearMediaNotification
	private Button bt05;
	
	//ClearALL
	private Button bt06;
	
	//CustomNotification
	private Button bt07;
	
	//通知管理器
	private NotificationManager nm;
	
	//通知显示内容
	private PendingIntent pd;
	
	@Override
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        /*加载页面*/
	        setContentView(R.layout.lesson10);
	        
	        init();
	}
	
	private void init() {
		bt01 = (Button)findViewById(R.id.le10bt01);
		bt02 = (Button)findViewById(R.id.le10bt02);
		bt03 = (Button)findViewById(R.id.le10bt03);
		bt04 = (Button)findViewById(R.id.le10bt04);
		bt05 = (Button)findViewById(R.id.le10bt05);
		bt06 = (Button)findViewById(R.id.le10bt06);
		bt07 = (Button)findViewById(R.id.le10bt07);
		
		bt01.setOnClickListener(onclick);
		bt02.setOnClickListener(onclick);
		bt03.setOnClickListener(onclick);
		bt04.setOnClickListener(onclick);
		bt05.setOnClickListener(onclick);
		bt06.setOnClickListener(onclick);	
		bt07.setOnClickListener(onclick);
		
		nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		
		Intent intent = new Intent(this,Lesson_10.class);
		
		pd = PendingIntent.getActivity(Lesson_10.this, 0, intent, 0);
	}
	
	OnClickListener onclick = new OnClickListener() {
		
		//BASE Notification ID
		private int Notification_ID_BASE = 110;
		
		private Notification baseNF;
		
		//Notification ID
		private int Notification_ID_MEDIA = 119;
		
		private Notification mediaNF;
		
		@Override
		public void onClick(View v) {
			switch(v.getId()) {
				case R.id.le10bt01:
					//新建状态栏通知
					baseNF = new Notification();
					 
					//设置通知在状态栏显示的图标
					baseNF.icon = R.drawable.icon;
					
					//通知时在状态栏显示的内容
					baseNF.tickerText = "You clicked BaseNF!";
					
					//通知的默认参数 DEFAULT_SOUND, DEFAULT_VIBRATE, DEFAULT_LIGHTS. 
					//如果要全部采用默认值, 用 DEFAULT_ALL.
					//此处采用默认声音
					baseNF.defaults |= Notification.DEFAULT_SOUND;
					baseNF.defaults |= Notification.DEFAULT_VIBRATE;
					baseNF.defaults |= Notification.DEFAULT_LIGHTS;
					
					//让声音、振动无限循环，直到用户响应
//					baseNF.flags |= Notification.FLAG_INSISTENT;
					
					//通知被点击后，自动消失
//					baseNF.flags |= Notification.FLAG_AUTO_CANCEL;
					
					//点击'Clear'时，不清楚该通知(QQ的通知无法清除，就是用的这个)
					baseNF.flags |= Notification.FLAG_NO_CLEAR;
					
					
					//第二个参数 ：下拉状态栏时显示的消息标题 expanded message title
					//第三个参数：下拉状态栏时显示的消息内容 expanded message text
					//第四个参数：点击该通知时执行页面跳转
					baseNF.setLatestEventInfo(Lesson_10.this, "Title01", "Content01", pd);
					
					//发出状态栏通知
					//The first parameter is the unique ID for the Notification 
					// and the second is the Notification object.
					nm.notify(Notification_ID_BASE, baseNF);
					
					break;
					
				case R.id.le10bt02:
					//更新通知
					//比如状态栏提示有一条新短信，还没来得及查看，又来一条新短信的提示。
					//此时采用更新原来通知的方式比较。
					//(再重新发一个通知也可以，但是这样会造成通知的混乱，而且显示多个通知给用户，对用户也不友好)
					baseNF.setLatestEventInfo(Lesson_10.this, "Title02", "Content02", pd);
					nm.notify(Notification_ID_BASE, baseNF);
					break;
					
				case R.id.le10bt03:
					
					//清除 baseNF
					nm.cancel(Notification_ID_BASE);
					break;
					
				case R.id.le10bt04:
					mediaNF = new Notification();
					mediaNF.icon = R.drawable.icon;
					mediaNF.tickerText = "You clicked MediaNF!";
					
					//自定义声音
					mediaNF.sound = Uri.withAppendedPath(Audio.Media.INTERNAL_CONTENT_URI, "6");
					
					//通知时发出的振动
					//第一个参数: 振动前等待的时间
					//第二个参数： 第一次振动的时长、以此类推
					long[] vir = {0,100,200,300};
					mediaNF.vibrate = vir;
					
					mediaNF.setLatestEventInfo(Lesson_10.this, "Title03", "Content03", pd);
					
					nm.notify(Notification_ID_MEDIA, mediaNF);
					break;
					
				case R.id.le10bt05:
					//清除 mediaNF
					nm.cancel(Notification_ID_MEDIA);
					break;
					
				case R.id.le10bt06:
					nm.cancelAll();
					break;
					
				case R.id.le10bt07:
					//自定义下拉视图，比如下载软件时，显示的进度条。
					Notification notification = new Notification();
					
					notification.icon = R.drawable.icon;
					notification.tickerText = "Custom!";
					
					RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.custom);
					contentView.setImageViewResource(R.id.image, R.drawable.icon);
					contentView.setTextViewText(R.id.text, "Hello, this message is in a custom expanded view");
					notification.contentView = contentView;
					
					//使用自定义下拉视图时，不需要再调用setLatestEventInfo()方法
					//但是必须定义 contentIntent
					notification.contentIntent = pd;
					
					nm.notify(3, notification);
					break;
			}
		}
	};
	
	 /**
		 * 用来判断服务是否运行.
		 * 
		 * @param context
		 * @param className
		 *            判断的服务名字
		 * @return true 在运行 false 不在运行
		 */
		public  boolean isServiceRunning(Context context, String className) {
			ActivityManager am = (ActivityManager) context
					.getSystemService(Context.ACTIVITY_SERVICE);
			List<RunningServiceInfo> serviceinfos = am.getRunningServices(100);
			for (RunningServiceInfo info : serviceinfos) {
				// System.out.println(info.service.getClassName()+"--->"+className);

				if (className.equals(info.service.getClassName())) {
					return true;
				}
			}
			return false;
		}
		
		
	    
	    @Override
		protected void onResume() {
	    	String classname=NotifyService.class.getName();
	    	if(!isServiceRunning(this, classname)){
	    		  Intent localIntent3 = new Intent(this, NotifyService.class);
	    	      startService(localIntent3);
	    	}
			super.onResume();
		}
	
}
