package ru.medvezhonokok.wfs.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.medvezhonokok.wfs.form.ForecastCredentials;
import ru.medvezhonokok.wfs.service.WeatherService;

@Controller
@RequestMapping("/weather")
public class WeatherController extends Page {
    private final WeatherService weatherService;
    private static final String MESSAGE_SESSION_KEY = "message";

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    public String getWeather(HttpSession httpSession, @RequestParam(required = false) String city, Model model) {
        if (city == null || city.trim().isEmpty()) {
            setMessage(httpSession, "City is required");
            return "WeatherPage";
        }

        try {
            ForecastCredentials forecast = weatherService.getWeatherForecast(city.trim());
            model.addAttribute("forecast", forecast);
        } catch (IllegalArgumentException e) {
            setMessage(httpSession, "No such city: " + city);
        }

        model.addAttribute("cacheSize", weatherService.getCacheSize());

        return "WeatherPage";
    }
}