package ru.medvezhonokok.wfs.form;

import java.util.List;

public class ForecastCredentials {
    private String city;
    private double latitude;
    private double longitude;
    private List<HourlyForecast> hourlyForecasts;

    public static class HourlyForecast {
        private String time;
        private double temperature;

        public HourlyForecast() {
        }

        public HourlyForecast(String time, double temperature) {
            this.time = time;
            this.temperature = temperature;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public double getTemperature() {
            return temperature;
        }

        public void setTemperature(double temperature) {
            this.temperature = temperature;
        }
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public List<HourlyForecast> getHourlyForecasts() {
        return hourlyForecasts;
    }

    public void setHourlyForecasts(List<HourlyForecast> hourlyForecasts) {
        this.hourlyForecasts = hourlyForecasts;
    }
}