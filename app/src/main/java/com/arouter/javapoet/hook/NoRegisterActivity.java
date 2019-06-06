package com.arouter.javapoet.hook;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.arouter.javapoet.R;

public class NoRegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noregister);
        Log.i("NoRegisterActivity", "没有被注册的Activity被启动了");
    }
}
