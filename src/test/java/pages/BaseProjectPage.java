package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BaseProjectPage extends BasePage {

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

    public <T extends BaseProjectPage> T clickOrderStatusButton(T page) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5000));
        wait.until(ExpectedConditions.elementToBeClickable(orderStatus));
        getDriver().findElement(orderStatus).click();

        return page;
    }

    public <T extends BaseProjectPage> T setOderNumberIntoHeaderInputOderNumberField(T page, String orderNumber) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputOderNumberField));
        getDriver().findElement(inputOderNumberField).sendKeys(orderNumber);

        return page;
    }

    public OrderStatusPage clickGoButton() {
        getDriver().findElement(goButton).click();

        return new OrderStatusPage(getDriver());
    }
}
