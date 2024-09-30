package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import testData.MetroStation;

public class FirstOrderFormPage extends BaseProjectPage {

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


    public FirstOrderFormPage(WebDriver driver) {
        super(driver);
    }

    public FirstOrderFormPage setFirstNameField(String name) {
        getDriver().findElement(firstNameField).sendKeys(name);

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

    public FirstOrderFormPage setMetroStationDropDown(MetroStation station) {
        getDriver().findElement(metroStationDropDown).click();

        Actions actions = new Actions(getDriver());
        actions
                .sendKeys(station.getStationName())
                .sendKeys(Keys.ARROW_DOWN, Keys.ENTER)
                .perform();

        return this;
    }

    public FirstOrderFormPage setPhoneNumberField(String phoneNumber) {
        getDriver().findElement(phoneNumberField).sendKeys(phoneNumber);

        return this;
    }

    public SecondOrderFormPage clickNextButton() {
        getDriver().findElement(nextButton).click();

        return new SecondOrderFormPage(getDriver());
    }
}
