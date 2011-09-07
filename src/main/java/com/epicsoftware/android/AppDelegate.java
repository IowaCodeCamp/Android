package com.epicsoftware.android;

import android.app.Application;

public class AppDelegate extends Application {
    private Session selectedSession;

    public Session getSelectedSession() {
        return selectedSession;
    }

    public void setSelectedSession(Session selectedSession) {
        this.selectedSession = selectedSession;
    }
}
