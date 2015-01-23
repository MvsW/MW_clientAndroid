package dam.mw.clientAndroid;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import dam.mw.clientAndroid.R;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class MainActivity extends Activity {

	/**
	 * Graphic components
	 */
	private EditText et_ip, et_message;
	private Button btn_connect, btn_send_message;
	private TextView tv_log, tv_xivato;

	private ObjectOutputStream sortida;
	private ObjectInputStream entrada;
	
	private OutputStream testSortida;
	private InputStream testEntrada;

	private Context context;
	private Activity activity;
	static String HOST = "null";
	static final int PORT = 4444;
	private Socket socket;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(1);
		setContentView(R.layout.activity_main);

		// Initialize graphic components
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_message = (EditText) findViewById(R.id.et_message);

		btn_connect = (Button) findViewById(R.id.btn_connect);
		btn_send_message = (Button) findViewById(R.id.btn_send_message);

		tv_log = (TextView) findViewById(R.id.tv_log);
		tv_xivato = (TextView) findViewById(R.id.tv_xivato);

		btn_send_message.setEnabled(false);
		context = this;
		activity = this;
		if (checkConnection()) {
			Toast toast = Toast.makeText(this, "true", Toast.LENGTH_SHORT);
			toast.show();

		} else {
			Toast toast = Toast.makeText(this, "false", Toast.LENGTH_SHORT);
			toast.show();
			System.exit(1);
		}

		btn_connect.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String host = et_ip.getText().toString();
				HOST = host;
				if (!host.isEmpty()) {
					tv_log.setText("");
					// new Thread(new ClientThread()).start();
					new ClientAsync().execute();
					btn_connect.setEnabled(false);
				} else {
					et_ip.setError("Camp buit. Impossible connectar");
				}
			}
		});

		btn_send_message.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Llegim les dades
				String message = et_message.getText().toString();
				if (!message.isEmpty() && message != null) {
					printarLog("-->"+message);
					try {
						enviarDades(message);
						et_message.setText("");
					} catch (IOException e) {
						Log.v("LogsAndroid", e.getMessage());
					}
				}
			}
		});
	}

	public boolean checkConnection() {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		boolean isConnected = activeNetwork != null
				&& activeNetwork.isConnectedOrConnecting();

		return isConnected;
	}

								/** METHODS UPDATE UI  **/
	
	public void printarLog(final String message) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				tv_log.append(message + "\n");
			}
		});
	}
	public void resetGUI(){
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				//ALGUNA COSA MES A REINICIAR??????
				btn_connect.setEnabled(true);
				btn_send_message.setEnabled(false);
			}
		});
	}
	public void activarBtnSend(){
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				btn_send_message.setEnabled(true);
				
			}
		});
	}
									/** FINAL UPDATES**/
	

	public void enviarDades(String message) throws IOException {
		//this.sortida.writeObject(message);
		testSortida.write(message.getBytes());
	}

	class ClientThread implements Runnable {
		@Override
		public void run() {

		}
	}

	class ClientAsync extends AsyncTask<Void, Integer, Boolean> {

		@Override
		protected Boolean doInBackground(Void... params) {
			try {
				InetAddress serverAddr = InetAddress.getByName(HOST);
				socket = new Socket(serverAddr, PORT);
				printarLog("Conectat a: "
						+ socket.getInetAddress().getHostName());

//				sortida = new ObjectOutputStream(socket.getOutputStream());
//				sortida.flush();
//				entrada = new ObjectInputStream(socket.getInputStream());
				
				testSortida = socket.getOutputStream();
				testSortida.flush();
				testEntrada = socket.getInputStream();

				printarLog("S'han rebut els flux de E/S");
				printarLog("Buscant oponent...");

				activarBtnSend();
				
				try {
					//printarLog((String) entrada.readObject()); // Rebem el què
					printarLog(llegir());						// ens diu el
																// servidor.
					printarLog("Introdueix un text pel servidor");
					// /VERSIO BETA PROTOCOL
					boolean escoltant = true;
					do {
						//String msg = (String) entrada.readObject();
						String msg = llegir();
						printarLog("<---" + msg);
						if(msg.equals("bye,bye")){
							escoltant = false;
						}
					} while(escoltant);
					Log.i("LogsAndroid", "<--- servidor a tancat la conexio amb exit");
					desconectar();
					
				} catch (Exception e) {
					Toast.makeText(activity, e.toString(), Toast.LENGTH_SHORT)
							.show();
				}
			} catch (UnknownHostException e1) {
				Log.e("LogsAndroid", "Host desconegut");
			} catch (IOException e1) {
				Log.e("LogsAndroid","Error I/O ->" + e1.toString());
			} catch (Exception e1) {
				Log.e("LogsAndroid", e1.getMessage());
			}
			return true;
		}
		
		public String llegir() throws IOException{
			String msg;
			byte array[] = new byte[1024];
            //int readed = testEntrada.read(array);
           // msg = new String(array).trim();
            
            BufferedInputStream bis = new BufferedInputStream(testEntrada);
            int readed = bis.read(array);
            if(readed == -1){
            	desconectar();
            	Log.i("LogsAndroid","-1 desconectar!!");
            }
            msg = new String(array).trim();
            return  msg;
		}
		
		public void desconectar(){
			try {
				socket.close();
				resetGUI();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.e("LogsAndroid",e.getMessage());
			}
		}
	}
}
