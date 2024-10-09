package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.component.ScooterHeaderComponent;
import testData.enums.MetroStation;

import java.time.Duration;

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

    public FirstOrderFormPage setFirstNameField(String firstName) {
        getDriver().findElement(firstNameField).sendKeys(firstName);

        return this;
    }

    public FirstOrderFormPage setLastNameField(String lastName) {
        getDriver().findElement(lastNameField).sendKeys(lastName);

        return this;
    }

    public FirstOrderFormPage setOrderAddressField(String orderAddress) {
        getDriver().findElement(orderAddressField).sendKeys(orderAddress);

        return this;
    }

    public FirstOrderFormPage setMetroStationDropDown(MetroStation metroStation) {
        getDriver().findElement(metroStationDropDown).click();

        Actions actions = new Actions(getDriver());
        actions
                .sendKeys(metroStation.getStationName())
                .sendKeys(Keys.ARROW_DOWN, Keys.ENTER)
                .perform();

        return this;
    }

    public FirstOrderFormPage setPhoneNumberField(String phoneNumber) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(phoneNumberField));
        getDriver().findElement(phoneNumberField).sendKeys(phoneNumber);

        return this;
    }

    public SecondOrderFormPage clickNextButton() {
        getDriver().findElement(nextButton).click();

        return new SecondOrderFormPage(getDriver());
    }

    public FirstOrderFormPage pressTabOnKeyboard() {

        Actions actions = new Actions(getDriver());
        actions
                .sendKeys(Keys.TAB)
                .perform();

        return this;
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

    public SecondOrderFormPage fillFirstScooterOrderFormAndClickNextButton(String firstName, String lastName, String address,
                                                                           MetroStation metroStation, String phoneNumber) {
        return new FirstOrderFormPage(getDriver())
                .setFirstNameField(firstName)
                .setLastNameField(lastName)
                .setOrderAddressField(address)
                .setMetroStationDropDown(metroStation)
                .setPhoneNumberField(phoneNumber)
                .clickNextButton();
    }

    public ScooterHeaderComponent getHeaderComponent() {
        return new ScooterHeaderComponent(getDriver());
    }
}
