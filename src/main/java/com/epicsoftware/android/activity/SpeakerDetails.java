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
import com.epicsoftware.entity.Speaker;

public class SpeakerDetails extends Activity {
    private NavigationAndTitleHelper navigationAndTitleHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speakerdetails);

        setTitle("Speaker Details");
        navigationAndTitleHelper = new NavigationAndTitleHelper(this);
        navigationAndTitleHelper.setNavigationAndTitle();

        Speaker selectedSpeaker = ((AppDelegate) getApplicationContext()).getSelectedSession().getSpeaker();

        TextView sessionTime = (TextView) findViewById(R.id.locationInfo);
        sessionTime.setText(selectedSpeaker.getLocation());

        TextView sessionRoom = (TextView) findViewById(R.id.webUrl);
        sessionRoom.setText(selectedSpeaker.getWeb());

        TextView sessionTitle = (TextView) findViewById(R.id.speakerName);
        sessionTitle.setText(selectedSpeaker.getName());

        TextView filterText = (TextView) findViewById(R.id.speakerBio);
        filterText.setText(selectedSpeaker.getBio());
        filterText.setMovementMethod(new ScrollingMovementMethod());

        ImageView vw = (ImageView) findViewById(R.id.speakerImg);
        MovieImgLoadingTask imgTask = new MovieImgLoadingTask(vw);
        imgTask.execute(new String[]{selectedSpeaker.getImg()});
    }
}
