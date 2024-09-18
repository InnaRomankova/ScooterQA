package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    //Кнопка, чтобы принять cookie "Да все привыкли"
    private final By acceptCookieButton = By.cssSelector("div[class^='App_CookieConsent'] button");

    //Кнопка "Заказать" внизу страницы
    private final By orderBottomButton = By.cssSelector("div[class^=Home]>button");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage clickAcceptCookieButton() {
        getDriver().findElement(acceptCookieButton).click();

        return this;
    }

    public FirstOrderFormPage clickOrderBottomButton() {
        getDriver().findElement(orderBottomButton).click();

        return new FirstOrderFormPage(getDriver());
    }
}
