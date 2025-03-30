package com.weather.service.impl;

import com.weather.entity.Weather;
import com.weather.service.WeatherService;
import com.weather.service.external.JsonWeatherService;
import com.weather.service.external.JsonWeatherServiceResponse;

public class XmlWeatherAdapter implements WeatherService {
    private JsonWeatherService weatherService;

    public XmlWeatherAdapter(JsonWeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Override
    public Weather getWeather(String location) {
        JsonWeatherServiceResponse response = weatherService.fetchJsonWeather(location);
        System.out.println("data fetch from actual service");
        return new Weather(location, response.getDescription());
    }
}
