package Application.WeatherStation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataStore {

    private static final String FILE_NAME = "weather_data.txt";

    public void saveWeatherData(String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(data);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readWeatherData() {
        try {
            return new String(Files.readAllBytes(Paths.get(FILE_NAME)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
