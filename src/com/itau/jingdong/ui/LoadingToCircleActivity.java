package com.itau.jingdong.ui;



import com.itau.weicircle.MimeWeiCircle;
import com.mychoice.R;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.WindowManager;
import android.widget.Toast;

public class LoadingToCircleActivity extends Activity{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.loadingtocircle);
			
	new Handler().postDelayed(new Runnable(){
		@Override
		public void run(){
			Intent intent = new Intent (LoadingToCircleActivity.this,MimeWeiCircle.class);			
			startActivity(intent);			
			LoadingToCircleActivity.this.finish();
			Toast.makeText(getApplicationContext(), "进入微信", Toast.LENGTH_SHORT).show();
		}
	}, 200);
   }
}