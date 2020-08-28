package com.example.office.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.office.R;
import com.example.office.main.BaseActivity;
import com.example.office.main.MainActivity;
import com.example.office.mcontext.Mycontext;

import java.util.Timer;
import java.util.TimerTask;

public class Tiaozhuan extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiaozhuan);
        final Intent intent = new Intent(Mycontext.getcontext(), MainActivity.class);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                startActivity(intent);
            }
        };
        Timer timer = new Timer();
        timer.schedule(task,3000);



    }
}