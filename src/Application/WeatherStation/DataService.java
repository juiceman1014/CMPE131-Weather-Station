package Application.WeatherStation;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class DataService {
    private static final String API_KEY = "cd06bddc6518484ba1721122232810";
    private static final String API_URL = "https://api.weatherapi.com/v1/current.json";

    public String getWeatherData(String location) {
        try {
            String encodedLocation = URLEncoder.encode(location, StandardCharsets.UTF_8);
            String fullURL = String.format("%s?key=%s&q=%s", API_URL, API_KEY, encodedLocation);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(fullURL))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return response.body();
            } else {
                throw new IOException("Unexpected HTTP response: " + response.statusCode() + " " + response.body());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null; // or handle the error appropriately
        }
    }
}
