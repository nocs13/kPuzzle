package com.example.kpuzzle;

import android.content.Context;
import android.view.View;
import android.widget.*;

public class KViewStart{
	int pr = 0x1;
	private Button m_continue, 
					m_start,
					m_quit;
	private LinearLayout m_layout;
	private MainActivity m_main;
	
	public KViewStart(Context context){
		//super(context);
		m_main = (MainActivity)context;
		
		m_layout = new LinearLayout(context);
		
		m_continue = new Button(context);
		m_continue.setText("Continue");
		m_continue.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	m_main.gameContinue();
            }
        });
		m_start = new Button(context);
		m_start.setText("Start");
		m_start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	m_main.gameStart();
            }
        });
		m_quit = new Button(context);
		m_quit.setText("Quit");
		m_quit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	m_main.gameQuit();
            }
        });
		
		m_layout.addView(m_continue);
		m_layout.addView(m_start);
		m_layout.addView(m_quit);
	}
	
	public LinearLayout layout(){
		return m_layout;
	}
	
}
