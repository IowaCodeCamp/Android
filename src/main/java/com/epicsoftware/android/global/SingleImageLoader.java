package com.epicsoftware.android.global;

import android.graphics.drawable.Drawable;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

public class SingleImageLoader {
    DefaultHttpClient httpClient;

    public SingleImageLoader() {
        httpClient = new DefaultHttpClient();
    }

    public Drawable getImageFromUrl(String url) {
        try {
            InputStream is = fetch(url);
            Drawable drawable = Drawable.createFromStream(is, "src");

            this.httpClient.getConnectionManager().shutdown();

            return drawable;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private InputStream fetch(String urlString) throws MalformedURLException, IOException {
        HttpGet request = new HttpGet("http://iowacodecamp.com/public/images/speakers/" + urlString);
        HttpResponse response = httpClient.execute(request);

        return response.getEntity().getContent();
    }
}
