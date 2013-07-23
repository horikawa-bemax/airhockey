package jp.horikawa.airhockey;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class Area extends ImageView implements OnTouchListener{
	static int PEACH = 0xffffcccc;
	static int MUSCAT = 0xffccffcc;
	private Color color;
	private Canvas canvas;
	private RectF rectf;
	
	public Area(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public Area(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public Area(Context context) {
		super(context);
		init();
	}

	private void init(){
		this.setOnTouchListener(this);
	}
	
	public void initField(RectF rectf, int color){
		float width = rectf.width();
		float height = rectf.height();
		rectf = new RectF(0, 0, width, height);
		Bitmap bitmap = Bitmap.createBitmap((int)width, (int)height, Config.ARGB_8888);
		canvas = new Canvas(bitmap);
		Paint paint = new Paint();
		paint.setColor(color);
		paint.setAlpha(0x88);
		canvas.drawRoundRect(rectf, 10.0f, 10.0f, paint);
		setImageBitmap(bitmap);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		return false;
	}
}
