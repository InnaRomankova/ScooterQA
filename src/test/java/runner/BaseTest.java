package runner;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import pages.BaseProjectPage;
import pages.FirstOrderFormPage;
import pages.HomePage;
import pages.OrderStatusPage;
import testData.PageName;

public abstract class BaseTest {

    private WebDriver driver;

    @Before
    public void beforeMethod() {
        driver = DriverManager.createDriver(Config.FIREFOX);
        if (driver != null) {
            driver.get("https://qa-scooter.praktikum-services.ru/");
        }
    }

    @After
    public void afterMethod() {
        if (driver != null) {
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public BaseProjectPage getCurrentPageInstance(PageName pageName) {
        switch (pageName) {
            case HOME_PAGE:
                return new HomePage(getDriver());

            case FIRST_ORDER_FORM_PAGE:
                return new FirstOrderFormPage(getDriver());

            case ORDER_STATUS_PAGE:
                return new OrderStatusPage(getDriver());
        }
        return null;
    }
}
