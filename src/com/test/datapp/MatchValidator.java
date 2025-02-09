package com.test.datapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Sends a request to the validation API to verify the generated matches.
 */
class MatchValidator {
    private static final String GET_USERS_URL = " https://snr-eng-7c5af300401d.herokuapp.com/api/users";
    
    private static final String VALIDATION_URL = "https://snr-eng-7c5af300401d.herokuapp.com/api/validate-matches";


    public static GetUsersResponse fetchUsersFromEndpoint() throws Exception {
        // Create URL object
        URL url = new URL(GET_USERS_URL);
        
        // Open connection
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");

        // Check the response code
        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new RuntimeException("Failed: HTTP error code : " + responseCode);
        }

        // Read response
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            response.append(line);
        }
        br.close();
        connection.disconnect();

        // Convert JSON response to List<UserProfile>
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response.toString(), GetUsersResponse.class);
    }
    
    public static void validateMatches(List<MatchedUsers> matches) {
        try {
            URL url = new URL(VALIDATION_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // Prepare JSON request body
            JSONArray matchArray = new JSONArray();
            for (MatchedUsers match : matches) {
                JSONObject matchObject = new JSONObject(match);
                matchObject.put("user1_id", match.getUser1_id());
                matchObject.put("user2_id", match.getUser2_id());
                matchArray.put(matchObject);
            }

            JSONObject requestBody = new JSONObject();
            requestBody.put("matches", matchArray);

            // Send request
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = requestBody.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Print response code from server
            int responseCode = conn.getResponseCode();
            System.out.println("Validation Response Code: " + responseCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
