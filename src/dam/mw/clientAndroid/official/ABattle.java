package dam.mw.clientAndroid.official;


import java.util.ArrayList;

import dam.mw.clientAndroid.R;
import dam.mw.clientAndroid.controlCenter.CApp;
import dam.mw.clientAndroid.controlCenter.CConstant;
import dam.mw.clientAndroid.official.AMyData.getData;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
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

	private int progressStatus;
	private int progressStatusMana;
	private int progressStatusContrincantLife;
	private int progressStatusContrincantMana;

	private static int originalLife = 0;
	private static int originalMana = 0;
	private static int originalContrincantLife = 0;
	private static int originalContrincantMana = 0;

	private static int originalLifePercent = 0;
	private static int originalManaPercent = 0;
	private static int originalContrincantLifePercent = 0;
	private static int originalContrincantManaPercent = 0;

	private String classTypePlayer1 = "";
	private String classTypePlayer2 = "";

	private String[] playerArray;

	private TextView tv_life;
	private TextView tv_mana;
	private TextView tv_contrincantLife;
	private TextView tv_contrincantMana;
	private TextView tv_namePlayer1;
	private TextView tv_namePlayer2;
	private TextView tv_nameAttack;
	private TextView tv_definitionAttack;
	

	private Button btn_normalAttack;
	private Button btn_spell1;
	private Button btn_spell2;
	private Button btn_superAttack;
	private Button btn_dodge;
	private Button btn_defense;

	private ImageView avatarPlayer1;
	private ImageView avatarPlayer2;
	
	AlertDialog.Builder dialog;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_abattle);
		playerArray = new String[0];
		dialog = new AlertDialog.Builder(this);
		
		tv_life = (TextView) findViewById(R.id.tv_life);
		tv_mana = (TextView) findViewById(R.id.tv_mana);
		tv_contrincantLife = (TextView) findViewById(R.id.tv_contrincantLife);
		tv_contrincantMana = (TextView) findViewById(R.id.tv_contrincantMana);
		tv_namePlayer1 = (TextView) findViewById(R.id.tv_namePlayer1);
		tv_namePlayer2 = (TextView) findViewById(R.id.tv_namePlayer2);
		
		// Action information boxes
		tv_nameAttack = (TextView) findViewById(R.id.tv_nameAttack);
		tv_definitionAttack = (TextView) findViewById(R.id.tv_definitionAttack);
		
		btn_normalAttack = (Button) findViewById(R.id.btn_normalAttack);
		btn_spell1 = (Button) findViewById(R.id.btn_spell1);
		btn_spell2 = (Button) findViewById(R.id.btn_spell2);
		btn_superAttack = (Button) findViewById(R.id.btn_superAttack);
		btn_dodge = (Button) findViewById(R.id.btn_dodge);
		btn_defense = (Button) findViewById(R.id.btn_defense);

		avatarPlayer1 = (ImageView) findViewById(R.id.avatarPlayer1);
		avatarPlayer2 = (ImageView) findViewById(R.id.avatarPlayer2);

		final PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		this.wakelock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK,
				"etiqueta");
		wakelock.acquire();
		context = this;

		pb_life = (ProgressBar) findViewById(R.id.pb_life);
		pb_mana = (ProgressBar) findViewById(R.id.pb_mana);
		pb_contrincantLife = (ProgressBar) findViewById(R.id.pb_contrincantLife);
		pb_contrincantMana = (ProgressBar) findViewById(R.id.pb_contrincantMana);

//		Typeface face = Typeface.createFromAsset(getAssets(), "Augusta.ttf");
//		
//		ArrayList<TextView> labels = new ArrayList<TextView>();
//		 labels.add(tv_nameAttack);
//	     labels.add(tv_definitionAttack);
//	     
//		
//		for(TextView textview:labels){
//        	textview.setTypeface(face);
//        }

		new ReadResponseBattleFirstTime().execute();

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()) {

		case R.id.btn_normalAttack:
			CApp.clearSound();
			CApp.onSound(context, "music/basico.mp3");
			new SendActionBattle().execute(CConstant.BASIC);
			tv_nameAttack.setText(CConstant.NAME_BASIC);
			tv_definitionAttack.setText(CConstant.TEXT_BASIC);
			break;
		case R.id.btn_spell1:
			CApp.clearSound();
			CApp.onSound(context, "music/spell1.mp3");
			new SendActionBattle().execute(CConstant.SPELL1);
			tv_nameAttack.setText(CConstant.NAME_SPELL1);
			tv_definitionAttack.setText(CConstant.TEXT_SPELL1);
			break;
		case R.id.btn_spell2:
			CApp.clearSound();
			CApp.onSound(context, "music/spell2.mp3");
			new SendActionBattle().execute(CConstant.SPELL2);
			tv_nameAttack.setText(CConstant.NAME_SPELL2);
			tv_definitionAttack.setText(CConstant.TEXT_SPELL2);
			break;
		case R.id.btn_superAttack:
			CApp.clearSound();
			CApp.onSound(context, "music/ua.mp3");
			new SendActionBattle().execute(CConstant.ULTIMATE);
			tv_nameAttack.setText(CConstant.NAME_ULTIMATE);
			tv_definitionAttack.setText(CConstant.TEXT_ULTIMATE);
			break;
		case R.id.btn_dodge:
			CApp.clearSound();
			CApp.onSound(context, "music/dodge.mp3");
			new SendActionBattle().execute(CConstant.DODGE);
			tv_nameAttack.setText(CConstant.NAME_DODGE);
			tv_definitionAttack.setText(CConstant.TEXT_DODGE);
			break;
		case R.id.btn_defense:
			CApp.clearSound();
			CApp.onSound(context, "music/shield.mp3");
			new SendActionBattle().execute(CConstant.SHIELD);
			tv_nameAttack.setText(CConstant.NAME_SHIELD);
			tv_definitionAttack.setText(CConstant.TEXT_SHIELD);
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
			Log.i("LogsAndroid", "sendActionBattle -> doInBackground action( "
					+ params[0] + " )");
			Boolean op = false;
			switch (params[0]) { // TRY CATCH
			case CConstant.BASIC:
				CApp.sendData(CConstant.BASIC);
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						btn_normalAttack.setEnabled(false);
						btn_spell1.setEnabled(false);
						btn_spell2.setEnabled(false);
						btn_superAttack.setEnabled(false);
						btn_dodge.setEnabled(false);
						btn_defense.setEnabled(false);
					}
				});
				op = true;
				break;
			case CConstant.SPELL1:
				CApp.sendData(CConstant.SPELL1);
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						btn_normalAttack.setEnabled(false);
						btn_spell1.setEnabled(false);
						btn_spell2.setEnabled(false);
						btn_superAttack.setEnabled(false);
						btn_dodge.setEnabled(false);
						btn_defense.setEnabled(false);
					}
				});
				op = true;
				break;
			case CConstant.SPELL2:
				CApp.sendData(CConstant.SPELL2);
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						btn_normalAttack.setEnabled(false);
						btn_spell1.setEnabled(false);
						btn_spell2.setEnabled(false);
						btn_superAttack.setEnabled(false);
						btn_dodge.setEnabled(false);
						btn_defense.setEnabled(false);
					}
				});
				op = true;
				break;
			case CConstant.ULTIMATE:
				CApp.sendData(CConstant.ULTIMATE);
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						btn_normalAttack.setEnabled(false);
						btn_spell1.setEnabled(false);
						btn_spell2.setEnabled(false);
						btn_superAttack.setEnabled(false);
						btn_dodge.setEnabled(false);
						btn_defense.setEnabled(false);
					}
				});
				op = true;
				break;
			case CConstant.DODGE:
				CApp.sendData(CConstant.DODGE);
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						btn_normalAttack.setEnabled(false);
						btn_spell1.setEnabled(false);
						btn_spell2.setEnabled(false);
						btn_superAttack.setEnabled(false);
						btn_dodge.setEnabled(false);
						btn_defense.setEnabled(false);
					}
				});
				op = true;
				break;
			case CConstant.SHIELD:
				CApp.sendData(CConstant.SHIELD);
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						btn_normalAttack.setEnabled(false);
						btn_spell1.setEnabled(false);
						btn_spell2.setEnabled(false);
						btn_superAttack.setEnabled(false);
						btn_dodge.setEnabled(false);
						btn_defense.setEnabled(false);
					}
				});
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
			// Revisar m�tode per veure qu� rep del servidor.

			playerArray = msg.split(",");
			Log.i("LogsAndroid", "ReadResponseBattle -> doInBackground " + msg);
			return op;
		}

		@Override
		protected void onPostExecute(Boolean op) {
			super.onPostExecute(op);
			
			

			runOnUiThread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					
					btn_normalAttack.setEnabled(true);
					btn_spell1.setEnabled(true);
					btn_spell2.setEnabled(true);
					btn_superAttack.setEnabled(true);
					btn_dodge.setEnabled(true);
					btn_defense.setEnabled(true);

					if (playerArray.length == 8) {
						// String number without decimals.
						double life_double = Double.parseDouble(playerArray[2]);
						Integer life_int = (int) life_double;
						// String life_string = Integer.toString(life_int);
						// tv_lifeNumber.setText(life_string);

						// String number without decimals.
						double energy_double = Double.parseDouble(playerArray[3]);
						Integer energy_int = (int) energy_double;

						tv_namePlayer1.setText(playerArray[0].toString());
						tv_namePlayer2.setText(playerArray[4].toString());

						if (energy_int <= 0) {
							btn_superAttack.setEnabled(false);
						} else {
							btn_superAttack.setEnabled(true);
						}
						
						
						// String energy_string = Integer.toString(energy_int);
						// tv_energyNumber.setText(energy_string);

						// String number without decimals.
						double life_contrincant_double = Double
								.parseDouble(playerArray[6]);
						Integer life_contrincant_int = (int) life_contrincant_double;
						// String life_contrincant_string =
						// Integer.toString(life_contrincant_int);
						// tv_lifeNumber2.setText(life_contrincant_string);

						// String number without decimals.
						double energy_contrincant_double = Double
								.parseDouble(playerArray[7]);
						Integer energy_contrincant_int = (int) energy_contrincant_double;
						// String energy_contrincant_string =
						// Integer.toString(energy_contrincant_int);
						// tv_energyNumber2.setText(energy_contrincant_string);

						progressStatus = (life_int * 100 / originalLife);
						pb_life.setProgress(progressStatus);
						tv_life.setText(life_int + "/"+ originalLife);

						progressStatusMana = (energy_int * 100 / originalMana);
						pb_mana.setProgress(progressStatusMana);
						tv_mana.setText(energy_int + "/"+ originalMana);
						
						if(progressStatusMana<=21){
							btn_spell1.setEnabled(false);
						}else{
							btn_spell1.setEnabled(true);
						}
						
						if(progressStatusMana<=34){
							btn_spell2.setEnabled(false);
						}else{
							btn_spell2.setEnabled(true);
						}
						
						if(progressStatusMana<=89){
							btn_superAttack.setEnabled(false);
						}else{
							btn_superAttack.setEnabled(true);
						}

						progressStatusContrincantLife = (life_contrincant_int * 100 / originalContrincantLife);
						pb_contrincantLife
								.setProgress(progressStatusContrincantLife);
						tv_contrincantLife.setText(life_contrincant_int + "/"
								+ originalContrincantLife);

						progressStatusContrincantMana = (energy_contrincant_int * 100 / originalContrincantMana);
						pb_contrincantMana
								.setProgress(progressStatusContrincantMana);
						tv_contrincantMana.setText(energy_contrincant_int + "/"
								+ originalContrincantMana);

					} else if (playerArray.length == 9) {
						String result = CApp.getErrorNameByErrorNum(playerArray[8]);
						
						/*if(playerArray[8]=="10"){
							sound.offMusic();
							sound.cleanMusicWin();
							sound.onMusicWin();
						}else{
							
							sound.offMusic();
							sound.clean();
							sound.onDefeat();
						}*/
						
						//Dialog end battle
						dialog.setTitle(result);           
						dialog.setCancelable(false);  
		                
						dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {  
		                    public void onClick(DialogInterface dialogo1, int id) {
		                    	CApp.offMusic();
								CApp.clear();
								CApp.onMusic(context, "music/maintheme.mp3");
		                    	Intent intent = new Intent(context, AMenu.class);
								startActivity(intent);
		                    }  
		                });  
						/*dialog.setNegativeButton("Editar", new DialogInterface.OnClickListener() {  
		                    public void onClick(DialogInterface dialogo1, int id) {  
		                    	
		                    }  
		                });*/
						dialog.show(); 
						

						
					}
				}
			});
			Log.i("LogsAndroid", "ReadResponseBattle -> onPostExecute");
		}
	}

	// Calculate original life & mana.
	class ReadResponseBattleFirstTime extends AsyncTask<String, Void, Boolean> {
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
			// Revisar m�tode per veure qu� rep del servidor.

			playerArray = msg.split(",");
			Log.i("LogsAndroid", "ReadResponseBattle -> doInBackground " + msg);
			return op;
		}

		@Override
		protected void onPostExecute(Boolean op) {
			super.onPostExecute(op);

			runOnUiThread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub

					classTypePlayer1 = playerArray[1];
					classTypePlayer2 = playerArray[5];

					if (classTypePlayer1.equals("1")) {
						avatarPlayer1.setBackgroundResource(R.drawable.pg_avatar_mage);
						btn_normalAttack.setBackgroundResource(R.drawable.button_basic1_mague_modified_states);
						
					} else {
						avatarPlayer1.setBackgroundResource(R.drawable.pg_avatar_warlock);
						btn_normalAttack.setBackgroundResource(R.drawable.button_basic1_warlock_modified_states);
					}

					if (classTypePlayer2.equals("1")) {
						avatarPlayer2.setBackgroundResource(R.drawable.pg_avatar_mage);
					} else {
						avatarPlayer2.setBackgroundResource(R.drawable.pg_avatar_warlock);
					}

					tv_namePlayer1.setText(playerArray[0].toString());
					tv_namePlayer2.setText(playerArray[4].toString());

					double life_double = Double.parseDouble(playerArray[2]);
					Integer life_int = (int) life_double;
					originalLife = life_int;
					originalLifePercent = (life_int * 100 / life_int);

					double energy_double = Double.parseDouble(playerArray[3]);
					Integer energy_int = (int) energy_double;
					originalMana = energy_int;
					originalManaPercent = (energy_int * 100 / energy_int);

					double life_contrincant_double = Double
							.parseDouble(playerArray[6]);
					Integer life_contrincant_int = (int) life_contrincant_double;
					originalContrincantLife = life_contrincant_int;
					originalContrincantLifePercent = (life_contrincant_int * 100 / life_contrincant_int);

					double energy_contrincant_double = Double
							.parseDouble(playerArray[7]);
					Integer energy_contrincant_int = (int) energy_contrincant_double;
					originalContrincantMana = energy_contrincant_int;
					originalContrincantManaPercent = (energy_contrincant_int * 100 / energy_contrincant_int);

					pb_life.setProgress(originalLifePercent);
					tv_life.setText(originalLife + "/"
							+ originalLife);

					pb_mana.setProgress(originalManaPercent);
					tv_mana.setText(originalMana + "/"
							+ originalMana);

					pb_contrincantLife
							.setProgress(originalContrincantLifePercent);
					tv_contrincantLife.setText(originalContrincantLife + "/"
							+ originalContrincantLife);

					pb_contrincantMana
							.setProgress(originalContrincantManaPercent);
					tv_contrincantMana.setText(originalContrincantMana + "/"
							+ originalContrincantMana);

				}
			});

			Log.i("LogsAndroid", "ReadResponseBattle -> onPostExecute");

		}

	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		
	}
}
