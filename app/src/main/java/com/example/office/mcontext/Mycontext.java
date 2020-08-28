package com.example.office.mcontext;

import android.app.Application;
import android.content.Context;

import org.litepal.LitePal;

public class Mycontext extends Application {
    private static Context mcontext;

    @Override
    public void onCreate() {
        super.onCreate();
        mcontext = getApplicationContext();
        LitePal.initialize(mcontext);
    }
    public static Context getcontext(){return mcontext;}
}
