package runner;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public abstract class BaseTest {

    private WebDriver driver;

    @Before
    public void beforeMethod() {
        driver = DriverManager.createDriver(Config.FIREFOX);
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @After
    public void afterMethod() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected WebDriver getDriver() {
        return driver;
    }
}
