package dev.wy.omaniollama.functions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClient;

import java.util.function.Function;

/**
 * Weather API
 * <a href="https://www.weatherapi.com/api-explorer.aspx">...</a>
 */
public class WeatherService implements Function<WeatherService.Request, WeatherService.Response> {

    private static final Logger log = LoggerFactory.getLogger(WeatherService.class);
    private final RestClient restClient;
    private final WeatherConfigProperties weatherProps;

    public WeatherService(WeatherConfigProperties props) {
        this.weatherProps = props;
        log.info("Weather API URL: {}", weatherProps.apiUrl());
        log.info("Weather API Key: {}", weatherProps.apiKey());
        this.restClient = RestClient.create(weatherProps.apiUrl());
    }

    @Override
    public Response apply(Request request) {
        log.info("Weather request: {}", request);
        Response resp = restClient.get()
                .uri("/current.json?key={key}&q={q}", weatherProps.apiKey(), request.city())
                .retrieve()
                .body(Response.class);
        log.info("Weather API Response: {}", resp);
        return resp;
    }

    // mapping the response of the Weather API to records
    public record Request(String city){}
    public record Response(Location location, Current current){}
    public record Location(String name, String region, String country, Long lat, Long lon){}
    public record Current(String temp_f, Condition condition, String wind_mph, String humidity){}
    public record Condition(String text){}
}
