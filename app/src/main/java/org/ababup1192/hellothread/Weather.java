package org.ababup1192.hellothread;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Weather {
    JSONObject json;

    public Weather(String jsonText) throws JSONException {
        this.json = new JSONObject(jsonText);
    }

    public String getWeather() throws JSONException {
        String weatherText = "";
        // 晴れのち曇り のように、天気が複数の可能性があるので、配列として取得。
        JSONArray weathers = json.getJSONArray("weather");
        for (int i = 0; i < weathers.length(); i++) {
            // 最後の天気
            if (i == weathers.length() - 1) {
                weatherText += ((JSONObject) weathers.get(i)).getString("main");
                // 最後じゃない場合は、 のち と繋げる。
            } else {
                weatherText += ((JSONObject) weathers.get(i)).getString("main") + " のち ";
            }
        }
        return weatherText;
    }

    // ケルビンをセルシウス温度へ変換。
    public int getTemp() {
        try {
            JSONObject mainInfo = json.getJSONObject("main");
            Double kelvin = mainInfo.getDouble("temp");
            // subtract 273.15 to convert to Celsius
            return (int) (kelvin - 273.15);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String toString() {
        try {
            return getWeather() + " " + getTemp() + "度";
        } catch (JSONException e) {
            e.printStackTrace();
            return "天気の取得に失敗しました。";
        }
    }
}

