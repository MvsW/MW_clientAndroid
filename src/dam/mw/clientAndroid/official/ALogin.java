package dam.mw.clientAndroid.official;

import java.util.ArrayList;

import dam.mw.clientAndroid.R;
import dam.mw.clientAndroid.R.id;
import dam.mw.clientAndroid.R.layout;
import dam.mw.clientAndroid.R.menu;
import dam.mw.clientAndroid.controlCenter.CApp;
import dam.mw.clientAndroid.controlCenter.CConstants;
import dam.mw.clientAndroid.controlCenter.JApp;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

/**
 * This class acts as main activity
 * 
 * @author Olivellaf
 * 
 */
public class ALogin extends Activity implements OnClickListener {

	private EditText et_usernameOrEmail, et_password;
	private ArrayList<EditText> fields = new ArrayList<EditText>();
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alogin);

		// FindViewBy ID
		et_usernameOrEmail = (EditText) findViewById(R.id.et_userOrMail);
		et_password = (EditText) findViewById(R.id.et_password);
		context = this;
		// Add to a ArrayList
		fields.add(et_usernameOrEmail);
		fields.add(et_password);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_login:
			if (true) {
				// TODO: VALIDACIONES de los CAMPOS
				// Si estan vacios o no.
				if (JApp.areEmpty(fields)) {
					et_usernameOrEmail.setError("Required field");
					et_password.setError("Required field");
				} else {
					// 2- Cada campo con su propia validación (revisar excel
					// para conocer los tamaños y maneres de realizar estos
					// checks) Password lenght: 8

					/** need read server response for start the next activity **/
					/** Es necesita un fil apart per tot el tema relacionat amb la network **/
					new Thread(new Runnable() {
						@Override
						public void run() {

							if (CApp.sendLogin(fields.get(0).getText()
									.toString(), fields.get(1).getText()
									.toString(), CApp.getLongitude(),
									CApp.getLatitude())) {
								Log.i("LogsAndroid", "Black byte rules");
								Intent intent = new Intent(context, AMenu.class);
								startActivity(intent);
							} else {
								// password incorrect o user not exist
								Log.i("LogsAndroid",
										"password incorrect o user not exist");
							}
						}
					}).start();

				}

				/** Hay que ver menudo lio. Saber y Ganar ;-) **/
				// 3- Validación de que el usuario existe en la bbdd. No solo
				// utilizar JAPP, seguramente se deberá recurrir a CConnection a
				// través de CApppp
				/*
				 * if (!JApp.areEmpty(fields) &&
				 * JApp.validatedLogin(JApp.getUserLoginInfo(fields))) { // Si
				 * Compleixen la condició // START PROTOCOL LOGIN }
				 */

			}
			break;
		case R.id.btn_register:
			Intent intent = new Intent(this, ARegister.class);
			startActivity(intent);
			break;
		default:
			break;
		}

	}
}
