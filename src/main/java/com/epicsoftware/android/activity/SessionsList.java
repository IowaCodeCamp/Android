package com.epicsoftware.android.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.epicsoftware.android.async.GetSessionsAsyncTask;

public class SessionsList extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setNavigationAndTitle();
    }

    @Override
    protected void onStart() {
        super.onStart();
        GetSessionsAsyncTask task = new GetSessionsAsyncTask(SessionsList.this, getApplicationContext());
        task.execute(new String[]{""});
    }

    private void setNavigationAndTitle() {
        setTitle("Iowa Code Camp");
        ViewGroup decorView = (ViewGroup) this.getWindow().getDecorView();
        LinearLayout root = (LinearLayout) decorView.getChildAt(0);
        FrameLayout titleContainer = (FrameLayout) root.getChildAt(0);
        TextView title = (TextView) titleContainer.getChildAt(0);
        title.setTextSize(20);
        title.setGravity(Gravity.CENTER);
        titleContainer.setBackgroundColor(Color.parseColor("#FF9000"));
        title.setBackgroundColor(Color.parseColor("#FF9000"));
    }
}


