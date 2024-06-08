package com.example.notificationlistener;

import android.app.Notification;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

public class MyNotificationListenerService extends NotificationListenerService {

    private static final String TAG = "NotificationListener";

    @Override
    public void onNotificationPosted(StatusBarNotification sbn){
        Notification notification = sbn.getNotification();
        if(notification != null){
            String packageName = sbn.getPackageName();
            CharSequence title = notification.extras.getCharSequence(Notification.EXTRA_TITLE);
            CharSequence text = notification.extras.getCharSequence(Notification.EXTRA_TEXT);
            Log.i(TAG,"Notification posted from: " + packageName);
            Log.i(TAG,"Title: "+ title);
            Log.i(TAG,"Text: "+ text);
        }
    }

}
