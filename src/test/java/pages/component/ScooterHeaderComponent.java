package pages.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;

import java.time.Duration;

public class ScooterHeaderComponent extends BasePage {

    //Кнопка логотипа "Самокат"
    private final By scooterLogoButton = By.cssSelector("[alt='Scooter']");

    //Кнопка "Заказать" вверху страницы
    private final By topOrderButton = By.xpath("//div[@class='Header_Nav__AGCXC']/button[text()='Заказать']");

    //Кнопка логотипа "Яндекс"
    private final By yandexLogoButton = By.cssSelector("[alt='Yandex']");

    //Кнопка "Статус заказа"
    private final By orderStatus = By.xpath("//button[text()='Статус заказа']");

    //Поле "Введите номер заказа"
    private final By inputOderNumberField = By.cssSelector("div[class^='Header'] input[type='text']");

    //Кнопка "Go"
    private final By goButton = By.xpath("//button[text()='Go!']");

    public ScooterHeaderComponent(WebDriver driver) {
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

    public ScooterHeaderComponent clickOrderStatusButton() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5000));
        wait.until(ExpectedConditions.elementToBeClickable(orderStatus));
        getDriver().findElement(orderStatus).click();

        return this;
    }

    public ScooterHeaderComponent setOderNumberIntoHeaderInputOderNumberField(String orderNumber) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputOderNumberField));
        getDriver().findElement(inputOderNumberField).sendKeys(orderNumber);

        return this;
    }

    public OrderStatusPage clickGoButton() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5000));
        wait.until(ExpectedConditions.elementToBeClickable(goButton));
        getDriver().findElement(goButton).click();

        return new OrderStatusPage(getDriver());
    }
}
