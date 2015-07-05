package org.ababup1192.hellothread.github;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;

public interface GitHubService {
    @GET("/users/{user}/repos")
    List<Repository> listRepos(@Path("user") String user);
}
