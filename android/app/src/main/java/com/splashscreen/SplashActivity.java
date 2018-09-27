package com.splashscreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

public class SplashActivity extends AppCompatActivity{

    private ProgressBar mProgressBar;
    private ProgressBar mProgressBarShow;
    private int mProgressStatus = 0;
    private Handler mHandler = new Handler();


    @Override
    protected void onCreate(Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);
        startService(new Intent(getBaseContext(), MyService.class));

        final Intent intent = new Intent(this, MainActivity.class);

        final SharedPreferences settings = getSharedPreferences("prefs", 0);

        boolean firstRun = settings.getBoolean("firstRun", true);
        if (firstRun) {
            setContentView(R.layout.launch_screen);

            mProgressBar = (ProgressBar) findViewById(R.id.progressbar);
            mProgressBarShow = (ProgressBar) findViewById(R.id.progressbarshow);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    mProgressBarShow.setVisibility(View.INVISIBLE);
                    mProgressBar.setVisibility(View.VISIBLE);
                    while (mProgressStatus < 100){
                        mProgressStatus++;
                        android.os.SystemClock.sleep(50);
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                mProgressBar.setProgress(mProgressStatus);
                            }
                        });
                    }
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putBoolean("firstRun", false);
                    editor.commit();
                    startActivity(intent);
                    finish();
                }
            }).start();
        } else {
            startActivity(intent);
            finish();
        }

    }

}
