package com.arouter.javapoet.hook;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.arouter.javapoet.MainActivity;
import com.arouter.javapoet.R;

public class TestHookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_hook);
    }

    /**
     * hook Instrumentation
     *
     * @param view
     */
    public void hookInstrumentation(View view) {
        Instrumentation instrumentation = (Instrumentation) Reflex.getFieldObject(Activity.class, TestHookActivity.this, "mInstrumentation");
        MyInstrumentation instrumentation1 = new MyInstrumentation(instrumentation);
        Reflex.setFieldObject(Activity.class, this, "mInstrumentation", instrumentation1);
        showToast("hook Instrumentation成功");
    }

    /**
     * 跳转到一个没有注册过的页面
     *
     * @param view
     */
    public void gotoNoRegister(View view) {
        Intent intent = new Intent(this, NoRegisterActivity.class);
        startActivity(intent);
    }

    /**
     * hook AMS
     *
     * @param view
     */
    public void hookAms(View view) {
        try {
            AmsHookHelperUtils.hookAmn();
            showToast("hook AMS成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    public void hookActivityThread(View view) {
        ActivityThreadHookUtils.hookActivityThread();
        showToast("hook ActivityThread成功");
    }
}
