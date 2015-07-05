package org.ababup1192.hellothread.github;

import com.google.gson.annotations.SerializedName;

public class Owner {
    public String login;
    public int id;
    @SerializedName("html_url")
    public String url;
    // ... 以下省略
}
