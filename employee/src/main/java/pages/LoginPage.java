package pages;

import components.login.KeyclockForm;
import components.login.LoginForm;
import org.openqa.selenium.WebDriver;

public class LoginPage extends pages.Page {

    private static final String URL = "";

    public LoginForm loginForm;
    public KeyclockForm keycloak;


    public HomePage doLogin() {
        loginForm.clickLoginViaKeyClock();
        keycloak.setLogin("akomolov");
        keycloak.setPassword("February2022!");
        keycloak.clickLoginBtn();
        return new HomePage(driver);
    }

    /**
     * LoginPage constructor
     */
    public LoginPage(WebDriver driver) {
        super(driver);
        this.loginForm = new LoginForm(driver);
        this.keycloak = new KeyclockForm(driver);
    }

    @Override
    public LoginPage open(String env) {
        driver.get(env + URL);
        return new LoginPage(driver);
    }
}
