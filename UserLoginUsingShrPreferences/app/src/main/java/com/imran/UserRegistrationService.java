package com.imran;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.HashMap;

public class UserRegistrationService {

    // Shared Preferences reference
    SharedPreferences userPref;
    // Edi5tor ref for Shared Preferences
    Editor editor;
    Context context;
    int PRIVATE_MODE = 0;

    // shared file name
    private static final String REG_USER_DATA_FILE_NAME="registeredUserDataFile";

    // user email (make variable public to access from outside)
    public static final String USER_NAME_KEY = "name";
    public static final String USER_EMAIL_KEY = "email";
    public static final String USER_PASSWORD_KEY = "password";

    //constructor
    public UserRegistrationService(Context context){
        this.context = context;
        userPref = context.getSharedPreferences(REG_USER_DATA_FILE_NAME, PRIVATE_MODE);
        editor = userPref.edit();
    }

    // Get Stored Session User data
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> userData = new HashMap<>();
        userData.put(USER_NAME_KEY, userPref.getString(USER_NAME_KEY, null));
        userData.put(USER_EMAIL_KEY, userPref.getString(USER_EMAIL_KEY, null));
        userData.put(USER_PASSWORD_KEY, userPref.getString(USER_PASSWORD_KEY, null));
        return userData;
    }

    // Create login session
    public void createUser(String name, String email, String pass){
        // Storing email and pass in Pref
        editor.putString(USER_NAME_KEY, name);
        editor.putString(USER_EMAIL_KEY, email);
        editor.putString(USER_PASSWORD_KEY, pass);
        // commit change
        editor.commit();
    }

    // Clearing all user data from Shared Preferences registeredUserDataFile
    public void clearAllData(){
        editor.clear();
        editor.commit();
    }
}
