package Tests;
//import Pages.BaseClass;
//import Pages.AddUserPage;
//import Pages.AdminUsersPage;
//import Pages.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.Pages.AddUserPage;
import org.example.Pages.AdminUsersPage;
import org.example.Pages.BaseClass;
import org.example.Pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Tests extends BaseClass {
    private WebDriver driver;
    private LoginPage loginPage;
    private AdminUsersPage adminPage;
    private AddUserPage addUserPage;
//    private final String BASE_URL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
//    private final String ADMIN_USERNAME = "Admin";
//    private final String ADMIN_PASSWORD = "admin123";
//    private final String NEW_USERNAME = "TestUser_" + System.currentTimeMillis();
//    private final String NEW_PASSWORD = "SecurePass123!";
    private int initialRecordCount;

    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        loginPage = new LoginPage(driver);
        adminPage = new AdminUsersPage(driver);
        addUserPage = new AddUserPage(driver);
        driver.get(BASE_URL);
    }

    @Test(priority = 1)
    public void loginWithAdminCredentials() {
        loginPage.login();
    }

    @Test(priority = 2)
    public void GetNumberOfRecords() {
        adminPage.clickAdminButton();
        initialRecordCount = adminPage.getRecordCount();
        System.out.println("Initial record count: " + initialRecordCount);
    }

    @Test(priority = 3)
    public void createNewUserAndVerifyCountIncreased() {
        addUserPage.createNewUser();
        adminPage.verifyRecordCountIncreased(initialRecordCount);
    }

    @Test(priority = 4)
    public void searchForNewUserAndVerifyPresence() {
        adminPage.searchByUsername();
        adminPage.verifyUserInTable();
    }

    @Test(priority = 5)
    public void deleteUserAndVerifyCountDecreased() {

        adminPage.deleteUser();
        adminPage.verifyUserDeleted(initialRecordCount);
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}