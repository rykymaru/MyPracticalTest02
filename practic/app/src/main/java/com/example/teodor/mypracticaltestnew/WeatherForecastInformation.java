package com.example.teodor.mypracticaltestnew;

public class WeatherForecastInformation {
    String temp, wind, condition, pressure, humidity;

    public WeatherForecastInformation(String temp, String wind, String condition, String pressure, String humidity) {
        this.temp = temp;
        this.wind = wind;
        this.condition = condition;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
}

