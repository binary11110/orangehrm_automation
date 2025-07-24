import com.google.common.annotations.VisibleForTesting;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;


public class Sauce {
    private static final String BASE_URL = "https://www.saucedemo.com/";
    private static final int WAIT= 120;

    private static final String VALID_USERNAME = "problem_user";
    private static final String VALID_PASSWORD = "secret_sauce";
    private static final String CUSTOMER_FIRST_NAME = "Sara";
    private static final String CUSTOMER_LAST_NAME = "Ahmed";
    private static final String POSTAL_CODE = "1234";
    private WebDriver driver;
    private WebDriverWait wait;
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        Sauce test = new Sauce();

        test.setupDriver();
        test.executeCompleteUserJourney();


    }

    void setupDriver() {

        WebDriverManager.chromedriver().setup();
        // Create Chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-extensions");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WAIT));
        wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT));

    }

    void executeCompleteUserJourney() {



        LogIn();
        AddToCart();
        RemoveFromCart();
        AddToCart();
        CheckOut();
        FillCheckoutForm();
        Finish();

    }
    private void LogIn() {
        driver.get(BASE_URL);

        driver.findElement(By.id("user-name")).sendKeys(VALID_USERNAME);
        driver.findElement(By.id("password")).sendKeys(VALID_PASSWORD);
        driver.findElement(By.id("login-button")).click();
    }
    private void AddToCart() {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.className("shopping_cart_link")).click();
    }
    private void RemoveFromCart() {
        driver.findElement(By.id("remove-sauce-labs-backpack")).click();
        driver.findElement(By.id("continue-shopping")).click();
    }
    private  void CheckOut(){
        driver.findElement(By.id("checkout")).click();
    }
    private  void FillCheckoutForm(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT));
        driver.findElement(By.id("first-name")).sendKeys(CUSTOMER_FIRST_NAME);

        driver.findElement(By.id("last-name")).sendKeys(CUSTOMER_LAST_NAME);

        driver.findElement(By.id("postal-code")).sendKeys(POSTAL_CODE);
        driver.findElement(By.id("continue")).click();
    }

    private void Finish(){
                driver.findElement(By.id("finish")).click();
    }
}