package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {

    //Кнопка логотипа "Самокат"
    private final By scooterLogoButton = By.cssSelector("[alt='Scooter']");

    private final WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected WebDriver getDriver() {
        return driver;
    }

    public HomePage clickScooterLogoButton() {
        getDriver().findElement(scooterLogoButton).click();

        return new HomePage(getDriver());
    }

    public String getCurrentURL() {
        return getDriver().getCurrentUrl();
    }
}
