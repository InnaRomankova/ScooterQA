package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class BaseProjectPage extends BasePage {

    //Кнопка логотипа "Самокат"
    private final By scooterLogoButton = By.cssSelector("[alt='Scooter']");

    //Кнопка "Заказать" вверху страницы
    private final By topOrderButton = By.xpath("//div[@class='Header_Nav__AGCXC']/button[text()='Заказать']");

    //Кнопка логотипа "Яндекс"
    private final By yandexLogoButton = By.cssSelector("[alt='Yandex']");

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

    public YandexDzenPage clickYandexLogoButtonAndSwitchToYandexDzenPage() {
        getDriver().findElement(yandexLogoButton).click();

        Object[] windowHandles = getDriver().getWindowHandles().toArray();
        getDriver().switchTo().window((String) windowHandles[1]);
        getDriver().navigate().refresh();

        return new YandexDzenPage(getDriver());
    }
}
