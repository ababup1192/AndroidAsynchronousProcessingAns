package org.ababup1192.hellothread;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;

// Workerの結果型であるStringをジェネリクスに指定。
public class MainActivity extends FragmentActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<String> {
    private TextView helloText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helloText = (TextView) findViewById(R.id.text_hello);
        // ボタンのClickListenerに自身(MainActivity)を登録。
        findViewById(R.id.btn_start).setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        // ボタンがクリックされたらLoaderManagerがLoaderを初期化する。
        if (v.getId() == R.id.btn_start) {
            helloText.setText("取得中です...");
            // 以前のTaskを削除する。(何度でも計算できるようにするため)
            getSupportLoaderManager().destroyLoader(0);
            getSupportLoaderManager().initLoader(0, null, this);
        }
    }

    // Workerをインスタンス化し、タスクを始める(Load)。
    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        Loader<String> loader = null;
        try {
            // 日付取得APIにアクセスするタスクを開始。
            loader = new HttpWorker(this, "http://date.jsontest.com");
            loader.forceLoad();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return loader;
    }

    // Workerが計算結果を通知、それを反映。
    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        String result;
        try {
            // Workerが空文字列を返した場合。
            if (data.isEmpty()) {
                result = "日付の取得に失敗しました。";
            } else {
                // JSONをパースして結果を取得。
                JSONObject json = new JSONObject(data);
                result = json.getString("date") + " " + json.getString("time");
            }
        } catch (JSONException e) {
            // JSONのパースに失敗した場合のエラー処理。
            result = "日付の取得に失敗しました";
            e.printStackTrace();
        }
        helloText.setText(result);
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }
}

