package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.component.ScooterHeaderComponent;
import testData.enums.MetroStation;

public class FirstOrderFormPage extends BasePage {

    //Поле "Имя"
    private final By firstNameField = By.cssSelector("[placeholder$='Имя']");

    //Поле "Фамилия"
    private final By lastNameField = By.cssSelector("[placeholder$='Фамилия']");

    //Поле "Адрес" доставки заказа
    private final By orderAddressField = By.cssSelector("[placeholder*='Адрес']");

    //Поле "Станция метро"
    private final By metroStationDropDown = By.cssSelector("[placeholder*='метро']");

    //Поле "Телефон"
    private final By phoneNumberField = By.cssSelector("[placeholder*='Телефон']");

    //Кнопка "Далее"
    private final By nextButton = By.xpath("//button[text()='Далее']");

    //Сообщение об ошибке для поля "Имя"
    private final By firstNameFieldErrorMessage = By.cssSelector("[placeholder$='Имя']+div");

    //Сообщение об ошибке для поля "Фамилия"
    private final By lastNameFieldErrorMessage = By.cssSelector("[placeholder$='Фамилия']+div");

    //Сообщение об ошибке для поля "Адрес: куда привезти заказ"
    private final By orderAddressFieldErrorMessage = By.cssSelector("[placeholder*='Адрес']+div");

    //Сообщение об ошибке для поля "Станция метро"
    private final By metroStationFieldErrorMessage = By.cssSelector("[class^='Order_MetroError']");

    //Сообщение об ошибке для поля "Телефон"
    private final By phoneNumberFieldErrorMessage = By.cssSelector("[placeholder*='Телефон']+div");

    public FirstOrderFormPage(WebDriver driver) {
        super(driver);
    }

    public void setFirstNameField(String firstName) {
        getDriver().findElement(firstNameField).sendKeys(firstName);
    }

    public void setLastNameField(String lastName) {
        getDriver().findElement(lastNameField).sendKeys(lastName);
    }

    public void setOrderAddressField(String orderAddress) {
        getDriver().findElement(orderAddressField).sendKeys(orderAddress);
    }

    public void setMetroStationDropDown(MetroStation metroStation) {
        getDriver().findElement(metroStationDropDown).click();

        getAction()
                .sendKeys(metroStation.getStationName())
                .sendKeys(Keys.ARROW_DOWN, Keys.ENTER)
                .perform();
    }

    public void setPhoneNumberField(String phoneNumber) {
        getWait(5).until(ExpectedConditions.visibilityOfElementLocated(phoneNumberField));
        getDriver().findElement(phoneNumberField).sendKeys(phoneNumber);
    }

    public void clickNextButton() {
        getDriver().findElement(nextButton).click();
    }

    public void pressTabOnKeyboard() {
        getAction()
                .sendKeys(Keys.TAB)
                .perform();
    }

    public String getFirstNameFieldErrorMessage() {
        return getDriver().findElement(firstNameFieldErrorMessage).getText();
    }

    public String getLastNameFieldErrorMessage() {
        return getDriver().findElement(lastNameFieldErrorMessage).getText();
    }

    public String getAddressFieldErrorMessage() {
        return getDriver().findElement(orderAddressFieldErrorMessage).getText();
    }

    public String clickNextButtonAndGetMetroStationFieldErrorMessage() {
        getDriver().findElement(nextButton).click();

        return getDriver().findElement(metroStationFieldErrorMessage).getText();
    }

    public String getPhoneNumberFieldErrorMessage() {
        return getDriver().findElement(phoneNumberFieldErrorMessage).getText();
    }

    public void fillFirstScooterOrderFormAndClickNextButton(String firstName, String lastName, String address,
                                                            MetroStation metroStation, String phoneNumber) {
        FirstOrderFormPage firstOrderFormPage = new FirstOrderFormPage(getDriver());

        firstOrderFormPage.setFirstNameField(firstName);
        firstOrderFormPage.setLastNameField(lastName);
        firstOrderFormPage.setOrderAddressField(address);
        firstOrderFormPage.setMetroStationDropDown(metroStation);
        firstOrderFormPage.setPhoneNumberField(phoneNumber);
        firstOrderFormPage.clickNextButton();
    }

    public ScooterHeaderComponent getHeaderComponent() {
        return new ScooterHeaderComponent(getDriver());
    }
}
