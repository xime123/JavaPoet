package com.arouter.javapoet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.arouter.javapoet.test.Test;
import com.arouter.javapoet.view.MyTextView;
import com.arouter.javapoet.view.TouchView;

public class TouchActivity extends AppCompatActivity {
    private String TAG = "TouchActivity::";
    private MyTextView tv;
    private TouchView touchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch);
        tv = findViewById(R.id.tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(Test.TAG_TEST, TAG + "onClick");
            }
        });
        touchView = findViewById(R.id.touch_view);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(Test.TAG_TEST, TAG +event.getAction()+"::"+ "onTouchEvent");
        return super.onTouchEvent(event);
    }
}
