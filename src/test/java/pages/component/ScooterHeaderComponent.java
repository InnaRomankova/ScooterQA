package pages.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

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

    public void clickScooterLogoButton() {
        getDriver().findElement(scooterLogoButton).click();
    }

    public void clickTopOrderButton() {
        getDriver().findElement(topOrderButton).click();
    }

    public void clickYandexLogoButtonAndSwitchToYandexDzenPage() {
        getDriver().findElement(yandexLogoButton).click();

        Object[] windowHandles = getDriver().getWindowHandles().toArray();
        getDriver().switchTo().window((String) windowHandles[1]);
        getDriver().navigate().refresh();
    }

    public void clickOrderStatusButton() {
        getWait(5).until(ExpectedConditions.elementToBeClickable(orderStatus));
        getDriver().findElement(orderStatus).click();
    }

    public void setOderNumberIntoHeaderInputOderNumberField(String orderNumber) {
        getWait(5).until(ExpectedConditions.visibilityOfElementLocated(inputOderNumberField));
        getDriver().findElement(inputOderNumberField).sendKeys(orderNumber);
    }

    public void clickGoButton() {
        getWait(5).until(ExpectedConditions.elementToBeClickable(goButton));
        getDriver().findElement(goButton).click();
    }
}
