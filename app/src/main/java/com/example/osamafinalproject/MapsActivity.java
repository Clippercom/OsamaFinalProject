package com.example.osamafinalproject;
// https://github.com/Pritish-git/get-Current-Location/blob/main/MainActivity.java

import MyData.MyLoc;
import MyData.MyLocAdapter;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.osamafinalproject.databinding.ActivityMaps2Binding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMaps2Binding binding;
    private FloatingActionButton addMap;
    private SearchView srvMap;
    private ListView lstvLocs;
    private MyLocAdapter myLocAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        srvMap = findViewById( R.id.srvMap );
        addMap = findViewById( R.id.addMap );
        binding = ActivityMaps2Binding.inflate( getLayoutInflater() );
        setContentView( binding.getRoot() );

        lstvLocs = findViewById( R.id.lstvLocs );
        myLocAdapter = new MyLocAdapter( this, R.layout.loc_item_layout );

        lstvLocs.setAdapter( myLocAdapter );
        lstvLocs.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MyLoc t = (MyLoc) adapterView.getItemAtPosition( i );
                LatLng loc = new LatLng( t.getLat(), t.getLang() );
                mMap.moveCamera( CameraUpdateFactory.newLatLngZoom( loc, 14 ) );
            }
        } );

//        addMap.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity( new Intent( getApplicationContext(), AddLocations.class ) );
//            }
//        } );


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById( R.id.map );
        mapFragment.getMapAsync( this );

        addMap = findViewById( R.id.addMap );
        registerForContextMenu( addMap );
        addMap.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText( getApplicationContext(), "PLEASE LONG CLICK", Toast.LENGTH_SHORT ).show();
                MapsActivity.this.openContextMenu( view );

            }
        } );

    }

    @Override
    protected void onResume() {
        super.onResume();

        readLocsFromFireBase( "" );
    }

    private void readLocsFromFireBase(String s) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        String uid = FirebaseAuth.getInstance().getUid();// cuurent user id.

        //?????????? ?????????????? "??????????????" ?????? ?????????? ?????????? ?????? ?????????? ???? ?????????? ????????????????
        ref.child( "mylocs" ).addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                myLocAdapter.clear();
                int i = 0;
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    MyLoc t = d.getValue( MyLoc.class );
                    myLocAdapter.add( t );
                    if (mMap != null) {
                        LatLng loc = new LatLng( t.getLat(), t.getLang() );
                        mMap.addMarker( new MarkerOptions().position( loc ).title( t.getTitle() ) );
                        if (i == 0) {
                            mMap.moveCamera( CameraUpdateFactory.newLatLngZoom( loc, 14 ) );
                            i++;
                        }
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu( menu, v, menuInfo );
        // you can set menu header with title icon etc
        menu.setHeaderTitle( "Add:" );
        // add menu items
        menu.add( 0, v.getId(), 0, "Location" );
    }

    // menu item select listener
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if (item.getTitle() == "Location") {
            startActivity( new Intent( getApplicationContext(), AddLocations.class ) );
        }

        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.mian_menu, menu );
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itmsettings) {
            Intent i = new Intent( getApplicationContext(), SettingsActivity.class );
            startActivity( i );
        }
        if (item.getItemId() == R.id.itmLocation) {
            Intent i = new Intent( getApplicationContext(), MyLocations.class );
            startActivity( i );
        }
        if (item.getItemId() == R.id.itmMap) {
            Intent i = new Intent( getApplicationContext(), MapsActivity.class );
            startActivity( i );
        }
        if (item.getItemId() == R.id.itmSignIn) {
            AlertDialog.Builder builder = new AlertDialog.Builder( this );
            builder.setMessage( "Are you sure?" );
            builder.setCancelable( true );
            builder.setPositiveButton( "Yes", this::onClick );
            builder.setNegativeButton( "No", this::onClick );
            AlertDialog dialog = builder.create();
            dialog.show();


        }
        return true;
    }

    public void onClick(DialogInterface dialogInterface, int which) {
        if (which == dialogInterface.BUTTON_POSITIVE) {
            Toast.makeText( getApplicationContext(), "loging out", Toast.LENGTH_SHORT ).show();
            dialogInterface.cancel();
            FirebaseAuth auth = FirebaseAuth.getInstance();
            auth.signOut();
            finish();
        }
        if (which == DialogInterface.BUTTON_NEGATIVE) {
            Toast.makeText( getApplicationContext(), "loging out canceled", Toast.LENGTH_SHORT ).show();
            dialogInterface.cancel();
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng( -34, 151 );
//        mMap.addMarker( new MarkerOptions().position( sydney ).title( "Marker in Sydney" ) );
//        mMap.moveCamera( CameraUpdateFactory.newLatLng( sydney ) );

//        for (int i = 0; i < myLocAdapter.getCount(); i++) {
//            MyLoc item = myLocAdapter.getItem( i );
//            LatLng loc = new LatLng( item.getLat(), item.getLang() );
//            mMap.addMarker( new MarkerOptions().position( loc ).title( item.getTitle() ) );
//            if(i==0)
//              mMap.moveCamera( CameraUpdateFactory.newLatLng( loc ) );
//        }
        if (ActivityCompat.checkSelfPermission( this, Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission( this, Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled( true );
        // Check if we were successful in obtaining the map.
        if (mMap != null) {
            mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
                @Override
                public void onMyLocationChange(Location arg0) {
                    mMap.addMarker(new MarkerOptions().position(new LatLng(arg0.getLatitude(), arg0.getLongitude())).title("It's Me!"));
                }
            });
        }
    }

//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.mian_menu,menu);
//        return true;
//    }
//
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (item.getItemId() == R.id.itmsettings) {
//            Intent i = new Intent( getApplicationContext(), SettingsActivity.class );
//            startActivity( i );
//        }
//        if (item.getItemId() == R.id.itmLocation) {
//            Intent i = new Intent( getApplicationContext(), MyLocations.class );
//            startActivity( i );
//        }
//        if (item.getItemId() == R.id.itmMap) {
//            Intent i = new Intent( getApplicationContext(), MainActivity.class );
//            startActivity( i );
//        }
//        return false;
//    }

}





