package org.ababup1192.hellothread.weather;

public class Condition {
    public int humidity;
    public int pressure;
    public double temp;
    public double temp_max;
    public double temp_min;

    public int getTemp() {
        // subtract 273.15 to convert to Celsius
        return (int) (temp - 273.15);
    }
}
