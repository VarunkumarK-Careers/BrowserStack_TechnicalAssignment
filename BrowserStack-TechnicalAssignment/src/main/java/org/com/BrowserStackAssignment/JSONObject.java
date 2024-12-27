package org.com.BrowserStackAssignment;

import org.apache.commons.io.FileUtils;


import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class JSONObject {
    public JSONObject(String response) {

    }

    public JSONObject() {

    }

    public static String translateText(String text, String sourceLang, String targetLang) {
        try {
            String apiKey = "0c1e75a0e4msh65faf643b9cac68p1f67f5jsn444e30548033";
            String apiUrl = "rapidapi.com";
            org.com.BrowserStackAssignment.JSONObject payload = new JSONObject();
            payload.put("text", text);
            payload.put("source", sourceLang);
            payload.put("target", targetLang);

            HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("X-RapidAPI-Key", apiKey);
            connection.setRequestProperty("X-RapidAPI-Host", "multi-traduction.p.rapidapi.com");
            connection.setDoOutput(true);

            try (FileOutputStream os = new FileOutputStream(String.valueOf(connection.getOutputStream()))) {
                os.write(payload.toString().getBytes());
            }

            Scanner scanner = new Scanner(connection.getInputStream());
            String response = scanner.useDelimiter("\\A").next();
            scanner.close();

            JSONObject jsonResponse = new JSONObject(response);
            return jsonResponse.getString("translatedText");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getString(String translatedText) {

        return translatedText;
    }

    private void put(String text, String text1) {

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


