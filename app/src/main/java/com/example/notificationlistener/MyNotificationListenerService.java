package com.example.notificationlistener;

import android.app.Notification;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
            if(text !=null) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://ipAdresss:8080")   //cmd ipConfig
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                TransactionApi transactionApi = retrofit.create(TransactionApi.class);
                transactionApi.save(text.toString()).enqueue(new Callback<Object>() {
                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {
                        Toast.makeText(MyNotificationListenerService.this, "successful", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Object> call, Throwable throwable) {
                        Toast.makeText(MyNotificationListenerService.this, "failed successful", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }

}
