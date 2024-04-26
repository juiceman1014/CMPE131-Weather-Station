package Application.WeatherStation;

import java.util.Scanner;
import org.json.JSONObject; // You'll need a JSON library for parsing

public class UserInterface {
    private DataService weatherService = new DataService();
    private DataStore dataStore = new DataStore();

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter location for weather data:");

        String location = scanner.nextLine().trim();
        if (!"exit".equalsIgnoreCase(location)) {
            String weatherData = weatherService.getWeatherData(location);
            JSONObject jsonData = new JSONObject(weatherData); // Convert the string to a JSON object
            JSONObject current = jsonData.getJSONObject("current");   
            dataStore.saveWeatherData(weatherData); // Store the raw JSON data

            System.out.println("Enter 'temperature', 'humidity', 'condition', or 'precipitation' for specific data or type 'exit' to quit:");
            
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine().trim();
                switch (input.toLowerCase()) {
                    case "temperature":
                        System.out.println("Temperature (F): " + getTemperature(current));
                        break;
                    case "humidity":
                        System.out.println("Humidity (%): " + getHumidity(current));
                        break;
                    case "condition":
                        System.out.println("Condition: " + current.getJSONObject("condition").getString("text"));
                        break;
                    case "precipitation":
                        System.out.println("Precipitation (mm): " + getPrecipitation(current));
                        break;
                    case "exit":
                        System.out.println("Exiting the weather station application.");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid input. Please enter 'temperature', 'humidity', 'condition', or 'precipitation' or type 'exit' to quit:");
                        break;
                }
            }
        } else {
            System.out.println("Exiting the weather station application.");
        }

        scanner.close();
    }
    
    // Getter methods for temperature, humidity, and precipitation
    private double getTemperature(JSONObject current) {
        return current.getDouble("temp_f");
    }
    
    private int getHumidity(JSONObject current) {
        return current.getInt("humidity");
    }
    
    private double getPrecipitation(JSONObject current) {
        return current.getDouble("precip_mm");
    }
}
