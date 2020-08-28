package com.example.office.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class User_service extends Service {

    private mybind bind = new mybind();
    private int Ids;

    public User_service() {
    }

    public class mybind extends Binder{
        public User_service getservice(){
            return User_service.this;
        }
        public void setids(int ids){
            Ids = ids;
        }
        public void queryids(){
            Log.d("现在服务里面IDS为", String.valueOf(Ids));
        }

    }
    @Override
    public IBinder onBind(Intent intent) {
        return bind;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }



    public void setDatainter(Callback callback) {
        this.callback = callback;
    }

    private Callback callback = null;
    public static interface Callback{
        void getUserData(int id);
    }




    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("服务的消息", "onDestroy: 我被干掉了");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("服务现在解绑了", "dddkkddkdkkdkd");

        return super.onUnbind(intent);
    }
}
