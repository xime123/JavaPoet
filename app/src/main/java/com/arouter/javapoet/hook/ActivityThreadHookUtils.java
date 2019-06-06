package com.arouter.javapoet.hook;

import android.os.Handler;

public class ActivityThreadHookUtils {
    public static void hookActivityThread() {
        //首先我们获取当前的ActivityThread对象
        Object currentActivityThread = Reflex.getStaticFieldObject("android.app.ActivityThread", "sCurrentActivityThread");
        //然后获取对象的mH对象
        Handler mH = (Handler) Reflex.getFieldObject("android.app.ActivityThread", currentActivityThread, "mH");
        //将mH替换为我们的自己自定义的MyCallback。
        Reflex.setFieldObject(Handler.class, mH, "mCallback", new MyCallback(mH));
    }
}
