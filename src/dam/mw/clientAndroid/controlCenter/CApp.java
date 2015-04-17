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
		InetAddress serverAddr = InetAddress.getByName(CConstants.HOST);// HOST
		socket = new Socket(serverAddr, CConstants.PORT);

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
		if (connection.readData().equals(CConstants.SUCCES)) {

			Log.i("LogsAndroid", "recived...");
			// Ok login correcte
			logged = true;
		}

		return logged;
	}
	public static boolean sendSearchBattle(){
		boolean op = false;

		Log.i("LogsAndroid", "prepare send searching...");
		connection.sendData(CConstants.START_BATTLE);
		Log.i("LogsAndroid", "send searching...");
		if(connection.readData().equals(CConstants.SUCCES)){
			op = true;
			Log.i("LogsAndroid", "recived...");
		}
		return op;
	}

	public static boolean sendRegisterData(String username, String mail,
			String password) {
		boolean logged = false;
		connection.sendData(username + "," + mail + "," + password + ","
				+ CApp.getLongitude() + "," + CApp.getLatitude());
		if (connection.readData().equals(CConstants.SUCCES)) {
			// Ok login correcte
			logged = true;
		}
		return logged;
	}

	public static String getData() {
		return connection.readData();
	}

	public static void sendDataShowMyData() {
		connection.sendData(CConstants.SHOW_MY_DATA);
	}
	public static void sendData(String data){
		connection.sendData(data);
	}

	public static void sendRegisterOp() {

		connection.sendData(CConstants.REGISTER + "," + CConstants.REGISTER
				+ "," + CConstants.REGISTER + "," + CConstants.REGISTER);
	}

}
