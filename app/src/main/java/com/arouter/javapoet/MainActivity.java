package com.arouter.javapoet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.arouter.javapoet.hook.TestHookActivity;
import com.arouter.javapoet.netty.udp.core.UDPClient;
import com.arouter.javapoet.view.AnimationUtils;


public class MainActivity extends AppCompatActivity {
    UDPClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hook();
        client = new UDPClient();
    }

    private void hook() {


    }

    public void goTouch(View view) {
//        Intent intent = new Intent(this, TouchActivity.class);
//        startActivity(intent);
        AnimationUtils.startPathAnimation(view,this);
    }

    public void testHook(View view) {
        Intent intent = new Intent(this, TestHookActivity.class);
        startActivity(intent);
    }

    public void sdk(View view) {
        client.send("from client", null, 0);
    }
}
