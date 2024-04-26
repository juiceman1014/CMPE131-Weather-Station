package Application.WeatherStation;

import java.util.Base64;

public class DataTransmitter {

    public String encodeData(String data) {
        return Base64.getEncoder().encodeToString(data.getBytes());
    }

    public String decodeData(String data) {
        byte[] decodedBytes = Base64.getDecoder().decode(data);
        return new String(decodedBytes);
    }
}
