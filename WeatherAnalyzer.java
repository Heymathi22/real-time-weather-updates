import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class WeatherAnalyzer {

    private static final String API_KEY = "your_api_key"; // Replace with your actual API key
    private static final String CITY = "London";
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather?q=" + CITY + "&appid=" + API_KEY + "&units=metric";

    public static void main(String[] args) {
        try {
            // Connect to the API
            URL url = new URL(BASE_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Read the response
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder jsonResponse = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                jsonResponse.append(inputLine);
            }
            in.close();

            // Parse JSON response
            JSONObject weatherData = new JSONObject(jsonResponse.toString());

            // Extract data
            JSONObject main = weatherData.getJSONObject("main");
            double temperature = main.getDouble("temp");
            int humidity = main.getInt("humidity");

            JSONObject weather = weatherData.getJSONArray("weather").getJSONObject(0);
            String description = weather.getString("description");

            // Output
            System.out.println("City: " + CITY);
            System.out.println("Temperature: " + temperature + "°C");
            System.out.println("Humidity: " + humidity + "%");
            System.out.println("Conditions: " + description);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
