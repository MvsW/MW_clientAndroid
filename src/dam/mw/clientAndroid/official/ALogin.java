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
	private ArrayList<EditText> fields;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alogin);
		
		// FindViewBy ID
		et_usernameOrEmail = (EditText) findViewById(R.id.et_userOrMail);
		et_password = (EditText) findViewById(R.id.et_password);
		
		// Add to a ArrayList
		fields.add(et_usernameOrEmail);
		fields.add(et_password);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.alogin, menu);
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
		Intent intent;
		switch (v.getId()) {
		case R.id.btn_login:
			if(true){ 
				//Validar fields
				if (!japp.areEmpty(fields) && japp.validatedLogin(japp.getUserLoginInfo(fields))) {
					
				}
				//Compleixen la condició 
				//start Protocol login
				intent = new Intent(this,AMenu.class);
			}
			
			break;
		case R.id.btn_register:
			intent = new Intent(this,ARegister.class);
			break;
		default:
			break;
		}
		
	}
}
