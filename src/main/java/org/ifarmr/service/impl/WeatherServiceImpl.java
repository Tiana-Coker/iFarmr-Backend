package org.ifarmr.service.impl;

import org.ifarmr.service.WeatherService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherServiceImpl implements WeatherService {
    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.url}")
    private String apiUrl;
    @Override
    public String getWeatherByCity(String cityName) {
        // Build the full API URL
        String url = apiUrl + "?key=" + apiKey + "&q=" + cityName + "&aqi=no";

        // Log the request URL
        System.out.println("Requesting URL: " + url);

        // Create RestTemplate instance to call external API
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        return response.getBody();
    }
}
