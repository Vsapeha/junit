package components.login;

import components.BasePortlet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.HomePage;
import pages.LoginPage;

public class KeyclockForm extends BasePortlet {

    @FindBy(id = "username")
    private WebElement loginFld;

    @FindBy(id = "password")
    private WebElement passwordFld;

    @FindBy(id = "kc-login")
    private WebElement loginBtn;

    @FindBy(className = "kc-feedback-text")
    private WebElement errorMessage;

    public LoginPage setLogin(String login) {
        logger.info("Enter Login: " + login);
        loginFld.clear();
        loginFld.sendKeys(login);
        return new LoginPage(driver);
    }

    public LoginPage setPassword(String password) {
        logger.info("Enter Password: " + password);
        passwordFld.clear();
        passwordFld.sendKeys(password);
        return new LoginPage(driver);
    }

    public LoginPage clickLoginBtn() {
        driver.findElement(By.id("kc-login")).click();
        return new LoginPage(driver);
    }

    public HomePage submitLogin(String login, String password) {
        setLogin(login);
        setPassword(password);
        clickLoginBtn();
        return new HomePage(driver);
    }
//
//    public SystemUserHomePage submitLoginWithoutProfile(String login, String password) {
//        setLogin(login);
//        setPassword(password);
//        clickLoginBtn();
//        return new SystemUserHomePage(driver);
//    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    public KeyclockForm(WebDriver driver) {
        super(driver);
    }
}
