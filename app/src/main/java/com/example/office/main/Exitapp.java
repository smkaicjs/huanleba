package com.example.office.main;

import android.app.Activity;
import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class Exitapp{

    public static List<Activity> activityList = new ArrayList<>();
    public static void addactivity(Activity activity){
        activityList.add(activity);
    }
    public static void removeactivity(Activity activity){

        activityList.remove(activity);
    }

    public static void removeallactivity(){
        for (Activity activity:activityList){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }

    }
}
