package com.patsud.melden.deprecated;

import java.util.zip.Inflater;

import com.patsud.melden.R;
import com.patsud.melden.R.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HomeworkBox extends LinearLayout{
	
	public HomeworkBox(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    public HomeworkBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public HomeworkBox(Context context) {
        super(context);
        initView(context);
    }

    TextView fach ,tv;
    private void initView(Context context) {
     /*   LinearLayout box = null;
        LayoutInflater inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        box.setOrientation(LinearLayout.VERTICAL);
        Button b = new Button(this);
		b.setText("I don't do anything, but I was added dynamically. :)");

        box.
         
        
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(inflater != null){       
            inflater.inflate(R.layout.thebox, this);
        }
        */
    //	 LinearLayout layout = (LinearLayout) findViewById(R.layout.thebox);
    	 
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.thebox,this);
        
       
        
       //   fach = (TextView) findViewById(R.id.boxTvFach);
       //  fach.setText("englisch");
      //    LinearLayout bla = (LinearLayout) findViewById(R.id.rlAssignement);
        //   tv = new TextView(this);
          
        //  tv.setText("Added tv");
      //    layout.addView(view);
          
    	
          
         
    }
    
    public void createBox(String fachName, String aufgaben[], boolean gemacht[]){
    //	LinearLayout box = new LinearLayout(this);
    	fach.setText(fachName);
    	
    	
    	
    	
    }
    
    public void EasyTest(String whatever){
        //	LinearLayout box = new LinearLayout(this);
        	fach.setText(whatever);
        	NewAssignment(null, true);
        }
    
    void NewAssignment(String assignment, boolean done){
    	LinearLayout assignmentLayout = null;
    	assignmentLayout.setGravity(LinearLayout.VERTICAL);
    	TextView tvAssi = null;
    	//tvAssi.setText("lalalla");
    	
    	//assignmentLayout.addView(tvAssi);
    	
    	
    }

}
