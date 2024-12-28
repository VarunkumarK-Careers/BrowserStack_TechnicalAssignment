package org.com.BrowserStackAssignment;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import java.net.MalformedURLException;
import java.util.*;

import static org.com.BrowserStackAssignment.JSONObject1.*;

public class ElPaisScraper1 {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {


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
            if (Subheader.equals(SpanishText)) {
                System.out.println("Website text is displayed in Spanish:" + Subheader);
            } else {
                System.out.println("Website text is not displayed in Spanish:" + Subheader);
            }


            driver.findElement(By.xpath("//a[@cmp-ltrk='portada_menu'][normalize-space()='Opinión']")).click();


            // Step 2: Fetch First Five Articles
            List<WebElement> articles = driver.findElements(By.cssSelector("._g._g-md._g-o.b.b-d"));
            Thread.sleep(3000);


            Map<String, String> articleHeaders = new HashMap<>();

                for (int i = 0; i < Math.min(5, articles.size()); i++) {
                    WebElement article = articles.get(i);
                    String content = article.getText();
                    System.out.println("Article Title1(Spanish): " + content);


                    List<WebElement> h2Elements = driver.findElements(By.tagName("h2"));
                    List<String> h2Texts = new ArrayList<>();
                    for (int j = 0; j < Math.min(5, h2Elements.size()); j++) {
                        WebElement h2 = h2Elements.get(j);
                        String Title = h2.getText(); // Get the text from the h2 element
                        h2Texts.add(Title);
                        System.out.println("H2 Text is :" + Title); // Print each h2 text
                        articleHeaders.put(Title, translateText1(Title));
                    }


                    // Fetch and download image
                    try {
                        WebElement imageElement = article.findElement(By.tagName("img"));
                        String imageUrl = imageElement.getAttribute("src");
                        saveImage1(imageUrl, content.replaceAll("[^0-9]", "") + ".jpg");
                    } catch (Exception e) {
                        System.out.println("Image not found for: " + content);
                    }
                }

                // Step 3: Print Translated Titles
                System.out.println("\nTranslated Headers:");
                articleHeaders.forEach((spanish, english) -> System.out.println("Spanish: " + spanish + " -> English: " + english));

                // Step 4: Analyze Translated Headers
                analyzeRepeatedWords1(articleHeaders.values());

                // Step 5: Cross-Browser Testing with BrowserStack (not included here, see notes below)
            } catch(Exception e){
                e.printStackTrace();
            } finally{
                if (driver != null) {
                    driver.quit();
                }
            }
        }
    }








