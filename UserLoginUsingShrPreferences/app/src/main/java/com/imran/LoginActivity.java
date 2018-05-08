package com.imran;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class LoginActivity extends Activity {
    // User Session Manager Class
    UserSessionManager sessionManager;
    UserRegistrationService userRegistrationService;
    Button btnLogin, btnGoToReg;
    EditText emailText, passwordText;
    String email,password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Session Class instance
        sessionManager = new UserSessionManager(getApplicationContext());
        // UserRegistrationService Class instance
        userRegistrationService = new UserRegistrationService(getApplicationContext());

        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnGoToReg = (Button)findViewById(R.id.btnGoToReg);
        emailText   = (EditText)findViewById(R.id.email);
        passwordText   = (EditText)findViewById(R.id.password);

        Toast.makeText(getApplicationContext(), "User Login Status: "+sessionManager.isUserLoggedIn(),Toast.LENGTH_SHORT).show();





        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                 email = emailText.getText().toString();
                 password = passwordText.getText().toString();
                    if(email.trim().equals("") || password.trim().equals("")){
                        Toast.makeText(getApplicationContext(), "user name or password empty", Toast.LENGTH_SHORT).show();
                        return;
                    }
               // Toast.makeText(getApplicationContext(), "User Name: "+email+"\nPassword: "+password, Toast.LENGTH_SHORT).show();
                HashMap<String, String> user = userRegistrationService.getUserDetails();
                String userEmail = user.get(UserRegistrationService.USER_EMAIL_KEY);
                String userPassword = user.get(UserRegistrationService.USER_PASSWORD_KEY);
                if(email.equals(userEmail) && password.equals(userPassword)){
                     sessionManager.createUserLoginSession(email, password);
                     // go to Welcome activity
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();

                }else{
                    Toast.makeText(getApplicationContext(), "Invalid Email or Password", Toast.LENGTH_SHORT).show();
                }

            }
        });


        btnGoToReg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // go to Registration activity
                Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });



    }
}
