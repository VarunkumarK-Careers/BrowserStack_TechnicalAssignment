package org.com.BrowserStackAssignment;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

import static org.com.BrowserStackAssignment.JSONObject.*;

public class ElPaisScraper {
    public static void main(String[] args) {
        // Set up WebDriver
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);

        try {
            // Step 1: Visit El País and Navigate to Opinion Section
            driver.get("https://elpais.com/");
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
            Thread.sleep(2000);// Wait for page load

            WebElement cookies = driver.findElement(By.id("didomi-notice-agree-button"));
            cookies.click();

            WebElement Editorials = driver.findElement(By.xpath("//a[@cmp-ltrk='portada_menu'][normalize-space()='El País Exprés']"));
            String Subheader = Editorials.getText();
            System.out.println(Subheader);

            driver.findElement(By.xpath("//a[@cmp-ltrk='portada_menu'][normalize-space()='Opinión']")).click();
            Thread.sleep(2000);

            // Step 2: Fetch First Five Articles
            List<WebElement> articles = driver.findElements(By.xpath("//div[@class='b-d_a']//article[1]")) // Adjust selector as needed
                    .stream().limit(5).collect(Collectors.toList());

            Map<String, String> articleHeaders = new HashMap<>();
            for (WebElement article : articles) {
                String title = article.findElement(By.tagName("h2")).getText();
                System.out.println("Article Title (Spanish): " + title);
                articleHeaders.put(title, translateText(title, "es", "en")); // Translate title

                // Fetch and download image
                try {
                    WebElement imageElement = article.findElement(By.tagName("img"));
                    String imageUrl = imageElement.getAttribute("src");
                    saveImage(imageUrl, title.replaceAll("[^a-zA-Z0-9]", "_") + ".jpg");
                } catch (Exception e) {
                    System.out.println("Image not found for: " + title);
                }
            }

            // Step 3: Print Translated Titles
            System.out.println("\nTranslated Headers:");
            articleHeaders.forEach((spanish, english) -> System.out.println("Spanish: " + spanish + " -> English: " + english));

            // Step 4: Analyze Translated Headers
            analyzeRepeatedWords(articleHeaders.values());

            // Step 5: Cross-Browser Testing with BrowserStack (not included here, see notes below)
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}

    // Translation API Call

