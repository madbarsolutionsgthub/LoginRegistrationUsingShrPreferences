package com.imran;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class RegistrationActivity extends Activity {
    // User Session Manager Class
    UserSessionManager sessionManager;
    UserRegistrationService userRegistrationService;
    Button btnBackToLogin, btnSignUp, btnTestUserData, btnResetUserData;
    EditText nameText, emailText, passwordText;
    String name,email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // UserRegistrationService Class instance
        userRegistrationService = new UserRegistrationService(getApplicationContext());

        btnTestUserData = (Button)findViewById(R.id.btnTestUserData);
        btnResetUserData = (Button)findViewById(R.id.btnResetUserData);
        btnBackToLogin = (Button)findViewById(R.id.btnBackToLogin);
        btnSignUp = (Button)findViewById(R.id.btnSignUp);

        nameText   = (EditText)findViewById(R.id.name);
        emailText   = (EditText)findViewById(R.id.email);
        passwordText   = (EditText)findViewById(R.id.password);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // go to User Registration activity

                name = nameText.getText().toString();
                email = emailText.getText().toString();
                password = passwordText.getText().toString();
                if(email.trim().equals("") || password.trim().equals("") || name.trim().equals("")){
                    Toast.makeText(getApplicationContext(), "User Email or password Must have a value", Toast.LENGTH_SHORT).show();
                    return;
                }
                userRegistrationService.createUser(name, email, password);
                Toast.makeText(getApplicationContext(), "Successfully user added."+password, Toast.LENGTH_SHORT).show();

            }
        });

        // Test User Data
        btnTestUserData.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //  userRegistrationService.createUser("MD IMRAN", "babu@gmail.com", "123");
               // get user data from shared preference
                HashMap<String, String> user = userRegistrationService.getUserDetails();
                String name = user.get(UserRegistrationService.USER_NAME_KEY);
                if(name == null){
                    Toast.makeText(getApplicationContext(), "User list is empty.", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(getApplicationContext(), "Current user name: "+name, Toast.LENGTH_SHORT).show();
             //   Toast.makeText(getApplicationContext(), "Current Data Email: "+email+"\nPassword: "+password, Toast.LENGTH_SHORT).show();

            }
        });

        // Reset User Data
        btnResetUserData.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                userRegistrationService.clearAllData();
                Toast.makeText(getApplicationContext(),"Successfully clean all user log", Toast.LENGTH_SHORT).show();
            }
        });

        // go to Login activity
        btnBackToLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}
