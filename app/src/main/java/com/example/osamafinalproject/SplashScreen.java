package com.example.osamafinalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SplashScreen extends AppCompatActivity {
    private ImageView ivSplash;
    private TextView tvFishMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ivSplash = findViewById(R.id.ivSplash);tvFishMap = findViewById(R.id.tvFishMap);
        //thread 1
        Thread th = new Thread() {
            //thead2
            public void run() {
                //هذا المقطع الذي سيعمل بالتزامن مع مقاطع اخرى
                //thread 3
                int i = 3 * 1000;//millseconds
                try {
                    sleep(i);
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        //thread 4
        th.start();
    }
    }




