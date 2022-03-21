package pages;

//import components.MainMenu;
//import components.employee.SendABadgePopUp;
//import components.home.HolidayPortlet;
//import components.home.PersonalInformationPortlet;
//import components.home.TimeOffPortlet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends Page {

    private static final String URL = "/rms-web/pages/index.jsf";

//    public MainMenu mainMenu;
//    public TimeOffPortlet timeOffPortlet;
//    public PersonalInformationPortlet personalInformationPortlet;
//    public SendABadgePopUp sendABadgePopUp;
//    public HolidayPortlet holidayPortlet;


    @FindBy(className = "version")
    private WebElement appVersion;

    /**
     * HomePage constructor
     */
    public HomePage(WebDriver driver) {
        super(driver);
//        mainMenu = new MainMenu(driver);
//        timeOffPortlet = new TimeOffPortlet(driver);
//        personalInformationPortlet = new PersonalInformationPortlet(driver);
//        sendABadgePopUp = new SendABadgePopUp(driver);
//        holidayPortlet = new HolidayPortlet(driver);
    }

    public WebElement getAppVersion() {
        return appVersion;
    }

    @Override
    public HomePage open(String env) {
        driver.get(env + URL);
        return this;
    }
}
