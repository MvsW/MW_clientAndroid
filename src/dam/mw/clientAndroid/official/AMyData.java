package dam.mw.clientAndroid.official;

import dam.mw.clientAndroid.R;
import dam.mw.clientAndroid.R.id;
import dam.mw.clientAndroid.R.layout;
import dam.mw.clientAndroid.R.menu;
import dam.mw.clientAndroid.controlCenter.CApp;
import dam.mw.clientAndroid.official.AMenu.sendData;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class AMyData extends Activity {
	ProgressDialog pDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_amy_data);
		
		new getData().execute();
		
	}
	
	class getData extends AsyncTask<String, String, String>{

		@Override
		protected void onPreExecute(){
			super.onPreExecute();
			pDialog = new ProgressDialog(AMyData.this);
			pDialog.setMessage("Getting data. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}
		
		@Override
		protected String doInBackground(String... params) {
			Log.i("LogsAndroid", CApp.getData());
			return "";
		}
		
		@Override
		protected void onPostExecute (String s){
			super.onPostExecute(s);
			pDialog.dismiss();
			
		}
		
	}
}
