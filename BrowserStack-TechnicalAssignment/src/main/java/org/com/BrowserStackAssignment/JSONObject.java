package org.com.BrowserStackAssignment;

import org.apache.commons.io.FileUtils;


import java.io.File;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class JSONObject {
    public JSONObject(String response) {

    }



    public static String translateText(String AllHeader) {
        try {
            String apiKey = "30c6301883mshb4b9391f8488e10p1f6dbajsneb64f2d668c8";
            String apiUrl = "https://rapid-translate-multi-traduction.p.rapidapi.com/t";


            //Sending API Request to Rapid API using HTTPURLConnection
            HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("x-rapidapi-key", apiKey);
            connection.setRequestProperty("x-rapidapi-host", "rapid-translate-multi-traduction.p.rapidapi.com");
            connection.setDoOutput(true);



            String jsonPayload = "{ \"q\": \"" + AllHeader + "\", \"from\": \"es\", \"to\": \"en\" }";




            try (OutputStream os = connection.getOutputStream()) {
                os.write(jsonPayload.toString().getBytes());
                os.flush();
            }

            //Validating the response code
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                Scanner scanner = new Scanner(connection.getInputStream());
                String response = scanner.useDelimiter("\\A").next();
                scanner.close();

                // Parse and return translated text
                JSONObject jsonResponse = new JSONObject(response);
                return jsonResponse.getString(response);
            } else {
                System.out.println("Error: HTTP response code " + responseCode);
                return "Error in Translation API";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Translation Error";
        }
    }



    public String getString(String translatedText) {

        System.out.println(translatedText);
        return translatedText;
    }



    // Save Image Locally
    public static void saveImage(String imageUrl, String fileName) {
        try {
            FileUtils.copyURLToFile(new URL(imageUrl), new File(fileName));
            System.out.println("Image saved: " + fileName);
        } catch (Exception e) {
            System.out.println("Failed to save image: " + e.getMessage());
        }
    }

    // Analyze Repeated Words in Translated Headers
    public static void analyzeRepeatedWords(Collection<String> headers) {
        Map<String, Integer> wordCount = new HashMap<>();
        for (String header : headers) {
            String[] words = header.split("\\s+");
            for (String word : words) {
                word = word.toLowerCase().replaceAll("[^a-zA-Z]", "");
                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
            }
        }
        System.out.println("\nRepeated Words:");
        wordCount.entrySet().stream()
                .filter(entry -> entry.getValue() > 2)
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
    }
}


