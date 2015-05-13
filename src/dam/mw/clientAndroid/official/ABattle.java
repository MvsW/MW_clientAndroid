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
import android.os.PowerManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class ABattle extends Activity implements OnClickListener {

	// FALTA IMPLEMENTAR EL SERVIDOR seachBattle
	protected PowerManager.WakeLock wakelock;
	private Context context;
	private ProgressDialog progressBarLife;
	
	private String[] playerArray = new String[0];
	
	private TextView tv_lifeNumber;
	private TextView tv_energyNumber;
	private TextView tv_lifeNumber2;
	private TextView tv_energyNumber2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_abattle);
		
		tv_lifeNumber = (TextView)findViewById(R.id.tv_lifeNumber);
		tv_energyNumber = (TextView)findViewById(R.id.tv_energyNumber);
		tv_lifeNumber2 = (TextView)findViewById(R.id.tv_lifeNumber2);
		tv_energyNumber2 = (TextView)findViewById(R.id.tv_energyNumber2);

		final PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		this.wakelock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK,
				"etiqueta");
		wakelock.acquire();
		context = this;
		
		progressBarLife = new ProgressDialog(this);
		
		
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
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					
					if(playerArray[0].length()>3){
						
					}
					
					tv_lifeNumber.setText(playerArray[0].substring(0, 2));
					tv_energyNumber.setText(playerArray[1].substring(0, 2));
					tv_lifeNumber2.setText(playerArray[2].substring(0, 2));
					tv_energyNumber2.setText(playerArray[3].substring(0, 2));
				}
			});
			
			Log.i("LogsAndroid", "ReadResponseBattle -> onPostExecute");

		}

	}
}
