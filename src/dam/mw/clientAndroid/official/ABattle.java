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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.abattle, menu);
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
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.btn_normalAttack:
			new SendActionBattle().execute(CConstant.NORMAL_ATTACK);
			break;
		case R.id.btn_spell1:
			new SendActionBattle().execute(CConstant.SPELL_1);
			break;
		case R.id.btn_spell2:
			new SendActionBattle().execute(CConstant.SPELL_2);
			break;
		case R.id.btn_superAttack:
			new SendActionBattle().execute(CConstant.SUPER_ATTACK);
			break;
		case R.id.btn_dodge:
			new SendActionBattle().execute(CConstant.DODGE);
			break;
		case R.id.btn_defense:
			new SendActionBattle().execute(CConstant.DEFENSE);
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
			case CConstant.NORMAL_ATTACK:
				CApp.sendData(CConstant.NORMAL_ATTACK);
				op = true;
				break;
			case CConstant.SPELL_1:
				CApp.sendData(CConstant.SPELL_1);
				op = true;
				break;
			case CConstant.SPELL_2:
				CApp.sendData(CConstant.SPELL_2);
				op = true;
				break;
			case CConstant.SUPER_ATTACK:
				CApp.sendData(CConstant.SUPER_ATTACK);
				op = true;
				break;
			case CConstant.DODGE:
				CApp.sendData(CConstant.DODGE);
				op = true;
				break;
			case CConstant.DEFENSE:
				CApp.sendData(CConstant.DEFENSE);
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
