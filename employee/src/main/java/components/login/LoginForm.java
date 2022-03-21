package components.login;

import components.BasePortlet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;

public class LoginForm extends BasePortlet {

    public LoginPage clickLoginViaKeyClock() {
        driver.findElement(By.id("loginForm:login")).click();
        return new LoginPage(driver);
    }

    public LoginForm(WebDriver driver) {
        super(driver);
    }
}
