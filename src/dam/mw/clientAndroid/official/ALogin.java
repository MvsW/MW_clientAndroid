package dam.mw.clientAndroid.official;

import java.util.ArrayList;

import dam.mw.clientAndroid.R;
import dam.mw.clientAndroid.R.id;
import dam.mw.clientAndroid.R.layout;
import dam.mw.clientAndroid.R.menu;
import dam.mw.clientAndroid.controlCenter.CApp;
import dam.mw.clientAndroid.controlCenter.JApp;
import android.R.drawable;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
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

	private JApp japp = new JApp();
	private EditText et_usernameOrEmail, et_password;
	private ArrayList<EditText> fields = new ArrayList<EditText>();
	Drawable image;
	String username;
	String password;
	boolean sendLogin = false;
	static Context context;

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
		context = this;
	}

	@Override
	public void onClick(View v) {

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

					new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							if (CApp.sendLogin(username, password, 0, 0)) {
								sendLogin = true;

								Intent intent = new Intent(context, AMenu.class);
								startActivity(intent);
							}
						}
					}).start();
					
					/*try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						Toast.makeText(getApplicationContext(), "sdfsfd", Toast.LENGTH_LONG).show();
					}
					if (sendLogin) {
						Intent intent = new Intent(this, AMenu.class);
						startActivity(intent);
					}*/

				}
			}
			break;
		case R.id.btn_register:
			// TODO: Falta iniciar la activity
			Intent intent = new Intent(this, ARegister.class);
			startActivity(intent);
			break;
		default:
			break;
		}

	}
}
