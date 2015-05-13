package dam.mw.clientAndroid.official;

import java.util.ArrayList;

import dam.mw.clientAndroid.R;
import dam.mw.clientAndroid.R.id;
import dam.mw.clientAndroid.R.layout;
import dam.mw.clientAndroid.R.menu;
import dam.mw.clientAndroid.controlCenter.CApp;
import dam.mw.clientAndroid.controlCenter.CConstant;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class ARegister_player extends Activity {
	
	private ToggleButton selectCharacter;
	private TextView characterClass;
	private TextView tv_life_point;
	private TextView tv_energy_point;
	private TextView tv_energyReg_point;
	private TextView tv_strength_point;
	private TextView tv_inteligence_point;
	private TextView tv_unassigned_points;
	private EditText et_characterName;
	
	private TextView tv_selectClass;
	int count_strength = 0;
	int count_inteligence = 0;
	int count_unasigned = 12;
	boolean maxpoints=false;
	boolean maxpointsIntel=false;
	
	boolean registreOK=true;
	
	private String playerName;
	private String idPlayerType;
	private String life;
	private String energy;
	private String eRegeneration;
	private String strength;
	private String intelligence;
	private int idUser;
	private int totalPoints;
	
	private static Context context;
	
	private String username= "";
	private String password = "";
	
	private ArrayList<String> errorNum= new ArrayList<String>();

	protected PowerManager.WakeLock wakelock;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_player);
		final PowerManager pm=(PowerManager)getSystemService(Context.POWER_SERVICE);
        this.wakelock=pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "etiqueta");
        wakelock.acquire();
        
        username = getIntent().getStringExtra("username");
        password = getIntent().getStringExtra("password");
		
		
		selectCharacter = (ToggleButton)findViewById(R.id.selectChamp);
		characterClass = (TextView)findViewById(R.id.characterClass);
		tv_selectClass = (TextView)findViewById(R.id.tv_selectClass);
		
		//Default points
		tv_life_point = (TextView)findViewById(R.id.life_point);
		tv_life_point.setTypeface(Typeface.SERIF);
		tv_life_point.setTextSize(20);
		tv_energy_point = (TextView)findViewById(R.id.energy_point);
		tv_energy_point.setTypeface(Typeface.SERIF);
		tv_energy_point.setTextSize(20);
		tv_energyReg_point = (TextView)findViewById(R.id.energyReg_point);
		tv_energyReg_point.setTypeface(Typeface.SERIF);
		tv_energyReg_point.setTextSize(20);
		
		//Custom points
		tv_strength_point = (TextView)findViewById(R.id.strength_point);
		tv_strength_point.setText(Integer.toString(count_strength));
		tv_inteligence_point = (TextView)findViewById(R.id.inteligence_point);
		tv_inteligence_point.setText(Integer.toString(count_inteligence));
		tv_unassigned_points = (TextView)findViewById(R.id.unassigned_points);
		tv_unassigned_points.setText(Integer.toString(count_unasigned));
		
		et_characterName = (EditText)findViewById(R.id.et_characterName);
		tv_selectClass.setTypeface(Typeface.SERIF);
		et_characterName.setTypeface(Typeface.SERIF, Typeface.BOLD);
		et_characterName.setTextSize(25);
		
		estatToggleButton();
		
		context = this;
		
	}
	
	public void estatToggleButton(){
		selectCharacter.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
				if	(buttonView.isChecked()){
					characterClass.setText("Mage");
					tv_life_point.setText("2");
					tv_energy_point.setText("8");
					tv_energyReg_point.setText("5");
					
					//Assign id of the type Mage/Warlock
					if(characterClass.getText().toString().equalsIgnoreCase("Mage")){
						idPlayerType = CConstant.MAGE;
					}
					
					//Get life
					life = tv_life_point.getText().toString();
					
					//Get energy
					energy = tv_energy_point.getText().toString();
					
					//Get energy regeneration
					eRegeneration = tv_energyReg_point.getText().toString();
					
					
					
				}else{
					characterClass.setText("Warlock");
					tv_life_point.setText("8");
					tv_energy_point.setText("5");
					tv_energyReg_point.setText("3");
					
					//Assign id of the type Mage/Warlock
					if(characterClass.getText().toString().equalsIgnoreCase("Warlock")){
						idPlayerType = CConstant.WARLOCK;
					}
					
					//Get life
					life = tv_life_point.getText().toString();
					
					//Get energy
					energy = tv_energy_point.getText().toString();
					
					//Get energy regeneration
					eRegeneration = tv_energyReg_point.getText().toString();
					
					
				}
			}
		});
	}
	
	public void onClick(View v){
		playerName = et_characterName.getText().toString();
		registreOK=true;
		if	(!playerName.isEmpty()){
			if	(playerName.length()>12){
				et_characterName.setError("Max length: 12");
				registreOK=false;
			}
			
			if(playerName.contains("@")){
				et_characterName.setError("'@' not allowed");
				registreOK=false;
			}
		}else{
			et_characterName.setError("Required field");
			registreOK=false;
		}
		
		if	(registreOK==true){
			//TODO: Guardar registre a la base de dades.
		    
		    new registerPlayer().execute();
		}
		
	}
	
	class registerPlayer extends AsyncTask<String, Void, Boolean>{

		@Override
		protected void onPreExecute(){
			super.onPreExecute();
		}
		
		@Override
		protected Boolean doInBackground(String... params) {
			Boolean sendRegister = false;
			
			/*Send register player data to server:
			  	playerName
				idPlayerType
				life
				energy
				eRegeneration
				strength
				intelligence
				idUser
				totalPoints	
			 */
			errorNum = CApp.sendRegisterPlayer(playerName, idPlayerType, life, energy, eRegeneration, strength, intelligence);
			if (errorNum.get(0).equals(CConstant.Response.SUCCES)) {
				sendRegister = true;

			}else{
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						for(int i = 0; i<errorNum.size(); i++){
						Toast.makeText(context, CApp.getErrorNameByErrorNum(errorNum.get(i)), Toast.LENGTH_SHORT).show();
						}
					}
				});
			}
			
			return sendRegister;
		}
		
		@Override
		protected void onPostExecute (Boolean s){
			super.onPostExecute(s);
			if(s == true){
				Intent intent = new Intent(ARegister_player.this, ALogin.class);
				intent.putExtra("username", username);
				intent.putExtra("password", password);
				startActivity(intent);
			}
		}
	}
	
	public void strenght_down (View v){
		count_strength--;
		if	(count_strength==0){
			count_strength=0;
		}else{
			count_unasigned++;
		}
		
		if(count_unasigned>=12){
			count_unasigned=12;
		}
		tv_strength_point.setText(Integer.toString(count_strength));
		tv_unassigned_points.setText(Integer.toString(count_unasigned));
		
		//Get strenght
		strength = Integer.toString(count_strength);
		
	}
	public void strenght_up (View v){
		if(maxpoints==false){
			count_strength++;
		}
		
		count_unasigned--;
		if	(count_unasigned<=0){
			count_unasigned=0;
			maxpoints = true;
		}else {
			maxpoints = false;
		}
		tv_strength_point.setText(Integer.toString(count_strength));
		tv_unassigned_points.setText(Integer.toString(count_unasigned));
		
		//Get strenght
		strength = Integer.toString(count_strength);
		
		
		
	}
	
	public void inteligence_down (View v){
		count_inteligence--;
		
		if	(count_inteligence<0){
			count_inteligence=0;
		}else{
			count_unasigned++;
		}
		if(count_unasigned>=12){
			count_unasigned=12;
		}
		tv_inteligence_point.setText(Integer.toString(count_inteligence));
		tv_unassigned_points.setText(Integer.toString(count_unasigned));
		
		//Get intelligence
		intelligence = Integer.toString(count_inteligence);
	}
	
	public void inteligence_up (View v){
		if	(maxpointsIntel==false){
			count_inteligence++;
		}
		
		count_unasigned--;
		if	(count_unasigned<=0){
			count_unasigned=0;
			maxpointsIntel=true;
		}else{
			maxpointsIntel=false;
		}
		tv_inteligence_point.setText(Integer.toString(count_inteligence));
		tv_unassigned_points.setText(Integer.toString(count_unasigned));
		
		//Get intelligence
		intelligence = Integer.toString(count_inteligence);
		
	}
	
	

}
