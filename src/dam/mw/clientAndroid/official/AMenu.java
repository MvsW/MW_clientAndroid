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
import android.widget.Toast;

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
		
		//dialog_cancel = (Button)findViewById(R.id.progress_cancel);
		
		
		
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
			try{
			CApp.sendDataShowMyData();
			}catch(Exception e){
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						Toast.makeText(context, "Error, please try again...", Toast.LENGTH_SHORT).show();
					}
				});
				
			}
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
			
			Button button = (Button)pDialog.findViewById(R.id.progress_cancel);
			button.setOnClickListener(new OnClickListener(){
		        @Override
		        public void onClick(View v){
		        	Log.i("LogsAndroid", "Cancel clicked");
		        	//new cancelBattle().execute();
		        	CApp.setButtonCancelTapped();
		        	Toast.makeText(context, "Search battle canceled.", Toast.LENGTH_SHORT).show();
		        	pDialog.dismiss();
		        }
		    });
		}

		@Override
		protected String doInBackground(String... params) {
			try{
			
			if (CApp.sendSearchBattle()){
				searching = true;
			}
			}catch(Exception e){
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						Toast.makeText(context, "Error, please try again...", Toast.LENGTH_SHORT).show();
					}
				});
			}
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
	
	/*class cancelBattle extends AsyncTask<String, String, String> {
		

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected String doInBackground(String... params) {
			Log.i("LogsAndroid", "Sending cancel battle...");
			try{
			CApp.sendData(CConstant.CANCEL);
			}catch(Exception e){
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
					}
				});
				
			}
			return "";
		}

		@Override
		protected void onPostExecute(String s) {
			super.onPostExecute(s);
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					Toast.makeText(context, "Search battle canceled", Toast.LENGTH_SHORT).show();
				}
			});
		}
		


	}*/


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
