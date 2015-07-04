package org.ababup1192.hellothread.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties({"base", "clouds", "cod", "coord", "dt", "id", "name", "rain", "sys", "wind"})
public class WeatherInfo {
    private Condition condition;
    private List<Weather> weatherList;


    public List<Weather> getWeatherList() {
        return weatherList;
    }

    @JsonProperty("weather")
    public void setWeatherList(List<Weather> weatherList) {
        this.weatherList = weatherList;
    }

    public Condition getCondition() {
        return condition;
    }

    @JsonProperty("main")
    public void setCondition(Condition condition) {
        this.condition = condition;
    }


    public String getWeather() {
        String weatherText = "";
        for (int i = 0; i < weatherList.size(); i++) {
            if (i == weatherList.size() - 1) {
                weatherText += weatherList.get(i).info;
            } else {
                weatherText += weatherList.get(i).info + " のち ";
            }
        }
        return weatherText;
    }

    public int getTemp() {
        return condition.getTemp();
    }

    @Override
    public String toString() {
        return getWeather() + " " + getTemp() + "度";
    }

}
