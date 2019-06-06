package com.arouter.javapoet;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.arouter.javapoet.hook.AmsHookHelperUtils;
import com.arouter.javapoet.hook.MyInstrumentation;
import com.arouter.javapoet.hook.Reflex;
import com.arouter.javapoet.hook.TestHookActivity;
import com.arouter.javapoet.view.TouchView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hook();
    }

    private void hook() {


    }

    public void goTouch(View view) {
        Intent intent = new Intent(this, TouchActivity.class);
        startActivity(intent);
    }

    public void testHook(View view) {
        Intent intent = new Intent(this, TestHookActivity.class);
        startActivity(intent);
    }
}
