package org.com.BrowserStackAssignment;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import java.net.MalformedURLException;
import java.util.*;

import static org.com.BrowserStackAssignment.JSONObject2.*;

public class ElPaisScraper2 {
    public static void main(String[] args) throws MalformedURLException {


        // String USERNAME = "varunkumark_x7b0IK";
        //String AUTOMATE_KEY = "dz6fww4CEEqAEqvhJqye";
        //String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);

        // WebDriver driver = null;


        try {
            // Step 1: Visit El País and Navigate to Opinion Section

           /* ChromeOptions options = new ChromeOptions();
            Map<String, Object> browserstackOptions = new HashMap<>();

            browserstackOptions.put("os", "Windows");
            browserstackOptions.put("osVersion", "11");
            browserstackOptions.put("browserName", "Chrome");
            browserstackOptions.put("browserVersion", "latest");
            browserstackOptions.put("buildName", "ElPaisScraper Build");
            browserstackOptions.put("sessionName", "ElPaisScraper Test");

            options.setCapability("bstack:options", browserstackOptions);

            driver = new RemoteWebDriver(new URL(URL), options);*/
            driver.get("https://elpais.com/");
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
            Thread.sleep(2000);// Wait for page load

            WebElement cookies = driver.findElement(By.id("didomi-notice-agree-button"));
            cookies.click();

            WebElement Editorials = driver.findElement(By.xpath("//a[@cmp-ltrk='portada_menu'][normalize-space()='El País Exprés']"));
            String Subheader = Editorials.getText();
            String SpanishText = "EL PAÍS EXPRÉS";
            if(Subheader.equals(SpanishText)){
                System.out.println("Website text is displayed in Spanish:" + Subheader);
            } else {
                System.out.println("Website text is not displayed in Spanish:" + Subheader);
            }






            driver.findElement(By.xpath("//a[@cmp-ltrk='portada_menu'][normalize-space()='Opinión']")).click();

            WebElement Header2 = driver.findElement(By.xpath("/html[1]/body[1]/main[1]/div[1]/section[1]/div[1]/article[3]/header[1]/h2[1]/a[1]"));
            Header2.click();

            // Step 2: Fetch First Five Articles
            List<WebElement> articles2 = driver.findElements(By.cssSelector(".a._g._g-lg._g-o"));
            Thread.sleep(3000);


            Map<String, String> articleHeaders2 = new HashMap<>();
            for (WebElement article2 : articles2) {
                String title2 = article2.findElement(By.tagName("h1")).getText();
                String content2 = article2.findElement(By.tagName("h2")).getText();
                String News2 = article2.findElement(By.xpath("//div[@class='a_c clearfix']//p[1]")).getText();
                System.out.println("Article Title1(Spanish): " + title2);
                System.out.println("Article Content1(Spanish):" + content2);
                System.out.println("Article News1(Spanish):" + News2);
                articleHeaders2.put(title2,  translateText1(title2, content2, News2));


                // Fetch and download image
                try {
                    WebElement imageElement = article2.findElement(By.tagName("img"));
                    String imageUrl = imageElement.getAttribute("src");
                    saveImage2(imageUrl, title2.replaceAll("[^0-9]", "") + ".jpg");
                } catch (Exception e) {
                    System.out.println("Image not found for: " + title2);
                }
            }

            // Step 3: Print Translated Titles
            System.out.println("\nTranslated Headers:");
            articleHeaders2.forEach((spanish, english) -> System.out.println("Spanish: " + spanish + " -> English: " + english));

            // Step 4: Analyze Translated Headers
            analyzeRepeatedWords2(articleHeaders2.values());

            // Step 5: Cross-Browser Testing with BrowserStack (not included here, see notes below)
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

}



