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
	
	//֪ͨ������
	private NotificationManager nm;
	
	//֪ͨ��ʾ����
	private PendingIntent pd;
	
	@Override
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        /*����ҳ��*/
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
					//�½�״̬��֪ͨ
					baseNF = new Notification();
					 
					//����֪ͨ��״̬����ʾ��ͼ��
					baseNF.icon = R.drawable.icon;
					
					//֪ͨʱ��״̬����ʾ������
					baseNF.tickerText = "You clicked BaseNF!";
					
					//֪ͨ��Ĭ�ϲ��� DEFAULT_SOUND, DEFAULT_VIBRATE, DEFAULT_LIGHTS. 
					//���Ҫȫ������Ĭ��ֵ, �� DEFAULT_ALL.
					//�˴�����Ĭ������
					baseNF.defaults |= Notification.DEFAULT_SOUND;
					baseNF.defaults |= Notification.DEFAULT_VIBRATE;
					baseNF.defaults |= Notification.DEFAULT_LIGHTS;
					
					//��������������ѭ����ֱ���û���Ӧ
//					baseNF.flags |= Notification.FLAG_INSISTENT;
					
					//֪ͨ��������Զ���ʧ
//					baseNF.flags |= Notification.FLAG_AUTO_CANCEL;
					
					//���'Clear'ʱ���������֪ͨ(QQ��֪ͨ�޷�����������õ����)
					baseNF.flags |= Notification.FLAG_NO_CLEAR;
					
					
					//�ڶ������� ������״̬��ʱ��ʾ����Ϣ���� expanded message title
					//����������������״̬��ʱ��ʾ����Ϣ���� expanded message text
					//���ĸ������������֪ͨʱִ��ҳ����ת
					baseNF.setLatestEventInfo(Lesson_10.this, "Title01", "Content01", pd);
					
					//����״̬��֪ͨ
					//The first parameter is the unique ID for the Notification 
					// and the second is the Notification object.
					nm.notify(Notification_ID_BASE, baseNF);
					
					break;
					
				case R.id.le10bt02:
					//����֪ͨ
					//����״̬����ʾ��һ���¶��ţ���û���ü��鿴������һ���¶��ŵ���ʾ��
					//��ʱ���ø���ԭ��֪ͨ�ķ�ʽ�Ƚϡ�
					//(�����·�һ��֪ͨҲ���ԣ��������������֪ͨ�Ļ��ң�������ʾ���֪ͨ���û������û�Ҳ���Ѻ�)
					baseNF.setLatestEventInfo(Lesson_10.this, "Title02", "Content02", pd);
					nm.notify(Notification_ID_BASE, baseNF);
					break;
					
				case R.id.le10bt03:
					
					//��� baseNF
					nm.cancel(Notification_ID_BASE);
					break;
					
				case R.id.le10bt04:
					mediaNF = new Notification();
					mediaNF.icon = R.drawable.icon;
					mediaNF.tickerText = "You clicked MediaNF!";
					
					//�Զ�������
					mediaNF.sound = Uri.withAppendedPath(Audio.Media.INTERNAL_CONTENT_URI, "6");
					
					//֪ͨʱ��������
					//��һ������: ��ǰ�ȴ���ʱ��
					//�ڶ��������� ��һ���񶯵�ʱ�����Դ�����
					long[] vir = {0,100,200,300};
					mediaNF.vibrate = vir;
					
					mediaNF.setLatestEventInfo(Lesson_10.this, "Title03", "Content03", pd);
					
					nm.notify(Notification_ID_MEDIA, mediaNF);
					break;
					
				case R.id.le10bt05:
					//��� mediaNF
					nm.cancel(Notification_ID_MEDIA);
					break;
					
				case R.id.le10bt06:
					nm.cancelAll();
					break;
					
				case R.id.le10bt07:
					//�Զ���������ͼ�������������ʱ����ʾ�Ľ�������
					Notification notification = new Notification();
					
					notification.icon = R.drawable.icon;
					notification.tickerText = "Custom!";
					
					RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.custom);
					contentView.setImageViewResource(R.id.image, R.drawable.icon);
					contentView.setTextViewText(R.id.text, "Hello, this message is in a custom expanded view");
					notification.contentView = contentView;
					
					//ʹ���Զ���������ͼʱ������Ҫ�ٵ���setLatestEventInfo()����
					//���Ǳ��붨�� contentIntent
					notification.contentIntent = pd;
					
					nm.notify(3, notification);
					break;
			}
		}
	};
	
	 /**
		 * �����жϷ����Ƿ�����.
		 * 
		 * @param context
		 * @param className
		 *            �жϵķ�������
		 * @return true ������ false ��������
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
