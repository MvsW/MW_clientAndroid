package dam.mw.clientAndroid.controlCenter;

import java.net.InetAddress;
import java.net.Socket;

public class CApp {

	private static CConnection connection;
	private static Socket socket;

	public static void connect() throws Exception { // Modificar per tractament
													// propi
		InetAddress serverAddr = InetAddress.getByName(CConstants.HOST);// HOST
		socket = new Socket(serverAddr, CConstants.PORT);

		connection = new CConnection(socket);
	}

	public static boolean sendLogin(String userOrMail, String Password,
			String longitude, String latitude) {
		boolean logged = false;
		connection.sendData(userOrMail+","+Password+","+longitude+","+latitude);
		if(connection.readData().equals(CConstants.SUCCES)){
			//Ok login correcte
			logged = true;
		}
		return logged;
	}
	public static void sendRegisterOp(){
		connection.sendData(CConstants.REGISTER+","+CConstants.REGISTER);
	}

}
