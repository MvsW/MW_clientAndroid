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
    public final static String CANCEL = "cancel";
    
    //ACTION BUTTONS BATTLE
    public final static String BASIC = "1";
    public final static String SPELL1 = "2";
    public final static String SPELL2 = "3";
    public final static String ULTIMATE = "4";
    public final static String SHIELD = "5";
    public final static String DODGE = "6";
    
    //DEFINITION ATTACK
    public final static String TEXT_BASIC ="No energy consumption damages the opponent based on your strength.";
    public final static String TEXT_SPELL1 ="In exchange for a small amount of power you damage your opponent depending on your intelligence.";
    public final static String TEXT_SPELL2 ="Cause considerable damage to the enemy according to your intelligence and your strength half.";
    public final static String TEXT_ULTIMATE ="In exchange for almost all your energy you can exercise more than considerable damage on your opponent. This spell takes into account both your strengths as intelligence.";
    public final static String TEXT_SHIELD ="It allows you to reduce all or part of the damage taken according to your strengths.";
    public final static String TEXT_DODGE ="Depending on your intelligence you have the possibility to avoid all or part of the damage taken.";
    
    //NAME ATTACKS
    public final static String NAME_BASIC = "Basic Attack";
    public final static String NAME_SPELL1 = "Spell 1";
    public final static String NAME_SPELL2 = "Spell 2";
    public final static String NAME_ULTIMATE = "Ultimate";
    public final static String NAME_SHIELD = "Shield";
    public final static String NAME_DODGE = "Dodge";
    
    public final static String SEPARATOR = ",";
    
    
    
    /**
     *CONSTANTS PLAYER POINTS & CALCS
     */
    public static class PlayerUtils {
    	public final static int TOTAL_POINTS = 200;
    	public final static double BASE_PERCENT = 0.76;
    	public final static double RAND_PERCENT = 0.04;
    	public final static double CUSTOM_PERCENT = 0.20;
    	public final static double LIFE_INTEL_PERCENT = 0.70;
    	public final static double REGEN_ENERGY_BASE_PERCENT = 0.10;
    
    //Calcs
		public static int BASE_CALC = (int) (TOTAL_POINTS * BASE_PERCENT);
    	public static int RAND_CALC = (int)(TOTAL_POINTS * RAND_PERCENT);
    	public static int CUSTOM_CALC = (int)( TOTAL_POINTS * CUSTOM_PERCENT);
    }
    /**
     * STRUCTURED ERRORS
     */
    public static class Response {
    	
    	// 
        public final static String WIN = "10";
        public final static String LOSE = "1";
        public final static String DRAW = "11";
        
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
