package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public abstract class BasePage {

    private final WebDriver driver;
    private final Map<Integer, WebDriverWait> waitMap = new HashMap<>();
    private Actions action;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected WebDriver getDriver() {
        return driver;
    }

    protected WebDriverWait getWait(int seconds) {
        return waitMap.computeIfAbsent(seconds, duration -> new WebDriverWait(driver, Duration.ofSeconds(duration)));
    }

    protected Actions getAction() {
        if(action == null) {
            action = new Actions(driver);
        }

        return action;
    }

    public String getCurrentURL() {
        return getDriver().getCurrentUrl();
    }
}
