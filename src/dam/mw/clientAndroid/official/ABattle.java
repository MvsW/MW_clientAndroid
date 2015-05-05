package dam.mw.clientAndroid.official;

import dam.mw.clientAndroid.R;
import dam.mw.clientAndroid.controlCenter.CApp;
import dam.mw.clientAndroid.controlCenter.CConstant;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class ABattle extends Activity implements OnClickListener {

	// FALTA IMPLEMENTAR EL SERVIDOR seachBattle
	protected PowerManager.WakeLock wakelock;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_abattle);

		final PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		this.wakelock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK,
				"etiqueta");
		wakelock.acquire();
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
		}

		@Override
		protected Boolean doInBackground(String... params) {
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
			new ReadResponseBattle().execute();

		}

	}

	class ReadResponseBattle extends AsyncTask<String, Void, Boolean> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(String... params) {
			Boolean op = true;
			CApp.getData();
			return op;
		}

		@Override
		protected void onPostExecute(Boolean op) {
			super.onPostExecute(op);

		}

	}
}
