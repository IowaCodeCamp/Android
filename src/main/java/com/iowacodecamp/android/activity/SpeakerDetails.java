package com.iowacodecamp.android.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;
import com.iowacodecamp.android.R;
import com.iowacodecamp.android.async.MovieImgLoadingTask;
import com.iowacodecamp.android.global.AppDelegate;
import com.iowacodecamp.android.global.NavigationAndTitleHelper;
import com.iowacodecamp.entity.Speaker;

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
