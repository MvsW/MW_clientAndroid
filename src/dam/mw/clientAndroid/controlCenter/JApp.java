package dam.mw.clientAndroid.controlCenter;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.*;

public class JApp {

	public static boolean isEmpty(View v) {
		if (v instanceof EditText) {
			EditText et = (EditText) v;
			return et.getText().toString().isEmpty();
		}
		return true;
	}

	public static boolean isEmpty(EditText et) {
		return et.getText().toString().isEmpty();
		
	}

	public static boolean areEmpty(ArrayList<EditText> fields) {
		for (EditText et : fields) {
			if (isEmpty(et)) {
				return true;
			}
		}
		return false;
	}

	public static boolean mailValidation(ArrayList<EditText> fields) {
		String fieldText = fields.get(1).getText().toString();
		if (!fieldText
				.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
			return true;
		}
		return false;
	}

	public static boolean passwordValidation(ArrayList<EditText> fields) {
		if (fields.get(2).length() <= 7) {
			return true;
		}
		return false;
	}

	/** WTF??? **/
	public static ContentValues getUserLoginInfo(ArrayList<EditText> fields) {
		ContentValues values = new ContentValues();
		values.put("USERNAME_OR_EMAIL", fields.get(0).toString());
		values.put("PASSWORD", fields.get(1).toString());
		return values;
	}

	public static boolean validatedLogin(ContentValues userLoginInfo) {
		userLoginInfo.get("USERNAME_OR_EMAIL");

		return false;
	}
}
