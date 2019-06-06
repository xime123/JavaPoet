package com.arouter.javapoet.hook;

import android.content.ComponentName;
import android.content.Intent;

import com.arouter.javapoet.TouchActivity;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class AMNInvocationHanlder1 implements InvocationHandler {
    private String actionName = "startActivity";
    private String packageName = "com.arouter.javapoet";
    private Object target;

    public AMNInvocationHanlder1(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals(actionName)) {
            Intent intent;
            int index = 0;
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof Intent) {
                    index = i;
                    break;
                }
            }
            intent = (Intent) args[index];
            Intent newIntent = new Intent();
            ComponentName componentName = new ComponentName(packageName, PitActivity.class.getName());
            newIntent.setComponent(componentName);
            //把
            newIntent.putExtra(AmsHookHelperUtils.TUREINTENT, intent);
            args[index] = newIntent;
        }
        return method.invoke(target, args);
    }
}
