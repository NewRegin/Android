package com.example.regin.draganddraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Regin on 15/6/15.
 */
public class BoxDrawingView extends View {
    private Box box;
    private ArrayList<Box> boxes = new ArrayList<Box>();
    private Paint boxPaint;
    private Paint backgroudPaint;

    public BoxDrawingView(Context context){
        this(context,null);
    }
    public BoxDrawingView(Context context,AttributeSet attributeSet){
        super(context,attributeSet);
        boxPaint = new Paint();
        boxPaint.setColor(Color.BLACK);
        boxPaint.setAlpha(255);
        backgroudPaint = new Paint();
        backgroudPaint.setColor(0xfff8efe0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        PointF curr = new PointF(event.getX(),event.getY());
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                box = new Box(curr);
                boxes.add(box);
                break;
            case MotionEvent.ACTION_MOVE:
                if (box != null){
                    box.setCurrent(curr);
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                box = null;
                break;
            case MotionEvent.ACTION_CANCEL:
                box = null;
                break;
        }
        return true;

    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawPaint(backgroudPaint);

        for (Box box : boxes){
            float left = Math.min(box.getOrigin().x,box.getCurrent().x);
            float right = Math.max(box.getOrigin().x,box.getCurrent().x);
            float top = Math.min(box.getOrigin().y,box.getCurrent().y);
            float bottom = Math.max(box.getOrigin().y, box.getCurrent().y);

            canvas.drawRect(left,top,right,bottom,boxPaint);
        }
    }
}
