package com.example.crmsbeta;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ImageView ha_logo, crms_logo;
    TextView textView2, textView;
    Animation splash_animate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ha_logo = findViewById(R.id.ha_logo);
        crms_logo = findViewById(R.id.crms_logo);

        textView2 = findViewById(R.id.textView2);
        textView = findViewById(R.id.textView);

        // set animate
        splash_animate = AnimationUtils.loadAnimation(this, R.anim.splash_animate);

        // start animate
        ha_logo.startAnimation(splash_animate);
        crms_logo.startAnimation(splash_animate);
        textView2.startAnimation(splash_animate);
        textView.startAnimation(splash_animate);

        // setting timer
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // change activity
                Intent gogetlogin = new Intent(MainActivity.this,LoginAct.class);
                gogetlogin.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(gogetlogin);
                overridePendingTransition(0,0); //0 for no animation
                finish();
            }
        }, 3000); // 3000 ms = 3s


    }
}
