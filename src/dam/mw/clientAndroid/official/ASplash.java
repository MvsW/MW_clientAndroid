package dam.mw.clientAndroid.official;

import dam.mw.clientAndroid.MainActivity;
import dam.mw.clientAndroid.R;
import dam.mw.clientAndroid.controlCenter.CApp;
import dam.mw.clientAndroid.controlCenter.GPSTracker;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public class ASplash extends Activity {

	/** Duration of wait **/
	private final int SPLASH_DISPLAY_LENGTH = 4000;

	GPSTracker gps;
	private Context context;

	private final String log = "LogsAndroid";

	private boolean check = false;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.splashscreen);
		/** Hide action bar **/
		// getActionBar().hide();
		context = this;
		if (checkConnection()) {
			Toast toast = Toast.makeText(this, "true", Toast.LENGTH_SHORT);
			toast.show();

		} else {
			Toast toast = Toast.makeText(this, "false", Toast.LENGTH_SHORT);
			toast.show();
			System.exit(1);
		}

	}

	private void startLogin() {
		/*
		 * New Handler to start the Menu-Activity and close this Splash-Screen
		 * after some seconds. Implements the connectivity test (GPS)
		 */
		Log.i(log, "display");
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				/* Create an Intent that will start the Menu-Activity. */
				Intent mainIntent = new Intent(ASplash.this, ALogin.class);
				ASplash.this.startActivity(mainIntent);
				ASplash.this.finish();
			}
		}, SPLASH_DISPLAY_LENGTH);
	}

	private void chechGPS() {
		gps = new GPSTracker(this);

		// Check if GPS enabled
		if (gps.canGetLocation()) {

			double latitude = gps.getLatitude();
			double longitude = gps.getLongitude();
			// \n is for new line
			check = true;
			Toast.makeText(
					getApplicationContext(),
					"Your Location is - \nLat: " + latitude + "\nLong: "
							+ longitude, Toast.LENGTH_LONG).show();
		} else {
			// Can't get location.
			// GPS or network is not enabled.
			// Ask user to enable GPS/network in settings.
			gps.showSettingsAlert();
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		chechGPS();
		if (check) {
			startLogin();
		}

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					CApp.connect();
				} catch (Exception e) {
					Log.e(log,
							"Error al abrir el socket cliente -> "
									+ e.toString());
				}
			}
		});

	}

	private boolean checkConnection() {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		boolean isConnected = activeNetwork != null
				&& activeNetwork.isConnectedOrConnecting();

		return isConnected;
	}

}
