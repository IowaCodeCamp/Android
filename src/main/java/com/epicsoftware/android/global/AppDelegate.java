package com.epicsoftware.android.global;

import android.app.Application;
import com.epicsoftware.entity.Session;

public class AppDelegate extends Application {
    private Session selectedSession;

    public Session getSelectedSession() {
        return selectedSession;
    }

    public void setSelectedSession(Session selectedSession) {
        this.selectedSession = selectedSession;
    }
}
