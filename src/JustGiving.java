import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class JustGiving{

    public static void main(String[] args) {
        try {
            // Set up the request parameters
            String apiKey = "YOUR_API_KEY";
            String charityId = "YOUR_CHARITY_ID";
            String fundraiserName = "Test Fundraiser";
            String event = "Test Event";
            String pageStory = "This is a test fundraiser";
            String targetAmount = "100";
            String currencyCode = "GBP";
            String charityOptIn = "true";
            String charityFunded = "true";

            // Build the request URL and headers
            String url = String.format("https://api.justgiving.com/v1/charity/%s/fundraising", charityId);
            String auth = apiKey + ":";
            byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
            String authHeader = "Basic " + new String(encodedAuth);
            String contentType = "application/json";

            // Build the request body
            String body = String.format("{"
                    + "\"name\":\"%s\","
                    + "\"event\":\"%s\","
                    + "\"pageStory\":\"%s\","
                    + "\"targetAmount\":%s,"
                    + "\"currencyCode\":\"%s\","
                    + "\"charityOptIn\":%s,"
                    + "\"charityFunded\":%s"
                    + "}", fundraiserName, event, pageStory, targetAmount, currencyCode, charityOptIn, charityFunded);

            // Send the request and print the response
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", authHeader);
            connection.setRequestProperty("Content-Type", contentType);
            connection.setDoOutput(true);
            connection.getOutputStream().write(body.getBytes(StandardCharsets.UTF_8));
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder responseBuilder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                responseBuilder.append(line);
            }
            reader.close();
            String response = responseBuilder.toString();
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
