package it.sandona.avis.avis.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import it.sandona.avis.avis.MainActivity;

import static it.sandona.avis.avis.R.layout.splash_initial;

/**
 * Created by Root on 27/01/2016.
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

/*        if (Build.VERSION.SDK_INT > 16) {
            View decorView = getWindow().getDecorView();
// Hide the status bar.
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }*/
        setContentView(splash_initial);

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(1500);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        };
        timerThread.start();
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}