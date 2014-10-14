package com.itau.weicircle;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.zxing.common.StringUtils;
import com.itau.weicircle.XListView.IXListViewListener;
import com.mychoice.R;


import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;

public class MimeWeiCircle extends Activity implements IXListViewListener,OnClickListener {

	
	private XListView circle_list;
    private MimeShareAdapter adapter;
    private ArrayList<Map<String, Object>> mlist = new ArrayList<Map<String, Object>>();
    
    
	private RelativeLayout pub_layout;
	private RelativeLayout pub_layout_01;
	

	
	private ImageButton ib01;
	private ImageButton ib02;
	private ImageButton ib03;
	private ImageButton ib04;
    
	private Boolean flag=true;
    
	private static final int TAKE_PICTURE = 0x000000;
	private String path = "";
	
	private ImageButton mime_circle_pub;
	private Button btn_back;
	private List<ItemEntity> pricelist = new ArrayList();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mimecircle);
		
		initData();
		
		
		circle_list=(XListView)findViewById(R.id.circle_list);
		
		
		circle_list.addHeaderView(getheadView());
		
		
		
		circle_list.setPullLoadEnable(false);
		circle_list.setXListViewListener(this);
		circle_list.setRefreshTime(new Date().toLocaleString());
		
		
		pub_layout=(RelativeLayout)findViewById(R.id.blay);
		pub_layout.setOnClickListener(this);
		pub_layout_01=(RelativeLayout)findViewById(R.id.blay1);
		
		ib01=(ImageButton)findViewById(R.id.compose_01);
		ib02=(ImageButton)findViewById(R.id.compose_02);
		ib03=(ImageButton)findViewById(R.id.compose_03);
		ib04=(ImageButton)findViewById(R.id.compose_04);
		
		ib01.setOnClickListener(this);
		ib02.setOnClickListener(this);
		ib03.setOnClickListener(this);
		ib04.setOnClickListener(this);
		
		mime_circle_pub=(ImageButton)findViewById(R.id.mime_circle_pub);
		btn_back=(Button)findViewById(R.id.btn_back);
		btn_back.setOnClickListener(this);
		
		adapter=new MimeShareAdapter(MimeWeiCircle.this, mlist,pricelist);	
		circle_list.setAdapter(adapter);
		
		
		
	}
	
	
	public void initData()
	{
		for (int i = 0; i < 5; i++)
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("key_name", "小七" + i);
		    mlist.add(map);
		}
		ItemEntity item = new ItemEntity();
		item.setPic_url(String.valueOf("drawable://"+R.drawable.kaxiou));
		item.setNum("222");
		item.setName("新佳能xd200");
		item.setPrice("5000");
		pricelist.add(item);
		ItemEntity item2 = new ItemEntity();
		item2.setPic_url(String.valueOf("drawable://"+R.drawable.kaxiou2));
		item2.setNum("1111");
		item2.setName("卡西欧md551");
		item2.setPrice("3000");
		pricelist.add(item2);
	}

	 private View getheadView(){
			
			
		  View view =LayoutInflater.from(MimeWeiCircle.this).inflate(R.layout.circle_head_layout, null);
		  

			return view;
	   }
	    
	

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		   case R.id.blay:
	        	
	        	if (flag) {
	        		pub_layout_01.setVisibility(View.VISIBLE);	
	        		flag=false;
				}
	        	else {
	        		pub_layout_01.setVisibility(View.GONE);	
	        		flag=true;
				}
	        	
	        	
		        break;  
		        
	        case R.id.compose_01:   
		        
//	    		Intent intent = new Intent(MimeWeiCircle.this,GetPicActivity.class);    	
//				startActivity(intent);
	        	pub_layout_01.setVisibility(View.GONE);
	        	
		        break;
		        
	        case R.id.compose_02:   
		        
	        	photo();        	
	        	pub_layout_01.setVisibility(View.GONE);
	        	
		        break;
	        case R.id.compose_03:   
	     
//	        	Intent intent1 = new Intent(MimeWeiCircle.this,PublishedActivity.class);
//	    		Bundle bundle1 = new Bundle();
//	    		bundle1.putBoolean("pubsort", false);
//				intent1.putExtras(bundle1);
//				startActivity(intent1);
				pub_layout_01.setVisibility(View.GONE);
	        	
	            break;
	        case R.id.compose_04:   
		
//	         ToastUtil.toastshow(MimeWeiCircle.this, "ֻ�й���Ա���ϲſ��Է���");	
//	         
//	            Intent intent2= new Intent(MimeWeiCircle.this,StartActivityActivity.class);		
//				startActivity(intent2);
				pub_layout_01.setVisibility(View.GONE);
	        	
	            break;
	            
	        case R.id. mime_circle_pub:   
	    		
//	          	Intent intent3= new Intent(MimeWeiCircle.this,PublishedActivity.class);
//	    		Bundle bundle3 = new Bundle();
//	    		bundle3.putBoolean("pubsort", true);
//				intent3.putExtras(bundle3);
//				startActivity(intent3);
				pub_layout_01.setVisibility(View.GONE);	
		            break;
	        case R.id. btn_back:   
	        	
//	          	Intent intent3= new Intent(MimeWeiCircle.this,PublishedActivity.class);
//	    		Bundle bundle3 = new Bundle();
//	    		bundle3.putBoolean("pubsort", true);
//				intent3.putExtras(bundle3);
//				startActivity(intent3);
	        	finish();	
	        	break;
	           
		default:
			break;
		}
	}

	
	
	public void photo() {
		Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		File file = new File(Environment.getExternalStorageDirectory()
				+ "/myimage/", String.valueOf(System.currentTimeMillis())
				+ ".jpg");
		path = file.getPath();
		Uri imageUri = Uri.fromFile(file);
		openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
		startActivityForResult(openCameraIntent, TAKE_PICTURE);
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	
		
		
	
//		if (data==null&&resultCode!=-1) {
//			
//			if (Bimp.act_bool) {			
//			Intent intent = new Intent(MimeWeiCircle.this,PublishedActivity.class);			
//			Bundle bundle1 = new Bundle();
//    		bundle1.putBoolean("pubsort", true);
//			intent.putExtras(bundle1);
//			startActivity(intent);
//			Bimp.act_bool=false;
//			}
//			
//		}
		

//		if (resultCode==-1) {
//
//			if (Bimp.drr.size() < 9) {
//				Bimp.drr.add(path);
//			}
//			
//			if (Bimp.act_bool) {
//				Intent intent = new Intent(MimeWeiCircle.this,PublishedActivity.class);			
//				Bundle bundle1 = new Bundle();
//	    		bundle1.putBoolean("pubsort", true);
//				intent.putExtras(bundle1);
//				startActivity(intent);
//				Bimp.act_bool=false;
//			}
//
//		   }
	
		
		
		
	}
	
	 @Override
		public boolean onKeyDown(int keyCode, KeyEvent event)
		{
			// TODO Auto-generated method stub
			if (keyCode == KeyEvent.KEYCODE_BACK&&!flag)
			{
				pub_layout_01.setVisibility(View.GONE);
				flag=true;
				return true;
			}
			if (keyCode == KeyEvent.KEYCODE_BACK) {
				MimeWeiCircle.this.finish();
			}
			
			return super.onKeyDown(keyCode, event);
				

			

		}

	
}
