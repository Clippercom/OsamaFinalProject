package com.example.osamafinalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {
    private TextInputEditText etEmail,etPassword;
    private Button BtnSignIn,BtnSignUp;
    private ImageView ivSignIn;
    private TextView tvSignIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        etEmail=findViewById(R.id.etEmail);etPassword=findViewById(R.id.etPassword);
        BtnSignIn=findViewById(R.id.BtnSignIn);BtnSignUp=findViewById(R.id.BtnSignUp);ivSignIn=findViewById(R.id.ivSignIn);tvSignIn=findViewById(R.id.tvSignIn);

        BtnSignUp.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(getApplicationContext(),SignUpActivity.class ));
            }
        } );

        BtnSignIn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }


            private void validate() {
                boolean isOk=true;
                String email=etEmail.getText().toString();
                String password=etPassword.getText().toString();
                if(email.length()==0)
                {
                    etEmail.setError( "enter email" );
                    isOk=false;
                }
                if(password.length()<8)
                {
                    etPassword.setError( "password at least 8 letters" );
                    isOk=false;
                }
                if(isOk)
                {
                    signiningIn(email,password);
                }
            }

            private void signiningIn(String email, String password)
            {
                FirebaseAuth auth=FirebaseAuth.getInstance();
                auth.signInWithEmailAndPassword( email, password ).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText( getApplicationContext(), "signing in successful", Toast.LENGTH_SHORT ).show();
                            finish();
                            startActivity( new Intent( getApplicationContext(), MapsActivity.class ) );
                        }
                        else
                            Toast.makeText( getApplicationContext(), "signing in erorr"+task.getException().getMessage(), Toast.LENGTH_SHORT ).show();
                    }
                } );
            }
        } );





    }
}