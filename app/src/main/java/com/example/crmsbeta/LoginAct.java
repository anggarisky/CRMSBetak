package com.example.crmsbeta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginAct extends AppCompatActivity {

    Animation login_animate;
    ImageView ha_logo, crms_logo, help_icon;
    TextView textView3, textView;
    EditText username, password;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        ha_logo = findViewById(R.id.ha_logo);
        crms_logo = findViewById(R.id.crms_logo);
        help_icon = findViewById(R.id.help_icon);

        textView3 = findViewById(R.id.textView3);
        textView = findViewById(R.id.textView);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        btn_login = findViewById(R.id.btn_login);


        // set animate
        login_animate = AnimationUtils.loadAnimation(this, R.anim.login_animate);

        // start animate
        ha_logo.startAnimation(login_animate);
        crms_logo.startAnimation(login_animate);
        help_icon.startAnimation(login_animate);

        textView3.startAnimation(login_animate);
        textView.startAnimation(login_animate);

        username.startAnimation(login_animate);
        password.startAnimation(login_animate);

        btn_login.startAnimation(login_animate);
    }
}
