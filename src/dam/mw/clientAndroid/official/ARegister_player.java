package dam.mw.clientAndroid.official;

import dam.mw.clientAndroid.R;
import dam.mw.clientAndroid.R.id;
import dam.mw.clientAndroid.R.layout;
import dam.mw.clientAndroid.R.menu;
import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
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
	private String characterName;
	private TextView tv_selectClass;
	int count_strength = 0;
	int count_inteligence = 0;
	int count_unasigned = 12;
	boolean maxpoints=false;
	boolean maxpointsIntel=false;
	
	boolean registreOK=true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_player);
		
		
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
		
	}
	
	public void estatToggleButton(){
		selectCharacter.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
				if	(buttonView.isChecked()){
					characterClass.setText("Mague");
					tv_life_point.setText("2");
					tv_energy_point.setText("8");
					tv_energyReg_point.setText("5");
					
					
				}else{
					characterClass.setText("Warlock");
					tv_life_point.setText("8");
					tv_energy_point.setText("5");
					tv_energyReg_point.setText("3");
					
					
				}
			}
		});
	}
	
	public void onClick(View v){
		characterName = et_characterName.getText().toString();
		registreOK=true;
		if	(!characterName.isEmpty()){
			if	(characterName.length()>12){
				et_characterName.setError("Max length: 12");
				registreOK=false;
			}
			
			if(characterName.contains("@")){
				et_characterName.setError("'@' not allowed");
				registreOK=false;
			}
		}else{
			et_characterName.setError("Required field");
			registreOK=false;
		}
		
		if	(registreOK==true){
			//TODO: Guardar registre a la base de dades.
			Toast e=Toast.makeText(this,"Correct!", Toast.LENGTH_SHORT);
		    e.show();
		}
		
	}
	
	public void strenght_down (View v){
		count_strength--;
		count_unasigned++;
		if	(count_strength<0){
			count_strength=0;
		}
		if(count_unasigned>=12){
			count_unasigned=12;
		}
		tv_strength_point.setText(Integer.toString(count_strength));
		tv_unassigned_points.setText(Integer.toString(count_unasigned));
		
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
		
	}
	
	public void inteligence_down (View v){
		count_inteligence--;
		count_unasigned++;
		if	(count_inteligence<=0){
			count_inteligence=0;
		}
		if(count_unasigned>=12){
			count_unasigned=12;
		}
		tv_inteligence_point.setText(Integer.toString(count_inteligence));
		tv_unassigned_points.setText(Integer.toString(count_unasigned));
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
		
	}
	
	

}
