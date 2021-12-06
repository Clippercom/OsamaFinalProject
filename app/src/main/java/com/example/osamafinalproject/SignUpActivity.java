package com.example.osamafinalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    private TextInputEditText UpEmail,UpPassword,UpRePassword,UpName,UpPhone;
    private Button btnUpsave;
    private ImageView ivSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        UpEmail=findViewById(R.id.UpEmail);UpPassword=findViewById(R.id.UpPassword);UpRePassword=findViewById(R.id.UpRePassword);UpName=findViewById(R.id.UpName);UpPhone=findViewById(R.id.UpPhone);btnUpsave=findViewById(R.id.btnUpsave);ivSignUp=findViewById(R.id.ivSignUp);

        btnUpsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });
    }
    private void validate(){
        String email = UpEmail.getText().toString();
        String Password = UpPassword.getText().toString();
        String RePassword = UpRePassword.getText().toString();
        String name = UpName.getText().toString();
        String Phone = UpPhone.getText().toString();
        //String btn = btnUpsave.getText().toString();

        boolean isOK=true;
        if (email.length()<5 || email.indexOf('@')<=0)
        {
            UpEmail.setError("wrong email syntex");
            isOK=false;
        }
        if (Phone.length()<10|| Phone.length()>10)
        {
            UpPhone.setError( "wrong phone number" );
            isOK=false;
        }
        if(Password.length()<8)
        {
            UpPassword.setError("at lest 8 chars");
            isOK=false;
        }
        if(Password.equals(RePassword)==false)
        {
            UpRePassword.setError("not equal passwords");
            isOK=false;
        }
        if(name.length()==0)
        {
            UpName.setError("must to enter full name");
            isOK=false;
        }
        if(isOK)
        {
            createAccount(email,Password);
        }
    }
    private void createAccount(String email,String Password)
    {
        FirebaseAuth auth=FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword( email, Password ).addOnCompleteListener( this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task. isSuccessful())
                {
                    finish();
                    startActivity( new Intent(getApplicationContext(),MainActivity.class));
                }
                else
                {
                    Toast.makeText( getApplicationContext(),"error create Acount"+task.getException().getMessage(),Toast.LENGTH_LONG ).show();
                }
            }
        });
    }
}