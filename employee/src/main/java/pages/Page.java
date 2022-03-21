package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitUtils;

/**
 * Abstract class representation of a Page in the UI. Page object pattern
 */
public abstract class Page {

    protected WebDriver driver;
    protected WebDriverWait wait;

    protected Page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 120);
        PageFactory.initElements(driver, this);
        WaitUtils.forPageLoaded(driver);
    }

    protected abstract <T> T open(String env);

    public String getTitle() {
        return driver.getTitle();
    }

}
