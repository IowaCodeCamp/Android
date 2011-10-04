package com.iowacodecamp.android.global;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class DialogHelper {
        public static void showDialogWithMessageAndTitleThatWillFinish(String title, String message, final Context activity) {
        AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                ((Activity)activity).finish();
            }
        });
        alertDialog.show();
    }
}
