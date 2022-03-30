package com.example.osamafinalproject;

import MyData.MyLoc;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddLocations extends AppCompatActivity {
    private TextInputEditText etSubjectAddlOC, etTitleAddLOC;
    private TextView tvGPS;
    private ImageView imgAddLOC;
    private Button btnAddLOC;
    private ImageButton imgLoc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_add_locations );

        tvGPS  = findViewById( R.id.tvGPS );
        imgLoc = findViewById( R.id.imgLoc );
        etSubjectAddlOC = findViewById( R.id.etSubjectAddLOC );
        etTitleAddLOC = findViewById( R.id.etTitleAddLOC );
        imgAddLOC = findViewById( R.id.imgAddLOC );
        btnAddLOC = findViewById( R.id.btnAddLOC );

        btnAddLOC.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateFeilds();
            }
        } );

    }

    private void validateFeilds() {
        boolean isOk = true;
        String title = etTitleAddLOC.getText().toString();
        String subject = etSubjectAddlOC.getText().toString();
        if (title.length() == 0) {
            etTitleAddLOC.setError( "must inter Title" );
            isOk = false;
        }
        if (isOk) {
            MyLoc myLoc = new MyLoc();
            myLoc.setTitle( title );
            myLoc.setSubject( subject );

            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            myLoc.setOwner( uid );

            FirebaseDatabase db = FirebaseDatabase.getInstance();
            DatabaseReference ref = db.getReference();

            String key = ref.child( "mylocs" ).push().getKey();
            myLoc.setKey( key );

            ref.child( "mylocs" ).child( key ).setValue( myLoc ).addOnCompleteListener( new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {//response
                    if (task.isSuccessful()) {
                        Toast.makeText( getApplicationContext(), "Add Successful", Toast.LENGTH_SHORT ).show();
                        finish();
                    } else {
                        Toast.makeText( getApplicationContext(), "Add Not Successful", Toast.LENGTH_SHORT ).show();
                    }
                }

            } );
        }
    }
}