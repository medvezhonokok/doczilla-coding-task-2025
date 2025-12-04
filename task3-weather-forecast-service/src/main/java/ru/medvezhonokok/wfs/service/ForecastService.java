package ru.medvezhonokok.wfs.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.medvezhonokok.wfs.form.ForecastCredentials;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ForecastService {
    @Value("${weather.city.forecast.url}")
    private String forecastUrl;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    private static final ZoneId MOSCOW_ZONE = ZoneId.of("Europe/Moscow");
    private static final DateTimeFormatter API_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    public ForecastService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public ForecastCredentials getForecast(double latitude, double longitude, String city) {
        try {
            String url = forecastUrl + "latitude=" + latitude + "&longitude=" + longitude
                         + "&hourly=temperature_2m&timezone=Europe/Moscow&forecast_days=2";

            String response = restTemplate.getForObject(url, String.class);
            JsonNode root = objectMapper.readTree(response);

            ForecastCredentials forecast = new ForecastCredentials();
            forecast.setCity(city);
            forecast.setLatitude(latitude);
            forecast.setLongitude(longitude);

            JsonNode times = root.get("hourly").get("time");
            JsonNode temps = root.get("hourly").get("temperature_2m");

            LocalDateTime nowInMoscow = ZonedDateTime.now(MOSCOW_ZONE)
                    .toLocalDateTime();

            List<ForecastCredentials.HourlyForecast> hourlyForecasts = new ArrayList<>();

            for (int i = 0; hourlyForecasts.size() < 24; i++) {
                String timeStr = times.get(i).asText();
                LocalDateTime forecastTime = LocalDateTime.parse(timeStr, API_FORMATTER);

                if (forecastTime.isAfter(nowInMoscow)) {
                    ForecastCredentials.HourlyForecast hf = new ForecastCredentials.HourlyForecast();
                    hf.setTime(timeStr.replace("T", " "));
                    hf.setTemperature(temps.get(i).asDouble());
                    hourlyForecasts.add(hf);
                }
            }

            forecast.setHourlyForecasts(hourlyForecasts);

            return forecast;
        } catch (Exception e) {
            System.err.println("Error fetching forecast: " + e.getMessage());
            throw new RuntimeException("Failed to fetch weather forecast", e);
        }
    }
}