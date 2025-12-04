package ru.medvezhonokok.wfs.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.medvezhonokok.wfs.form.GeocodingCredentials;

import java.util.Optional;

@Service
public class GeocodingService {
    @Value("${weather.city.geocoding.url}")
    private String geocodingUrl;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public GeocodingService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public Optional<GeocodingCredentials> getCoordinates(String city) {
        try {
            String url = geocodingUrl + "name=" + city;
            String response = restTemplate.getForObject(url, String.class);

            JsonNode root = objectMapper.readTree(response);
            JsonNode results = root.get("results");

            if (results != null && results.isArray() && !results.isEmpty()) {
                JsonNode firstResult = results.get(0);

                GeocodingCredentials geocoding = new GeocodingCredentials();
                geocoding.setCity(city);
                geocoding.setLatitude(firstResult.get("latitude").asDouble());
                geocoding.setLongitude(firstResult.get("longitude").asDouble());

                return Optional.of(geocoding);
            }
        } catch (Exception e) {
            System.err.println("Error fetching coordinates for city: " + city);
            e.printStackTrace();
        }

        return Optional.empty();
    }
}