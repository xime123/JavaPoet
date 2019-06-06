package com.arouter.javapoet.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

import com.arouter.javapoet.test.Test;

public class MyButton extends Button {
    private String TAG=MyButton.class.getSimpleName()+"::";
    public MyButton(Context context) {
        super(context);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(Test.TAG_TEST,TAG+"onTouchEvent");
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e(Test.TAG_TEST,TAG+"dispatchTouchEvent");
        return super.dispatchTouchEvent(event);
    }
}
