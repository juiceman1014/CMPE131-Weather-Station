package Application.WeatherStation;

public class Analyzer {
    public double calculateAverageTemperature(double[] temperatures) {
        double sum = 0;
        for (double temp : temperatures) {
            sum += temp;
        }
        return temperatures.length > 0 ? sum / temperatures.length : 0;
    }
}
