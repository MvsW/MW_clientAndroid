package dam.mw.clientAndroid.controlCenter;

import java.net.InetAddress;
import java.net.Socket;

import org.apache.http.util.LangUtils;

import android.util.Log;

public class CApp {

	private static CConnection connection;
	private static Socket socket;
	private static double latitude, longitude;

	public static void connect() throws Exception { // Modificar per tractament
													// propi
		InetAddress serverAddr = InetAddress.getByName(CConstant.HOST);// HOST
		socket = new Socket(serverAddr, CConstant.PORT);

		connection = new CConnection(socket);
		Log.i("LogsAndroid", "Conectat a: "
				+ socket.getInetAddress().getHostName());
	}

	public static void setPosition(double lat, double lon) {
		longitude = lon;
		latitude = lat;
	}

	public static double getLatitude() {
		return latitude;
	}

	public static double getLongitude() {
		return longitude;
	}

	public static String sendLogin(String userOrMail, String Password, double longitude, double latitude) {

		String result = "";
		String data ="";
		
		String arrayErrors[];
		
		Log.i("LogsAndroid", "prepare send login...");
		connection.sendData(userOrMail + CConstant.SEPARATOR + Password
				+ CConstant.SEPARATOR + latitude + CConstant.SEPARATOR
				+ longitude);
		Log.i("LogsAndroid", "send login...");
		
		
		data = connection.readData();
		
		Log.i("LogsAndroid", "Data: " + data);
		
		arrayErrors = data.split(CConstant.SEPARATOR);
		
		for (int x = 0; x < arrayErrors.length; x++){
			
			Log.i("LogsAndroid", "Data: " + arrayErrors[x]);
		
		switch(arrayErrors[x]){
		
		case CConstant.Response.SUCCES:
			result = CConstant.Response.SUCCES; 
			break;
		case CConstant.Response.ERROR:
			result = CConstant.Response.ERROR;
			break;	
		case CConstant.Response.E_USER_ALREADY_LOGGED:
			result = CConstant.Response.E_USER_ALREADY_LOGGED;
			break;	
		case CConstant.Response.E_PASSWORD_INCORRECT:
			result = CConstant.Response.E_PASSWORD_INCORRECT;
			break;
		case CConstant.Response.E_EMAIL_DOESNT_EXIST:
			result = CConstant.Response.E_EMAIL_DOESNT_EXIST;
			break;	
		case CConstant.Response.E_USER_DOESENT_EXIST:
			result = CConstant.Response.E_USER_DOESENT_EXIST;
			break;	
		case CConstant.Response.E_USER_ALREADY_USED:
			result = CConstant.Response.E_USER_ALREADY_USED;
			break;	
		case CConstant.Response.E_EMAIL_ALREADY_USED:
			result = CConstant.Response.E_EMAIL_ALREADY_USED;
			break;
		case CConstant.Response.E_PLAYERNAME_ALREADY_USED:
			result = CConstant.Response.E_PLAYERNAME_ALREADY_USED;
			break;	
			
		default:
			break;
		}
		
		}
		Log.i("LogsAndroid", "Result: " + result);
		return result;
	}

	public static boolean sendRegisterTaped() {

		boolean logged = false;
		// Log.i("LogsAndroid", ""+connection.equals(null));
		Log.i("LogsAndroid", "prepare register...");
		connection.sendData(CConstant.REGISTER + CConstant.SEPARATOR
				+ CConstant.REGISTER + CConstant.SEPARATOR + 0.0
				+ CConstant.SEPARATOR + 0.0);
		Log.i("LogsAndroid", "send register tapped");
		if (connection.readData().equals(CConstant.Response.SUCCES)) {
			Log.i("LogsAndroid", "Register Tapped...");
			logged = true;
		}

		return logged;
	}

	public static boolean sendSearchBattle() {
		boolean op = false;

		Log.i("LogsAndroid", "prepare send searching...");
		connection.sendData(CConstant.START_BATTLE);
		Log.i("LogsAndroid", "send searching...");
		if (connection.readData().equals(CConstant.Response.SUCCES)) {
			op = true;
			Log.i("LogsAndroid", "recived...");
		}
		return op;
	}

	public static boolean sendRegisterData(String mail, String username,
			String password) {
		boolean logged = false;
		Log.i("LogsAndroid", "Preparing send data...");
		connection.sendData(mail + CConstant.SEPARATOR + username
				+ CConstant.SEPARATOR + password);
		Log.i("LogsAndroid", "Data sended");
		if (connection.readData().equals(CConstant.Response.SUCCES)) {
			Log.i("LogsAndroid", "Data recived");
			logged = true;
		}
		return logged;
	}

	public static boolean sendRegisterPlayer(String playerName,
			String idPlayerType, String life, String energy,
			String eRegeneration, String strength, String intelligence) {
		boolean logged = false;
		Log.i("LogsAndroid", "Preparing send register player data...");
		connection.sendData(playerName + CConstant.SEPARATOR + idPlayerType
				+ CConstant.SEPARATOR + life + CConstant.SEPARATOR + energy
				+ CConstant.SEPARATOR + eRegeneration + CConstant.SEPARATOR
				+ strength + CConstant.SEPARATOR + intelligence
				+ CConstant.SEPARATOR + 1 + CConstant.SEPARATOR + 5);
		Log.i("LogsAndroid", "Register player data sended");
		if (connection.readData().equals(CConstant.Response.SUCCES)) {
			Log.i("LogsAndroid", "Register player data recived");
			logged = true;
		}
		return logged;
	}

	public static String getData() {
		return connection.readData();
	}

	public static void sendDataShowMyData() {
		connection.sendData(CConstant.SHOW_MY_DATA);
		Log.i("LogsAndroid", "Data recived.");
	}

	public static void sendData(String data) {
		Log.i("LogsAndroid", "Prepare send battle action...");
		connection.sendData(data);
		Log.i("LogsAndroid", "Battle action sended: " + data);
	}

	public static void sendRegisterOp() {

		connection.sendData(CConstant.REGISTER + CConstant.SEPARATOR
				+ CConstant.REGISTER + CConstant.SEPARATOR + CConstant.REGISTER
				+ CConstant.SEPARATOR + CConstant.REGISTER);
	}

	// Get error number from server and converts to error name
	public static String getErrorNameByErrorNum(String num) {
		String errorName = "";

		switch (num) {
		case CConstant.Response.SUCCES:
			errorName = "Success :)";
			break;
		case CConstant.Response.ERROR:
			errorName = "Error :(";
			break;
		case CConstant.Response.E_USER_ALREADY_LOGGED:
			errorName = "Error: User already logged.";
			break;
		case CConstant.Response.E_PASSWORD_INCORRECT:
			errorName = "Error: Password incorrect.";
			break;
		case CConstant.Response.E_EMAIL_DOESNT_EXIST:
			errorName = "Error: Email doesn't exists.";
			break;
		case CConstant.Response.E_USER_DOESENT_EXIST:
			errorName = "Error: User doesn't exists.";
			break;
		case CConstant.Response.E_USER_ALREADY_USED:
			errorName = "Error: User already used.";
			break;
		case CConstant.Response.E_EMAIL_ALREADY_USED:
			errorName = "Error: Email alredy used.";
			break;
		case CConstant.Response.E_PLAYERNAME_ALREADY_USED:
			errorName = "Error: Player name already used.";
			break;
		default:
			break;
		}
		return errorName;
	}

}
