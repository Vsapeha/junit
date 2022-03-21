package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;
import static org.jsoup.helper.Validate.fail;


/**
 * Class provides common used waits
 */
public abstract class WaitUtils {

    public static WebDriverWait wait;
    private static final Logger logger = LoggerFactory.getLogger(WaitUtils.class);

    /**
     * Wait for page fully loads
     * @param driver WebDriver
     * @param <T> Class
     */
    public static <T> void forPageLoaded(WebDriver driver) {
        ExpectedCondition<Boolean> condition = dvr -> {
            assert dvr != null;
            return ((JavascriptExecutor) dvr)
                    .executeScript("return document.readyState")
                    .toString().equals("complete");
        };
        try {
            wait = new WebDriverWait(driver, WaitConfig.WAIT_TIME_OUT);
            wait.pollingEvery(Duration.ofMillis(WaitConfig.POLING_TIME));
            wait.until(condition);
        } catch (Throwable e) {
            logger.error("Page did not load within " + WaitConfig.WAIT_TIME_OUT + " sec");
            fail("Page with did not load within " + WaitConfig.WAIT_TIME_OUT + " sec");
        }
    }

    /**
     * Wait for text in element loads
     * @param driver WebDriver
     * @param el WebElement
     * @param text String Text in element
     */
    public static void waitForTextInElement(WebDriver driver, WebElement el, String text) {
        wait = new WebDriverWait(driver, WaitConfig.WAIT_TIME_OUT);
        wait.until(ExpectedConditions.textToBePresentInElement(el, text));
    }

    /**
     * Wait for text in element loads
     * @param driver WebDriver
     * @param text String Text in element
     */
    public static void forErrorMessage(WebDriver driver, String text) {
        wait = new WebDriverWait(driver, WaitConfig.WAIT_TIME_OUT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li[class^='errorMessage'], ul.errorMessage>li")));
        WebElement errorMessage = driver.findElement(By.cssSelector("li[class^='errorMessage'], ul.errorMessage>li"));
        logger.info("Error message: " + errorMessage.getText());
        wait.until(ExpectedConditions.textToBePresentInElement(errorMessage, text));
    }

    /**
     * Wait for text in element loads
     * @param driver WebDriver
     * @param text String Text in element
     */
    public static void forValidationMessage(WebDriver driver, String text) {
        wait = new WebDriverWait(driver, WaitConfig.WAIT_TIME_OUT);
        WebElement errorMessage = driver.findElement(By.cssSelector("li.errorMessage, ul.errorMessage>li"));
        logger.info("Validation message: " + errorMessage.getText());
        wait.until(ExpectedConditions.textToBePresentInElement(errorMessage, text));
    }


    /**
     * Wait for element be visible
     * @param driver WebDriver
     * @param el WebElement
     */
    public static void forElementBeVisible(WebDriver driver, WebElement el) {
        wait = new WebDriverWait(driver, WaitConfig.WAIT_TIME_OUT);
        wait.until(ExpectedConditions.visibilityOf(el));

    }

    public static void forPreloaderDisappear(WebDriver driver) {
        WebDriverWait waiter = new WebDriverWait(driver, WaitConfig.PRELOADER_TIME_OUT);
        waiter.until(ExpectedConditions.invisibilityOfElementLocated(By.className("preloader-text")));
        forPageLoaded(driver);
        logger.info("Preloader displayed: Please wait...");
    }

    public static void forNotificationMessage(WebDriver driver) {
        wait = new WebDriverWait(driver, WaitConfig.FLAKY_APPEARING_TIME_OUT);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("notificationMessage")));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("notificationMessage")));
            logger.info("Waiting for notification message");
        } catch (TimeoutException e) {
            logger.info("Notification message did not appeared");
        }
    }

    public static void forEditPageOpened(WebDriver driver) {
        wait = new WebDriverWait(driver, WaitConfig.WAIT_TIME_OUT);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("add-edit-panel-header")));
        logger.info("Waiting for Edit page opened");
    }

    public static void forTextInLabel(WebDriver driver, String label) {
        wait = new WebDriverWait(driver, WaitConfig.WAIT_TIME_OUT);
        wait.until(ExpectedConditions.textToBe(By.className("page-title"), label));
        logger.info("Page with title: " + label + " loaded");
    }

    public static void forInputNotReadOnly (WebDriver driver, WebElement el) {
        logger.info("Wait until field should be not readonly");
        wait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(el, "readonly", "true")));
    }

    public static void forInputNotDisabled(WebDriver driver, WebElement el) {
        logger.info("Wait until field should be not disabled");
        wait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(el, "disabled", "true")));
    }



}