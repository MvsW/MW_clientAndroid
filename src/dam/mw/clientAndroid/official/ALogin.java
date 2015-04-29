package dam.mw.clientAndroid.official;

import java.util.ArrayList;

import dam.mw.clientAndroid.R;
import dam.mw.clientAndroid.controlCenter.CApp;
import dam.mw.clientAndroid.controlCenter.JApp;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;


public class ALogin extends Activity implements OnClickListener {

	private JApp japp = new JApp();
	private EditText et_usernameOrEmail, et_password;
	private ArrayList<EditText> fields = new ArrayList<EditText>();
	Drawable image;
	String username;
	String password;
	static Context context;
	ProgressDialog pDialog;
	
	Button btn_register;
	Button btn_login;

	protected PowerManager.WakeLock wakelock;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alogin);
		final PowerManager pm=(PowerManager)getSystemService(Context.POWER_SERVICE);
        this.wakelock=pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "etiqueta");
        wakelock.acquire();
		// TODO: Revisar documentos juancar para quitar top-bars y status-bar.
		
		//Set typeface.
		Typeface face = Typeface.createFromAsset(getAssets(), "Augusta.ttf");
		
		
		//Buttons
		btn_register = (Button)findViewById(R.id.btn_register);
		btn_register.setTypeface(face);
		
		btn_login = (Button)findViewById(R.id.btn_login);
		btn_login.setTypeface(face);
		
		// FindViewBy ID
		et_usernameOrEmail = (EditText) findViewById(R.id.et_userOrMail);
		et_usernameOrEmail.setTextColor(Color.WHITE);
		
		et_password = (EditText) findViewById(R.id.et_password);
		et_password.setTextColor(Color.WHITE);

		// Add to a ArrayList
		fields.add(et_usernameOrEmail);
		fields.add(et_password);
		context = this;
	}
	
	class login extends AsyncTask<String, Void, Boolean>{

		@Override
		protected void onPreExecute(){
			super.onPreExecute();
			pDialog = new ProgressDialog(ALogin.this);
			pDialog.setMessage("Login in. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}
		
		@Override
		protected Boolean doInBackground(String... params) {
			Boolean sendLogin = false;
			if (CApp.sendLogin(username, password, 0, 0)) {
				sendLogin = true;
			}
			return sendLogin;
		}
		
		@Override
		protected void onPostExecute (Boolean s){
			super.onPostExecute(s);
			pDialog.dismiss();
			if(s == true){
				Intent intent = new Intent(context, AMenu.class);
				startActivity(intent);
			}
		}
		
	}
	
	

	@Override
	public void onClick(View v) {
		
		//user1@hotmail.com	User1964

		switch (v.getId()) {
		case R.id.btn_login:
			if (true) {
				username = et_usernameOrEmail.getText().toString();
				password = et_password.getText().toString();
				// TODO: VALIDACIONES de los CAMPOS
				// Si estan vac�os o no.
				if (japp.areEmpty(fields)) {
					et_usernameOrEmail.setError("Required field");
					et_password.setError("Required field");
				} else {
					// 2- Cada campo con su propia validación (revisar excel
					// para conocer los tamaños y maneres de realizar estos
					// checks) Password lenght: 8

					new login().execute();
				}
			}
			break;
		case R.id.btn_register:
			// TODO: Falta iniciar la activity
			
			new register().execute();
			
			
			break;
		default:
			break;
		}

	}
	
	class register extends AsyncTask<String, Void, Boolean>{

		@Override
		protected void onPreExecute(){
			super.onPreExecute();
		}
		
		@Override
		protected Boolean doInBackground(String... params) {
			Boolean sendRegister = false;
			if (CApp.sendRegisterTaped()) {
				sendRegister = true;
			}
			return sendRegister;
		}
		
		@Override
		protected void onPostExecute (Boolean s){
			super.onPostExecute(s);
			if(s == true){
				Intent intent = new Intent(context, ARegister.class);
				startActivity(intent);
			}
		}
	}
}
