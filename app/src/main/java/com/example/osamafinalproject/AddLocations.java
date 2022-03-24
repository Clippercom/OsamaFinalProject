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
        imgAddtsak = findViewById( R.id.imgAddtsak );
        btnAddtask = findViewById( R.id.btnAddtask );

        btnAddtask.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                validateFeilds();
            }
        } );

    }
    private void validateFeilds()
    {
        boolean isOk=true;
        String title=etTitleAddtask.getText().toString();
        String subject=etSubjectAddtask.getText().toString();
        int Progress=sbrAddtask.getProgress();
        String s=sprAddtask.getSelectedItem().toString();
        if (title.length()==0)
        {
            etTitleAddtask.setError( "must inter Title" );
            isOk=false;
        }
        if (isOk)
        {
            MyTask myTask=new MyTask();
            myTask.setTitle( title );
            myTask.setSubject( subject );

        }
    }
}