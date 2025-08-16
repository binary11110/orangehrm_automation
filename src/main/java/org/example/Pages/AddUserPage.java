package org.example.Pages;


import org.example.Utilts.waitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddUserPage extends BaseClass {
    private final WebDriver driver;
    private final waitHelper explicitWait;


    private final By addUserButton = By.cssSelector("button[class='oxd-button oxd-button--medium oxd-button--secondary']");
    private final By userRoleDropdown = By.xpath("//label[text()='User Role']/../following-sibling::div");
    private final By adminOption = By.xpath("//div[@role='listbox']//div[normalize-space()='Admin']");
    private final By employeeNameField = By.cssSelector("input[placeholder='Type for hints...']");
    private final By employeeNameOption = By.xpath("//div[@role='listbox']//div[2]");
    private final By statusDropdown = By.xpath("//label[text()='Status']/../following-sibling::div");
    private final By enabledOption = By.xpath("//div[@role='listbox']//div[normalize-space()='Enabled']");
    private final By usernameField = By.xpath("//label[text()='Username']/../following-sibling::div/input");
    private final By passwordField = By.xpath("//label[text()='Password']/../following-sibling::div/input");
    private final By confirmPasswordField = By.xpath("//label[text()='Confirm Password']/../following-sibling::div/input");
    private final By saveButton = By.xpath("//button[normalize-space()='Save']");
    private final By successToast = By.cssSelector(".oxd-toast-container");

    public AddUserPage(WebDriver driver) {
        this.driver = driver;
        this.explicitWait = new waitHelper(driver, 30);
    }

    public void clickAddUserButton() {
        explicitWait.waitForVisible(addUserButton).click();
    }

    public void selectUserRole() {
        explicitWait.waitForVisible(userRoleDropdown).click();
        explicitWait.waitForVisible(adminOption).click();
    }

    public void selectEmployeeName() {
        WebElement empField = explicitWait.waitForVisible(employeeNameField);
        empField.sendKeys("a");
        explicitWait.waitForVisible(employeeNameOption).click();
    }

    public void selectStatus() {
        explicitWait.waitForVisible(statusDropdown).click();
        explicitWait.waitForVisible(enabledOption).click();
    }

    public void enterUsername() {
        explicitWait.waitForVisible(usernameField).sendKeys(NEW_USERNAME);

    }

    public void enterPassword() {
        explicitWait.waitForVisible(passwordField).sendKeys(NEW_PASSWORD);
    }

    public void enterConfirmPassword() {
        explicitWait.waitForVisible(confirmPasswordField).sendKeys(NEW_PASSWORD);
    }

    public void clickSave() {
        explicitWait.waitForVisible(saveButton).click();
        explicitWait.waitForVisible(successToast);
    }

    public void createNewUser() {
        clickAddUserButton();
        selectUserRole();
        selectEmployeeName();
        selectStatus();
        enterUsername();
        enterPassword();
        enterConfirmPassword();
        clickSave();
    }
}