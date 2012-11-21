package org.fasttether;

import android.app.Activity;
import android.os.Bundle;

/**
 * FastTether main activity.
 * 
 * @author takayuki hirota
 */
public class FastTether extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fast_tether);

		if (new WifiApSwitch(this).toggle()) {
			new AirplaneModeSwitch(this).disable();
		} else {
			new AirplaneModeSwitch(this).enable();
		}

		finish();
	}
}
