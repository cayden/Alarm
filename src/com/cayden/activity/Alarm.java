package com.cayden.activity;

import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;
/**
 * 
 * ��������Activity
 * @author cuiran
 * @version 1.0.0
 */
public class Alarm extends Activity implements OnClickListener{
	
	public static final String TAG = "Alarm";
	
	public static final int PENDINGINTENT_TYPE_ACTIVITY=0;
	
	public static final int PENDINGINTENT_TYPE_RECEIVER=1;
	
	public static final int PENDINGINTENT_TYPE_SERVICE=2;
	
	public static final int PENDINGINTENT_TYPE_INTENTSERVICE=3;
	
	
	private int selectType=-1;
	private Calendar calendar ;
	private int hour,minute;
	private Button timeBtn,activityBtn,receiverBtn,serviceBtn,cancelAlarmBtn,intentServiceBtn;
	
	//��ȡ���ӹ�����
	private AlarmManager alarmManager = null;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //��ȡ����ʵ��
        calendar = Calendar.getInstance();
        //��ȡʱ�䰴ť
        timeBtn = (Button)findViewById(R.id.timeBtn);
        
        activityBtn=(Button)findViewById(R.id.activityBtn);
        receiverBtn=(Button)findViewById(R.id.receiverBtn);
        serviceBtn=(Button)findViewById(R.id.serviceBtn);
        intentServiceBtn=(Button)findViewById(R.id.intentServiceBtn);
        
        activityBtn.setOnClickListener(this);
        receiverBtn.setOnClickListener(this);
        serviceBtn.setOnClickListener(this);
        intentServiceBtn.setOnClickListener(this);
        //����ʱ��
        timeBtn.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Log.d(TAG, "click the time button to set time");
				calendar.setTimeInMillis(System.currentTimeMillis());
				new TimePickerDialog(Alarm.this,new TimePickerDialog.OnTimeSetListener() {
					@Override
					public void onTimeSet(TimePicker arg0, int h, int m) {
						//���°�ť�ϵ�ʱ��
						timeBtn.setText(formatTime(h,m));
						hour=h;
						minute=m;
					
					}
				},calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true).show();				
			}
        });
        
        //ȡ�����Ӱ�ť�¼�����  ����ֻ���˶Զ�ʱ�Ĺ㲥ȡ��
        cancelAlarmBtn = (Button)findViewById(R.id.cancelAlarmBtn);
        
        cancelAlarmBtn.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View arg0) {
				cancelAlarm();
			}
        });
        
        alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        
        
      
    }
    
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

	private void cancelAlarm(){
    	Intent intent = null;
		PendingIntent pendingIntent = null;
		if(selectType<0)return;
    	switch(selectType){
    	case PENDINGINTENT_TYPE_ACTIVITY:
    		 intent=new Intent(Alarm.this,ActionActivity.class);
    		 pendingIntent = PendingIntent.getActivity(Alarm.this, 0, intent, 0);
    		break;
    	case PENDINGINTENT_TYPE_RECEIVER:
    		 intent=new Intent(Alarm.this,AlarmReceiver.class);
    		 pendingIntent = PendingIntent.getBroadcast(Alarm.this, 0, intent, 0);
    			
    		break;
    	case PENDINGINTENT_TYPE_SERVICE:
    		 intent=new Intent(Alarm.this,ActionService.class);
    		 pendingIntent = PendingIntent.getService(Alarm.this, 0, intent, 0);
    		break;
    	case PENDINGINTENT_TYPE_INTENTSERVICE:
	   		 intent=new Intent(Alarm.this,ActionIntentService.class);
	   		 pendingIntent = PendingIntent.getService(Alarm.this, 0, intent, 0);
  		 	break;
    	}
		//��ȡ���ӹ�����
		AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
		alarmManager.cancel(pendingIntent);
		selectType=-1;
		Toast.makeText(Alarm.this, "�ϴ�����ȡ����", Toast.LENGTH_SHORT).show();
    }
    
    /**
     * ��ʽ��ʱ��Ϊ00:00   
     * @param h
     * @param m           
     * @return 
     */
    public String formatTime(int h , int m) {
    	StringBuffer buf = new StringBuffer();
    	if(h<10){
    		buf.append("0"+h);
    	}else {
			buf.append(h);
		}
    	buf.append(" : ");
    	if(m<10){
    		buf.append("0"+m);
    	}else {
    		buf.append(m);
		}
		return buf.toString();
	}
    private void setAlarm(){
    	//����������ʱ�䣬��Ҫ���������������պ͵�ǰͬ��
		calendar.setTimeInMillis(System.currentTimeMillis());
		//����������Сʱ�ͷ���
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		//����ͺ�������Ϊ0
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		//����Intent��PendingIntent���������ӹ�����
		Intent intent = null;
		PendingIntent pendingIntent = null;
		PendingIntent pendingIntent1 = null;
    	switch(selectType){
    	case PENDINGINTENT_TYPE_ACTIVITY:
    		 intent=new Intent(Alarm.this,ActionActivity.class);
    		 pendingIntent = PendingIntent.getActivity(Alarm.this, 0, intent, 0);
    		break;
    	case PENDINGINTENT_TYPE_RECEIVER:
    		 intent=new Intent(Alarm.this,AlarmReceiver.class);
    		 pendingIntent = PendingIntent.getBroadcast(Alarm.this, 0, intent, 0);
    			
    		break;
    	case PENDINGINTENT_TYPE_SERVICE:
    	     
    		 intent=new Intent(Alarm.this,ActionService.class);
    		 pendingIntent = PendingIntent.getService(Alarm.this, 0, intent, 0);
    		break;
    	case PENDINGINTENT_TYPE_INTENTSERVICE:
	   		 intent=new Intent(Alarm.this,ActionIntentService.class);
	   		 intent.putExtra("type", 0);//�������
	   		 pendingIntent = PendingIntent.getService(Alarm.this, 0, intent, 0);
	   		 intent.putExtra("type", 1);//�������
	   		 //�����ҪalarmManager��������ʱ��Ҫ��֤requestCode����Ψһ�Ϳ���
	   		 pendingIntent1 = PendingIntent.getService(Alarm.this, 1, intent, 0);
	   		 
	   		//����ִ�ж����ʱ
	     	calendar.set(Calendar.MINUTE, minute+1);
	     	alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent1);
	     	
   		 	break;
    		
    	}

    	//��������
    	alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    	
    	//�����ظ�����
//    	alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 10*1000, pendingIntent);
    	Toast.makeText(Alarm.this, "�������ӵ�ʱ��Ϊ��"+String.valueOf(hour)+":"+String.valueOf(minute), Toast.LENGTH_SHORT).show();
    	Log.d(TAG, "set the time to "+formatTime(hour,minute));
    }
	@Override
	public void onClick(View v) {
		cancelAlarm();
		//�жϲ�ͬ���
		switch(v.getId()){
		case R.id.activityBtn:
			selectType=PENDINGINTENT_TYPE_ACTIVITY;
			break;
		case R.id.receiverBtn:
			selectType=PENDINGINTENT_TYPE_RECEIVER;
			break;
		case R.id.serviceBtn:
			selectType=PENDINGINTENT_TYPE_SERVICE;
			break;
		case R.id.intentServiceBtn:
			selectType=PENDINGINTENT_TYPE_INTENTSERVICE;
			break;
		}
		setAlarm();
	}
}