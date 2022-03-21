package test;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import pages.LoginPage;
import ru.stqa.selenium.factory.LooseWebDriverPool;
import ru.stqa.selenium.factory.WebDriverPool;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import selenium.BasePage;

public abstract class TestBase {

    protected static String env;
    protected static WebDriver driver;
    protected static WebDriverPool driverPool = new LooseWebDriverPool();
    private static BasePage loginPage;

    @BeforeClass
    public static void initTestSuite() {
        env = "https://rvision-autotests.exadel.by";
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        loginPage = PageFactory.initElements(driver, BasePage.class);

    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }


}

