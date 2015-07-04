package org.ababup1192.hellothread;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpWorker extends AsyncTaskLoader<String> {
    private URL url;

    public HttpWorker(Context context, URL url) {
        super(context);
        this.url = url;
    }

    public HttpWorker(Context context, String url) throws MalformedURLException {
        this(context, new URL(url));
    }

    @Override
    public String loadInBackground() {
        String result = "";
        HttpURLConnection connection = null;
        try {
            // HTTP通信の設定。
            connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("GET");
            // HTTP通信の開始。
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            // 本文の取得。
            result = getBody(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 通信の切断。
            if (connection != null) {
                connection.disconnect();
            }
        }
        return result;
    }

    public String getBody(InputStream inputStream) {
        String result = "";
        // データを読める形に変換。
        // バイナリデータ -> テキストデータ -> 効率のよいテキストデータ読み込み
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        try {
            // 一行ずつ読み込み、データが終わるまで、resultに文字列を足していく。
            while ((line = reader.readLine()) != null) {
                result += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // ストリームを閉じる。
                inputStream.close();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
