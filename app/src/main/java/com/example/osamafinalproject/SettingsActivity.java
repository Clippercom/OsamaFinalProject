package com.example.osamafinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

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
}