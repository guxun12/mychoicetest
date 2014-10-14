package com.itau.jingdong.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.itau.jingdong.ui.base.BaseActivity;
import com.itau.weicircle.ItemEntity;
import com.mychoice.R;
import com.nostra13.universalimageloader.core.ImageLoader;


public class CartActivity extends BaseActivity implements OnClickListener {

	private Button cart_login,cart_market,cat_pay_btn;
	private Intent mIntent;
	
	private MyChoiceAdapter adapter;
	private List<ItemEntity> itemList= new ArrayList<ItemEntity>();
	private ListView mlistview;
    private Boolean sameto=false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cart_main);
		ItemEntity item = new ItemEntity();
		item.setPic_url(String.valueOf("drawable://"+R.drawable.kaxiou));
		item.setNum("222");
		item.setName("新佳能xd200");
		item.setPrice("5000");
		itemList.add(item);
		ItemEntity item2 = new ItemEntity();
		item2.setPic_url(String.valueOf("drawable://"+R.drawable.kaxiou2));
		item2.setNum("1111");
		item2.setName("卡西欧md551");
		item2.setPrice("3000");
		itemList.add(item2);
		findViewById();
		initView();
	}

	@Override
	protected void findViewById() {
		mlistview = (ListView)this.findViewById(R.id.listv_content_car);
		cat_pay_btn = (Button)this.findViewById(R.id.cat_pay_btn);
	}

	@Override
	protected void initView() {
		adapter = new MyChoiceAdapter(CartActivity.this, itemList);
		mlistview.setAdapter(adapter);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			
		case R.id.cart_market:
			
			break;
		case R.id.cat_pay_btn:
			
			break;

		default:
			break;
		}
		
	}
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
            holder.choice_le.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if (!sameto) {
						
						aa.setImageResource(R.drawable.s_choose_bg_hover);	
						sameto=true;
							
						}else {
							
							aa.setImageResource(R.drawable.s_choose_bg_nor);	
							sameto=false;
						}	
				}
			});
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
