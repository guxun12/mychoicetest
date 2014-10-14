package com.itau.piccut;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * �ü�߿�
 * 
 * @author king
 * @time 2014-6-18 ����3:53:00
 */
public class ClipView extends View {
	
	/**
	 * �߿�����ұ߽���룬���ڵ���߿򳤶�
	 */
	public static final int BORDERDISTANCE = 200;
	
	private Paint mPaint;
	
	public ClipView(Context context) {
		this(context, null);
	}

	public ClipView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ClipView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mPaint = new Paint();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		int width = this.getWidth();
		int height = this.getHeight();

		// �߿򳤶ȣ�����Ļ���ұ�Ե50px
		int borderlength = width - BORDERDISTANCE *2;
				
		mPaint.setColor(0xaa000000);

		// ���»���͸��ɫ����
		// top
		canvas.drawRect(0, 0, width, (height - borderlength) / 2, mPaint);
		// bottom
		canvas.drawRect(0, (height + borderlength) / 2, width, height, mPaint);
		// left
		canvas.drawRect(0, (height - borderlength) / 2, BORDERDISTANCE,
				(height + borderlength) / 2, mPaint);
		// right
		canvas.drawRect(borderlength + BORDERDISTANCE, (height - borderlength) / 2, width,
				(height + borderlength) / 2, mPaint);
		
		// ���»��Ʊ߿���
		mPaint.setColor(Color.WHITE);
		mPaint.setStrokeWidth(2.0f);
		// top
		canvas.drawLine(BORDERDISTANCE, (height - borderlength) / 2, width - BORDERDISTANCE, (height - borderlength) / 2, mPaint);
		// bottom
		canvas.drawLine(BORDERDISTANCE, (height + borderlength) / 2, width - BORDERDISTANCE, (height + borderlength) / 2, mPaint);
		// left
		canvas.drawLine(BORDERDISTANCE, (height - borderlength) / 2, BORDERDISTANCE, (height + borderlength) / 2, mPaint);
		// right
		canvas.drawLine(width - BORDERDISTANCE, (height - borderlength) / 2, width - BORDERDISTANCE, (height + borderlength) / 2, mPaint);
	}

}
