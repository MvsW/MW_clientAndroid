package dam.mw.clientAndroid.official;
import dam.mw.clientAndroid.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class ASplash extends Activity {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 1000;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.splashscreen);
        /** Hide action bar **/
        getActionBar().hide();
        /* New Handler to start the Menu-Activity 
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(ASplash.this,ALogin.class);
                ASplash.this.startActivity(mainIntent);
                ASplash.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
