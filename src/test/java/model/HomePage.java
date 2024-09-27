package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    //Кнопка, чтобы принять cookie "Да все привыкли"
    private final By acceptCookieButton = By.cssSelector("div[class^='App_CookieConsent'] button");

    //Кнопка "Заказать" внизу страницы
    private final By bottomOrderButton = By.cssSelector("div[class^=Home]>button");

    //Кнопка "Заказать" вверху страницы
    private final By topOrderButton = By.xpath("//div[@class='Header_Nav__AGCXC']/button[text()='Заказать']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage clickAcceptCookieButton() {
        getDriver().findElement(acceptCookieButton).click();

        return this;
    }

    public FirstOrderFormPage clickBottomOrderButton() {
        getDriver().findElement(bottomOrderButton).click();

        return new FirstOrderFormPage(getDriver());
    }

    public FirstOrderFormPage clickTopOrderButton() {
        getDriver().findElement(topOrderButton).click();

        return new FirstOrderFormPage(getDriver());
    }
}
