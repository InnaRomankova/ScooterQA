package runner;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public abstract class BaseTest {

    private WebDriver driver;

    @Before
    public void beforeMethod() {
        driver = DriverManager.createDriver(Config.FIREFOX);
        if (driver != null) {
            driver.get(ProjectProperties.getPropertyValue("scooter.base.url"));
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
}
