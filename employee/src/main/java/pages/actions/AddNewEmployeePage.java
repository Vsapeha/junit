package pages.actions;

import objects.Employee;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.Page;
import utils.CalendarActionUtil;
import utils.WaitUtils;


public class AddNewEmployeePage extends Page {

    private static final String URL = "/rms-web/pages/resources/resource-add-fast.jsf?add_new=true";

    @FindBy(id = "formEdit:first_name")
    private WebElement firstNameInput;

    @FindBy(id = "formEdit:lastName")
    private WebElement lastNameInput;

    @FindBy(id = "formEdit:input_russian_name")
    private WebElement nativeNameInput;

    @FindBy(id = "formEdit:newFirstStartDateInputDate")
    private WebElement firstStartDateInput;

    @FindBy(id = "formEdit:office")
    private WebElement officeSelect;

    @FindBy(id = "formEdit:hr")
    private WebElement hrSelect;

    @FindBy(id = "formEdit:substitute_hr") //
    private WebElement hrSubstituteSelect;

    @FindBy(id = "formEdit:employmentType")
    private WebElement employmentTypeSelect;

    @FindBy(id = "formEdit:organization")
    private WebElement departmentSelect;

    @FindBy(id = "formEdit:position")
    private WebElement positionSelect;

    @FindBy(id = "formEdit:location")
    private WebElement whereaboutsSelect;

    @FindBy(id = "formEdit:rate")
    private WebElement normalHoursPerWeekInput;

    @FindBy(id = "formEdit:overtimeRate")
    private WebElement overtimeHoursPerWeekInput;

    @FindBy(id = "formEdit:resource_active_status")
    private WebElement activeCheckbox;

    @FindBy(className = "buttonSaveContinue")
    private WebElement saveAndContinueBtn;

    @FindBy(css = "input[id='formEdit:saveButtonBottom']")
    private WebElement saveAndSendEmailBtn;

    @FindBy(css = "input[id='formEdit:sendAndContinueButtonTop']")
    private WebElement sendEmailAndContinueBtn;

    @FindBy(css = "input[id='formEdit:sendAndCloseButtonBottom']")
    private WebElement sendEmailAndCloseBtn;

    @FindBy(id = "formEdit:saveButtonBottom")
    private WebElement saveAndCloseBtn;

    @FindBy(id = "formEdit:cancelButtonBottom")
    private WebElement discardBtn;

    @FindBy(id = "formEdit:personalProfileResourceHrEditLink")
    private WebElement editEmployeeHrLink;

    @FindBy(id = "formResourceHrModalPanel:resourceHrList:newResourceHr")
    private WebElement newHrSelect;

    @FindBy(css = "input[id$='InputDate'][id^='formResourceHrModalPanel:resourceHrList:']")
    private WebElement hRStartDateInput;

    @FindBy(css = "a[id^='formResourceHrModalPanel:resourceHrList:'][title='Save']")
    private WebElement hRSaveSelected;

    @FindBy(css = "input[id^='formResourceHrModalPanel:'][value='Save & Close']")
    private WebElement hRSaveAndCloseButton;

    @FindBy(xpath = "//table[@class='buttonHolder']//input[@value='Save & Close']")
    private WebElement saveAndCloseButton;


    public AddNewEmployeePage setFirstName(String name) {
        firstNameInput.clear();
        firstNameInput.sendKeys(name);
        return this;
    }

    public AddNewEmployeePage setLastName(String name) {
        lastNameInput.clear();
        lastNameInput.sendKeys(name);
        return this;
    }

    public AddNewEmployeePage setNativeName(String name) {
        nativeNameInput.clear();
        nativeNameInput.sendKeys(name);
        return this;
    }

    public AddNewEmployeePage setFirstStartDate(String date) {
        WaitUtils.forElementBeVisible(driver, firstStartDateInput);
        CalendarActionUtil.enterDateWithShade(driver, firstStartDateInput, date);
        WaitUtils.forPreloaderDisappear(driver);
        return this;
    }

    public AddNewEmployeePage setOffice(String office) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOf(officeSelect));
        new Select(officeSelect).selectByVisibleText(office);
        WaitUtils.forPreloaderDisappear(driver);
        return this;
    }

    public AddNewEmployeePage setPersonalHr(String hr) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOf(hrSelect));
        new Select(hrSelect).selectByVisibleText(hr);
        return this;
    }

    public AddNewEmployeePage setSubstituteHr(String hr) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOf(hrSubstituteSelect));
        new Select(hrSubstituteSelect).selectByVisibleText(hr);
        return this;
    }

    public AddNewEmployeePage setEmploymentType(String type) {
        new Select(employmentTypeSelect).selectByVisibleText(type);
        return this;
    }

    public AddNewEmployeePage setDepartment(String department) {
        new Select(departmentSelect).selectByVisibleText(department);
        return this;
    }

    public AddNewEmployeePage setPosition(String position) {
        new Select(positionSelect).selectByVisibleText(position);
        return this;
    }

    public AddNewEmployeePage setWhereabouts(String whereabout) {
        new Select(whereaboutsSelect).selectByVisibleText(whereabout);
        return this;
    }

    public AddNewEmployeePage setNormalHoursPerWeek(String hours) {
        normalHoursPerWeekInput.clear();
        normalHoursPerWeekInput.sendKeys(hours);
        return this;
    }

    public AddNewEmployeePage setOvertimeHoursPerWeek(String hours) {
        overtimeHoursPerWeekInput.clear();
        overtimeHoursPerWeekInput.sendKeys(hours);
        return this;
    }

    public AddNewEmployeePage checkActive(boolean value) {
        activeCheckbox.click();
        return this;
    }

    public WebElement getSaveAndCloseBtn() {
        return saveAndCloseBtn;
    }

    public AddNewEmployeePage fastCreateEmployee(Employee employee) {
        setFirstName(employee.getFirstName())
                .setLastName(employee.getLastName())
                .setNativeName(employee.getNativeName())
                .setFirstStartDate(employee.getStartDate())
                .setOffice(employee.getOffice())
                .setPersonalHr(employee.getPersonalHr())
                .setSubstituteHr(employee.getPersonalHr())
                .setEmploymentType(employee.getEmploymentType())
                .setDepartment(employee.getDepartment())
                .setPosition(employee.getPosition())
                .setWhereabouts(employee.getWhereabout())
                .setNormalHoursPerWeek(employee.getNormalHoursPerWeek())
                .setOvertimeHoursPerWeek(employee.getOvertimeHoursPerWeek())
                .saveAndCloseBtnClick();
        return new AddNewEmployeePage(driver);
    }

    public AddNewEmployeePage saveAndCloseBtnClick() {
        saveAndCloseBtn.click();
        WaitUtils.forPreloaderDisappear(driver);
        return new AddNewEmployeePage(driver);
    }



    /**
     * AddNewEmployeePage constructor
     *
     * @param driver Webdriver
     */
    public AddNewEmployeePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public AddNewEmployeePage open(String env) {
        driver.get(env + URL);
        return new AddNewEmployeePage(driver);
    }


}
