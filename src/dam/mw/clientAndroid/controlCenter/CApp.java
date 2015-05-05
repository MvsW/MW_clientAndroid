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

	public static boolean sendLogin(String userOrMail, String Password,
			double longitude, double latitude) {

		boolean logged = false;
		// Log.i("LogsAndroid", ""+connection.equals(null));
		Log.i("LogsAndroid", "prepare send login...");
		connection.sendData(userOrMail + "," + Password + "," + latitude + ","
				+ longitude);
		Log.i("LogsAndroid", "send login...");
		if (connection.readData().equals(CConstant.SUCCES)) {
			Log.i("LogsAndroid", "recived...");
			// Ok login correcte
			logged = true;
		}else if(connection.readData().equals(CConstant.ERROR)){
			Log.i("LogsAndroid", "Error ocurred...");
			// Ok login correcte
			logged = false;
		}
		
	

		return logged;
	}
	
	public static boolean sendRegisterTaped() {

		boolean logged = false;
		// Log.i("LogsAndroid", ""+connection.equals(null));
		Log.i("LogsAndroid", "prepare register...");
		connection.sendData(CConstant.REGISTER + "," + CConstant.REGISTER + "," + 0.0 + ","+ 0.0);
		Log.i("LogsAndroid", "send register tapped");
		if (connection.readData().equals(CConstant.SUCCES)) {
			Log.i("LogsAndroid", "Register Tapped...");
			logged = true;
		}

		return logged;
	}
	
	public static boolean sendSearchBattle(){
		boolean op = false;

		Log.i("LogsAndroid", "prepare send searching...");
		connection.sendData(CConstant.START_BATTLE);
		Log.i("LogsAndroid", "send searching...");
		if(connection.readData().equals(CConstant.SUCCES)){
			op = true;
			Log.i("LogsAndroid", "recived...");
		}
		return op;
	}

	public static boolean sendRegisterData(String mail, String username,
			String password) {
		boolean logged = false;
		Log.i("LogsAndroid", "Preparing send data...");
		connection.sendData(mail + "," + username + "," + password);
		Log.i("LogsAndroid", "Data sended");
		if (connection.readData().equals(CConstant.SUCCES)) {
			Log.i("LogsAndroid", "Data recived");
			logged = true;
		}
		return logged;
	}
	
	public static boolean sendRegisterPlayer(String playerName, String idPlayerType, String life, String energy, String eRegeneration, String strength, String intelligence) {
		boolean logged = false;
		Log.i("LogsAndroid", "Preparing send register player data...");
		connection.sendData(playerName + "," + idPlayerType + "," + life + "," + energy + "," + eRegeneration + "," + strength + "," + intelligence+"," + 1+"," + 5);
		Log.i("LogsAndroid", "Register player data sended");
		if (connection.readData().equals(CConstant.SUCCES)) {
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
	}
	public static void sendData(String data){
		connection.sendData(data);
	}

	public static void sendRegisterOp() {

		connection.sendData(CConstant.REGISTER + "," + CConstant.REGISTER
				+ "," + CConstant.REGISTER + "," + CConstant.REGISTER);
	}

}
