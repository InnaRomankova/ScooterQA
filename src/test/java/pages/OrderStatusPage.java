package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.component.ScooterHeaderComponent;

import java.time.Duration;

public class OrderStatusPage extends BasePage {

    //Рисунок с надписью "Такого заказа нет"
    private final By orderNotFoundPicture = By.cssSelector("[alt='Not found']");

    //Поле для кнопки "Посмотреть"
    private final By inputFieldForLookButton = By.cssSelector("div[class^='Track_Form'] input[type='text']");

    //Кнопка "Посмотреть"
    private final By lookButton = By.xpath("//button[text()='Посмотреть']");

    public OrderStatusPage(WebDriver driver) {
        super(driver);
    }

    public boolean verifyOrderNotFoundPictureIsVisible() {
        return getDriver().findElement(orderNotFoundPicture).isDisplayed();
    }

    public OrderStatusPage setInputFieldForLookButton(String orderNumber) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5000));
        wait.until(ExpectedConditions.elementToBeClickable(inputFieldForLookButton));
        getDriver().findElement(inputFieldForLookButton).clear();
        getDriver().findElement(inputFieldForLookButton).sendKeys(orderNumber);

        return this;
    }

    public OrderStatusPage clickLookButton() {
        getDriver().findElement(lookButton).click();

        return this;
    }

    public ScooterHeaderComponent getHeaderComponent() {
        return new ScooterHeaderComponent(getDriver());
    }
}
