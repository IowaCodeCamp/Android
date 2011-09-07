package com.epicsoftware.android.activity;

import android.app.Activity;
import android.os.Bundle;
import com.epicsoftware.android.R;
import com.epicsoftware.android.global.AppDelegate;
import com.epicsoftware.entity.Session;

public class SessionDetails extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sessiondetails);

        Session selectedSession = ((AppDelegate) getApplicationContext()).getSelectedSession();

        System.out.println("selected session was... " + selectedSession.getSession());
    }
}
