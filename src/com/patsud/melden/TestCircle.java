package com.patsud.melden;

import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TestCircle extends Activity {


	
	HomeworkBox box;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.testview);
		
		box = (HomeworkBox) findViewById(R.id.homeworkBoxTest);
		Button button = (Button) findViewById(R.id.testButton);
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String[] aufgaben = {"labern","schwetzen"};
				boolean[] boolThing ={true,false};
				//box.createBox("Deutsch", aufgaben,boolThing);
				box.EasyTest("Hey Im Pat");
			}
		});
	}
}
