package com.example.mymarket;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mymarket.model.User;

public class SharedPreferencesManager {
    private static final String PREF_NAME = "MyMarketPrefs";
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_USER_NAME = "user_name";
    private static final String KEY_USER_EMAIL = "user_email";
    private static final String KEY_USER_ADMIN = "user_admin";

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static void saveUserData(Context context, User user) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putInt(KEY_USER_ID, user.getId()); // Assuming User has getId() method
        editor.putString(KEY_USER_NAME, user.getNome());
        editor.putString(KEY_USER_EMAIL, user.getEmail());
        editor.putBoolean(KEY_USER_ADMIN, user.getAdmin());
        editor.apply();
    }

    public static User getUserData(Context context) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        int userId = sharedPreferences.getInt(KEY_USER_ID, -1); // -1 is a default value
        String userName = sharedPreferences.getString(KEY_USER_NAME, "");
        String userEmail = sharedPreferences.getString(KEY_USER_EMAIL, "");
        boolean userAdmin = sharedPreferences.getBoolean(KEY_USER_ADMIN, false); // false is a default value

        User user = new User();
        user.setId(userId);
        user.setNome(userName);
        user.setEmail(userEmail);
        user.setAdmin(userAdmin);

        return user;
    }

    public static void clearUserData(Context context) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.clear();
        editor.apply();
    }
}

