package selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.*;
import static java.util.concurrent.TimeUnit.SECONDS;

public class BasePage {

    protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected static Actions actions;

    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
        wait = new WebDriverWait(driver, 120);
        actions = new Actions(driver);
    }

    public static Integer totalCount = 0;
    public static String name = "";

    public final static String applyBtnXpath = ".//input[contains(@value,'Apply')]";
    public final static String resetBtnXpath = ".//input[contains(@value,'Reset')]";
    public final static String discardBtnXpath = ".//span[@class = 'nowrap']/input[@value='Discard']"; // .//span[contains(@class,'topHolder')]//input[contains(@value,'Discard')]
    public final static String addNewLinkXpath = ".//a[contains(text(),'Add new')]";
    protected final static String labelXpath = "//label";

    public final String loginInputID = "loginForm:login";
    @FindBy(id = loginInputID)
    private WebElement loginInput;

    public final String passwordInputID = "loginForm:password";
    @FindBy(id = passwordInputID)
    private WebElement passwordInput;

    public final String loginButtonXpath = "//input[@value='Login']";
    @FindBy(xpath = loginButtonXpath)
    private WebElement loginButton;

//    APPS
    public final static String appsMenuCSS="#mainMenuForm\\:appsMI";

    @FindBy(how = How.XPATH, using = applyBtnXpath)
    public static WebElement applyBtn;

    @FindBy(how = How.XPATH, using = resetBtnXpath)
    public static WebElement resetBtn;

    @FindBy(how = How.XPATH, using = discardBtnXpath)
    public static WebElement discardBtn;

    @FindBy(how = How.XPATH, using = addNewLinkXpath)
    public static WebElement addNewLink;

    @FindBy(how = How.XPATH, using = labelXpath)
    public static WebElement label;

    @FindBy(how = How.CSS, using = appsMenuCSS)
    public static WebElement appsMenu;


    public static void applyBtnClick() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(applyBtnXpath)));
        applyBtn.click();
        waitShadeDisappear();
    }

    public static void resetBtnClick() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(resetBtnXpath)));
        resetBtn.click();
        Thread.sleep(2000);
    }

    public static void addNewLinkClick() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(addNewLinkXpath)));
        addNewLink.click();
        waitShadeDisappear();
    }

    public static void generateName() {
        name = "0test" + Math.floor(Math.random() * 11111);
    }

    public static String getName() {
        return name;
    }

    public boolean checkLabel(String label) {
        return isElementDisplayed(By.xpath("//label[contains(text(),'" + label + "')]"));
    }

    public static String closeAlertAndGetItsText(boolean acceptNextAlert) {
        wait.until((ExpectedConditions.alertIsPresent()));
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        if (acceptNextAlert) {
            alert.accept();
            waitShadeDisappear();
        } else {
            alert.dismiss();
        }
        return alertText;
    }

    protected static void waitPageLoadsBylabelName(String labelName) {
        wait.until(ExpectedConditions.textToBePresentInElement(label, labelName));
    }

    public static boolean isElementDisplayed(By locatorKey) {
        try {
            driver.manage().timeouts().implicitlyWait(2, SECONDS);
            return driver.findElement(locatorKey).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        } finally {
            driver.manage().timeouts().implicitlyWait(30, SECONDS);
        }
    }

    public static void waitShadeDisappear() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//span[@class='preloader-text' and contains(text(), 'Please wait...')]/../../..")));
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void enterLogin(String login){loginInput.sendKeys(login);}

    public void enterPass(String pass){passwordInput.sendKeys(pass);}

    public void setLoginButtonClick() {
        loginButton.click();
    }

}