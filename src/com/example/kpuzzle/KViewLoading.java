package com.example.kpuzzle;

import android.content.Context;
import android.view.View;
import android.widget.*;

public class KViewLoading{
	int pr = 0x1;
	private ProgressBar m_bar;
	private LinearLayout m_layout;
	public KViewLoading(Context context){
		//super(context);
		m_layout = new LinearLayout(context);
		m_bar = new ProgressBar(context);
		m_bar.setMax(100);
		m_layout.addView(m_bar);
	}
	
	public LinearLayout layout(){
		return m_layout;
	}
	
	public void set(int i){
		m_bar.setProgress(i);
	}
	
	public int get(int i){
		return m_bar .getProgress();
	}
	
	public void reset(){
		m_bar.setProgress(0);
	}
}
