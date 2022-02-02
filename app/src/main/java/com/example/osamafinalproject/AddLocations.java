package com.example.osamafinalproject;

import MyData.MyTask;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddLocations extends AppCompatActivity {
    private TextInputEditText etSubjectAddtask,etTitleAddtask;
    private TextView tvAddtask;
    private Spinner sprAddtask;
    private SeekBar sbrAddtask;
    private ImageView imgAddtsak;
    private Button btnAddtask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_add_locations );

        etSubjectAddtask = findViewById( R.id.etSubjectAddtask );
        etTitleAddtask = findViewById( R.id.etTitleAddtask );
        tvAddtask = findViewById( R.id.tvAddtask );
        sprAddtask = findViewById( R.id.sprAddtask );
        sbrAddtask = findViewById( R.id.sbrAddtask );
        imgAddtsak = findViewById( R.id.imgAddtsak );
        btnAddtask = findViewById( R.id.btnAddtask );

        btnAddtask.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }

            private void validate() {
            }
        } );

    }
    public void validate()
    {
        String title=etTitleAddtask.getText().toString();
        String subject=etSubjectAddtask.getText().toString();
        int progress=sbrAddtask.getProgress();
        String s=sprAddtask.getSelectedItem().toString();
        boolean isOk=true;
        if (title.length()==0)
        {
            etTitleAddtask.setError( "must inter subject" );
            isOk=false;
        }
        if (isOk)
        {
            MyTask t=new MyTask();

            String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
            MyTask.setOwner(uid);
            FirebaseDatabase db=FirebaseDatabase.getInstance();
            DatabaseReference ref= db.getReference();
            MyTask.setKey();
        }
    }
}