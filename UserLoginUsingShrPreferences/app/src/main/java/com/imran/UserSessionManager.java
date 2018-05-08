package com.imran;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences;
import android.widget.EditText;

import java.util.HashMap;

public class UserSessionManager {

    // Shared Preferences reference
    SharedPreferences pref;
    // Edi5tor ref for Shared Preferences
    Editor editor;
    Context context;
    int PRIVATE_MODE = 0;

    // shared file name
    private static final String PREFER_FILE_NAME="userLoginInfoFile";

    //Shared pref keys
    private static final String IS_USER_LOGIN = "isUserLogin";

    // user email (make variable public to access from outside)
    public static final String EMAIL_KEY = "email";
    public static final String PASSWORD_KEY = "password";

    //constructor
    public UserSessionManager(Context context){
        this.context = context;
        pref = context.getSharedPreferences(PREFER_FILE_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    // Get Stored Session User data
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> userData = new HashMap<>();
        userData.put(EMAIL_KEY, pref.getString(EMAIL_KEY, null));
        userData.put(PASSWORD_KEY, pref.getString(PASSWORD_KEY, null));
        return userData;
    }

    // Create login session
    public void createUserLoginSession(String email, String pass){
        // Storing login value as true
        editor.putBoolean(IS_USER_LOGIN, true);

        // Storing email and pass in Pref
        editor.putString(EMAIL_KEY, email);
        editor.putString(PASSWORD_KEY, pass);
        // commit change
        editor.commit();
    }

    // Check login status
    public boolean checkLogin(){
        if(!this.isUserLoggedIn()){
            //if user not logged in redirect him to login activity
            Intent intent = new Intent(context, LoginActivity.class);
            // Closing all the Activities from stack
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            // Add new Flag to start new Activity
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            // sTARTING lOGIN Acitvity
            context.startActivity(intent);
            return true;
        }
        return false;
    }

    // Clearing all user data from Shared Preferences
    public void logOutUser(){
        editor.clear();
        editor.commit();
        // After logout redirect user to login page
        Intent intent = new Intent(context, LoginActivity.class);
        // Closing all the Activities from stack
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        // Add new Flag to start new Activity
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // sTARTING lOGIN Acitvity
        context.startActivity(intent);
    }

    public boolean isUserLoggedIn(){
        return pref.getBoolean(IS_USER_LOGIN, false);
    }





}
