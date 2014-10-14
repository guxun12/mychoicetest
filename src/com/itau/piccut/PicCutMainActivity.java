package com.itau.piccut;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itau.chat.MyDialog;
import com.itau.jingdong.BaseApplication;
import com.itau.jingdong.ui.HomeActivity;
import com.itau.weicircle.ItemEntity;
import com.mychoice.R;
import com.nostra13.universalimageloader.core.ImageLoader;

public class PicCutMainActivity extends Activity {

	private ClipImageView imageView;
	private String picUrl = "";
	private Button search;
	private MyChoiceAdapter adapter;
	private List<ItemEntity> itemList = new ArrayList<ItemEntity>();
	private ListView mlistview;
	private MyDialog myDialog;
	private BaseApplication baseApp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cut_main);
		Intent intent = getIntent();
		baseApp = (BaseApplication) getApplication();
		picUrl = intent.getStringExtra("picurl");
		imageView = (ClipImageView) findViewById(R.id.src_pic);
		search = (Button) findViewById(R.id.right_btn);
		init();
		ImageLoader.getInstance().displayImage(picUrl, imageView);
	}

	public void init() {
		ItemEntity item = new ItemEntity();
		item.setPic_url(picUrl);
		item.setNum("222");
		item.setName("新佳能xd200");
		item.setPrice("5000");
		itemList.add(item);
		search.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				showListView();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	private void showListView() {
		// 引用自定义布局
		adapter = new MyChoiceAdapter(PicCutMainActivity.this, itemList);
		View v = LayoutInflater.from(PicCutMainActivity.this).inflate(
				R.layout.choice_listview, null);
		ListView listview = (ListView) v.findViewById(R.id.listv_content);
		listview.setAdapter(adapter);
		myDialog = new MyDialog(PicCutMainActivity.this, "已为您自动识别该商品，将跳转到京东商城",
				v, new OnClickListener() {
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						myDialog.dismiss();
						baseApp.setJumpType(picUrl);
						Intent intent = new Intent(PicCutMainActivity.this,
								HomeActivity.class);
						Log.i("picCut", picUrl);
						intent.putExtra("picUrl", picUrl);
						intent.putExtra("type", "picJump");
						startActivity(intent);
						finish();
					}
				});
		myDialog.show();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.action_clip) {
			Bitmap bitmap = imageView.clip();

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
			byte[] bitmapByte = baos.toByteArray();

			Intent intent = new Intent(this, PreviewActivity.class);
			intent.putExtra("bitmap", bitmapByte);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}

	public void chat_back(View v) {
		finish();
	}

	class MyChoiceAdapter extends BaseAdapter {
		private Activity mactivity;
		private List<ItemEntity> itemList;

		public MyChoiceAdapter(Activity activity, List<ItemEntity> list) {
			this.mactivity = activity;
			this.itemList = list;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder2 holder = null;
			if (convertView == null) {
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
			} else {
				holder = (ViewHolder2) convertView.getTag();
			}
			final ImageView aa = holder.choice_iv;
			holder.choice_le.setVisibility(View.GONE);
			holder.price_tv.setText(itemList.get(position).getPrice());
			holder.item_tv.setText(itemList.get(position).getName());
			ImageLoader.getInstance().displayImage(
					itemList.get(position).getPic_url(), holder.left_iv);
			return convertView;
		}

		@Override
		public Object getItem(int position) {
			return itemList.get(position);
		}

		@Override
		public int getCount() {
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
