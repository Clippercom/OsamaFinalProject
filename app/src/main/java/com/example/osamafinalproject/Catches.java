package com.example.osamafinalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.widget.ListView;

public class Catches extends AppCompatActivity {
    private ListView ltvCatch;
    private SearchView srvCatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catches);
        ltvCatch=findViewById(R.id.ltvCatch);
        srvCatch=findViewById(R.id.srvCatch);
    }
}