package com.splashscreen;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.facebook.react.ReactActivity;

import org.devio.rn.splashscreen.SplashScreen;

public class MainActivity extends ReactActivity {

    @Override
    protected void onCreate(Bundle SavedInstanceState) {
        SplashScreen.show(this);
        super.onCreate(SavedInstanceState);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        final SharedPreferences settings = getSharedPreferences("prefs", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("firstRun", true);
        editor.commit();
    }

    /**
     * Returns the name of the main component registered from JavaScript.
     * This is used to schedule rendering of the component.
     */
    @Override
    protected String getMainComponentName() {
        return "splashscreen";
    }
}
