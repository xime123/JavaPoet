package com.arouter.javapoet.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class RadialGradientView extends View {
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    // 环形渐变渲染
    private Shader mRadialGradient = null;

    public RadialGradientView(Context context) {
        super(context);
    }

    public RadialGradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RadialGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 颜色表示值未ARGB格式，A代表透明度，MIN_COLOR_TRANSPARENT就是表示透明度
     */
    private static final int MIN_COLOR_TRANSPARENT = 0x70;
    /**
     * 把透明度赋值上去，因为ARGB各占四个字节，RGB占三个字节也就是24位，所以是左移24为，其实也可以直接写成0x70FFFFFF也可以
     */
    private int mCenterColor = MIN_COLOR_TRANSPARENT << 24 | 0x00FFFFFF;

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float centerX = getWidth() / 2;
        float centerY = getHeight() / 2;
        int radius = (int) (Math.min(centerX, centerY) * 1.8F);
        mRadialGradient = new RadialGradient(centerX, centerY, radius, new int[]{
                mCenterColor, 0x00FFFFFF}, new float[]{0.1F,1},
                Shader.TileMode.CLAMP);

        // 绘制环形渐变
        mPaint.setShader(mRadialGradient);
        // 第一个,第二个参数表示圆心坐标
        // 第三个参数表示半径
        canvas.drawCircle(centerX, centerY, radius, mPaint);//画一个容器，大的园
    }

    public void setBgProgress(float progress){
        //算透明度的值，transparent在progeress为100时，接近255也就是不透明
        int transparent = (int) (MIN_COLOR_TRANSPARENT + progress * (0xFF - MIN_COLOR_TRANSPARENT));
        mCenterColor = transparent << 24 | 0x00FFFFFF;
        postInvalidate();
    }
}
