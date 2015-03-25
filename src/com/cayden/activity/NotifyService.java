/**
 * NotifyService.java
 * Copyright (C) 2015
 * All right reserved. 2015-3-25
 */
package com.cayden.activity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;



import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

/**
 * ��ʾ֪ͨ�� 
 * @author cuiran
 * @version 1.0.0
 */
public class NotifyService extends Service {

	private static final String TAG = "NotifyService";



    private boolean mReflectFlg = false;

    private static final int NOTIFICATION_ID = 100; // ���id����Ϊ0,�ᵼ�²�������Ϊǰ̨service
    private static final Class<?>[] mSetForegroundSignature = new Class[] {
            boolean.class};
    private static final Class<?>[] mStartForegroundSignature = new Class[] {
            int.class, Notification.class};
    private static final Class<?>[] mStopForegroundSignature = new Class[] {
            boolean.class};

    private NotificationManager mNM;
    private Method mSetForeground;
    private Method mStartForeground;
    private Method mStopForeground;
    private Object[] mSetForegroundArgs = new Object[1];
    private Object[] mStartForegroundArgs = new Object[2];
    private Object[] mStopForegroundArgs = new Object[1];





    public NotifyService() {
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "LockServiceIntentHandler onCreate");


        mNM = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        try {
            mStartForeground = NotifyService.class.getMethod("startForeground", mStartForegroundSignature);
            mStopForeground = NotifyService.class.getMethod("stopForeground", mStopForegroundSignature);
        } catch (NoSuchMethodException e) {
            mStartForeground = mStopForeground = null;
        }

        try {
            mSetForeground = getClass().getMethod("setForeground",
                    mSetForegroundSignature);
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException(
                    "OS doesn't have Service.startForeground OR Service.setForeground!");
        }
        /**
         * ��ʾ��֪ͨ��
         */
        Notification.Builder builder = new Notification.Builder(this);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 100,
                new Intent(this, Alarm.class), 0);
        builder.setContentIntent(contentIntent);
        builder.setSmallIcon(R.drawable.icon);
        builder.setTicker(this.getString(R.string.app_name));
        builder.setContentTitle(this.getString(R.string.app_name));
        builder.setContentText(getString(R.string.enter));
        Notification notification = builder.getNotification();

        startForegroundCompat(NOTIFICATION_ID, notification);


    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.d(TAG, "LockServiceIntentHandler onStart -- startId is " + startId);
        onIntent(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");

        stopForegroundCompat(NOTIFICATION_ID);
    }


    protected void onIntent(Intent paramIntent) {
        try {
        	
        } catch (Exception e) {
           Log.d(TAG, "onIntent error", e);
        }
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }


    void invokeMethod(Method method, Object[] args) {
        try {
            method.invoke(this, args);
        } catch (InvocationTargetException e) {
            // Should not happen.
            Log.w("ApiDemos", "Unable to invoke method", e);
        } catch (IllegalAccessException e) {
            // Should not happen.
            Log.w("ApiDemos", "Unable to invoke method", e);
        }
    }

    /**
     * This is a wrapper around the new startForeground method, using the older
     * APIs if it is not available.
     */
    void startForegroundCompat(int id, Notification notification) {
        if (mReflectFlg) {
            // If we have the new startForeground API, then use it.
            if (mStartForeground != null) {
                mStartForegroundArgs[0] = Integer.valueOf(id);
                mStartForegroundArgs[1] = notification;
                invokeMethod(mStartForeground, mStartForegroundArgs);
                return;
            }

            // Fall back on the old API.
            mSetForegroundArgs[0] = Boolean.TRUE;
            invokeMethod(mSetForeground, mSetForegroundArgs);
            mNM.notify(id, notification);
        } else {
            /* ������ʹ�����·�������sdk���ڵ���5ʱ������sdk���еķ���startForeground����ǰ̨���У�
             * ������÷���ȡ�õ�sdk level 5����ӦAndroid 2.0�����²��еľɷ���setForeground����ǰ̨���� */

            if(Build.VERSION.SDK_INT >= 5) {
                startForeground(id, notification);
            } else {
                // Fall back on the old API.
                mSetForegroundArgs[0] = Boolean.TRUE;
                invokeMethod(mSetForeground, mSetForegroundArgs);
                mNM.notify(id, notification);
            }
        }
    }

    /**
     * This is a wrapper around the new stopForeground method, using the older
     * APIs if it is not available.
     */
    void stopForegroundCompat(int id) {
        if (mReflectFlg) {
            // If we have the new stopForeground API, then use it.
            if (mStopForeground != null) {
                mStopForegroundArgs[0] = Boolean.TRUE;
                invokeMethod(mStopForeground, mStopForegroundArgs);
                return;
            }

            // Fall back on the old API.  Note to cancel BEFORE changing the
            // foreground state, since we could be killed at that point.
            mNM.cancel(id);
            mSetForegroundArgs[0] = Boolean.FALSE;
            invokeMethod(mSetForeground, mSetForegroundArgs);
        } else {
            /* ������ʹ�����·�������sdk���ڵ���5ʱ������sdk���еķ���stopForegroundֹͣǰ̨���У�
             * ������÷���ȡ�õ�sdk level 5����ӦAndroid 2.0�����²��еľɷ���setForegroundֹͣǰ̨���� */

            if(Build.VERSION.SDK_INT >= 5) {
                stopForeground(true);
            } else {
                // Fall back on the old API.  Note to cancel BEFORE changing the
                // foreground state, since we could be killed at that point.
                mNM.cancel(id);
                mSetForegroundArgs[0] = Boolean.FALSE;
                invokeMethod(mSetForeground, mSetForegroundArgs);
            }
        }
    }

}
