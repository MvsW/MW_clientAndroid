package dam.mw.clientAndroid.controlCenter;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.*;

public class JApp {
	
	
	
	
	
	public boolean isEmpty (View v) {
		if (v instanceof EditText) {
			EditText et = (EditText) v ;
			return et.getText().toString().isEmpty();
		}
		return true;
	}
	
	public boolean isEmpty (EditText et) {
		return et.getText().toString().isEmpty();
	}
	
	public boolean areEmpty (ArrayList<EditText> fields) {
		for (EditText et: fields) {
			if (isEmpty(et)){
				return true; 
			}
		}	
		return false;
	}
	
	public ContentValues getUserLoginInfo (ArrayList<EditText> fields) {
		ContentValues values = new ContentValues();
		values.put("USERNAME_OR_EMAIL", fields.get(0).toString());
		values.put("PASSWORD", fields.get(1).toString());
		return values;
	}

	public boolean validatedLogin(ContentValues userLoginInfo) {
		userLoginInfo.get("USERNAME_OR_EMAIL");
		
		
		return false;
	}
}
