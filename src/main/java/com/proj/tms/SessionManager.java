package com.proj.tms;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class SessionManager {

    Preferences sharedpreferences;

    public SessionManager() {

        sharedpreferences = Preferences.userRoot();

    }

    public void setEmail(String email) {
        sharedpreferences.put("email", email);

    }

    public void setUserID(int ID) {
        sharedpreferences.putInt("UserID", ID);

    }

    public String getEmail() {
        return sharedpreferences.get("email", null);
    }

    public String getUserID() {
        return sharedpreferences.get("UserID", null);
    }

    public void clear() {
        try {
            sharedpreferences.clear();
        } catch (BackingStoreException ex) {
            Logger.getLogger(SessionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
