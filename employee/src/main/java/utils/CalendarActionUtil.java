package utils;

import lombok.SneakyThrows;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


public abstract class CalendarActionUtil {


    @SneakyThrows
    public static void enterDate(WebDriver driver, WebElement el, String date) {
        Map<String, String> formattedDate = DateFormatterUtil.formatDateForCalendar(date);
        el.click();
        WebDriverWait wait = new WebDriverWait(driver, WaitConfig.WAIT_TIME_OUT);
        // click change month/year on calendar
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("td[class='rf-cal-hdr-month']"))).click();
        // select month
        driver.findElements(By.xpath(".//td/div[text()='" + formattedDate.get("month") + "']")).get(0).click();
        // select year
        for (int i = 0; i < 3; i++) {
            List<WebElement> getListYears = driver.findElements(By.xpath(".//td/div[text()='" + formattedDate.get("year") + "']"));
            if (getListYears.size() > 0) {
                getListYears.get(0).click();
                break;
            }
            if(Integer.parseInt(formattedDate.get("year")) < Integer.parseInt(driver.findElement(By.xpath("//div[contains(@id,'DateEditorLayoutY0')]")).getText())) {
                wait.until(ExpectedConditions.elementToBeClickable(driver.findElements(By.xpath("//div[@id='']")).get(0))).click();
            } else if(Integer.parseInt(formattedDate.get("year")) < Integer.parseInt(driver.findElement(By.xpath("//div[contains(@id,'DateEditorLayoutY9')]")).getText())) {
                wait.until(ExpectedConditions.elementToBeClickable(driver.findElements(By.xpath("//div[@id='']")).get(1))).click();
            }
        }
        // click 'Ok'
        driver.findElement(By.xpath(".//td/div/span[text()='Ok']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//table[contains(@style, 'display: table') or contains(@style, 'position: absolute')]//td[text()='" + formattedDate.get("day") + "' and contains(@id, 'Day')]")));
        WebDriverWait waitShort = new WebDriverWait(driver, 15);

        // select date on calendar
        waitShort.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath(".//table[contains(@style, 'display: table') or contains(@style, 'position: absolute')]//td[text()='" + formattedDate.get("day") + "' and not(contains(@class, 'boundary-day')) and not(contains(@class, 'rf-cal-week')) and contains(@id, 'Cell')]")))
                .click();
        try {
            waitShort.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("td[class='rf-cal-hdr-month']")));
        } catch (TimeoutException e) {
            //workaround to close calendar with already preselected day of month (taht is not closed after clicking on already selected day)
            System.out.println("Caught TimeoutException...");
            driver.findElement(By.xpath(".//span[text()='Period']")).click();
        }
    }

    @SneakyThrows
    public static void enterDateToNthDatepicker(WebDriver driver, WebElement el, String date, int orderNoOfDatepicker) {
        Map<String, String> formattedDate = DateFormatterUtil.formatDateForCalendar(date);
        el.click();
        WebDriverWait wait = new WebDriverWait(driver, WaitConfig.WAIT_TIME_OUT);
        // click change month/year on calendar
        wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("(//td[@class='rf-cal-hdr-month'])[" +
                orderNoOfDatepicker + "]")))).click();
        // select month
        driver.findElements(By.xpath("(.//td/div[text()='" + formattedDate.get("month") + "'])[" +
                orderNoOfDatepicker + "]")).get(0).click();
        // select year
        for (int i = 0; i < 3; i++) {
            if (Integer.parseInt(formattedDate.get("year")) == LocalDate.now().getYear()) {
                break;
            }
            List<WebElement> getListYears = driver.findElements(By.xpath(("(.//td/div[text()='" + formattedDate.get("year") + "'])[" + orderNoOfDatepicker + "]")));
            if (getListYears.size() > 0) {
                getListYears.get(0).click();
                break;
            }
            if(Integer.parseInt(formattedDate.get("year")) < Integer.parseInt(driver.findElement(By.xpath("//div[contains(@id,'DateEditorLayoutY0')]")).getText())) {
                wait.until(ExpectedConditions.elementToBeClickable(driver.findElements(By.xpath("//div[@id='']")).get(0))).click();
            } else if(Integer.parseInt(formattedDate.get("year")) < Integer.parseInt(driver.findElement(By.xpath("////div[contains(@id,'DateEditorLayoutY9')]")).getText())) {
                wait.until(ExpectedConditions.elementToBeClickable(driver.findElements(By.xpath("//div[@id='']")).get(1))).click();
            }
        }
        // click 'Ok'
        driver.findElement(By.xpath("(.//td/div/span[text()='Ok'])[" + orderNoOfDatepicker + "]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(.//table[contains(@style, 'display: table') or contains(@style, 'position: absolute')]//td[text()='"
                + formattedDate.get("day") + "' and contains(@id, 'Day')])[" + orderNoOfDatepicker + "]")));
        WebDriverWait waitShort = new WebDriverWait(driver, 15);

        // select date on calendar
        waitShort.until(ExpectedConditions
                        .presenceOfElementLocated(By.xpath("(.//table[contains(@style, 'display: table') or " +
                                "contains(@style, 'position: absolute')]//td[text()='" + formattedDate.get("day") +
                                "' and not(contains(@class, 'boundary-day')) and not(contains(@class, 'rf-cal-week')) and contains(@id, 'Cell')])["
                                + orderNoOfDatepicker + "]")))
                .click();
        try {
            waitShort.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("td[class='rf-cal-hdr-month']")));
        } catch (TimeoutException e) {
            //workaround to close calendar with already preselected day of month (taht is not closed after clicking on already selected day)
            System.out.println("Caught TimeoutException...");
            driver.findElement(By.xpath(".//span[text()='Period']")).click();
        }
    }


    @SneakyThrows
    public static void enterDateWithShade(WebDriver driver, WebElement el, String date) {
        el.click();
        WaitUtils.forPreloaderDisappear(driver);
        WebDriverWait wait = new WebDriverWait(driver, WaitConfig.WAIT_TIME_OUT);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[@class='rf-cal-hdr-month']")));
        Map<String, String> formattedDate = DateFormatterUtil.formatDateForCalendar(date);

        // click change month/year on calendar
        driver.findElement(By.xpath("//td[@class='rf-cal-hdr-month']")).click();
        // select month
        driver.findElement(By.xpath("//td/div[text()='" + formattedDate.get("month") + "']")).click();
        // select year
        for (int i = 0; i < 3; i++) {
            List<WebElement> getListYears = driver.findElements(By.xpath("//td/div[text()='" + formattedDate.get("year") + "']"));
            if (getListYears.size() > 0) {
                getListYears.get(0).click();
                break;
            }
            if(Integer.parseInt(formattedDate.get("year")) < Integer.parseInt(driver.findElement(By.xpath("//div[contains(@id,'DateEditorLayoutY0')]")).getText())) {
                wait.until(ExpectedConditions.elementToBeClickable(driver.findElements(By.xpath("//div[@id='']")).get(0))).click();
            } else if(Integer.parseInt(formattedDate.get("year")) < Integer.parseInt(driver.findElement(By.xpath("//div[contains(@id,'DateEditorLayoutY9')]")).getText())) {
                wait.until(ExpectedConditions.elementToBeClickable(driver.findElements(By.xpath("//div[@id='']")).get(1))).click();
            }
        }
        // click 'Ok'
        driver.findElement(By.xpath("//td/div/span[text()='Ok']")).click();
        Thread.sleep(200);
        // select date on calendar
        driver.findElement(By.xpath("//td[text()='" + formattedDate.get("day") + "' and not(contains(@class, 'boundary-day')) and not(contains(@class, 'rf-cal-week')) and contains(@id, 'Cell')]")).click();
        WaitUtils.forPreloaderDisappear(driver);
    }


}