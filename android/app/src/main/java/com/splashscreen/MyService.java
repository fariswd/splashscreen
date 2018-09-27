package com.splashscreen;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class MyService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onTaskRemoved(Intent rootIntent) {
        final SharedPreferences settings = getSharedPreferences("prefs", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("firstRun", true);
        editor.commit();
        stopSelf();
    }
}

