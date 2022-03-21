package components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.WaitConfig;
import utils.WaitUtils;

public abstract class BasePortlet {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected final Logger logger;

    public BasePortlet(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, WaitConfig.WAIT_TIME_OUT);
        this.logger = LoggerFactory.getLogger(this.getClass());
        PageFactory.initElements(driver, this);
        WaitUtils.forPageLoaded(driver);
    }

}