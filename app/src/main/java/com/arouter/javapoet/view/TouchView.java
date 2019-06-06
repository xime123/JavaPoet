package com.arouter.javapoet.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import com.arouter.javapoet.R;
import com.arouter.javapoet.test.Test;

public class TouchView extends RelativeLayout implements SeekBar.OnSeekBarChangeListener {
    private String TAG="TouchView::";
    private SeekBar seekBar;
    private RadialGradientView rgv;

    public TouchView(@NonNull Context context) {

        this(context,null);
    }

    public TouchView(@NonNull Context context, @Nullable AttributeSet attrs) {
       this(context,attrs,0)
;    }

    public TouchView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.red_layout,this);
        seekBar=findViewById(R.id.sb);
        rgv=findViewById(R.id.rgv);
        seekBar.setOnSeekBarChangeListener(this);
        setBackgroundColor(0xFF3F51B5);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(Test.TAG_TEST,TAG+event.getAction()+"::"+"onTouchEvent");
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e(Test.TAG_TEST,TAG+ev.getAction()+"::"+"onInterceptTouchEvent");
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(Test.TAG_TEST,TAG+ev.getAction()+"::"+"dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        rgv.setBgProgress(1.0F * progress / seekBar.getMax());
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
