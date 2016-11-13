package com.example.sth0409.gaodeguiji.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by STH0409 on 2016/10/25.
 */

public class Yuan extends View {

    private RectF oval;
    private Paint paint;

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public float getI() {
        return i;
    }

    public void setI(float i) {
        this.i = i;
    }

    public float getI1() {
        return i1;
    }

    public void setI1(float i1) {
        this.i1 = i1;
    }

    private float i1 ;
    private float i ;

    public Yuan(Context context) {
        super(context);
    }

    public Yuan(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public Yuan(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int hight = MeasureSpec.getSize(heightMeasureSpec);
        oval = new RectF(0, 0, width, hight);
        setMeasuredDimension(width, hight);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawArc(oval, i1, i, true, paint);
        super.onDraw(canvas);
    }

    public void startDraw( Paint paint,float s,float e) {
        this.paint=paint;
        this.i1=s;
        i=e;
        postInvalidate();
    }


}
