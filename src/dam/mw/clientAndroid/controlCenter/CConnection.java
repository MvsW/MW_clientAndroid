package dam.mw.clientAndroid.controlCenter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author bc
 */
/*Tipo A*/
//procesar Streams I/O i establir flux


public class CConnection {

    private Socket conexio;

    private OutputStream os;
    private InputStream is;

    public CConnection(Socket socket) {
        this.conexio = socket;
        obtenirFlux();
    }

    private void obtenirFlux() {//obtenir flux deja preparado los outputs y inputs
        try {
            os = conexio.getOutputStream();
            os.flush();
            is = conexio.getInputStream();
        } catch (IOException ex) {
            System.err.println("error al obtenir el flux client");
            //Implementar Tancar socket acaba thread
        }
    }

    public void sendData(String msg) {
        try {
            os.write(msg.getBytes());
            os.flush();
        } catch (IOException ex) {
            System.err.println("error d'escritura d'objecte");
            //Implementar Tancar socket acaba thread
        }
    }

    public String readData() {
        try {
            byte array[] = new byte[1024];
            int readed = is.read(array);
            return new String(array).trim();
        } catch (IOException ex) {
            System.err.println("Error de lectura");
            //Implementar Tancar socket acaba thread
            return "";
        }
    }

}
