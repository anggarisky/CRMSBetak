package com.example.crmsbeta;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class HomeAct extends AppCompatActivity {

    private LinearLayout containerCaseHandling;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar_home = findViewById(R.id.home_toolbar);
        containerCaseHandling = findViewById(R.id.containerCaseHandling);

        setSupportActionBar(toolbar_home);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        containerCaseHandling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeAct.this, CaseHandlingActivity.class));
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {

            case R.id.task_list:
                Toast.makeText(HomeAct.this, item.getTitle().toString(), Toast.LENGTH_SHORT).show();
                break;

            case R.id.notification:
                Toast.makeText(HomeAct.this, item.getTitle().toString(), Toast.LENGTH_SHORT).show();
                break;

            case R.id.message_box:
                Toast.makeText(HomeAct.this, item.getTitle().toString(), Toast.LENGTH_SHORT).show();
                break;

            case R.id.setting:
                Toast.makeText(HomeAct.this, item.getTitle().toString(), Toast.LENGTH_SHORT).show();
                break;

            case R.id.help:
                Toast.makeText(HomeAct.this, item.getTitle().toString(), Toast.LENGTH_SHORT).show();
                break;

            case R.id.logout:
                Toast.makeText(HomeAct.this, item.getTitle().toString(), Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
