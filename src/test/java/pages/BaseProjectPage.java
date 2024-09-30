package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class BaseProjectPage extends BasePage {

    //Кнопка логотипа "Самокат"
    private final By scooterLogoButton = By.cssSelector("[alt='Scooter']");

    //Кнопка "Заказать" вверху страницы
    private final By topOrderButton = By.xpath("//div[@class='Header_Nav__AGCXC']/button[text()='Заказать']");

    public BaseProjectPage(WebDriver driver) {
        super(driver);
    }

    public HomePage clickScooterLogoButton() {
        getDriver().findElement(scooterLogoButton).click();

        return new HomePage(getDriver());
    }

    public FirstOrderFormPage clickTopOrderButton() {
        getDriver().findElement(topOrderButton).click();

        return new FirstOrderFormPage(getDriver());
    }
}
