package ru.medvezhonokok.wfs.service;

import org.springframework.stereotype.Service;
import ru.medvezhonokok.wfs.cache.impl.InMemoryCacheImpl;
import ru.medvezhonokok.wfs.configuration.InMemoryCacheConfiguration;
import ru.medvezhonokok.wfs.form.ForecastCredentials;
import ru.medvezhonokok.wfs.form.GeocodingCredentials;

@Service
public class WeatherService {
    private final GeocodingService geocodingService;
    private final ForecastService forecastService;
    private final InMemoryCacheImpl inMemoryCache;
    private final long cacheTtlMillis;

    public WeatherService(GeocodingService geocodingService,
                          ForecastService forecastService,
                          InMemoryCacheImpl inMemoryCache,
                          InMemoryCacheConfiguration cacheConfig) {
        this.geocodingService = geocodingService;
        this.forecastService = forecastService;
        this.inMemoryCache = inMemoryCache;
        this.cacheTtlMillis = cacheConfig.getCacheTtlMillis();
    }

    public ForecastCredentials getWeatherForecast(String city) {
        String key = city.trim().toLowerCase();

        ForecastCredentials cachedForecast = (ForecastCredentials) inMemoryCache.get(key);

        if (cachedForecast != null) {
            return cachedForecast;
        }

        GeocodingCredentials geocoding = geocodingService.getCoordinates(city)
                .orElseThrow(() -> new IllegalArgumentException("No such city: " + city));

        ForecastCredentials forecast = forecastService.getForecast(
                geocoding.getLatitude(),
                geocoding.getLongitude(),
                city
        );

        inMemoryCache.set(key, forecast, cacheTtlMillis);

        return forecast;
    }

    public int getCacheSize() {
        return inMemoryCache.size();
    }

    public void clearCache() {
        inMemoryCache.clear();
    }
}