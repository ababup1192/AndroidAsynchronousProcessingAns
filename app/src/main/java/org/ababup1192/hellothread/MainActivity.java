package org.ababup1192.hellothread;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {
    private TextView helloText;
    private Handler handler;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helloText = (TextView) findViewById(R.id.text_hello);
        handler = new Handler();

        // 別スレッドで時間の掛かる計算をする。そして、その得られた結果をTextViewに反映する。
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 時間が掛かる計算
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < 1000; i++) {
                    count += i;
                }
                // Handlerを使い、得られた結果をUIスレッドへ反映する。
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        helloText.setText(String.valueOf(count));
                    }
                });
            }
        }).start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
