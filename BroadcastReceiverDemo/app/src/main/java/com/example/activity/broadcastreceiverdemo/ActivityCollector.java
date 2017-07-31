package com.example.activity.broadcastreceiverdemo;

import android.app.Activity;

import java.util.ArrayList;

/**
 * Created by duyh on 2017/7/26.
 */

public class ActivityCollector {
    public static ArrayList<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity){
        activities.remove(activity);
    }

    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }

    public static void finishAll(){
        for (Activity activity : activities){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
    }

}
