package com.example.mymarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends AppCompatActivity {
        private static final long SPLASH_DISPLAY_LENGTH= 3000;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_splash);
            /*
             * visualizzare l activity per un tempo definito da una variabile
             * */
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Intent i = getIntent();
                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);

                }
            }, SPLASH_DISPLAY_LENGTH);
    }
}