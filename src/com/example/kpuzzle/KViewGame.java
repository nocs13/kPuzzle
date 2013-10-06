package com.example.kpuzzle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class KViewGame extends View
{
	public class Item{
		public int num = -1;
		public int pos = -1;
	}
	
	public static  int current_level = 1;
	public static  int number_of_levels = 5;
	
	boolean game_finish = false;
    Paint   paint       = new Paint();
    int     fpos        = 15;
    Item []items;
    
    Context current_context = null;
    Bitmap  current_bitmap  = null;
    
	public KViewGame(Context context){
		super(context);
		current_context = context;
        paint.setColor(Color.BLACK);
        
        items = new Item[15];
        
        String bmp_file = "lev";
        
        if(current_level < 10)
        {
        	bmp_file += "00" + Integer.toString(current_level) + "";
        }
        else if(current_level < 100)
        {
        	bmp_file += "0" + Integer.toString(current_level) + "";
        }
        
        current_bitmap = getBitmap(bmp_file);
        
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

        invalidate();
	}
	
	public void reset(){
		items = null;
        items = new Item[15];
        fpos  = 15;
		
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
        
        if(current_bitmap != null)
        {
        	current_bitmap.recycle();
        	current_bitmap = null;
        }
        
        String bmp_file = "lev";
        
        if(current_level < 10)
        {
        	bmp_file += "00" + Integer.toString(current_level) + "";
        }
        else if(current_level < 100)
        {
        	bmp_file += "0" + Integer.toString(current_level) + "";
        }
        
        current_bitmap = getBitmap(bmp_file);
        
        invalidate();
	}
	
	public void check(){
		if(game_finish)
			return;
		
		for(int i = 0; i < 3; i++)
			if(items[i].pos != i)
				return;
		
		game_finish = true;
		MainActivity ma = (MainActivity)this.getContext();
		
		if(current_level < number_of_levels)
		{
		  current_level ++;
		  ma.next();
		}
		else
		{
		  current_level = 1;
  	      ma.fine();
		}		
	}
	
    @Override
    public void onDraw(Canvas canvas) {
    	int swidth = this.getWidth();
    	int sheight = this.getHeight();
    	int iwidth = swidth / 4;
    	int iheight = sheight / 4;
    	int tsize = iwidth / 4;
    	
    	int bmp_width = current_bitmap.getWidth();
    	int bmp_height = current_bitmap.getHeight();
    	int bmp_iwidth = current_bitmap.getWidth() / 4;
    	int bmp_iheight = current_bitmap.getHeight() / 4;
    	
    	paint.setTextSize((float)tsize);
    	canvas.drawBitmap(current_bitmap, 
    			          new Rect(0, 0, current_bitmap.getWidth(), current_bitmap.getHeight()), 
    			          new Rect(0, 0, this.getWidth(), this.getHeight()), paint);
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
        	
        	int brow = (num - 1) / 4; 
        	int bcol = (num - 1) % 4; 
        	float blx = bcol * bmp_iwidth;
        	float bly = brow * bmp_iheight;
        	float brx = (bcol + 1) * bmp_iwidth;
        	float bry = (brow + 1) * bmp_iheight;
        	
        	int frow = fpos / 4; 
        	int fcol = fpos % 4; 
        	float flx = fcol * iwidth;
        	float fly = frow * iheight;
        	float frx = (fcol + 1) * iwidth;
        	float fry = (frow + 1) * iheight;
        	
            paint.setColor(Color.BLACK);
        	canvas.drawRect(lx, ly, rx, ry, paint);
        	
        	canvas.drawBitmap(current_bitmap, 
			          new Rect((int)blx, (int)bly, (int)brx, (int)bry), 
			          new Rect((int)lx, (int)ly, (int)rx, (int)ry), paint);

            paint.setColor(Color.RED);
            canvas.drawLine(lx, ly, rx, ly, paint);
            canvas.drawLine(rx, ly, rx, ry, paint);
            canvas.drawLine(rx, ry, lx, ry, paint);
            canvas.drawLine(lx, ry, lx, ly, paint);
            /*        	
        	String text = Integer.toString(num);
        	
            paint.setColor(Color.YELLOW);
        	canvas.drawText(text, (float)((lx + rx) * 0.5 - (float)(tsize * 0.5)), 
        					(float)((ly + ry) * 0.5 + (float)(tsize * 0.5)), paint);
            */
        	paint.setColor(Color.RED);
        	paint.setAlpha(90);
            canvas.drawRect(flx, fly, frx, fly, paint);
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
    	
    	System.out.print("touch updated");
    	return true;
    }
    
    public Bitmap getBitmap(String sid)
	{
		Bitmap bmp = null;

		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inPreferredConfig = Bitmap.Config.ARGB_8888;

		int id = current_context.getResources().getIdentifier(sid, "drawable",
				current_context.getPackageName());
		bmp = BitmapFactory.decodeResource(current_context.getResources(), id,
				opts);

		if (bmp == null) {
			System.out.println("No bitmap found");
		}

		return bmp;
	}
}
