package dam.mw.clientAndroid.official;

import java.util.ArrayList;
import dam.mw.clientAndroid.R;
import dam.mw.clientAndroid.R.id;
import dam.mw.clientAndroid.R.layout;
import dam.mw.clientAndroid.R.menu;
import dam.mw.clientAndroid.controlCenter.JApp;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ARegister extends Activity {
	
	private JApp japp = new JApp(); 
	private EditText et_username, et_mail, et_password, et_confirmPassword;
	private ArrayList<EditText> fields = new ArrayList<EditText>();
	private String username, mail, password, passwordConfirm;
	private boolean validationOK = true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aregister);
		
		//getActionBar().setDisplayHomeAsUpEnabled(true);
		
		// FindViewBy ID
		et_username = (EditText) findViewById(R.id.et_userName);
		et_mail = (EditText) findViewById(R.id.et_mail);
		et_password = (EditText) findViewById(R.id.et_password);
		et_confirmPassword = (EditText) findViewById(R.id.et_confirmPassword);
		
		
		
		// Add to a ArrayList
		fields.add(et_username);
		fields.add(et_mail);
		fields.add(et_password);
		fields.add(et_confirmPassword);
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
				//if	(validationOK == true){
					Toast e=Toast.makeText(this,"Correct!", Toast.LENGTH_SHORT);
				    e.show();
				    Intent i = new Intent(ARegister.this, ARegister_player.class);
					startActivity(i);
						
					overridePendingTransition(R.animator.animator5, R.animator.animator6);
				//}
			}
			break;
		default:
			break;
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
