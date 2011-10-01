package com.epicsoftware.android.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import com.epicsoftware.android.R;
import com.epicsoftware.android.async.MovieImgLoadingTask;
import com.epicsoftware.android.global.AppDelegate;
import com.epicsoftware.android.global.NavigationAndTitleHelper;
import com.epicsoftware.entity.Session;

public class SpeakerDetails extends Activity {
    private NavigationAndTitleHelper navigationAndTitleHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speakerdetails);

        setTitle("Speaker Details");
        navigationAndTitleHelper = new NavigationAndTitleHelper(this);
        navigationAndTitleHelper.setNavigationAndTitle();

        Session selectedSession = ((AppDelegate) getApplicationContext()).getSelectedSession();

        ImageView vw = (ImageView) findViewById(R.id.widget32);
        MovieImgLoadingTask imgTask = new MovieImgLoadingTask(vw);
        imgTask.execute(new String[]{selectedSession.getSpeaker().getImg()});
    }
}
