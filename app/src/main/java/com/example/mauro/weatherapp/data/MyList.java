
package com.example.mauro.weatherapp.data;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class MyList {

    private static final int MILISECONDS = 1000;
    private static final String TAG = "MyListTAG_";

    @SerializedName("dt")
    @Expose
    private Long dt;
    @SerializedName("temp")
    @Expose
    private Temp temp;
    @SerializedName("pressure")
    @Expose
    private Double pressure;
    @SerializedName("humidity")
    @Expose
    private Integer humidity;
    @SerializedName("weather")
    @Expose
    private List<Weather> weather = null;
    @SerializedName("speed")
    @Expose
    private Double speed;
    @SerializedName("deg")
    @Expose
    private Integer deg;
    @SerializedName("clouds")
    @Expose
    private Integer clouds;
    @SerializedName("rain")
    @Expose
    private Double rain;

    public Long getDt() {
        return dt;
    }

    public void setDt(Long dt) {
        this.dt = dt;
    }

    public Temp getTemp() {
        return temp;
    }

    public void setTemp(Temp temp) {
        this.temp = temp;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Integer getDeg() {
        return deg;
    }

    public void setDeg(Integer deg) {
        this.deg = deg;
    }

    public Integer getClouds() {
        return clouds;
    }

    public void setClouds(Integer clouds) {
        this.clouds = clouds;
    }

    public Double getRain() {
        return rain;
    }

    public void setRain(Double rain) {
        this.rain = rain;
    }

    public String getTemperatureFormart() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.getTemp().getMaxCelsius());
        stringBuilder.append("°");
        stringBuilder.append("/");
        stringBuilder.append(this.getTemp().getMinCelsius());
        stringBuilder.append("°");
        return stringBuilder.toString();
    }

    public String getWeatherDescription() {
        StringBuilder stringBuilder = new StringBuilder();
        List<Weather> weathers = getWeather();
        for (Weather weatherAux : weathers) {
            stringBuilder.append(weatherAux.getDescription());
        }
        return stringBuilder.toString();
    }

    public String getDayFormart() {
//       this.dt is a unixtimestamp format
        Date date = new Date(this.getDt() * MILISECONDS);
        String day = new SimpleDateFormat("EE", Locale.US).format(date);
        //Log.d(TAG, "getDayFormart: " + date.toString() + " - " + day + " - " + this.getDt());
        return day;
    }

    public String getMonthDayFormat() {
        //       this.dt is a unixtimestamp format
        Date date = new Date(this.getDt() * MILISECONDS);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(new SimpleDateFormat("MM").format(date));
        stringBuilder.append("/");
        stringBuilder.append(new SimpleDateFormat("dd").format(date));
        //Log.d(TAG, "getMonthDayFormat: " + date.toString() + " - " + new SimpleDateFormat("MM").format(date) + " - " + new SimpleDateFormat("dd").format(date));
        System.out.println(date.toString() + " - " + new SimpleDateFormat("MM").format(date) + " - " + new SimpleDateFormat("dd").format(date));
        return stringBuilder.toString();
    }

    public String getWeatherIcon() {
        StringBuilder stringBuilder = new StringBuilder();
        List<Weather> weathers = getWeather();
        for (Weather weatherAux : weathers) {
            stringBuilder.append(weatherAux.getIcon() + ".png");
        }
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return "MyList{" +
                "dt=" + dt +
                ", temp=" + temp +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", weather=" + weather +
                ", speed=" + speed +
                ", deg=" + deg +
                ", clouds=" + clouds +
                ", rain=" + rain +
                '}';
    }
}
