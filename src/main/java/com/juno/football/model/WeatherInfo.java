package com.juno.football.model;

public class WeatherInfo {
    private double humidity;       // Влажность в процентах (например, 90.0)
    private double rainIntensity;  // Осадки в мм/час (например, 2.5)

    public WeatherInfo(double humidity, double rainIntensity) {
        this.humidity = humidity;
        this.rainIntensity = rainIntensity;
    }

    public double getHumidity() { return humidity; }
    public double getRainIntensity() { return rainIntensity; }
}

