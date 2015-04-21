package dam.mw.clientAndroid.game;

import java.net.Socket;


//esta clase es provisional y puede terminar desapareciendo....

public class CUser {
    private String userName;
    private String email;
    private String password; // se tiene que encriptar? Hace falta que la tengamos aquí?
    private CPlayer player;
    private Socket socket;
    private String longitude;
    private String latitude;
    private String createdAt;

    /**
     * CONSTRUCTOR
     * @param userName
     * @param email
     * @param password
     * @param player 
     */
    public CUser(String userName, String email, String password, CPlayer player) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.player = player;
    }
    
    public CUser(String userName, String email, String password, String createdAt,  CPlayer player){
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.player = player;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public CPlayer getPlayer() {
        return player;
    }
    public void setPlayer(CPlayer player) {
        this.player = player;
    }
    
    public String getLatitude(){
        return latitude;
    }
    public void setLatitude(String latitude){
        this.latitude = latitude;
    }
    public void setLongitude(String longitude){
        this.longitude = longitude;
    }
    public String getLongitude(){
        return longitude;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    
    public Socket getSocket(){
        return socket;
    }
    public void setSocket(Socket socket){
        this.socket = socket;
    }
    
    
       
}
