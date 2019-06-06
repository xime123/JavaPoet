package com.arouter.javapoet.hook;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class MyCallback implements Handler.Callback {
    Handler mBase;

    public MyCallback(Handler base) {
        mBase = base;
    }

    @Override
    public boolean handleMessage(Message msg) {
        int what = msg.what;
        Log.i("hookCallBack", "what=" + what);
        switch (what) {
            case 100:
                handleLaunchActivity(msg);
                break;
            default:
                break;

        }

        mBase.handleMessage(msg);
        return true;
    }

    private void handleLaunchActivity(Message msg) {
        try {
            Object obj = msg.obj;
            Intent intent = (Intent) Reflex.getFieldObject(obj.getClass().getName(), obj, "intent");
            Intent targetIntent = intent.getParcelableExtra(AmsHookHelperUtils.TUREINTENT);
            intent.setComponent(targetIntent.getComponent());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
