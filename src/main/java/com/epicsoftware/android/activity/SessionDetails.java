package com.epicsoftware.android.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;
import com.epicsoftware.android.R;
import com.epicsoftware.android.async.MovieImgLoadingTask;
import com.epicsoftware.android.global.AppDelegate;
import com.epicsoftware.android.global.NavigationAndTitleHelper;
import com.epicsoftware.entity.Session;

public class SessionDetails extends Activity {
    private NavigationAndTitleHelper navigationAndTitleHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sessiondetails);

        setTitle("Session Details");
        navigationAndTitleHelper = new NavigationAndTitleHelper(this);
        navigationAndTitleHelper.setNavigationAndTitle();

        Session selectedSession = ((AppDelegate) getApplicationContext()).getSelectedSession();

        //Button viewSpeakerDetails = (Button) findViewById(R.id.speakerDetails);
        //viewSpeakerDetails.getBackground().setColorFilter(new LightingColorFilter(Color.BLUE, Color.BLUE));

        TextView sessionSpeakerName = (TextView) findViewById(R.id.widget34);
        sessionSpeakerName.setText(selectedSession.getSpeaker().getName());

         TextView sessionTime = (TextView) findViewById(R.id.widget36);
        sessionTime.setText(selectedSession.getTime());

         TextView sessionRoom = (TextView) findViewById(R.id.widget38);
        sessionRoom.setText(selectedSession.getRoom());


        TextView sessionTitle = (TextView) findViewById(R.id.widget31);
        sessionTitle.setText(selectedSession.getSession());

        TextView filterText = (TextView) findViewById(R.id.widget39);
        filterText.setText(selectedSession.getDesc());
        filterText.setMovementMethod(new ScrollingMovementMethod());

        ImageView vw = (ImageView) findViewById(R.id.widget32);
        MovieImgLoadingTask imgTask = new MovieImgLoadingTask(vw);
        imgTask.execute(new String[]{selectedSession.getSpeaker().getImg()});
    }
}
