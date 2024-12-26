package org.com.BrowserStackAssignment;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ELPais {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://elpais.com/");
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();

        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement cookies = driver.findElement(By.id("didomi-notice-agree-button"));
        cookies.click();

        WebElement Editorials = driver.findElement(By.xpath("//a[@cmp-ltrk='portada_menu'][normalize-space()='El País Exprés']"));
        String Subheader = Editorials.getText();
        System.out.println(Subheader);

        WebElement Hamburgermenu = driver.findElement(By.xpath("//button[@id='btn_open_hamburger']"));
        Hamburgermenu.click();

        WebElement Opinion = driver.findElement(By.xpath("//a[@cmp-ltrk='header_hamburguesa'][normalize-space()='Opinión']"));
        Opinion.click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));


        driver.quit();

    }
}

