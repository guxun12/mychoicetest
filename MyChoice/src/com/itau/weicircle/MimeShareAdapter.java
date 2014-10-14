package com.itau.weicircle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.itau.chat.MyDialog;
import com.itau.chat.PieActivity;
import com.itau.piccut.PicCutMainActivity;
import com.mychoice.R;
import com.nostra13.universalimageloader.core.ImageLoader;

public class MimeShareAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<? extends Map<String, ?>> data;
	private Context mContext;
	private Activity activity;
	private View view[] = null;
	private MyChoiceAdapter adapter;
	private List<ItemEntity> itemList= new ArrayList<ItemEntity>();

	public MimeShareAdapter(Context context, List<Map<String, Object>> list,List<ItemEntity> pricelist ) {
		this.data = list;
		this.mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.mContext = context;
		this.activity = (Activity) mContext;
		this.itemList = pricelist;
	}

	class ImageData {
		public long imageID;
		public String imageURL;

		public ImageData(long imageID, String mImageIds) {
			super();
			this.imageID = imageID;
			this.imageURL = mImageIds;
		}
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();

			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.mime_share_item, parent, false);
			holder.username_tv = (TextView) convertView
					.findViewById(R.id.username);
			holder.contenttime_tv = (TextView) convertView
					.findViewById(R.id.contenttime);
			holder.content_tv = (TextView) convertView
					.findViewById(R.id.content);
			holder.sharecontent_tv = (TextView) convertView
					.findViewById(R.id.share_content);
			holder.fromcircle_tv = (TextView) convertView
					.findViewById(R.id.circlefrom);
			holder.circlename_tv = (TextView) convertView
					.findViewById(R.id.circlename);
			holder.good_tv = (TextView) convertView.findViewById(R.id.good_num);
			holder.comment_tv = (TextView) convertView
					.findViewById(R.id.comment_num);
			holder.goodname_tv = (TextView) convertView
					.findViewById(R.id.share_names);
			holder.userimg_iv = (ImageView) convertView.findViewById(R.id.iv);
			holder.shareimg_iv = (ImageView) convertView
					.findViewById(R.id.share_img);
			holder.comgood_iv = (ImageView) convertView
					.findViewById(R.id.comment_good_iv);
			holder.contentimg_ll = (LinearLayout) convertView
					.findViewById(R.id.image_container);
			holder.comment_ll = (LinearLayout) convertView
					.findViewById(R.id.comment_container);
			holder.comgoodhandle = (RelativeLayout) convertView
					.findViewById(R.id.comgoodhandle);
			holder.showpie_iv = (ImageView) convertView
					.findViewById(R.id.comment_show_pie);
			holder.choice_img = (ImageView) convertView
					.findViewById(R.id.choice_img);
			convertView.setTag(holder);
			convertView.setId(position);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		List<ImageData> imageMsg = new ArrayList<ImageData>();

		String[] mImageIds = { "drawable://" + R.drawable.kaxiou,
				"drawable://" + R.drawable.kaxiou2
		// String[] mImageIds = {"drawable://" +
		// R.drawable.catergory_appliance,"drawable://"
		// +R.drawable.catergory_book,"drawable://"
		// +R.drawable.catergory_cloth,"drawable://"
		// +R.drawable.catergory_deskbook,
		// "drawable://" +R.drawable.catergory_digtcamer,"drawable://"
		// +R.drawable.catergory_furnitrue,"drawable://"
		// +R.drawable.catergory_mobile,"drawable://"
		// +R.drawable.catergory_skincare
		};
		for (int i = 0; i < mImageIds.length; i++) {
			imageMsg.add(new ImageData(12, mImageIds[i]));
		}

		if (imageMsg.size() == 1) {
			holder.contentimg_ll.removeAllViews();
			ImageView image1 = new ImageView(mContext);
			holder.contentimg_ll.addView(image1);
			ImageLoader.getInstance().displayImage(
					imageMsg.get(0).imageURL.toString(), image1);
		} else if (imageMsg.size() == 2) {
			holder.contentimg_ll.removeAllViews();

			LinearLayout horizonLayout = new LinearLayout(mContext);
			RelativeLayout.LayoutParams params;
			float density = DeviceInfo.getDensity(mContext);
			WindowManager wm = (WindowManager) mContext
					.getSystemService(Context.WINDOW_SERVICE);
			int width = wm.getDefaultDisplay().getWidth();// ��Ļ���
			float imageLayWidth = width - (10 + 12 + 10 + 50) * density;
			for (int i = 0; i < imageMsg.size(); i++) {
				params = new RelativeLayout.LayoutParams(
						(int) (imageLayWidth / 2), (int) (imageLayWidth / 2));
				ImageView image2 = new ImageView(mContext);
				image2.setPadding((int) (4 * density), (int) (4 * density),
						(int) (4 * density), (int) (4 * density));
				horizonLayout.addView(image2, params);
				final String aaaaaa = imageMsg.get(i).imageURL;
				image2.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(activity,
								PicCutMainActivity.class);
						intent.putExtra("picurl", aaaaaa);
						activity.startActivity(intent);
					}
				});

				ImageLoader.getInstance().displayImage(
						imageMsg.get(i).imageURL.toString(), image2);
			}
			holder.contentimg_ll.addView(horizonLayout);
		} else if (imageMsg.size() > 2 && imageMsg.size() <= 9) {
			holder.contentimg_ll.removeAllViews();

			RelativeLayout.LayoutParams params;
			float density = DeviceInfo.getDensity(mContext);
			WindowManager wm = (WindowManager) mContext
					.getSystemService(Context.WINDOW_SERVICE);
			int width = wm.getDefaultDisplay().getWidth();// ��Ļ���
			float imageLayWidth = width - (10 + 12 + 10 + 50) * density;

			int size = imageMsg.size();
			int yuShu = size % 3;
			if (yuShu == 0) {
				int hangNum = (int) (size / 3);
				for (int i = 0; i < hangNum; i++) {
					LinearLayout horizonLayout = new LinearLayout(mContext);
					for (int j = 0; j < 3; j++) {
						params = new RelativeLayout.LayoutParams(
								(int) (imageLayWidth / 3),
								(int) (imageLayWidth / 3));
						ImageView image3 = new ImageView(mContext);
						image3.setPadding((int) (2 * density),
								(int) (2 * density), (int) (2 * density),
								(int) (2 * density));
						horizonLayout.addView(image3, params);
						ImageLoader.getInstance().displayImage(
								imageMsg.get(i * 3 + j).imageURL.toString(),
								image3);
					}
					holder.contentimg_ll.addView(horizonLayout);
				}
			} else {
				int hangNum = (int) (size / 3) + 1;
				for (int i = 0; i <= hangNum - 1; i++) {
					LinearLayout horizonLayout = new LinearLayout(mContext);
					if (i < hangNum - 1) {
						for (int j = 0; j < 3; j++) {
							params = new RelativeLayout.LayoutParams(
									(int) (imageLayWidth / 3),
									(int) (imageLayWidth / 3));
							ImageView image3 = new ImageView(mContext);
							image3.setPadding((int) (2 * density),
									(int) (2 * density), (int) (2 * density),
									(int) (2 * density));
							horizonLayout.addView(image3, params);
							ImageLoader
									.getInstance()
									.displayImage(
											imageMsg.get(i * 3 + j).imageURL
													.toString(),
											image3);
						}
						holder.contentimg_ll.addView(horizonLayout);
					} else if (i == hangNum - 1) {
						for (int j = 0; j < yuShu; j++) {
							params = new RelativeLayout.LayoutParams(
									(int) (imageLayWidth / 3),
									(int) (imageLayWidth / 3));
							ImageView image3 = new ImageView(mContext);
							horizonLayout.addView(image3, params);
							ImageLoader
									.getInstance()
									.displayImage(
											imageMsg.get(i * 3 + j).imageURL
													.toString(),
											image3);
						}
						holder.contentimg_ll.addView(horizonLayout);
					}
				}
			}
		}

		holder.username_tv.setText(data.get(position).get("key_name")
				.toString());

		holder.username_tv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.v("ttttttttttttttttttttttttt",
						data.get(position).get("key_name").toString());
			}
		});
		holder.showpie_iv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(activity, PieActivity.class);
				activity.startActivity(intent);
			}
		});
		holder.choice_img.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showListView();
			}
		});

		holder.comgood_iv.setOnClickListener(new OnClickListener() {
			Boolean flag = false;

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (!flag) {

					holder.comgoodhandle.setVisibility(View.VISIBLE);
					flag = true;
				} else {
					holder.comgoodhandle.setVisibility(View.GONE);
					flag = false;
				}

			}
		});

		return convertView;
	}
    private Boolean sameto=false;

	private MyDialog myDialog;
	private void showListView() {
		// 引用自定义布局
		adapter = new MyChoiceAdapter(activity, itemList);
		View v = LayoutInflater.from(activity).inflate(R.layout.choice_listview, null);
		ListView listview = (ListView) v.findViewById(R.id.listv_content);
		listview.setAdapter(adapter);
		myDialog = new MyDialog(activity, "小伙伴们帮我choice一下", v, new OnClickListener() {
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(activity, "投票成功！",Toast.LENGTH_SHORT).show();
				myDialog.dismiss();
			}
		});
		myDialog.show();
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
	public class ViewHolder {
		public ImageView userimg_iv;
		public TextView username_tv;
		public TextView contenttime_tv;
		public TextView content_tv;
		public LinearLayout contentimg_ll;
		public ImageView shareimg_iv;
		public TextView sharecontent_tv;
		public TextView fromcircle_tv;
		public TextView circlename_tv;
		public TextView good_tv;
		public TextView comment_tv;
		public ImageView comgood_iv;
		public ImageView showpie_iv;
		public ImageView choice_img;
		public ImageView good_iv;
		public ImageView comment_iv;
		public TextView goodname_tv;
		public LinearLayout comment_ll;
		public RelativeLayout comgoodhandle;
	}
	public class ViewHolder2 {
		public ImageView left_iv;
		public ImageView choice_iv;
		public TextView price_tv;
		public TextView item_tv;
		public RelativeLayout choice_le;
		
	}
}
