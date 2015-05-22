package dam.mw.clientAndroid.official;

import java.util.ArrayList;
import dam.mw.clientAndroid.R;
import dam.mw.clientAndroid.R.id;
import dam.mw.clientAndroid.R.layout;
import dam.mw.clientAndroid.R.menu;
import dam.mw.clientAndroid.controlCenter.CApp;
import dam.mw.clientAndroid.controlCenter.CConstant;
import dam.mw.clientAndroid.controlCenter.JApp;
import dam.mw.clientAndroid.official.ARegister_player.sendBackData;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ARegister extends Activity {
	
	private JApp japp = new JApp(); 
	private EditText et_username, et_mail, et_password, et_confirmPassword;
	private TextView tv_registration, tv_username, tv_mail, tv_password, tv_confirmPassword;
	private ArrayList<EditText> fields = new ArrayList<EditText>();
	private String username, mail, password, passwordConfirm;
	private Button btn_registerActivity;
	private boolean validationOK = true;
	
	ProgressDialog pDialog;
	
	private static Context context;
	
	private ArrayList<String> errorNum= new ArrayList<String>();
	
	protected PowerManager.WakeLock wakelock;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aregister);
		final PowerManager pm=(PowerManager)getSystemService(Context.POWER_SERVICE);
        this.wakelock=pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "etiqueta");
        wakelock.acquire();
        
        context = this;
        
        
        //Set typeface.
      	Typeface face = Typeface.createFromAsset(getAssets(), "Augusta.ttf");
		
		//getActionBar().setDisplayHomeAsUpEnabled(true);
		
		// FindViewBy ID
      	btn_registerActivity = (Button)findViewById(R.id.btn_registerActivity);
      	btn_registerActivity.setTypeface(face);
      	
		et_username = (EditText) findViewById(R.id.et_userName);
		et_mail = (EditText) findViewById(R.id.et_mail);
		et_password = (EditText) findViewById(R.id.et_password);
		et_confirmPassword = (EditText) findViewById(R.id.et_confirmPassword);
		
		tv_registration = (TextView)findViewById(R.id.tv_registration);
		tv_username = (TextView)findViewById(R.id.tv_userName);
		tv_mail = (TextView)findViewById(R.id.tv_mail);
		tv_password = (TextView)findViewById(R.id.tv_password);
		tv_confirmPassword = (TextView)findViewById(R.id.tv_confirmPassword);
		
		tv_registration.setTypeface(face);
		tv_username.setTypeface(face);
		tv_mail.setTypeface(face);
		tv_password.setTypeface(face);
		tv_confirmPassword.setTypeface(face);
		
		
		// Add to a ArrayList
		fields.add(et_username);
		fields.add(et_mail);
		fields.add(et_password);
		fields.add(et_confirmPassword);
	}
	
	//Get actions of physical buttons.
		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			// TODO Auto-generated method stub

			switch(keyCode){
				case KeyEvent.KEYCODE_BACK:
					new sendBackData().execute();
					return true;
			}
			return super.onKeyDown(keyCode, event);
		}
		
		class sendBackData extends AsyncTask<String, Void, Boolean> {

			@Override
			protected void onPreExecute() {
				super.onPreExecute();
			}

			@Override
			protected Boolean doInBackground(String... params) {
				Boolean sendBackData = false;

				/*
				 * Send back action to server.
				 */
				try {
					CApp.sendData(CConstant.CANCEL);
					sendBackData = true;

				} catch (Exception e) {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(context, "Error, please try again...",
									Toast.LENGTH_SHORT).show();
						}
					});
				}

				return sendBackData;
			}

			@Override
			protected void onPostExecute(Boolean s) {
				super.onPostExecute(s);
				if (s == true) {
					Intent intent = new Intent(ARegister.this, ALogin.class);
					startActivity(intent);
				}
			}
		}
	
	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.btn_registerActivity:
			if(true){
				validationOK=true;
				//Cojer los textos de los editTexts
				username = et_username.getText().toString();
				mail = et_mail.getText().toString();
				password = et_password.getText().toString();
				passwordConfirm = et_confirmPassword.getText().toString();
				// TODO: VALIDACIONES de los CAMPOS
				//Si estan vac�os o no.
				if (username.equalsIgnoreCase("")){
					et_username.setError("Required field.");
					validationOK = false;
				}
				
				if (mail.isEmpty()){
					et_mail.setError("Required field.");
					validationOK = false;
				}
				
				if (password.isEmpty()){
					et_password.setError("Required field.");
					validationOK = false;
				}
				
				if (passwordConfirm.isEmpty()){
					et_confirmPassword.setError("Required field.");
					validationOK = false;
				}
				// 2- Cada campo con su propia validación (revisar excel para conocer los tamaños y maneres de realizar estos checks) Password lenght: 8
				if(!username.isEmpty()){
					if	(username.length()>12){
						et_username.setError("Max lenght: 12");
						validationOK = false;
					}
					
					if	(username.contains("@")){
						et_username.setError("'@' not allowed");
						validationOK = false;
					}
				}
				
				
				
				if(!mail.isEmpty()){
				if	(japp.mailValidation(fields)){
				    et_mail.setError("The e-mail not have the correct format");
				    validationOK = false;
				}
				}
				
				if(!password.isEmpty()){
					if	(japp.passwordValidation(fields)){
						et_password.setError("Lenght: Min 8");
						validationOK = false;
					}
				}
				
				if(!password.isEmpty() && !passwordConfirm.isEmpty()){
					if(!passwordConfirm.equalsIgnoreCase(password)){
						et_confirmPassword.setError("Passwords do not match");
						validationOK = false;
					}
				}
				
				// 3- Registrar usuario a la base de datos
				if (validationOK == true){
					new sendRegisterData().execute();
				}
			}
			break;
		default:
			break;
		}
		
	}
	
	class sendRegisterData extends AsyncTask<String, Void, Boolean>{

		@Override
		protected void onPreExecute(){
			super.onPreExecute();
			pDialog = new ProgressDialog(ARegister.this);
			pDialog.show();
			pDialog.setContentView(R.layout.custom_progressdialog);
			pDialog.setMessage("Loading...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
		}
		
		@Override
		protected Boolean doInBackground(String... params) {
			Boolean sendRegisterData = false;
			try{
			errorNum = CApp.sendRegisterData(username, mail, password);
			
			if (errorNum.get(0).equals(CConstant.Response.SUCCES)) {
				sendRegisterData = true;

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
			
			}catch(Exception e){
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						Toast.makeText(context, "Error, please try again...", Toast.LENGTH_SHORT).show();
					}
				});
			}
			
				
			
			return sendRegisterData;
		}
		
		@Override
		protected void onPostExecute (Boolean s){
			super.onPostExecute(s);
			pDialog.dismiss();
			if(s == true){
				
				Intent i = new Intent(ARegister.this, ARegister_player.class);
				i.putExtra("username", username);
				i.putExtra("password", password);
				startActivity(i);
					
				overridePendingTransition(R.animator.animator5, R.animator.animator6);
			}
		}
		
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home: 
            onBackPressed();
            return true;

        default:
            return super.onOptionsItemSelected(item);
        }
    }
	
}
