package com.arouter.javapoet.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

import com.arouter.javapoet.test.Test;

public class MyTextView extends TextView {
    private String TAG=MyTextView.class.getSimpleName()+"::";
    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(Test.TAG_TEST,TAG+event.getAction()+"::"+"onTouchEvent");
//        switch (event.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                return false;
//        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e(Test.TAG_TEST,TAG+event.getAction()+"::"+"dispatchTouchEvent");
        return super.dispatchTouchEvent(event);
    }

}
