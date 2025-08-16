package org.example.Pages;


import org.example.Utilts.waitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


public class AdminUsersPage extends BaseClass {
    private final WebDriver driver;
    private final waitHelper explicitWait;


    private final By adminButton = By.xpath("//li[1]//a[1]//span[1]");
    private final By recordNumber = By.xpath("//span[@class='oxd-text oxd-text--span']");
    private final By searchBox = By.xpath("//label[text()='Username']/../following-sibling::div/input");
    private final By submitButton = By.cssSelector("button[type='submit']");
    private final By userRow = By.xpath(String.format("//div[text()='%s']", NEW_USERNAME));
   private final By deleteButton = By.xpath(String.format("//div[text()='%s']/ancestor::div[@role='row']//button[i[contains(@class,'bi-trash')]]", NEW_USERNAME));
    private final By confirmDelete = By.xpath("//button[contains(., 'Yes, Delete')]");
    public AdminUsersPage(WebDriver driver) {
        this.driver = driver;
        this.explicitWait = new waitHelper(driver, 30);
    }

    public void clickAdminButton() {
        explicitWait.waitForVisible(adminButton).click();
    }

    public int getRecordCount() {
        String textNumber = explicitWait.waitForVisible(recordNumber).getText();
        return Integer.parseInt(textNumber.replaceAll("\\D+", ""));
    }

    public void searchByUsername() {
        WebElement searchBoxElement = explicitWait.waitForVisible(searchBox);
        searchBoxElement.clear();
        searchBoxElement.sendKeys(NEW_USERNAME);
        driver.findElement(submitButton).click();
    }

    public void verifyRecordCountIncreased(int initialCount) {
        int currentCount = getRecordCount();
        Assert.assertEquals(currentCount, initialCount + 1);
        System.out.println("Record count increased by 1 = " + currentCount);
    }



    public void verifyUserInTable( ) {

        explicitWait.waitForVisible(userRow);
        Assert.assertTrue(driver.findElement(userRow).isDisplayed());
        System.out.println("user " + NEW_USERNAME+ " found in table");
    }

    public void deleteUser() {

        explicitWait.waitForClickable(deleteButton).click();
        explicitWait.waitForClickable(confirmDelete).click();

    }
    public void verifyUserDeleted( int initialCount) {

        driver.navigate().refresh();
        int currentCount = getRecordCount();
        Assert.assertEquals(currentCount, initialCount);
        System.out.println("Record count decreased by 1 = " + currentCount);
    }
}