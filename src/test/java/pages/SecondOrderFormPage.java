package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.component.ScooterHeaderComponent;
import testData.enums.RentalPeriod;
import testData.enums.ScooterColor;

public class SecondOrderFormPage extends BasePage {

    //Поле "Когда привезти самокат"
    private final By dateDeliveryField = By.cssSelector("[placeholder$='Когда привезти самокат']");

    //Поле "Срок аренды"
    private final By rentalPeriodField = By.xpath("//div[contains(text(),'Срок аренды')]");

    //Чекбокс "черный жемчуг" в поле "Цвет самоката"
    private final By blackScooterColorCheckbox = By.id("black");

    //Чекбокс "серая безысходность" в поле "Цвет самоката"
    private final By greyScooterColorCheckbox = By.id("grey");

    //Поле "Комментарий для курьера"
    private final By commentField = By.cssSelector("[placeholder^='Комментарий']");

    //Кнопка "Заказать" внизу страницы
    private final By orderBottomButton = By.xpath("//button[text()='Назад']/..//button[text()='Заказать']");

    //Кнопка "Да" в диалоговом окне подтверждения заказа
    private final By acceptOrderInDialogBoxButton = By.xpath("//button[text()='Да']");

    //Текст подтверждения заказа в диалоговом окне
    private final By orderConfirmationTextInModalDialog = By.cssSelector("[class^='Order_ModalHeader']");

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

    public SecondOrderFormPage setScooterColorCheckbox(ScooterColor color) {
        if (color.equals(ScooterColor.BLACK_PEARL)) {
            getDriver().findElement(blackScooterColorCheckbox).click();
        } else getDriver().findElement(greyScooterColorCheckbox).click();

        return this;
    }

    public SecondOrderFormPage setCommentField(String comment) {
        getDriver().findElement(commentField).sendKeys(comment);

        return this;
    }

    public SecondOrderFormPage clickBottomOrderButton() {
        getDriver().findElement(orderBottomButton).click();

        return this;
    }

    public SecondOrderFormPage acceptOrderInDialogBox() {
        getDriver().findElement(acceptOrderInDialogBoxButton).click();

        return this;
    }

    public String getOrderConfirmationText() {
        return getDriver().findElement(orderConfirmationTextInModalDialog).getText();
    }

    public OrderStatusPage clickLookStatusButton() {
        getDriver().findElement(By.xpath("//button[text()='Посмотреть статус']")).click();

        return new OrderStatusPage(getDriver());
    }

    public SecondOrderFormPage fillSecondScooterOrderForm(String deliveryDate, RentalPeriod rentalPeriod, ScooterColor scooterColor,
                                                          String comment) {
        return new SecondOrderFormPage(getDriver())
                .setDateDeliveryField(deliveryDate)
                .setRentalPeriodField(rentalPeriod)
                .setScooterColorCheckbox(scooterColor)
                .setCommentField(comment);
    }

    public ScooterHeaderComponent getHeaderComponent() {
        return new ScooterHeaderComponent(getDriver());
    }
}
