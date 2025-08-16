//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.openqa.selenium.*;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
//import org.testng.annotations.*;
//
//import java.time.Duration;
//
//public class TestCases {
//    WebDriver driver;
//    WebDriverWait wait;
//    String baseUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
//    String username = "Admin";
//    String password = "admin123";
//    String employeeUsername = "Sara123";
//    String employeePassword = "12QwaszX";
//    int initialRecordCount;
//
//    @BeforeTest
//    public void setUp() {
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//        driver.manage().window().maximize();
//        driver.get(baseUrl);
//    }
//
//    private WebElement waitForVisible(By locator) {
//        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
//    }
//
//    private int getRecordCount() {
//        String textNumber = waitForVisible(By.xpath("//span[@class='oxd-text oxd-text--span']")).getText();
//        return Integer.parseInt(textNumber.replaceAll("\\D+", ""));
//    }
//
//    @Test(priority = 1)
//    public void loginTest() {
//        waitForVisible(By.name("username")).sendKeys(username);
//        driver.findElement(By.name("password")).sendKeys(password);
//        driver.findElement(By.xpath("//button[@type='submit']")).click();
//
//        wait.until(ExpectedConditions.urlContains("dashboard"));
//        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"), "Login failed");
//        System.out.println("✅ Login successful");
//    }
//
//    @Test(priority = 2)
//    public void captureAdminRecords() {
//        waitForVisible(By.xpath("//span[text()='Admin']")).click();
//        initialRecordCount = getRecordCount();
//        System.out.println("Initial record count: " + initialRecordCount);
//    }
//
//    @Test(priority = 3)
//    public void addUserData() {
//        driver.findElement(By.cssSelector("button[class='oxd-button oxd-button--medium oxd-button--secondary']")).click();
//
//        // Select User Role
//        waitForVisible(By.xpath("//label[text()='User Role']/../following-sibling::div")).click();
//        waitForVisible(By.xpath("//div[@role='listbox']//div[normalize-space()='Admin']")).click();
//
//        // Select Employee Name
//        WebElement empField = waitForVisible(By.cssSelector("input[placeholder='Type for hints...']"));
//        empField.sendKeys("a");
//        waitForVisible(By.xpath("//div[@role='listbox']//div[2]")).click();
//
//        // Select Status
//        waitForVisible(By.xpath("//label[text()='Status']/../following-sibling::div")).click();
//        waitForVisible(By.xpath("//div[@role='listbox']//div[normalize-space()='Enabled']")).click();
//
//        // Fill Username and Password
//        driver.findElement(By.xpath("//label[text()='Username']/../following-sibling::div/input")).sendKeys(employeeUsername);
//        driver.findElement(By.xpath("//label[text()='Password']/../following-sibling::div/input")).sendKeys(employeePassword);
//        driver.findElement(By.xpath("//label[text()='Confirm Password']/../following-sibling::div/input")).sendKeys(employeePassword);
//
//        driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();
//        wait.until(ExpectedConditions.invisibilityOfElementLocated(
//                By.xpath("//button[normalize-space()='Save' and @disabled]")
//        ));
//        System.out.println("User added: " + employeeUsername);
//    }
//
//    @Test(priority = 4)
//    public void verifyAddedUserData() {
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        driver.navigate().to("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
//        wait.until(ExpectedConditions.invisibilityOfElementLocated(
//                By.xpath("//button[normalize-space()='Save' and @disabled]")
//        ));
//
//        Assert.assertEquals(getRecordCount(), initialRecordCount + 1, "Record count did not increase by 1");
//        System.out.println("Record count increased by 1");
//    }
//
//    @Test(priority = 5)
//    public void searchByNewUserData() {
//        WebElement searchBox = waitForVisible(By.xpath("//label[text()='Username']/../following-sibling::div/input"));
//        searchBox.clear();
//        searchBox.sendKeys(employeeUsername);
//        driver.findElement(By.cssSelector("button[type='submit']")).click();
//    }
//
//    @Test(priority = 6)
//    public void deleteUserData() {
//        waitForVisible(By.xpath("//div[text()='" + employeeUsername + "']/../..//button[contains(@class,'danger')]")).click();
//
//
//        driver.findElement(By.xpath("//button[normalize-space()='Yes, Delete']")).click();
//        System.out.println("User deleted: " + employeeUsername);
//    }
//
//    @Test(priority = 7)
//    public void verifyRemovedUserData() {
//        driver.navigate().refresh();
//        int currentCount = getRecordCount();
//        Assert.assertEquals(currentCount, initialRecordCount, "Record count did not decrease by 1");
//
//    }
//
//    @AfterTest
//    public void tearDown() {
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//        driver.quit();
//    }
//}
