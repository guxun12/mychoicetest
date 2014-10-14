package com.itau.chat;

import com.mychoice.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * 锟皆讹拷锟斤拷dialog
 * 
 * @author 夕锟斤拷
 * 
 */
public class MyDialog extends Dialog {

	Context context;
	View view;
	View.OnClickListener onClickListener;
	String title;

	public MyDialog(Context context, String title, View view,
			View.OnClickListener onClickListener) {
		super(context, R.style.MyDialog);
		this.context = context;
		this.title = title;
		this.view = view;
		this.onClickListener = onClickListener;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.my_dialog);
		// 实锟斤拷丶锟�
		Button btn_confir = (Button) findViewById(R.id.btn_confir);
		Button btn_cancel = (Button) findViewById(R.id.btn_cancel);
		TextView textv_title = (TextView) findViewById(R.id.textv_title);
		// 锟斤拷锟矫憋拷锟斤拷
		textv_title.setText(title);
		// 锟斤拷锟揭拷锟斤拷玫牟锟斤拷锟�
		LinearLayout mainLayout = (LinearLayout) findViewById(R.id.relayout_addview);
		mainLayout.addView(view);
		// 锟斤拷确锟较硷拷锟斤拷锟矫硷拷锟斤拷
		btn_confir.setOnClickListener(onClickListener);
		// 锟斤拷锟饺★拷锟截憋拷dialog
		btn_cancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				MyDialog.this.dismiss();
			}
		});
	}
}