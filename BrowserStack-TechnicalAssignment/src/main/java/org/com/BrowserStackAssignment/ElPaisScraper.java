package org.com.BrowserStackAssignment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import static org.com.BrowserStackAssignment.JSONObject.*;
import static org.com.BrowserStackAssignment.JSONObject.translateText;


public class ElPaisScraper {
    @Test
    public  void BrowserStackTechAssignment() throws MalformedURLException {


        String username = "varunkumark_x7b0IK";
        String accessKey = "dz6fww4CEEqAEqvhJqye";


        WebDriver driver = null;
        try {


            DesiredCapabilities caps = new DesiredCapabilities();


            Map<String, Object> browserStackOptions = new HashMap<>();
            browserStackOptions.put("buildName", "BrowserStack TechAssignment");
            browserStackOptions.put("sessionName", "ELPaisScraper");

            caps.setCapability("bstack:options", browserStackOptions);

            driver = new RemoteWebDriver(new URL("https://" + username + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub"), caps);

            driver.get("https://elpais.com/");
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
            Thread.sleep(2000);// Wait for page load

            //Accepting the cookies
            WebElement cookies = driver.findElement(By.id("didomi-notice-agree-button"));
            cookies.click();

            // Validating the website is loading in Spanish
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

            //fetching five articles using the xpath
            Map<String, String> articleHeaders = new HashMap<>();
            StringBuilder Header = new StringBuilder();
            for (WebElement article : articles) {
                String title1 = article.findElement(By.xpath("/html[1]/body[1]/main[1]/div[1]/section[1]/div[1]/article[1]/header[1]/h2[1]/a[1]")).getText();
                String title2 = article.findElement(By.xpath("/html[1]/body[1]/main[1]/div[1]/section[1]/div[1]/article[2]/header[1]/h2[1]/a[1]")).getText();
                String title3 = article.findElement(By.xpath("/html[1]/body[1]/main[1]/div[1]/section[1]/div[1]/article[3]/header[1]/h2[1]/a[1]")).getText();
                String title4 = article.findElement(By.xpath("/html[1]/body[1]/main[1]/div[1]/section[1]/div[2]/article[1]/header[1]/h2[1]/a[1]")).getText();
                String title5 = article.findElement(By.xpath("/html[1]/body[1]/main[1]/div[1]/section[1]/div[3]/article[1]/header[1]/h2[1]/a[1]")).getText();
                String content1 = article.findElement(By.xpath("/html[1]/body[1]/main[1]/div[1]/section[1]/div[1]/article[1]/p[1]")).getText();
                String content2 = article.findElement(By.xpath("/html[1]/body[1]/main[1]/div[1]/section[1]/div[1]/article[2]/p[1]")).getText();
                String content3 = article.findElement(By.xpath("/html[1]/body[1]/main[1]/div[1]/section[1]/div[1]/article[3]/p[1]")).getText();
                String content4 = article.findElement(By.xpath("/html[1]/body[1]/main[1]/div[1]/section[1]/div[2]/article[1]/p[1]")).getText();
                String content5 = article.findElement(By.xpath("/html[1]/body[1]/main[1]/div[1]/section[1]/div[3]/article[1]/p[1]")).getText();
                //combining all the output into single output
                System.out.println("Article Title (Spanish): " + title1 + title2 + title3 + title4 + title5);
                Header.append(title1).append("\n");
                Header.append(title2).append("\n");
                Header.append(title3).append("\n");
                Header.append(title4).append("\n");
                Header.append(title5).append("\n");
                String AllHeader = Header.toString();
                System.out.println(AllHeader);
                System.out.println("Article Content(Spanish):" + content1 + content2 + content3 + content4 + content5);
                articleHeaders.put(AllHeader, translateText(AllHeader));


                // Fetch and download image
                try {
                    WebElement imageElement = article.findElement(By.tagName("img"));
                    String imageUrl = imageElement.getAttribute("src");
                    saveImage(imageUrl, title1.replaceAll("[^0-9]", "") + ".jpg");
                } catch (Exception e) {
                    System.out.println("Image not found for: " + title1);
                }
            }

            // Print Translated Titles
            System.out.println("\nTranslated Headers:");
            articleHeaders.forEach((spanish, english) -> System.out.println("Spanish: " + spanish + " -> English: " + english));

            //Analyze Translated Headers
            analyzeRepeatedWords(articleHeaders.values());


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

}



