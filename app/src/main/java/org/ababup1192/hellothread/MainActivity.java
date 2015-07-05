package org.ababup1192.hellothread;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import org.ababup1192.hellothread.github.GitHubService;
import org.ababup1192.hellothread.github.Repository;

import java.util.List;

import retrofit.RestAdapter;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private EditText usernameEdit;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEdit = (EditText) findViewById(R.id.edit_username);
        listView = (ListView) findViewById(R.id.list_view);
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
        if (v.getId() == R.id.btn_start) {
            String userName = usernameEdit.getText().toString();
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint("https://api.github.com")
                    .build();
            restAdapter.create(GitHubService.class).listRepos(userName)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<List<Repository>>() {
                        @Override
                        public void onCompleted() {
                            Log.d("MainActivity", "complete");
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d("MainActivity", "error");
                            e.printStackTrace();
                        }

                        @Override
                        public void onNext(List<Repository> repositories) {
                            if (repositories != null) {
                                render(repositories);
                            }
                        }
                    });
        }
    }

    public void render(List<Repository> repositories) {
        RepositoryAdapter adapter =
                new RepositoryAdapter(MainActivity.this, repositories);
        listView.setAdapter(adapter);
    }
}

