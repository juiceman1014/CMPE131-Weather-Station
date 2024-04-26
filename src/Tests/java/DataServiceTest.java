package Tests.java;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

class DataServiceTest {

    @Test
    void testDataFileValues() {
        // This is the line that contains the data you want to test
        String lastLine = readLastLine("weather_data.txt");
        assertNotNull(lastLine, "The last line of weather data should not be null");

        // Parse the last line as a JSON object
        JSONObject weatherData = new JSONObject(lastLine);
        JSONObject currentConditions = weatherData.getJSONObject("current");

        // Extract values from JSON
        double temperature = currentConditions.getDouble("temp_f");
        System.out.println(temperature);
        int humidity = currentConditions.getInt("humidity");
        System.out.println(humidity);
        double precipitation = currentConditions.getDouble("precip_mm");
        System.out.println(precipitation);

        // Validate the values
        assertTrue(temperature >= -100 && temperature <= 150, "Temperature should be within range");
        assertTrue(humidity >= 0 && humidity <= 100, "Humidity should be within range");
        assertTrue(precipitation >= 0 && precipitation <= 100, "Precipitation should be within range");
    }

    // Helper method to read the last line of a file
    private String readLastLine(String filePath) {
        String lastLine = null;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lastLine = line;
            }
        } catch (IOException e) {
            fail("IOException occurred when reading the file: " + e.getMessage());
        }
        return lastLine;
    }
}
