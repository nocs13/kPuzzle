package com.example.kpuzzle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

public class KViewGame extends View{
	public class Item{
		public int num = -1;
		public int pos = -1;
	}
	
	boolean game_finish = false;
    Paint paint = new Paint();
    Item []items;
    int fpos = 15;
    
	public KViewGame(Context context){
		super(context);
        paint.setColor(Color.BLACK);
        
        items = new Item[15];
		
        for(int i = 0; i < 15; i++){
        	items[i] = new Item();
        	items[i].num = i + 1;
        	
        	while(true){
        		boolean exist = false;
        		int k = (int) (Math.random() * 15);
        		for(int j = i; j >= 0; j--){
        			if(items[j].pos == k){
        				exist = true;
        				break;
        			}
        		}
        		if(exist == false){
        			items[i].pos = k;
        			break;
        		}
        	}
        }
	}
	
	public void reset(){
		items = null;
        items = new Item[15];
		
        for(int i = 0; i < 15; i++){
        	items[i] = new Item();
        	items[i].num = i + 1;
        	
        	while(true){
        		boolean exist = false;
        		int k = (int) (Math.random() * 15);
        		for(int j = i; j >= 0; j--){
        			if(items[j].pos == k){
        				exist = true;
        				break;
        			}
        		}
        		if(exist == false){
        			items[i].pos = k;
        			break;
        		}
        	}
        }
	}
	
	public void check(){
		if(game_finish)
			return;
		
		for(int i = 0; i < 15; i++)
			if(items[i].pos != i)
				return;
		
		game_finish = true;
		
		MainActivity ma = (MainActivity)this.getContext();
		ma.fine();
	}
	
    @Override
    public void onDraw(Canvas canvas) {
    	int swidth = this.getWidth();
    	int sheight = this.getHeight();
    	int iwidth = swidth / 4;
    	int iheight = sheight / 4;
    	int tsize = iwidth / 4;
    	
    	paint.setTextSize((float)tsize);
    	
//        /*
        for(int i = 0; i < 15; i++){
        	int num = items[i].num;
        	int pos = items[i].pos;
        	
        	int row = pos / 4; 
        	int col = pos % 4; 
        	float lx = col * iwidth;
        	float ly = row * iheight;
        	float rx = (col + 1) * iwidth;
        	float ry = (row + 1) * iheight;
        	
            paint.setColor(Color.BLACK);
        	canvas.drawRect(lx, ly, rx, ry, paint);

            paint.setColor(Color.RED);
            canvas.drawLine(lx, ly, rx, ly, paint);
            canvas.drawLine(rx, ly, rx, ry, paint);
            canvas.drawLine(rx, ry, lx, ry, paint);
            canvas.drawLine(lx, ry, lx, ly, paint);
        	
        	String text = Integer.toString(num);
        	
            paint.setColor(Color.YELLOW);
        	canvas.drawText(text, (float)((lx + rx) * 0.5 - (float)(tsize * 0.5)), 
        					(float)((ly + ry) * 0.5 + (float)(tsize * 0.5)), paint);
        }
//        */
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	float x = event.getX();
    	float y = event.getY();
    	
    	int swidth = this.getWidth();
    	int sheight = this.getHeight();
    	int iwidth = swidth / 4;
    	int iheight = sheight / 4;
    	
        for(int i = 0; i < 15; i++){
        	int num = items[i].num;
        	int pos = items[i].pos;
        	
        	int row = pos / 4; 
        	int col = pos % 4; 
        	float lx = col * iwidth;
        	float ly = row * iheight;
        	float rx = (col + 1) * iwidth;
        	float ry = (row + 1) * iheight;
        	
        	if( (x > lx) && (y > ly) && 
        		(x < rx) && (y < ry) ){
        		if((pos == (fpos + 1)) || (pos == (fpos - 1)) ||
        			(pos == (fpos + 4)) || (pos == (fpos - 4)) ){
        			items[i].pos = fpos;
        			fpos = pos;
        			break;
        		}
        	}
        }
    	
        check();
        
    	invalidate();
    	return true;
    }
}
