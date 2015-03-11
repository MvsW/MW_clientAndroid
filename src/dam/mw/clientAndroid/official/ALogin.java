package dam.mw.clientAndroid.official;

import java.util.ArrayList;

import dam.mw.clientAndroid.R;
import dam.mw.clientAndroid.R.id;
import dam.mw.clientAndroid.R.layout;
import dam.mw.clientAndroid.R.menu;
import dam.mw.clientAndroid.controlCenter.JApp;
import android.R.drawable;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

/**
 * This class acts as main activity 
 * @author Olivellaf
 *
 */
public class ALogin extends Activity implements OnClickListener {

	private JApp japp = new JApp(); 
	private EditText et_usernameOrEmail, et_password;
	private ArrayList<EditText> fields = new ArrayList<EditText>();
	Drawable image;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alogin);
		// TODO: Revisar documentos juancar para quitar top-bars y status-bar.
		
		// FindViewBy ID
		et_usernameOrEmail = (EditText) findViewById(R.id.et_userOrMail);
		et_password = (EditText) findViewById(R.id.et_password);
		
		// Add to a ArrayList
		fields.add(et_usernameOrEmail);
		fields.add(et_password);
	}
	
	@Override
	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.btn_login:
			if(true){
				// TODO: VALIDACIONES de los CAMPOS
				//Si estan vac韔s o no.
				if	(japp.areEmpty(fields)){
					et_usernameOrEmail.setError("Required field");
					//et_usernameOrEmail.setError("fghf", image);
					et_password.setError("Required field");
				}else{
				// 2- Cada campo con su propia validaci贸n (revisar excel para conocer los tama帽os y maneres de realizar estos checks) Password lenght: 8
				
				}
				
				// 3- Validaci贸n de que el usuario existe en la bbdd. No solo utilizar JAPP, seguramente se deber谩 recurrir a CConnection a trav茅s de CApppp
				if (!japp.areEmpty(fields) && japp.validatedLogin(japp.getUserLoginInfo(fields))) {
					// Si Compleixen la condici贸 
					// START PROTOCOL LOGIN
					intent = new Intent(this, AMenu.class);
					// Tenemos que a帽adir algun put extra? Alguna informaci贸n?
					// Falta iniciar la activity
				}
			}
			break;
		case R.id.btn_register:
			// TODO: Falta iniciar la activity
			intent = new Intent(this,ARegister.class);
			startActivity(intent);
			break;
		default:
			break;
		}
		
	}
}
