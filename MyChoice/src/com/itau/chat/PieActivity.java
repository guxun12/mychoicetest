package com.itau.chat;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.itau.weicircle.ItemEntity;
import com.mychoice.R;
import com.nostra13.universalimageloader.core.ImageLoader;

public class PieActivity extends Activity {
	Button weidao, huanjie, xueyuan;
	List<Integer> mStrings = new ArrayList<Integer>();
	WebView mWebView;
	private Context context = PieActivity.this;
	private Button btn_back,buy_btn;
	private MyChoiceAdapter adapter;
	private List<ItemEntity> itemList= new ArrayList<ItemEntity>();
	private MyDialog myDialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview);
		ItemEntity item = new ItemEntity();
		item.setPic_url(String.valueOf("drawable://"+R.drawable.kaxiou));
		item.setNum("222");
		item.setName("新佳能xd200");
		item.setPrice("5000");
		itemList.add(item);
//		ItemEntity item2 = new ItemEntity();
//		item2.setPic_url(String.valueOf("drawable://"+R.drawable.kaxiou2));
//		item2.setNum("1111");
//		item2.setName("卡西欧md551");
//		item2.setPrice("3000");
//		pricelist.add(item2);
		findView();
	}

	/**
	 * 界面初始化方法。
	 */
	public void get3dPie() {
			new Thread(new Runnable() {
				@Override
				public void run() {
					mStrings.add(10);
					mStrings.add(21);
					handler.sendEmptyMessage(1);
				}
			}).start();
	}

	/**
	 * 控件初始化*/
	@SuppressLint("JavascriptInterface")
	private void findView() {
		btn_back=(Button)findViewById(R.id.btn_back);
		buy_btn=(Button)findViewById(R.id.buy_btn);
		buy_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// 引用自定义布局
				adapter = new MyChoiceAdapter(PieActivity.this, itemList);
				View v1 = LayoutInflater.from(PieActivity.this).inflate(R.layout.choice_listview, null);
				ListView listview = (ListView) v1.findViewById(R.id.listv_content);
				listview.setAdapter(adapter);
				myDialog = new MyDialog(PieActivity.this, "即将启用京东商城APP购买此项商品", v1, new OnClickListener() {
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						myDialog.dismiss();
					}
				});
				myDialog.show();
		}
		});
		btn_back.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		mWebView = (WebView) findViewById(R.id.webview);
		WebSettings ws = mWebView.getSettings();
		ws.setJavaScriptEnabled(true);
		mWebView.addJavascriptInterface(this, "android");
		mWebView.setInitialScale(100);

		mWebView.loadUrl("file:///android_asset/www/3dpie.html");
		mWebView.setVisibility(View.VISIBLE);
		mWebView.requestFocus();
	}


	// 定义一个Handler，用来异步处理数据
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				if (mStrings != null) {
					String str = PieUtil.getContactsByMessage(mStrings,   "投票结果分析");
					mWebView.loadUrl("javascript:draw('" + str + "')");
				}
				break;
			}

		};
	};
    class MyChoiceAdapter extends BaseAdapter
    {
    	private Activity mactivity;
    	private List<ItemEntity> itemList;
    	public MyChoiceAdapter(Activity activity,List<ItemEntity> list){
    		this.mactivity = activity;
    		this.itemList=list;
    	}
        @Override
        
        public View getView(int position, View convertView, ViewGroup parent)
        {
            ViewHolder2 holder = null;
            if (convertView == null)
            {
                convertView = mactivity.getLayoutInflater().inflate(
                        R.layout.choice_item, null);
                holder = new ViewHolder2();
                holder.choice_iv = (ImageView) convertView
                        .findViewById(R.id.samleto);
                holder.left_iv = (ImageView) convertView
                		.findViewById(R.id.circleiv);
                holder.price_tv = (TextView) convertView
                		.findViewById(R.id.circle_num);
                holder.item_tv = (TextView) convertView
                		.findViewById(R.id.circlename);
                holder.choice_le = (RelativeLayout) convertView
                        .findViewById(R.id.samleto_rl);
                convertView.setTag(holder);
            }
            else
            {
                holder = (ViewHolder2) convertView.getTag();
            }
            final ImageView aa = holder.choice_iv;
            holder.choice_le.setVisibility(View.GONE);
            holder.price_tv.setText(itemList.get(position).getPrice());
            holder.item_tv.setText(itemList.get(position).getName());
            ImageLoader.getInstance().displayImage(itemList.get(position).getPic_url(), holder.left_iv);
            return convertView;
        }
        
        
        @Override
        public Object getItem(int position)
        {
            return itemList.get(position);
        }
        
        @Override
        public int getCount()
        {
            return itemList.size();
        }
		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}
    }
	public class ViewHolder2 {
		public ImageView left_iv;
		public ImageView choice_iv;
		public TextView price_tv;
		public TextView item_tv;
		public RelativeLayout choice_le;
		
	}
	
}
