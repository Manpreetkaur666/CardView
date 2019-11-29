package com.assignment.cardview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    TextView _register;
    TextView _forgot;
    Button _login;
    FirebaseAuth LoginAuth;

    EditText editText_Email;
    EditText editText_Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );
        LoginAuth=FirebaseAuth.getInstance();
        editText_Email=findViewById(R.id.editText_email);
        editText_Password=findViewById(R.id.editText_password);

        _register=findViewById(R.id.txtRegister);
        _register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login.this,MainActivity.class);
                startActivity(intent);

            }
        });

        _forgot=findViewById(R.id.txtforgot);
        _forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Login.this,MainActivity.class);
                startActivity(intent);

            }
        });

        _login=findViewById(R.id.btn_login);
        _login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });


    }

    private void userLogin()
    {
        String email = editText_Email.getText().toString().trim();
        String password = editText_Password.getText().toString().trim();

        //checking if email and passwords are empty
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
            return;
        }

        LoginAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful())
                {
                    Intent i = new Intent(Login.this, MainActivity.class);
                    //clear all the activities on the top of the stack and open up the new activity
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);

                }
                else
                {
                    //Toast.makeText(getApplicationContext(),task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(Login.this, "login unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
