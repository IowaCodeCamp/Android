package com.epicsoftware.android.activity;

import android.app.Activity;
import com.epicsoftware.android.async.GetSessionsAsyncTask;

public class SessionsList extends Activity {
    @Override
    protected void onStart() {
        super.onStart();
        GetSessionsAsyncTask task = new GetSessionsAsyncTask(SessionsList.this, getApplicationContext());
        task.execute(new String[]{""});
    }
}


