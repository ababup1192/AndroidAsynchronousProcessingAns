package org.ababup1192.hellothread;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// 取得しない項目はIgnoreする必要がある。(JSONのパースに失敗する。)
@JsonIgnoreProperties({"milliseconds_since_epoch"})
public class MyDate {
    public String time;
    public String date;

    @Override
    public String toString() {
        return time + " " + date;
    }
}
