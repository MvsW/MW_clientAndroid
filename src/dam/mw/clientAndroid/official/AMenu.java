package dam.mw.clientAndroid.official;

import dam.mw.clientAndroid.R;
import dam.mw.clientAndroid.R.id;
import dam.mw.clientAndroid.R.layout;
import dam.mw.clientAndroid.R.menu;
import dam.mw.clientAndroid.controlCenter.CApp;
import dam.mw.clientAndroid.controlCenter.CConstant;
import dam.mw.clientAndroid.official.ALogin.login;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class AMenu extends Activity implements OnClickListener {

	private Context context;
	private ProgressDialog pDialog;
	private boolean searching = false;
	

	protected PowerManager.WakeLock wakelock;
	
	TextView tv_menu;
	Button btn_battle;
	Button btn_showData;
	Button dialog_cancel;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_amenu);

		final PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		this.wakelock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK,
				"etiqueta");
		wakelock.acquire();

		context = this;
		
		Typeface face = Typeface.createFromAsset(getAssets(), "Augusta.ttf");
		
		tv_menu = (TextView)findViewById(R.id.tv_menu);
		btn_battle = (Button)findViewById(R.id.btn_battle);
		btn_showData = (Button)findViewById(R.id.btn_showData);
		
		tv_menu.setTypeface(face);
		btn_battle.setTypeface(face);
		btn_showData.setTypeface(face);
		
		dialog_cancel = (Button)findViewById(R.id.progress_cancel);
		
	}

	class sendData extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(AMenu.this);
			pDialog.show();
			pDialog.setContentView(R.layout.custom_progressdialog);
			pDialog.setMessage("Show My Data. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
		}

		@Override
		protected String doInBackground(String... params) {
			Log.i("LogsAndroid", "Sending data to Show my data...");
			CApp.sendDataShowMyData();
			return "";
		}

		@Override
		protected void onPostExecute(String s) {
			super.onPostExecute(s);
			pDialog.dismiss();
			Intent intentShowData = new Intent(context, AMyData.class);
			startActivity(intentShowData);

		}

	}

	class searchBattle extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(AMenu.this);
			pDialog.show();
			pDialog.setContentView(R.layout.custom_progressdialog);
			pDialog.setMessage("Searching battle. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
		}

		@Override
		protected String doInBackground(String... params) {
			if (CApp.sendSearchBattle())
				searching = true;

			return "";
		}

		@Override
		protected void onPostExecute(String s) {
			super.onPostExecute(s);
			Log.i("LogsAndroid",
					"PostExecute Sending data to Searching battle...");
			pDialog.dismiss();
			if (searching) {
				Intent intentBattle = new Intent(context, ABattle.class);
				startActivity(intentBattle);
			}

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.amenu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {

		case R.id.btn_battle: // click battle
			// previament notificar al servidor per buscar un rival, mostrar
			// pantalla de searching (timeOut), i al finalitzar si tenim
			// contricant pasem
			// a Abattle, rebre dades a mostrar en la activity. Inicia la
			// batalla (timeOuts)

			new searchBattle().execute();

			break;
		case R.id.btn_showData: // click show data
			// send data to server (Register constant).
			new sendData().execute();

			break;
		default:
			break;
		}

	}
}
