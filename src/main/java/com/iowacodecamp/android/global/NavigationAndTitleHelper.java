package com.iowacodecamp.android.global;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NavigationAndTitleHelper {
    private Activity activity;

    public NavigationAndTitleHelper(Activity activity) {
        this.activity = activity;
    }

    public void setNavigationAndTitle() {
        ViewGroup decorView = (ViewGroup) this.activity.getWindow().getDecorView();
        LinearLayout root = (LinearLayout) decorView.getChildAt(0);

        try {
            FrameLayout titleContainer = (FrameLayout) root.getChildAt(0);
            TextView title = (TextView) titleContainer.getChildAt(0);
            title.setTextSize(20);
            title.setGravity(Gravity.CENTER);
            titleContainer.setBackgroundColor(Color.parseColor("#FF9000"));
            title.setBackgroundColor(Color.parseColor("#FF9000"));
        } catch (Exception ex) {
            //for now it seems tablets blow up here ... need to play with one to figure out why
        }
    }
}
