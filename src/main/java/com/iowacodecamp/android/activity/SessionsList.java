package com.iowacodecamp.android.activity;

import android.app.Activity;
import android.os.Bundle;
import com.iowacodecamp.android.async.GetSessionsAsyncTask;
import com.iowacodecamp.android.global.NavigationAndTitleHelper;

public class SessionsList extends Activity {
    private NavigationAndTitleHelper navigationAndTitleHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Iowa Code Camp");
        navigationAndTitleHelper = new NavigationAndTitleHelper(this);
        navigationAndTitleHelper.setNavigationAndTitle();

        GetSessionsAsyncTask task = new GetSessionsAsyncTask(SessionsList.this, getApplicationContext());
        task.execute(new String[]{""});
    }
}


