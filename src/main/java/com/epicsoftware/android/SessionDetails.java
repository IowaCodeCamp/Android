package com.epicsoftware.android;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class SessionDetails extends Activity {

    private static String TAG = "IowaCodeCamp";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        setContentView(R.layout.sessiondetails);

        Session selectedSession = ((AppDelegate) getApplicationContext()).getSelectedSession();

        System.out.println("selectedSession.getSession() = " + selectedSession.getSession());
    }
}
