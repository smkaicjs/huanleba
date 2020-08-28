package com.example.office.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.office.mcontext.Mycontext;

public class BaseActivity extends AppCompatActivity {
    private reciver my_reciver=null;
    private LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Exitapp.addactivity(this);

    }



    @Override
    protected void onResume() {
        super.onResume();
        localBroadcastManager = LocalBroadcastManager.getInstance(getApplicationContext());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.office.QUIT");
        my_reciver = new reciver();
        this.localBroadcastManager.registerReceiver(my_reciver,intentFilter);
        Log.d("注册成功了", "onResume:在这里注册成功了 ");
    }



    @Override
    protected void onPause() {
        super.onPause();
        if (my_reciver!=null){
            localBroadcastManager.unregisterReceiver(my_reciver);
            my_reciver = null;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Exitapp.removeactivity(this);

    }

    class reciver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Exitapp.removeallactivity();
            Intent intent1 = new Intent(Mycontext.getcontext(),MainActivity.class);
            startActivity(intent1);


        }
    }
}
