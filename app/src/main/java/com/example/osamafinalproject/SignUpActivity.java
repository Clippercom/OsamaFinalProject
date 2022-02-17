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
        UpEmail=findViewById(R.id.UpEmail);
        UpPassword=findViewById(R.id.UpPassword);
        UpRePassword=findViewById(R.id.UpRePassword);
        UpName=findViewById(R.id.UpName);
        UpPhone=findViewById(R.id.UpPhone);
        btnUpsave=findViewById(R.id.btnUpsave);
        ivSignUp=findViewById(R.id.ivSignUp);

        btnUpsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });
    }
    private void validate(){
        String email = UpEmail.getText().toString();
        String pass1 = UpPassword.getText().toString();
        String pass2 = UpRePassword.getText().toString();
        String name = UpName.getText().toString();
        String Phone = UpPhone.getText().toString();
        boolean isOK=true;

        //1.يقوم بفحص ازا كان الايمال اطول من 5او يتضمن اشارة"@" ليختبر ازا كان صالح ام لا
        if (email.length()<5 || email.indexOf('@')<=0)
        {
            UpEmail.setError("wrong email syntex");
            isOK=false;
        }
        //2.يتم بفحص ازا كان رقم الجوال 10 ارقام فانه ازا كان اكثر او اقل لا يقبل
        if (Phone.length()<10|| Phone.length()>10)
        {
            UpPhone.setError( "wrong phone number" );
            isOK=false;
        }
        //3.يفحص ازا كانت كلمة المرور اكثر من 8 ارقام لانه لا يسمح باقل من 8 ارقام
        if(pass1.length()<8)
        {
            UpPassword.setError("at lest 8 chars");
            isOK=false;
        }
        //4.يتم بمقارنة كلمة المرور مع اعادة كلمة المرور للتأكيد
        if(pass1.equals(pass2)==false)
        {
            UpRePassword.setError("not equal passwords");
            isOK=false;
        }
        //5.يتم بفحص ان خانة الاسم ليست فارغة
        if(name.length()==0)
        {
            UpName.setError("must to enter full name");
            isOK=false;
        }
        //6.ازا كانت جميع الخانات مقبولة فأنه يتم ببناء حساب جديد
        if(isOK)
        {
            createAccount(email,pass1);
        }
    }
    private void createAccount(String email,String pass1)
    {
        FirebaseAuth auth=FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword( email, pass1 ).addOnCompleteListener( this, new OnCompleteListener<AuthResult>() {
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