package test;

import objects.Employee;
import org.junit.*;
import pages.LoginPage;
import pages.actions.AddNewEmployeePage;

public class EmployeeTest extends test.TestBase {
    private objects.Employee employee;
    private AddNewEmployeePage addNewEmployeePage;


    @Before
    public void before() {
    LoginPage loginPage = new LoginPage(driver);
    loginPage.open(env);
    loginPage.doLogin();
    employee = Employee.builder().build();
    addNewEmployeePage = new AddNewEmployeePage(driver);

    }


    @Test
    public void createEmployeeTest() throws Exception {
        addNewEmployeePage
                .open(env)
                .fastCreateEmployee(employee);
    }

    @After
    public void afterMethod() {
        System.out.println("Code executes after each test method");
    }
}