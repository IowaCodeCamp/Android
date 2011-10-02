package com.iowacodecamp.android.async;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.ImageView;
import com.iowacodecamp.android.global.SingleImageLoader;

public class MovieImgLoadingTask extends AsyncTask<String, Void, Drawable> {
    private ImageView imageView;

    public MovieImgLoadingTask(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    protected void onPreExecute() {
        //do nothing
    }

    @Override
    protected Drawable doInBackground(String... url) {
        Drawable img = null;
        SingleImageLoader svc = new SingleImageLoader();
        img = svc.getImageFromUrl(url[0]);
        return img;
    }

    @Override
    protected void onPostExecute(Drawable img) {
        if (img != null) {
            imageView.setImageDrawable(img);
        }
    }
}
