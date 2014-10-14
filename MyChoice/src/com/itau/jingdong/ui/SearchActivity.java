package com.itau.jingdong.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.itau.jingdong.BaseApplication;
import com.itau.jingdong.ui.base.BaseActivity;
import com.itau.jingdong.widgets.AutoClearEditText;
import com.itau.weicircle.MimeWeiCircle;
import com.mychoice.R;


public class SearchActivity extends BaseActivity {

	private AutoClearEditText mEditText = null;
	private ImageButton mImageButton = null;
	private String picUrl = "";
	private ListView catergory_listview;
	private LayoutInflater layoutInflater;
	private ImageView shopCart;//购物车
	private ViewGroup anim_mask_layout;//动画层
	private ImageView buyImg;// 这是在界面上跑的小图片
	private int buyNum = 0;//购买数量
	private BadgeView buyNumView;//显示购买数量的控件
	private BaseApplication baseApp;
//	private Integer[] mImageIds = {R.drawable.catergory_appliance,R.drawable.catergory_book,R.drawable.catergory_cloth,R.drawable.catergory_deskbook,
//			R.drawable.catergory_digtcamer,R.drawable.catergory_furnitrue,R.drawable.catergory_mobile,R.drawable.catergory_skincare
//			 };
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		Intent intent = getIntent();
		baseApp = (BaseApplication) getApplication();
//		picUrl = intent.getStringExtra("picUrl");
		picUrl = baseApp.getJumpType();
		Log.v("picUrl", picUrl);
		String no1 = "drawable://" + R.drawable.kaxiou;
		String no2 = "drawable://" + R.drawable.kaxiou2;
		String no3 = "drawable://" + R.drawable.kaxiou2;
		String no4 = "drawable://" + R.drawable.kaxiou2;
		findViewById();
		initView();
		Log.i("no1", no1+"q"+picUrl);
		if(no1.equals(picUrl)){
			Integer[] mImageIds={R.drawable.kaxiou};
			String[] mTitleValues = { "新佳能照相机"  };
			String[] mContentValues={"家电/生活电器/电子产品" };
			catergory_listview.setAdapter(new CatergorAdapter(mTitleValues,mContentValues,mImageIds,false));
			catergory_listview.setVisibility(View.VISIBLE);
		}else if(no2.equals(picUrl)){
			Integer[] mImageIds={R.drawable.kaxiou2};
			String[] mTitleValues = {   "卡西欧照相机" };
			String[] mContentValues={ "家电/生活电器/电子产品" };
			catergory_listview.setAdapter(new CatergorAdapter(mTitleValues,mContentValues,mImageIds,false));
			catergory_listview.setVisibility(View.VISIBLE);
		}else if(no3.equals(picUrl)){
			Integer[] mImageIds={R.drawable.kaxiou2};
			String[] mTitleValues = {   "新尼康照相机" };
			String[] mContentValues={ "家电/生活电器/电子产品" };
			catergory_listview.setAdapter(new CatergorAdapter(mTitleValues,mContentValues,mImageIds,false));
			catergory_listview.setVisibility(View.VISIBLE);
		}else if(no4.equals(picUrl)){
			Integer[] mImageIds={R.drawable.kaxiou2};
			String[] mTitleValues = {   "新佳能照相机" };
			String[] mContentValues={ "家电/生活电器/电子产品" };
			catergory_listview.setAdapter(new CatergorAdapter(mTitleValues,mContentValues,mImageIds,false));
			catergory_listview.setVisibility(View.VISIBLE);
		}
		
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		mEditText = (AutoClearEditText) findViewById(R.id.search_edit);
		mImageButton = (ImageButton) findViewById(R.id.search_button);
		catergory_listview=(ListView)this.findViewById(R.id.search_list);
		shopCart = (ImageView) findViewById(R.id.shopping_img_cart);
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		mEditText.requestFocus();
		mImageButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Integer[] mImageIds={R.drawable.kaxiou,R.drawable.kaxiou2,R.drawable.kaxiou2,R.drawable.kaxiou2};
				String[] mTitleValues = {   "卡西欧md551","新佳能xd200","新尼康xf300","新佳能fm661" };
				String[] mContentValues={ "单价：3000元","单价：4000元","单价：2000元","单价：3200元" };
				catergory_listview.setAdapter(new CatergorAdapter(mTitleValues,mContentValues,mImageIds,true));
				catergory_listview.setVisibility(View.VISIBLE);
			}
		});
		
		buyNumView = new BadgeView(SearchActivity.this, shopCart);
		buyNumView.setTextColor(Color.WHITE);
		buyNumView.setBackgroundColor(Color.RED);
		buyNumView.setTextSize(11);
		shopCart.setOnClickListener(new OnClickListener() {
			
			@SuppressWarnings("static-access")
			@Override
			public void onClick(View v) {
				if(buyNum>0){
					Intent intent = new Intent(SearchActivity.this, LoadingToCircleActivity.class);
					intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent);
					finish();
				}else{
					Toast.makeText(getApplicationContext(), "请先选择商品进行MakeChoice", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
	private class CatergorAdapter extends BaseAdapter{
		String[] mTitleValues;
		String[] mContentValues;
		Integer[] mImageIds;
		boolean isShowChoice;
		public CatergorAdapter(String[] TitleValues,String[] ContentValues, Integer[] ImageIds,boolean isShowChoice){
			this.mTitleValues = TitleValues;
			this.mContentValues = ContentValues;
			this.mImageIds = ImageIds;
			this.isShowChoice=isShowChoice;
			Log.i("mContentValues", mContentValues[0]);
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mTitleValues.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder=new ViewHolder();
			layoutInflater=LayoutInflater.from(SearchActivity.this);
			//组装数据
			if(convertView==null){
				convertView=layoutInflater.inflate(R.layout.activity_category_item, null);
				holder.image=(ImageView) convertView.findViewById(R.id.catergory_image);
				holder.title=(TextView) convertView.findViewById(R.id.catergoryitem_title);
				holder.content=(TextView) convertView.findViewById(R.id.catergoryitem_content);
				holder.choice=(Button) convertView.findViewById(R.id.product_buy_btn);
				if(isShowChoice){
					holder.choice.setVisibility(View.VISIBLE);
				}
				//使用tag存储数据
				convertView.setTag(holder);
			}else{
				holder=(ViewHolder) convertView.getTag();
			}
			final int picaa = mImageIds[position];
			holder.choice.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if(buyNum<=2){
						int[] start_location = new int[2];// 一个整型数组，用来存储按钮的在屏幕的X、Y坐标
						v.getLocationInWindow(start_location);// 这是获取购买按钮的在屏幕的X、Y坐标（这也是动画开始的坐标）
						buyImg = new ImageView(SearchActivity.this);// buyImg是动画的图片，我的是一个小球（R.drawable.sign）
						buyImg.setImageResource(picaa);// 设置buyImg的图片
						setAnim(buyImg, start_location);// 开始执行动画
						shopCart.setVisibility(View.VISIBLE);
					}else{
						Toast.makeText(SearchActivity.this, "最多购买3件商品",Toast.LENGTH_SHORT).show();
					}
				}
			});
			holder.image.setImageResource(mImageIds[position]);
			holder.title.setText(mTitleValues[position]);
			holder.content.setText(mContentValues[position]);
		//	holder.title.setText(array[position]);
			
			return convertView;
		
		}
	}
	/**
	 * @Description: 创建动画层
	 * @param
	 * @return void
	 * @throws
	 */
	private ViewGroup createAnimLayout() {
		ViewGroup rootView = (ViewGroup) this.getWindow().getDecorView();
		LinearLayout animLayout = new LinearLayout(this);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT);
		animLayout.setLayoutParams(lp);
		animLayout.setId(Integer.MAX_VALUE);
		animLayout.setBackgroundResource(android.R.color.transparent);
		rootView.addView(animLayout);
		return animLayout;
	}
	private void setAnim(final View v, int[] start_location) {
		anim_mask_layout = null;
		anim_mask_layout = createAnimLayout();
		anim_mask_layout.addView(v);//把动画小球添加到动画层
		final View view = addViewToAnimLayout(anim_mask_layout, v,
				start_location);
		int[] end_location = new int[2];// 这是用来存储动画结束位置的X、Y坐标
		shopCart.getLocationInWindow(end_location);// shopCart是那个购物车

		// 计算位移
		int endX = 0 - start_location[0] + 40;// 动画位移的X坐标
		int endY = end_location[1] - start_location[1];// 动画位移的y坐标
		TranslateAnimation translateAnimationX = new TranslateAnimation(0,
				endX, 0, 0);
		translateAnimationX.setInterpolator(new LinearInterpolator());
		translateAnimationX.setRepeatCount(0);// 动画重复执行的次数
		translateAnimationX.setFillAfter(true);

		TranslateAnimation translateAnimationY = new TranslateAnimation(0, 0,
				0, endY);
		translateAnimationY.setInterpolator(new AccelerateInterpolator());
		translateAnimationY.setRepeatCount(0);// 动画重复执行的次数
		translateAnimationX.setFillAfter(true);

		AnimationSet set = new AnimationSet(false);
		set.setFillAfter(false);
		set.addAnimation(translateAnimationY);
		set.addAnimation(translateAnimationX);
		set.setDuration(800);// 动画的执行时间
		view.startAnimation(set);
		// 动画监听事件
		set.setAnimationListener(new AnimationListener() {
			// 动画的开始
			@Override
			public void onAnimationStart(Animation animation) {
				v.setVisibility(View.VISIBLE);
			}
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
			}

			// 动画的结束
			@Override
			public void onAnimationEnd(Animation animation) {
				v.setVisibility(View.GONE);
				buyNum++;//让购买数量加1
				buyNumView.setText(buyNum + "");//
				buyNumView.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
				buyNumView.show();
			}
		});

	}
	
	private View addViewToAnimLayout(final ViewGroup vg, final View view,
			int[] location) {
		int x = location[0];
		int y = location[1];
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		lp.leftMargin = x;
		lp.topMargin = y;
		view.setLayoutParams(lp);
		return view;
	}
 public static class ViewHolder {
		ImageView image;
		TextView title;
		TextView content;
		Button choice;
	}
}
