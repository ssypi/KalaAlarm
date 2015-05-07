package kala.alarm.client;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Internal class used to access the remote error notification API.
 */
class ApiClient {
    private static final String CONFIG_FILE_NAME = "kala-alarm.properties";

    private String apiUrl;
    private int apiKey;

    public ApiClient() {
        ConfigReader configReader = new ConfigReader(CONFIG_FILE_NAME);
        apiUrl = configReader.readString("API_URL", null);
        apiKey = configReader.readInt("API_KEY", -1);
        if (apiUrl == null) {
            throw new AssertionError("No API_URL set in config file. Check " + CONFIG_FILE_NAME);
        }
    }

    public void sendError(String message) {
        String apiInput = "{\n" +
                " \"message\" : \"" + message + "\",\n" +
                " \"application\": {\n" +
                "   \"id\" : " + apiKey + "\n" +
                "   }\n" +
                "}";
        String output;

        try {
            System.out.println(apiInput);
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost postRequest = new HttpPost(apiUrl);
            StringEntity input = new StringEntity(apiInput);
            input.setContentType("application/json");
            postRequest.setEntity(input);

            HttpResponse response = httpClient.execute(postRequest);

            if (response.getStatusLine().getStatusCode() != 201) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));

            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            httpClient.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
