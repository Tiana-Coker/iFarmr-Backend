package org.ifarmr.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import org.ifarmr.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;


    @GetMapping("/api/v1/weather")
    public String getWeather(@RequestParam String city) {
        // Call the service to get weather data based on city name
        return weatherService.getWeatherByCity(city);
    }
}

