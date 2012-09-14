package com.example.kpuzzle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;


public class KView extends View {
    Paint paint = new Paint();
    float m_X = 0;
    float m_Y = 0;
    
    public KView(Context context) {
        super(context);
        paint.setColor(Color.BLACK);
    }

    @Override
    public void onDraw(Canvas canvas) {
            canvas.drawLine(0, 0, 20, 20, paint);
            canvas.drawLine(20, 0, 0, 20, paint);
            canvas.drawLine(20, 0, 0, 20, paint);
            canvas.drawLine(0, 0, m_X, m_Y, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	float x = event.getX();
    	float y = event.getY();
    	m_X = x;
    	m_Y = y;
    	
    	invalidate();
    	return true;
    }
}
