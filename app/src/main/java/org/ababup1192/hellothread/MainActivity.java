package org.ababup1192.hellothread;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

// Workerの結果型であるIntegerをジェネリクスに指定。
public class MainActivity extends FragmentActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<Integer> {
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
            helloText.setText("計算中です...");
            getSupportLoaderManager().initLoader(0, null, this);
        }
    }

    // Workerをインスタンス化し、タスクを始める(Load)。
    @Override
    public Loader<Integer> onCreateLoader(int id, Bundle args) {
        Loader<Integer> loader = new FactorialWorker(this, 10);
        loader.forceLoad();
        return loader;
    }

    // Workerが計算結果を通知、それを反映。
    @Override
    public void onLoadFinished(Loader<Integer> loader, Integer data) {
        helloText.setText(data.toString());
    }

    @Override
    public void onLoaderReset(Loader<Integer> loader) {

    }
}

