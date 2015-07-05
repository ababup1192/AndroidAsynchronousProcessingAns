package org.ababup1192.hellothread;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import org.ababup1192.hellothread.github.GitHubService;
import org.ababup1192.hellothread.github.Repository;

import java.util.List;

import retrofit.RestAdapter;
import retrofit.RetrofitError;

public class GitHubRepositoryWorker extends AsyncTaskLoader<List<Repository>> {
    String userName;

    public GitHubRepositoryWorker(Context context, String userName) {
        super(context);
        this.userName = userName;
    }

    // 時間の掛かる計算処理
    @Override
    public List<Repository> loadInBackground() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://api.github.com")
                .build();

        GitHubService service = restAdapter.create(GitHubService.class);
        try {
            return service.listRepos(userName);
            // 取得に失敗したとき。
        } catch (RetrofitError e) {
            e.printStackTrace();
        }
        return null;
    }
}
