package com.example.regin.draganddraw;

import android.graphics.Point;
import android.graphics.PointF;

/**
 * Created by Regin on 15/6/15.
 */
public class Box {
    private PointF origin;
    private PointF current;

    public PointF getCurrent() {
        return current;
    }

    public void setCurrent(PointF current) {
        this.current = current;
    }

    public PointF getOrigin() {
        return origin;
    }

    public Box(PointF pointF){
        origin = pointF;
        current = pointF;


    }
}
