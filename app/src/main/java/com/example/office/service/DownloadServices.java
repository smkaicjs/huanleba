package com.example.office.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

import com.example.office.R;
import com.example.office.activity.User_firstpage;
import com.example.office.activity.async;

public class DownloadServices extends Service {
    private binder mbinder = new binder();
    private async masync;
    private async.DownloaderLinster linster = new async.DownloaderLinster() {
        @Override
        public void pause() {

        }

        @Override
        public void success() {
            masync = null;
            stopForeground(true);
        }

        @Override
        public void cancle() {

            masync = null;
            stopForeground(true);
        }

        @Override
        public void failed() {
            masync = null;
            stopForeground(true);
        }

        @Override
        public void onproess(int proess) {

            getnotimanager().notify(1,getNoti(proess));

        }
    };
    public DownloadServices() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mbinder;
    }






    public class binder extends Binder{
        public void startdownload(String url){
            if (masync == null){
                masync = new async(linster);
                masync.execute(url);
                startForeground(1,getNoti(0));
            }

        }
        public void Pausedownload(){

            if (masync!= null){
                masync.setPause(true);
            }
        }
        public void Cancledownload(){
            if (masync!= null){
                masync.setCancele(true);
            }

        }

    }
    public NotificationManager getnotimanager(){
        return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }
    public Notification getNoti(int process){
        //
        String id = "smk";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "下载通知";
            String description = "这是下载通知";

            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(id, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            getnotimanager().createNotificationChannel(channel);
        }

        NotificationCompat.Builder notification = new NotificationCompat.Builder(this,id);
        Intent intent = new Intent(this, User_firstpage.class);
        PendingIntent pi = PendingIntent.getActivity(this,0,intent,0);
        notification.setContentIntent(pi);
        notification.setSmallIcon(R.mipmap.ic_launcher);
        notification.setContentTitle("downloading");
        if (process>=0){
            notification.setContentText(process+"%");
            notification.setProgress(100,process,false);
        }
        return notification.build();
    }
}
