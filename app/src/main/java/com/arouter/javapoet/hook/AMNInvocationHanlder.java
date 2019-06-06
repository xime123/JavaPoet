package com.arouter.javapoet.hook;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class AMNInvocationHanlder implements InvocationHandler {
    private String actionName = "startActivity";

    private Object target;

    public AMNInvocationHanlder(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (method.getName().equals(actionName)) {
            Log.i("---", "啦啦啦我是hook AMN进来的");
            return method.invoke(target, args);
        }

        return method.invoke(target, args);
    }

}
