package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.component.ScooterHeaderComponent;

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

    public void setInputFieldForLookButton(String orderNumber) {
        getWait(5).until(ExpectedConditions.elementToBeClickable(inputFieldForLookButton));
        getDriver().findElement(inputFieldForLookButton).clear();
        getDriver().findElement(inputFieldForLookButton).sendKeys(orderNumber);
    }

    public void clickLookButton() {
        getDriver().findElement(lookButton).click();
    }

    public ScooterHeaderComponent getHeaderComponent() {
        return new ScooterHeaderComponent(getDriver());
    }
}
