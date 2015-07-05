package org.ababup1192.hellothread.github;

import com.google.gson.annotations.SerializedName;

public class Repository {
    public long id;
    public String name;
    @SerializedName("full_name")
    public String fullName;
    public Owner owner;
    @SerializedName("html_url")
    public String url;
    // 以下略...
}
