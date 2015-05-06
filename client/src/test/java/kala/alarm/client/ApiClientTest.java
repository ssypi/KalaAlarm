package kala.alarm.client;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Jussi on 6.5.2015.
 */
public class ApiClientTest {

    @Test
    public void testPost() throws Exception {

        ApiClient testApiClient = new ApiClient();

        testApiClient.sendError("Jeejeetestia", 1);


    }
}