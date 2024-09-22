package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.RentalPeriod;

public class SecondOrderFormPage extends BasePage {

    //Поле "Когда привезти самокат"
    private final By dateDeliveryField = By.cssSelector("[placeholder$='Когда привезти самокат']");

    //Поле "Срок аренды"
    private final By rentalPeriodField = By.xpath("//div[contains(text(),'Срок аренды')]");

    //Чекбокс "черный жемчуг" в поле "Цвет самоката"
    private final By blackColorScooterCheckbox = By.xpath("//label[contains(text(),'чёрный жемчуг')]");

    //Поле "Комментарий для курьера"
    private final By commentField = By.cssSelector("[placeholder^='Комментарий']");

    //Кнопка "Заказать" внизу страницы
    private final By orderBottomButton = By.xpath("//button[text()='Назад']/..//button[text()='Заказать']");

    //Кнопка "Да" в диалоговом окне подтверждения заказа
    private final By acceptOrderInDialogBoxButton = By.xpath("//button[text()='Да']");

    public SecondOrderFormPage(WebDriver driver) {
        super(driver);
    }

    public SecondOrderFormPage setDateDeliveryField(String dateDelivery) {
        getDriver().findElement(dateDeliveryField).click();
        getDriver().findElement(By.xpath(String.format("//div[text()=%s]", dateDelivery))).click();

        return this;
    }

    public SecondOrderFormPage setRentalPeriodField(RentalPeriod rentalPeriod) {
        getDriver().findElement(rentalPeriodField).click();
        getDriver().findElement(By.xpath(String.format("//div[text()='%s']", rentalPeriod.getRentalPeriod()))).click();

        return this;
    }

    public SecondOrderFormPage clickBlackColorScooterCheckbox() {
        getDriver().findElement(blackColorScooterCheckbox).click();

        return this;
    }

    public SecondOrderFormPage setCommentField(String comment) {
        getDriver().findElement(commentField).sendKeys(comment);

        return this;
    }

    public SecondOrderFormPage clickOrderBottomButton() {
        getDriver().findElement(orderBottomButton).click();

        return this;
    }

    public SecondOrderFormPage acceptOrderInDialogBox() {
        getDriver().findElement(acceptOrderInDialogBoxButton).click();

        return this;
    }

    public String getOrderConfirmationText() {

        return getDriver().findElement(By.cssSelector("[class^='Order_ModalHeader']")).getText();
    }
}
