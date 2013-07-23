package jp.horikawa.airhockey;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class GameActivity extends Activity {
	private Field field;
	private Area area1, area2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		field = (Field)findViewById(R.id.field);
		area1 = (Area)findViewById(R.id.area1);
		area2 = (Area)findViewById(R.id.area2);
		field.setAreas(area1, area2);
		field.getHolder().addCallback(field);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}

}
