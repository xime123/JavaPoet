package com.arouter.javapoet.app;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    public static Context context;
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        context = getApplicationContext();
    }

    public static Application getAppInstance() {
        return instance;
    }
}
