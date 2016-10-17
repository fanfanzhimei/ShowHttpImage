package com.zhi.showhttpimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/10/17.
 */
public class HttpUtils {

    public static Bitmap getUrl(String address) throws IOException {
        URL url = new URL(address);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();
        InputStream is = connection.getInputStream();
        return BitmapFactory.decodeStream(is);
    }
}
