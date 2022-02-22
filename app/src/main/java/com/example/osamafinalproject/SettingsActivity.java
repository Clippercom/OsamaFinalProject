package com.example.osamafinalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class SettingsActivity extends AppCompatActivity {
    private Button btnoutsettings;
    private Switch sthNightMode;
    private TextView tvMaps,tvDistances,tvSpeed,tvWeather,tvTemperature,tvWater,tvTide,tvWaveheight;
    private RadioGroup rgpDistances,rgpSpeed,rgpTemperature,rgpTide,rgpWave;
    private RadioButton rbtnDistances1,rbtnDistances2,rbtnDistances3,rbtnSpeed1,rbtnSpeed2,rbtnTemperature1,rbtnTemperature2,rbtnTide1,rbtnTide2,rbtnWave1,rbtnWave2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);btnoutsettings=findViewById(R.id.btnoutsettings);sthNightMode=findViewById(R.id.sthNightMode);tvMaps=findViewById(R.id.tvMaps);tvDistances=findViewById(R.id.tvDistances);tvSpeed=findViewById(R.id.tvSpeed);tvWeather=findViewById(R.id.tvWeather);tvTemperature=findViewById(R.id.tvTemperature);tvWater=findViewById(R.id.tvWater);tvTide=findViewById(R.id.tvTide);tvWaveheight=findViewById(R.id.tvWaveheight);rgpDistances=findViewById(R.id.rgpDistances);rgpSpeed=findViewById(R.id.rgpSpeed);rgpTemperature=findViewById(R.id.rgpTemperature);rgpTide=findViewById(R.id.rgpTide);rgpWave=findViewById(R.id.rgpWave);rbtnDistances1=findViewById(R.id.rbtnDistances1);rbtnDistances2=findViewById(R.id.rbtnDistances2);rbtnDistances3=findViewById(R.id.rbtnDistances3);rbtnSpeed1=findViewById(R.id.rbtnSpeed1);rbtnSpeed2=findViewById(R.id.rbtnSpeed2);rbtnTemperature1=findViewById(R.id.rbtnTemperature1);rbtnTemperature2=findViewById(R.id.rbtnTemperature2);rbtnTide1=findViewById(R.id.rbtnTide1);rbtnTide2=findViewById(R.id.rbtnTide2);rbtnWave1=findViewById(R.id.rbtnWave1);rbtnWave2=findViewById(R.id.rbtnWave2);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mian_menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.itmsettings)
        {
            Intent i=new Intent(getApplicationContext(),SettingsActivity.class);
            startActivity(i);
        }
        if(item.getItemId()==R.id.itmLocation)
        {
            Intent i=new Intent(getApplicationContext(),MyLocations.class);
            startActivity( i );
        }
        if(item.getItemId()==R.id.itmWaves)
        {
            Intent i=new Intent(getApplicationContext(),Waves.class);
            startActivity(i);
        }
        if(item.getItemId()==R.id.itmMap)
        {
            Intent i=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
        }
        if(item.getItemId()==R.id.itmCatches)
        {
            Intent i=new Intent(getApplicationContext(),Catches.class);
            startActivity(i);
        }
        if(item.getItemId()==R.id.itmSignIn)
        {
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage("Are you sure?");
            builder.setCancelable(true);
            builder.setPositiveButton("Yes",this::onClick);
            builder.setNegativeButton("No",this::onClick);
            AlertDialog dialog = builder.create();
            dialog.show();


        }
        return true;
    }

    public void onClick(DialogInterface dialogInterface, int which) {
        if(which == dialogInterface.BUTTON_POSITIVE)
        {
            Toast.makeText(getApplicationContext(), "loging out", Toast.LENGTH_SHORT).show();
            dialogInterface.cancel();
            FirebaseAuth auth=FirebaseAuth.getInstance();
            auth.signOut();
            finish();
        }
        if( which == DialogInterface.BUTTON_NEGATIVE)
        {
            Toast.makeText(getApplicationContext(), "loging out canceled", Toast.LENGTH_SHORT).show();
            dialogInterface.cancel();
        }
    }
}