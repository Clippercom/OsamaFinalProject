package com.example.osamafinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class SignInActivity extends AppCompatActivity {
    private TextInputEditText etEmail,etPassword;
    private Button BtnSignIn,BtnSignUp;
    private ImageView ivSignIn;
    private TextView tvSignIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        etEmail=findViewById(R.id.etEmail);etPassword=findViewById(R.id.etPassword);BtnSignIn=findViewById(R.id.BtnSignIn);BtnSignUp=findViewById(R.id.BtnSignUp);ivSignIn=findViewById(R.id.ivSignIn);tvSignIn=findViewById(R.id.tvSignIn);
    }
}