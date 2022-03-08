package com.example.osamafinalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements DialogInterface.OnClickListener {

    private TextView tvFishMain;
    private ListView lvMain;
    private SearchView svMain;
    private FloatingActionButton fabMain;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvFishMain=findViewById(R.id.tvFishMain);
        lvMain=findViewById(R.id.lvMain);
        svMain=findViewById(R.id.svMain);
        fabMain=findViewById(R.id.fabMain);
        registerForContextMenu( fabMain );
        fabMain.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText( getApplicationContext(), "PLEASE LONG CLICK", Toast.LENGTH_SHORT ).show();
                MainActivity.this.openContextMenu(view);

            }
        } );

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        // you can set menu header with title icon etc
        menu.setHeaderTitle("Add:");
        // add menu items
        menu.add(0, v.getId(), 0, "Location");
        menu.add(0, v.getId(), 0, "Catch");
    }

    // menu item select listener
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if (item.getTitle() == "Location") {
            startActivity( new Intent(getApplicationContext(),AddLocations.class) );
        } else if (item.getTitle() == "Catch") {
            startActivity( new Intent(getApplicationContext(),MyLocations.class) );        }

        return true;
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
        if(item.getItemId()==R.id.itmMap)
        {
            Intent i=new Intent(getApplicationContext(),MainActivity.class);
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