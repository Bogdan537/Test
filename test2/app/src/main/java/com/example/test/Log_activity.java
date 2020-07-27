package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Log_activity extends AppCompatActivity {
   private EditText edLogin, edPassword;
    private FirebaseAuth mAuth;
    private Button bStart, bSignUp, bSignIn, SignOut;
    private TextView tvUserName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_layout);
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser cUser = mAuth.getCurrentUser();
        if(cUser != null)
        {
           showSigned();
            String userName = "Ви ввійшли як: " + cUser.getEmail();
            tvUserName.setText(userName);

        }
        else
        {
           notSigned();

        }
    }

    private void init()
    {
        SignOut = findViewById(R.id.bSignOut);
        tvUserName = findViewById(R.id.tvUserEmail);
        bStart = findViewById(R.id.bStart);
        bSignIn = findViewById(R.id.bSignIn);
        bSignUp = findViewById(R.id.bSignUp);
        edLogin = findViewById(R.id.edLogin);
        edPassword = findViewById(R.id.edPassword);
        mAuth = FirebaseAuth.getInstance();
    }


    public void onClickSignUp(View view)
    {
        if(!TextUtils.isEmpty(edLogin.getText().toString()) &&  !TextUtils.isEmpty(edPassword.getText().toString()))
        {
            mAuth.createUserWithEmailAndPassword(edLogin.getText().toString(),edPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        showSigned();
                        sendEmailVer();
                        Toast.makeText(getApplicationContext(),"Ви успішно зареєструвались", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        notSigned();
                        Toast.makeText(getApplicationContext(),"Уже існує такий користувач", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
        else
        {
            Toast.makeText(getApplicationContext(),"Введіть усі дані ", Toast.LENGTH_SHORT).show();
        }
     }

    public void onClickSignIn(View view) {
        if (!TextUtils.isEmpty(edLogin.getText().toString()) && !TextUtils.isEmpty(edPassword.getText().toString()))
        {
            mAuth.signInWithEmailAndPassword(edLogin.getText().toString(),edPassword.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                 @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        showSigned();
                        Toast.makeText(getApplicationContext(),"User SignIn Succesful", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        notSigned();
                        Toast.makeText(getApplicationContext(),"User SignIn failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }
    }
    public void onClickSignOut(View view)
    {
        FirebaseAuth.getInstance().signOut();
       notSigned();
    }
    private void showSigned()
    {
        FirebaseUser user = mAuth.getCurrentUser();
        if(user.isEmailVerified())
        {
            String userName = "Ви ввійшли як: " + user.getEmail();
            tvUserName.setText(userName);
        bStart.setVisibility(View.VISIBLE);
        tvUserName.setVisibility(View.VISIBLE);
        SignOut.setVisibility(View.VISIBLE);
        edLogin.setVisibility(View.GONE);
        edPassword.setVisibility(View.GONE);
        bSignIn.setVisibility(View.GONE);
        bSignUp.setVisibility(View.GONE);
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Перейдіть на пошту для підтвердження адреси", Toast.LENGTH_SHORT).show();
        }
    }
    private void notSigned()
    {
        bStart.setVisibility(View.GONE);
        tvUserName.setVisibility(View.GONE);
        SignOut.setVisibility(View.GONE);
        edLogin.setVisibility(View.VISIBLE);
        edPassword.setVisibility(View.VISIBLE);
        bSignIn.setVisibility(View.VISIBLE);
        bSignUp.setVisibility(View.VISIBLE);
    }

    public void onClickStart(View view)
    {
        Intent i = new Intent(Log_activity.this, MainActivity.class);
        
        startActivity(i);
    }
    private void sendEmailVer()
    {
        FirebaseUser user = mAuth.getCurrentUser();
        
        assert user != null;

        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(),"Перейдіть на пошту для підтвердження адреси", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Send Email failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
