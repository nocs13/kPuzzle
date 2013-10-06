package com.example.kpuzzle;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v4.app.NavUtils;
import android.graphics.Color;
import android.widget.*;

public class MainActivity extends Activity {

	KView m_view;
	KViewLoading m_viewLoading;
	KViewStart m_viewStart;
	KViewGame m_viewGame = null;
	
	Button m_btn;
	LinearLayout m_layout;
	
	int m_state;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    	m_state = 0;
    	
    	//m_viewGame = new KViewGame(this);
        //setContentView(m_viewGame);
    	if(m_viewGame == null){
    		m_viewGame = new KViewGame(this);
    		setContentView(m_viewGame);
    	}
        
        //m_viewStart = new KViewStart(this);
        //setContentView(m_viewStart.layout());
        
        m_viewLoading = new KViewLoading(this);
        m_viewLoading.reset();
        
        //setContentView(m_loading.layout());
        //m_view = new KView(this);
        //m_view.setBackgroundColor(Color.WHITE);
        //setContentView(m_view);
        
        //m_layout = new LinearLayout(this);
        //m_btn = new Button(this);
        //m_btn.setText("mybutton");
        //m_btn.setOnClickListener(new View.OnClickListener() {
        //    public void onClick(View v) {
        //        // Perform action on click
        //    	gameQuit();
        //    }
        //});
        //m_layout.addView(m_btn);
        //setContentView(m_layout);
        
//        setContentView(this.findViewById(R.layout.hello));
        
        //setContentView(R.layout.hello);
        
        /*final Button button = (Button) findViewById(R.id.btn_hello_continue);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            }
        });*/
        
        /*button = (Button) findViewById(R.id.btn_hello_start);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            }
        });        
        final button = (Button) findViewById(R.id.btn_hello_exit);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            }
        });*/        
        //setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            /*moveTaskToBack(true);
        	if(m_state != 0){
        		m_state = 0;
                setContentView(m_viewStart.layout());
        	}else{
        		moveTaskToBack(true);
        	}*/
        	
        	gameQuit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
        }
    }    
    
    public boolean gameQuit(){
    	this.finish();
    	return true;
    }
    
    public boolean gameStart(){
    	m_state = 1;
        setContentView(m_viewLoading.layout());
    	m_state = 2;
        //KViewGame vg = new KViewGame(this);
        //setContentView(vg);
    	if(m_viewGame != null){
    		m_viewGame.reset();
            setContentView(m_viewGame);
    	}
    	return true;
    }
    
    public boolean gameContinue(){
    	m_state = 1;
        setContentView(m_viewLoading.layout());
    	m_state = 2;
        KViewGame vg = new KViewGame(this);
        setContentView(vg);
    	return true;
    }
    
    public int alert(String text){
    	AlertDialog ad = new AlertDialog.Builder(this).create();
    	ad.setCancelable(false);
    	ad.setMessage(text);
    	ad.setButton("OK", new DialogInterface.OnClickListener() {  
    	    @Override  
    	    public void onClick(DialogInterface dialog, int which) {  
    	        dialog.dismiss();                      
    	    }  
    	});  
    	ad.show();    	
    	return 0;
    }
    
    public void fine(){
    	m_state = 1;
        setContentView(m_viewLoading.layout());
    	
    	AlertDialog ad = new AlertDialog.Builder(this).create();
    	ad.setCancelable(false);
    	ad.setMessage("Congradulations...");
    	ad.setButton("Finish", new DialogInterface.OnClickListener() {  
    	    @Override  
    	    public void onClick(DialogInterface dialog, int which) {  
    	        dialog.dismiss();
    	        gameQuit();
    	        //gameStart();
    	    }  
    	});  
/*    	ad.setButton2("Quit", new DialogInterface.OnClickListener() {  
    	    @Override  
    	    public void onClick(DialogInterface dialog, int which) {  
    	        dialog.dismiss();
    	        gameQuit();
    	    }  
    	});*/  
    	ad.show();    	
    }
    
    public void next(){
    	m_state = 1;
        setContentView(m_viewLoading.layout());
    	
    	AlertDialog ad = new AlertDialog.Builder(this).create();
    	ad.setCancelable(false);
    	ad.setMessage("Congradulations...");
    	ad.setButton("Next", new DialogInterface.OnClickListener() {  
    	    @Override  
    	    public void onClick(DialogInterface dialog, int which) {  
    	        dialog.dismiss();
    	        gameStart();
    	    }  
    	});  
/*    	ad.setButton2("Quit", new DialogInterface.OnClickListener() {  
    	    @Override  
    	    public void onClick(DialogInterface dialog, int which) {  
    	        dialog.dismiss();
    	        gameQuit();
    	    }  
    	});*/  
    	ad.show();    	
    }
}
