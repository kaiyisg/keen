package com.noc.keen;

import android.app.Application;
import android.content.Context;

/**
 * Created by kaiyilee on 2/11/17.
 */

public class KeenApplication extends Application {

    private static KeenApplication instance;

    public static KeenApplication getContext(){
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        initCore(this);
    }

    static void initCore(KeenApplication ctx) {
        instance = ctx;
    }

    public static void shutdown() {
        Context context = getContext();
        if (context != null) {
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}
