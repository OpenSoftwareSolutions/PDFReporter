package org.oss.pdfreporter.android;

import java.util.Arrays;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends Activity {

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		View view = findViewById(R.id.view1);
		view.setBackgroundDrawable(new TrapezoidDrawable(getResources().getColor(R.color.bg_light), (float)347/453));
		
		ListView list = (ListView) findViewById(R.id.listView1);
		list.setAdapter(new ReportAdapter(this, Arrays.asList("Test1", "Test2", "Test3", "Test2", "Test3", "Test2", "Test3" )));
		list.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				String text = (String) arg0.getItemAtPosition(arg2);
				TextView tView = (TextView) findViewById(R.id.spinnerText);
				tView.setText(text);
				hidePicker();
			}
		});
		
		findViewById(R.id.picker).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				hidePicker();
			}
		});
		
		findViewById(R.id.linearLayout1).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showPicker();
			}
		});
		
		
		findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
								
			}
		});
	}
	
	@Override
	public void onBackPressed() {
		if(findViewById(R.id.picker).getVisibility() == View.VISIBLE) {
			hidePicker();
		}
		else {
			super.onBackPressed();
		}
	}
	
	private void hidePicker() {
		findViewById(R.id.picker).startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_up));
		findViewById(R.id.picker).setVisibility(View.GONE);
	}
	
	private void showPicker() {
		findViewById(R.id.picker).startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_up));
		findViewById(R.id.picker).setVisibility(View.VISIBLE);
	}
}
