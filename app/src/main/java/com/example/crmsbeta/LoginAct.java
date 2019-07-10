package com.example.crmsbeta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginAct extends AppCompatActivity {

    Animation login_animate;
    ImageView ha_logo, crms_logo, help_icon;
    TextView textView3, textView, error_msg;
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
        error_msg = findViewById(R.id.error_msg);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String xpassword = password.getText().toString();

                if(xpassword.isEmpty()){
                    error_msg.setAlpha(1);
                }
                else
                {
                    error_msg.setAlpha(0);
                    // change activity
                    Intent gogethome = new Intent(LoginAct.this,HomeAct.class);
                    gogethome.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(gogethome);
                    overridePendingTransition(0,0); //0 for no animation
                    finish();
                }
            }
        });

        error_msg.setAlpha(0);


        // set animate
        login_animate = AnimationUtils.loadAnimation(this, R.anim.login_animate);

        // start animate
        ha_logo.startAnimation(login_animate);
        crms_logo.startAnimation(login_animate);
        help_icon.startAnimation(login_animate);

        textView3.startAnimation(login_animate);
        textView.startAnimation(login_animate);
        error_msg.startAnimation(login_animate);

        username.startAnimation(login_animate);
        password.startAnimation(login_animate);

        btn_login.startAnimation(login_animate);
    }
}
