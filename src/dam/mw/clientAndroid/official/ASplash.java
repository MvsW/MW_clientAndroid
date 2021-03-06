package dam.mw.clientAndroid.official;

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
import android.os.PowerManager;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ASplash extends Activity {

	/** Duration of wait **/
	private final int SPLASH_DISPLAY_LENGTH = 4000;
	
	Animation animationFadeIn;

	GPSTracker gps;
	private Context context;

	private final String log = "LogsAndroid";

	private boolean check = false;
	
	private ImageView splashImage;

	/** Called when the activity is first created. */
	protected PowerManager.WakeLock wakelock;
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.splashscreen);
		splashImage = (ImageView)findViewById(R.id.splashImage);
		
		animationFadeIn = AnimationUtils.loadAnimation(this, R.animator.fade_in);
		
		splashImage.setVisibility(View.VISIBLE);
		splashImage.startAnimation(animationFadeIn);
		
		
		final PowerManager pm=(PowerManager)getSystemService(Context.POWER_SERVICE);
        this.wakelock=pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "etiqueta");
        wakelock.acquire();
		context = this;
	}

	private void startLogin() {
		/*
		 * New Handler to start the Login-Activity and close this Splash-Screen
		 * after some seconds. Implements the connectivity test (GPS)
		 */
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				/* Create an Intent that will start the Login-Activity. */
				Intent mainIntent = new Intent(ASplash.this, ALogin.class);
				
				ASplash.this.startActivity(mainIntent);
				overridePendingTransition(R.animator.animation1, R.animator.animation2);
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
			CApp.setPosition(latitude, longitude);
			// \n is for new line
			check = true;
			/*Toast.makeText(
					getApplicationContext(),
					"Your Location is - \nLat: " + latitude + "\nLong: "
							+ longitude, Toast.LENGTH_LONG).show();*/
		} else {
			// Can't get location.
			// GPS or network is not enabled.
			// Ask user to enable GPS/network in settings.
			gps.showSettingsAlert();
		}
	}

	@Override
	protected void onResume(){
		//falta pulir el metode per recupera la posició al torna del intent settings
		super.onResume();
		chechGPS();
		if (check) {
			startLogin();
		}
		// Thread per separar network del fil principal
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Log.i(log, "trying to connect");
					CApp.connect();
				} catch (Exception e) {
					Log.e(log,
							"Error al abrir el socket cliente -> "
									+ e.toString());
				}
			}
		}).start();

	}

	// Check connectivity network
	private boolean checkConnection() {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		boolean isConnected = activeNetwork != null
				&& activeNetwork.isConnectedOrConnecting();

		return isConnected;
	}

}
