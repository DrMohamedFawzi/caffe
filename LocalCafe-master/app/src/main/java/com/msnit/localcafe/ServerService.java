package com.msnit.localcafe;

import static com.msnit.localcafe.App.CHANNEL_ID;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class ServerService extends Service {


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent notIntent =  new Intent(this,MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0,notIntent,0);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID).
                setContentTitle("جاري استقبال الطلبات").setContentText("اضغط لإيقاف او عرض الطلبات")
                .setSmallIcon(R.drawable.cafe_24).setContentIntent(pendingIntent).build();

        startForeground(1,notification);

        return START_STICKY;

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }
}
