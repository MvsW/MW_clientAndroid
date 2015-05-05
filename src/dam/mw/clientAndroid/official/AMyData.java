package dam.mw.clientAndroid.official;

import dam.mw.clientAndroid.R;
import dam.mw.clientAndroid.R.id;
import dam.mw.clientAndroid.R.layout;
import dam.mw.clientAndroid.R.menu;
import dam.mw.clientAndroid.controlCenter.CApp;
import dam.mw.clientAndroid.official.AMenu.sendData;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class AMyData extends Activity {
	ProgressDialog pDialog;
	
	private String[] playerArray = new String[10];
	
	private TextView tv_player_name;
	private TextView tv_lifeData;
	private TextView tv_energyData;
	private TextView tv_regEnergyData;
	private TextView tv_strengthData;
	private TextView tv_inteligenceData;
	private TextView tv_dateRegisterData;
	private TextView tv_totalPointsData;
	private TextView tv_totalWinsData;
	

	protected PowerManager.WakeLock wakelock;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_amy_data);
		final PowerManager pm=(PowerManager)getSystemService(Context.POWER_SERVICE);
        this.wakelock=pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "etiqueta");
        wakelock.acquire();
        
        //Components declaration
        tv_player_name = (TextView) findViewById(R.id.tv_name_avatar);
        tv_lifeData = (TextView)findViewById(R.id.tv_lifeData);
        tv_energyData = (TextView)findViewById(R.id.tv_energyData);
        tv_regEnergyData = (TextView)findViewById(R.id.tv_regEnergyData);
        tv_strengthData = (TextView)findViewById(R.id.tv_strengthData);
        tv_inteligenceData = (TextView)findViewById(R.id.tv_inteligenceData);
        
        tv_totalPointsData = (TextView)findViewById(R.id.tv_totalPointsData);
        tv_totalWinsData = (TextView)findViewById(R.id.tv_totalWinsData);
        tv_dateRegisterData = (TextView)findViewById(R.id.tv_dateRegisterData);
        
		
		new getData().execute();
	}
	
	class getData extends AsyncTask<String, String, String>{

		@Override
		protected void onPreExecute(){
			super.onPreExecute();
		}
		
		@Override
		protected String doInBackground(String... params) {
			playerArray = CApp.getData().split(",");
			return "";
		}
		
		@Override
		protected void onPostExecute (String s){
			super.onPostExecute(s);
			printMyData();
			
		}
	}
	
	public void printMyData() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				
				tv_player_name.setText(playerArray[0]);
				tv_lifeData.setText(playerArray[1]);
				tv_energyData.setText(playerArray[3]);
				tv_regEnergyData.setText(playerArray[4]);
				tv_strengthData.setText(playerArray[5]);
				tv_inteligenceData.setText(playerArray[6]);
				tv_totalPointsData.setText(playerArray[7]);
				tv_totalWinsData.setText(playerArray[8]);
				tv_dateRegisterData.setText(playerArray[9]);
				
			}
		});
	}
}
