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

    public void setDateDeliveryField(String dateDelivery) {
        getDriver().findElement(dateDeliveryField).click();
        getDriver().findElement(By.xpath(String.format("//div[text()=%s]", dateDelivery))).click();
    }

    public void setRentalPeriodField(RentalPeriod rentalPeriod) {
        getDriver().findElement(rentalPeriodField).click();
        getDriver().findElement(By.xpath(String.format("//div[text()='%s']", rentalPeriod.getRentalPeriod()))).click();
    }

    public void setScooterColorCheckbox(ScooterColor color) {
        if (color.equals(ScooterColor.BLACK_PEARL)) {
            getDriver().findElement(blackScooterColorCheckbox).click();
        } else getDriver().findElement(greyScooterColorCheckbox).click();
    }

    public void setCommentField(String comment) {
        getDriver().findElement(commentField).sendKeys(comment);
    }

    public void clickBottomOrderButton() {
        getDriver().findElement(orderBottomButton).click();
    }

    public void acceptOrderInDialogBox() {
        getDriver().findElement(acceptOrderInDialogBoxButton).click();
    }

    public String getOrderConfirmationText() {
        return getDriver().findElement(orderConfirmationTextInModalDialog).getText();
    }

    public void clickLookStatusButton() {
        getDriver().findElement(By.xpath("//button[text()='Посмотреть статус']")).click();
    }

    public void fillSecondScooterOrderForm(String deliveryDate, RentalPeriod rentalPeriod, ScooterColor scooterColor,
                                           String comment) {
        SecondOrderFormPage secondOrderFormPage = new SecondOrderFormPage(getDriver());
        secondOrderFormPage.setDateDeliveryField(deliveryDate);
        secondOrderFormPage.setRentalPeriodField(rentalPeriod);
        secondOrderFormPage.setScooterColorCheckbox(scooterColor);
        secondOrderFormPage.setCommentField(comment);
    }

    public ScooterHeaderComponent getHeaderComponent() {
        return new ScooterHeaderComponent(getDriver());
    }
}
