package org.ababup1192.hellothread.github;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

public interface GitHubService {
    @GET("/users/{user}/repos")
    Observable<List<Repository>> listRepos(@Path("user") String user);
}
