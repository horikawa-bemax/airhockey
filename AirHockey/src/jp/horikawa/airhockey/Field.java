package jp.horikawa.airhockey;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Field extends SurfaceView implements SurfaceHolder.Callback, Runnable{
	private int fieldWidth, fieldHeight;
	private RectF fieldRect;
	private Thread thread;
	private boolean loop;
	private SurfaceHolder holder;
	private Area area1, area2;
	private Context appContext;

	public Field(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public Field(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public Field(Context context) {
		super(context);
		init();
	}
	
	private void init(){
		appContext = getContext().getApplicationContext();
		holder = getHolder();
	}
	
	public void setAreas(Area area1, Area area2){
		this.area1 = area1;
		this.area2 = area2;
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		fieldWidth = width;
		fieldHeight = height;
		fieldRect = new RectF(10,10,fieldWidth-10, fieldHeight-10);
		area1.initField(new RectF(0,0,fieldRect.width(), fieldRect.height()*5/11), Area.PEACH);
		
		if(thread != null){
			thread.start();
		}
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if(thread == null){
			thread = new Thread(this);
		}
		loop = true;
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		
	}

	@Override
	public void run() {
		Paint paint = new Paint();
		Path path = new Path();
		
		while(loop){
			Canvas canvas = holder.lockCanvas();
			canvas.drawColor(Color.rgb(0x66, 0xaa, 0xff));
			paint.setColor(Color.rgb(0xdd,0xee,0xff));
			path.addRoundRect(fieldRect,10.0f,10.0f,Path.Direction.CW);
			canvas.drawPath(path,paint);
			holder.unlockCanvasAndPost(canvas);
		}
	}
	
	public void setLoop(boolean b){
		this.loop = b;
	}
	
	public RectF getFieldRect(){
		return fieldRect;
	}
}
