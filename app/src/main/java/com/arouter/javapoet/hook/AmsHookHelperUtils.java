package com.arouter.javapoet.hook;

import android.os.Build;

import java.lang.reflect.Proxy;


public class AmsHookHelperUtils {
    public static final String TUREINTENT = "tureintent";

    public static void hookAmn() throws ClassNotFoundException {
        Object singleton = null;
        if (Build.VERSION.SDK_INT >= 27) {//是不是从27开始改成了这种方式有待确认
            singleton = Reflex.getStaticFieldObject("android.app.ActivityManager", "IActivityManagerSingleton");
        } else {
            singleton = Reflex.getStaticFieldObject("android.app.ActivityManagerNative", "gDefault");
        }

        //mInstance 其实就是IActivityManager的实现对象
        Object mInstance = Reflex.getFieldObject("android.util.Singleton", singleton, "mInstance");

        Class<?> classInterface = Class.forName("android.app.IActivityManager");
        //IActivityManager 的代理对象  代理上面的mInstance对象
        Object proxy = Proxy.newProxyInstance(classInterface.getClassLoader(),
                new Class<?>[]{classInterface}, new AMNInvocationHanlder1(mInstance));
        //把Singleton 里面的mInstance（也就是我们的IActivityManager）替换成代理对象
        Reflex.setFieldObject("android.util.Singleton", singleton, "mInstance", proxy);
    }
}
