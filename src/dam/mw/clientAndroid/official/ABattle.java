package dam.mw.clientAndroid.official;

import dam.mw.clientAndroid.R;
import dam.mw.clientAndroid.controlCenter.CApp;
import dam.mw.clientAndroid.controlCenter.CConstant;
import dam.mw.clientAndroid.official.AMyData.getData;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class ABattle extends Activity implements OnClickListener {
	
	protected PowerManager.WakeLock wakelock;
	private Context context;
	
	private ProgressBar pb_life;
	private ProgressBar pb_mana;
	private ProgressBar pb_contrincantLife;
	private ProgressBar pb_contrincantMana;
	
	private int progressStatus = 100;
	private int progressStatusMana = 100;
	private int progressStatusContrincantLife = 100;
	private int progressStatusContrincantMana = 100;
	
	private String[] playerArray = new String[0];
	
	
	private TextView tv_life;
	private TextView tv_mana;
	private TextView tv_contrincantLife;
	private TextView tv_contrincantMana;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_abattle);
		
		tv_life = (TextView)findViewById(R.id.tv_life);
		tv_mana = (TextView)findViewById(R.id.tv_mana);
		tv_contrincantLife = (TextView)findViewById(R.id.tv_contrincantLife);
		tv_contrincantMana = (TextView)findViewById(R.id.tv_contrincantMana);

		final PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		this.wakelock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK,
				"etiqueta");
		wakelock.acquire();
		context = this;
		
		pb_life = (ProgressBar) findViewById(R.id.pb_life);
		pb_mana = (ProgressBar) findViewById(R.id.pb_mana);
		pb_contrincantLife = (ProgressBar) findViewById(R.id.pb_contrincantLife);
		pb_contrincantMana = (ProgressBar) findViewById(R.id.pb_contrincantMana);
		
		
		
		new ReadResponseBattle().execute();
		
	}
	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.btn_normalAttack:
			new SendActionBattle().execute(CConstant.BASIC);
			break;
		case R.id.btn_spell1:
			new SendActionBattle().execute(CConstant.SPELL1);
			break;
		case R.id.btn_spell2:
			new SendActionBattle().execute(CConstant.SPELL2);
			break;
		case R.id.btn_superAttack:
			new SendActionBattle().execute(CConstant.ULTIMATE);
			break;
		case R.id.btn_dodge:
			new SendActionBattle().execute(CConstant.DODGE);
			break;
		case R.id.btn_defense:
			new SendActionBattle().execute(CConstant.SHIELD);
			break;
		default:
			break;
		}

	}

	class SendActionBattle extends AsyncTask<String, Void, Boolean> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			Log.i("LogsAndroid", "sendActionBattle -> onPreExecute");
		}

		@Override
		protected Boolean doInBackground(String... params) {
			Log.i("LogsAndroid", "sendActionBattle -> doInBackground action( " + params[0]+" )");
			Boolean op = false;
			switch (params[0]) { //TRY CATCH
			case CConstant.BASIC:
				CApp.sendData(CConstant.BASIC);
				op = true;
				break;
			case CConstant.SPELL1:
				CApp.sendData(CConstant.SPELL1);
				op = true;
				break;
			case CConstant.SPELL2:
				CApp.sendData(CConstant.SPELL2);
				op = true;
				break;
			case CConstant.ULTIMATE:
				CApp.sendData(CConstant.ULTIMATE);
				op = true;
				break;
			case CConstant.DODGE:
				CApp.sendData(CConstant.DODGE);
				op = true;
				break;
			case CConstant.SHIELD:
				CApp.sendData(CConstant.SHIELD);
				op = true;
				break;
			default:
				break;
			}
			return op;
		}

		@Override
		protected void onPostExecute(Boolean op) {
			super.onPostExecute(op);
			Log.i("LogsAndroid", "sendActionBattle -> onPostExecute");
			new ReadResponseBattle().execute();

		}

	}

	class ReadResponseBattle extends AsyncTask<String, Void, Boolean> {
		String msg;
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			Log.i("LogsAndroid", "ReadResponseBattle -> onPreExecute");
		}

		@Override
		protected Boolean doInBackground(String... params) {
			Boolean op = true;
			msg = CApp.getData();
			//Revisar mètode per veure què rep del servidor.
			
			playerArray = msg.split(",");
			Log.i("LogsAndroid", "ReadResponseBattle -> doInBackground "+msg);
			return op;
		}

		@Override
		protected void onPostExecute(Boolean op) {
			super.onPostExecute(op);
			
			if(playerArray.length == 3){
				
			}
			
			runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					
					//String number without decimals.
					double life_double = Double.parseDouble(playerArray[0]);
					Integer life_int = (int) life_double;
					//String life_string = Integer.toString(life_int);
					//tv_lifeNumber.setText(life_string);
					
					//String number without decimals.
					double energy_double = Double.parseDouble(playerArray[1]);
					Integer energy_int = (int) energy_double;
					//String energy_string = Integer.toString(energy_int);
					//tv_energyNumber.setText(energy_string);
					
					//String number without decimals.
					double life_contrincant_double = Double.parseDouble(playerArray[2]);
					Integer life_contrincant_int = (int) life_contrincant_double;
					//String life_contrincant_string = Integer.toString(life_contrincant_int);
					//tv_lifeNumber2.setText(life_contrincant_string);
					
					//String number without decimals.
					double energy_contrincant_double = Double.parseDouble(playerArray[3]);
					Integer energy_contrincant_int = (int) energy_contrincant_double;
					//String energy_contrincant_string = Integer.toString(energy_contrincant_int);
					//tv_energyNumber2.setText(energy_contrincant_string);
					
					progressStatus = life_int;
					pb_life.setProgress(progressStatus);
					tv_life.setText("Life: " + progressStatus+"/"+"100");
					
					progressStatusMana = energy_int;
					pb_mana.setProgress(progressStatus);
					tv_mana.setText("Mana: " + progressStatusMana+"/"+"100");
					
					progressStatusContrincantLife = life_contrincant_int;
					pb_contrincantLife.setProgress(progressStatusContrincantLife);
					tv_contrincantLife.setText("Contrincant life: " + progressStatusContrincantLife+"/"+"100");
					
					progressStatusContrincantMana = energy_contrincant_int;
					pb_contrincantMana.setProgress(progressStatusContrincantMana);
					tv_contrincantMana.setText("Contrincant mana: " + progressStatusContrincantMana+"/"+"100");
				}
			});
			
			Log.i("LogsAndroid", "ReadResponseBattle -> onPostExecute");

		}

	}
}
