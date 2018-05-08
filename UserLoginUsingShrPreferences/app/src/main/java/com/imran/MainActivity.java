package com.imran;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends Activity {
    Button btnLogout;
    // User Session Manager Class
    UserSessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Session Class instance
        sessionManager = new UserSessionManager(getApplicationContext());

        btnLogout = (Button) findViewById(R.id.btnLogout);

        Toast.makeText(getApplicationContext(), "User Login Status: "+sessionManager.isUserLoggedIn(),Toast.LENGTH_SHORT).show();

//        btnLogout.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                Intent inten = new Intent(MainActivity.this, LoginActivity.class);
//                inten.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(inten);
//            }
//        });

        // If user not logged in redirect and finish current activity stack
        if(sessionManager.checkLogin()) {
            finish();
        }

        // get user data from session
        HashMap<String, String> user = sessionManager.getUserDetails();
        String email = user.get(UserSessionManager.EMAIL_KEY);
        String password = user.get(UserSessionManager.PASSWORD_KEY);

        btnLogout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
             // Clear the user session data and redirect to login page
                sessionManager.logOutUser();
            }
        });


    }
}
