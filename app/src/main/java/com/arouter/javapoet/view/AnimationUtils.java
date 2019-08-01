package com.arouter.javapoet.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

import com.arouter.javapoet.R;

public class AnimationUtils {
    public static void startPathAnimation(View view, Activity activity) {
        Context context = view.getContext();
        int startXY[] = new int[2];
        int endXY[] = new int[2];
        view.getLocationOnScreen(startXY);
        int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        int screenHeight = context.getResources().getDisplayMetrics().heightPixels;
        endXY[0] = screenWidth / 2;
        endXY[1] = screenHeight - 120;
        Path path = new Path();
        path.moveTo(startXY[0], startXY[1]);
        int cX = (startXY[0] + endXY[0]) / 2+200;
        int cY = startXY[1]-500;
        path.quadTo(cX, cY, endXY[0], endXY[1]);
        final PathMeasure pm = new PathMeasure(path, false);
        final ValueAnimator animator = ValueAnimator.ofFloat(0.0F, 1.0F);
        final ImageView imageView = new ImageView(context);
        imageView.setImageResource(R.mipmap.icon_shoppingcart_hover_nor);
        final ViewGroup rootView = (ViewGroup) activity.getWindow().getDecorView();
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rootView.addView(imageView, params);
        animator.setDuration(500);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                imageView.setScaleX((float) (1 - 0.5 * value));
                imageView.setScaleY((float) (1 - 0.5 * value));
                float pmLength = pm.getLength();
                float currPos[] = new float[2];
                // 将曲线看做有方向，且长度为pathLength的点集合，取长度值为pathLength * value的对应点的坐标
                pm.getPosTan(pmLength*value, currPos, null);
                imageView.setTranslationX(currPos[0]);
                imageView.setTranslationY(currPos[1]);
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                rootView.removeView(imageView);
            }
        });
        animator.start();
    }
}
