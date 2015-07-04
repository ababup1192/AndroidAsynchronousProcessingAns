package org.ababup1192.hellothread.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Weather {
    public int id;
    @JsonProperty("main")
    public String info;
    public String icon;
    public String description;
}