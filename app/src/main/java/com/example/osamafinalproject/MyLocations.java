package com.example.osamafinalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.widget.ListView;

public class MyLocations extends AppCompatActivity {
    private SearchView svLocations;
    private ListView lvLocations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_locations);
        svLocations=findViewById(R.id.svLocations);
        lvLocations=findViewById(R.id.lvLocations);

    }
}