package dam.mw.clientAndroid.controlCenter;

public final class CConstant {

	public static final String HOST = "kintoncloud.com";
	//public static final String HOST = "192.168.1.11";
	public static final int PORT = 4444;
	
	public static final String MAGE = "1";
	public static final String WARLOCK = "2";
	
	
    public static final String REGISTER = "register";
    public static final String SHOW_MY_DATA = "showData";
    public static final String START_BATTLE = "startBattle";
    //ACTION BUTTONS BATTLE
    public final static String BASIC = "1";
    public final static String SPELL1 = "2";
    public final static String SPELL2 = "3";
    public final static String ULTIMATE = "4";
    public final static String SHIELD = "5";
    public final static String DODGE = "6";
    
    /**
     * STRUCTURED ERRORS
     */
    public static class Response {
        
        /**
         * This class can be used for switching which type of responses
         * have receiving. Success also put on this class.
         */
        
        // SUCCESS
        public final static String SUCCES = "0";
        
        /**
         * ERRORS
         */
        public final static String ERROR = "100"; // generic
        
        // Login errors
        public final static String E_USER_ALREADY_LOGGED = "101";
        public final static String E_PASSWORD_INCORRECT = "102";
        public final static String E_EMAIL_DOESNT_EXIST = "103";
        public final static String E_USER_DOESENT_EXIST = "104";
        
        // Register errors
        public final static String E_USER_ALREADY_USED = "110";
        public final static String E_EMAIL_ALREADY_USED = "111";
        public final static String E_PLAYERNAME_ALREADY_USED = "112";
        
    }
	
	
}
