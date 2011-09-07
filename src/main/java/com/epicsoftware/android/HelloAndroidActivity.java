package com.epicsoftware.android;

import android.app.Activity;

public class HelloAndroidActivity extends Activity {
    @Override
    protected void onStart() {
        super.onStart();
        GetSessionsAsyncTask task = new GetSessionsAsyncTask(HelloAndroidActivity.this, getApplicationContext());
        task.execute(new String[]{""});
    }
}


