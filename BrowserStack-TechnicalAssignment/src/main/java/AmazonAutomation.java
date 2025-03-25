import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AmazonAutomation {
    public static void main(String[] args) {


        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        try {

            driver.get("https://www.amazon.com/");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement Language = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='icp-nav-link-inner']//span[@class='nav-line-1']")));
            Language.click();

            WebElement Currency = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='a-dropdown-prompt']")));
            Currency.click();

            WebElement INR = driver.findElement(By.xpath("//a[@id=\"icp-currency-dropdown_44\"]"));
            INR.click();

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            WebElement SaveChanges = driver.findElement(By.xpath("//input[@class='a-button-input']"));
            SaveChanges.click();


            WebElement searchBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("twotabsearchtextbox")));
            searchBox.sendKeys("wireless mouse");
            searchBox.sendKeys(Keys.RETURN);


            WebElement firstProduct = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='puisg-col-inner']")));
            firstProduct.click();


            WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-button")));
            addToCartBtn.click();


            WebElement proceedToCheckoutBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='proceedToRetailCheckout']")));
            proceedToCheckoutBtn.click();


            WebElement emailInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ap_email")));
            emailInput.sendKeys("your-email@example.com");
            emailInput.sendKeys(Keys.RETURN);


            WebElement passwordInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ap_password")));
            passwordInput.sendKeys("your-password");
            passwordInput.sendKeys(Keys.RETURN);

            System.out.println("Successfully reached the checkout page!");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.quit();
        }
    }
}

