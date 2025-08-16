package org.example.Pages;


import org.example.Utilts.waitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class LoginPage extends BaseClass {
    private final WebDriver driver;
    private final waitHelper explicitWait;

    // Locators
    private final By usernameField = By.name("username");
    private final By passwordField = By.name("password");
    private final By loginButton = By.xpath("//button[@type='submit']");
    private final By dashboardHeader = By.xpath("//h6[text()='Dashboard']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.explicitWait = new waitHelper(driver, 10);
    }

    public void enterUsername() {
        explicitWait.waitForVisible(usernameField).sendKeys(ADMIN_USERNAME);
    }

    public void enterPassword() {
        explicitWait.waitForVisible(passwordField).sendKeys(ADMIN_PASSWORD);
    }

    public void clickLogin() {
        explicitWait.waitForVisible(loginButton).click();
    }

    public void verifySuccessfulLogin() {
        explicitWait.waitForVisible(dashboardHeader);
        Assert.assertTrue(driver.findElement(dashboardHeader).isDisplayed());
    }

    public void login() {
        enterUsername();
        enterPassword();
        clickLogin();
        verifySuccessfulLogin();
    }
}