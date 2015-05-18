package dam.mw.clientAndroid.controlCenter;

/**
 * All the metods of this static class uses for get some random value.
 * @author Roger Martín
 * @since MagesVsWarlocks 1.0.0
 */
public class CRandom {
    
    /**
     * Without params this method returns an integer between 0 and 10 as 
     * integer format.
     * @return int
     */
    public static int generaInt(){
        int i=(int)(Math.random()*10);
        return i;
    }
    
    
    
    /**
     * This method returns an integer between 0 and the maxValue given with
     * the last not included.
     * @param maxValue int
     * @return int
     */
    public static int generaInt(int maxValue){
        //genera num valor max restringit
        int i=(int)(Math.random()*maxValue)+1;
        return i;
    }
    
    /**
     * Without params this method returns an integer between 0 and 10 as float
     * format.
     * @return float
     */
    public static float generaFloat(){
        float i=((int)(Math.random()*10));
        float b = (float)(((int)(Math.random()*10)))/10;
        return i+b;
    }
    
    /**
     * Returns a random float value between the range, min and max given. Both
     * numbers are not included.
     * @param min int
     * @param max int
     * @return float
     */
    public static float getIntBetween(int min, int max){
        int range = (max-min)+1;
        float i=(int)(Math.random()*range)+min;
        float b = (float)(((int)(Math.random()*10)))/10;
        return i+b;        
    }
    
}
