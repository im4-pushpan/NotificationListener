package com.example.notificationlistener;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;
    private Button buttonRequestAccess;

    private Boolean isLoggedIn = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsername= findViewById(R.id.editText_username);
        editTextPassword= findViewById(R.id.editText_password);
        buttonLogin= findViewById(R.id.button_login);
        buttonRequestAccess= findViewById(R.id.button_request_access);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleLogin();
            }
        });
        buttonRequestAccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent[] intent = Collections.singletonList(new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS)).toArray(new Intent[0]);
                startActivities(intent);
            }
        });
        updateUIBasedOnLoginState();
    }

    private void updateUIBasedOnLoginState() {

        if(isLoggedIn){
            editTextUsername.setVisibility(View.GONE);
            editTextPassword.setVisibility(View.GONE);
            buttonLogin.setVisibility(View.GONE);
            buttonRequestAccess.setVisibility(View.VISIBLE);
        }else{
            editTextUsername.setVisibility(View.VISIBLE);
            editTextPassword.setVisibility(View.VISIBLE);
            buttonLogin.setVisibility(View.VISIBLE);
            buttonRequestAccess.setVisibility(View.GONE);
        }

    }

    private void handleLogin() {
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        if(username.equals("admin") && password.equals("password")){
            isLoggedIn=true;
            Toast.makeText(this,"login successful",Toast.LENGTH_SHORT).show();
            updateUIBasedOnLoginState();
        }
    }
}