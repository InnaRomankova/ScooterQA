package tests;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import pages.FirstOrderFormPage;
import pages.MainPage;
import pages.SecondOrderFormPage;
import runner.BaseTest;
import testData.dataProvider.*;
import testData.enums.MetroStation;
import testData.enums.RentalPeriod;
import testData.enums.ScooterColor;

@RunWith(JUnitParamsRunner.class)
public class BuyingScooterTest extends BaseTest {

    @Test
    @Parameters(source = ScooterOrderFormParameters.class)
    public void testOrderScooterByBottomOrderButton(String firstName, String lastName, String address,
                                                    MetroStation metroStation, String phoneNumber, String deliveryDate,
                                                    RentalPeriod rentalPeriod, ScooterColor scooterColor, String comment) {
        MainPage mainPage = new MainPage(getDriver());
        FirstOrderFormPage firstOrderFormPage = new FirstOrderFormPage(getDriver());
        SecondOrderFormPage secondOrderFormPage = new SecondOrderFormPage(getDriver());

        mainPage.clickAcceptCookieButton();
        mainPage.clickBottomOrderButton();
        firstOrderFormPage.fillFirstScooterOrderFormAndClickNextButton(firstName, lastName, address, metroStation, phoneNumber);
        secondOrderFormPage.fillSecondScooterOrderForm(deliveryDate, rentalPeriod, scooterColor, comment);
        secondOrderFormPage.clickBottomOrderButton();
        secondOrderFormPage.acceptOrderInDialogBox();

        Assert.assertTrue(secondOrderFormPage.getOrderConfirmationText().contains("Заказ оформлен"));
    }

    @Test
    @Parameters(source = ScooterOrderFormParameters.class)
    public void testOrderScooterByTopOrderButton(String firstName, String lastName, String address,
                                                 MetroStation metroStation, String phoneNumber, String deliveryDate,
                                                 RentalPeriod rentalPeriod, ScooterColor scooterColor, String comment) {
        MainPage mainPage = new MainPage(getDriver());
        FirstOrderFormPage firstOrderFormPage = new FirstOrderFormPage(getDriver());
        SecondOrderFormPage secondOrderFormPage = new SecondOrderFormPage(getDriver());

        mainPage.clickAcceptCookieButton();
        mainPage.getHeaderComponent().clickTopOrderButton();
        firstOrderFormPage.fillFirstScooterOrderFormAndClickNextButton(firstName, lastName, address, metroStation, phoneNumber);
        secondOrderFormPage.fillSecondScooterOrderForm(deliveryDate, rentalPeriod, scooterColor, comment);
        secondOrderFormPage.clickBottomOrderButton();
        secondOrderFormPage.acceptOrderInDialogBox();

        Assert.assertTrue(secondOrderFormPage.getOrderConfirmationText().contains("Заказ оформлен"));
    }

    @Test
    @Parameters(source = InvalidFirstNameParameters.class)
    public void testGetErrorMessageInFirstNameField(String firstName) {
        String expectedErrorMessage = "Введите корректное имя";
        String actualErrorMessage;
        MainPage mainPage = new MainPage(getDriver());
        FirstOrderFormPage firstOrderFormPage = new FirstOrderFormPage(getDriver());

        mainPage.clickAcceptCookieButton();
        mainPage.clickBottomOrderButton();
        firstOrderFormPage.setFirstNameField(firstName);
        firstOrderFormPage.pressTabOnKeyboard();
        actualErrorMessage = firstOrderFormPage.getFirstNameFieldErrorMessage();

        Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test
    @Parameters(source = InvalidLastNameParameters.class)
    public void testGetErrorMessageInLastNameField(String lastName) {
        String expectedErrorMessage = "Введите корректную фамилию";
        String actualErrorMessage;
        MainPage mainPage = new MainPage(getDriver());
        FirstOrderFormPage firstOrderFormPage = new FirstOrderFormPage(getDriver());

        mainPage.clickAcceptCookieButton();
        mainPage.clickBottomOrderButton();
        firstOrderFormPage.setLastNameField(lastName);
        firstOrderFormPage.pressTabOnKeyboard();
        actualErrorMessage = firstOrderFormPage.getLastNameFieldErrorMessage();

        Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test
    @Parameters(source = InvalidAddressParameters.class)
    public void testGetErrorMessageAddressField(String address) {
        String expectedErrorMessage = "Введите корректный адрес";
        String actualErrorMessage;
        MainPage mainPage = new MainPage(getDriver());
        FirstOrderFormPage firstOrderFormPage = new FirstOrderFormPage(getDriver());

        mainPage.clickAcceptCookieButton();
        mainPage.clickBottomOrderButton();
        firstOrderFormPage.setOrderAddressField(address);
        firstOrderFormPage.pressTabOnKeyboard();
        actualErrorMessage = firstOrderFormPage.getAddressFieldErrorMessage();

        Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test
    @Parameters(source = FirstScooterOrderFormParameters.class)
    public void testGetErrorMessageMetroStationField(String firstName, String lastName, String address, MetroStation metroStation,
                                                     String phoneNumber) {
        String expectedErrorMessage = "Выберите станцию";
        String actualErrorMessage;
        MainPage mainPage = new MainPage(getDriver());
        FirstOrderFormPage firstOrderFormPage = new FirstOrderFormPage(getDriver());

        mainPage.clickAcceptCookieButton();
        mainPage.clickBottomOrderButton();
        firstOrderFormPage.setFirstNameField(firstName);
        firstOrderFormPage.setLastNameField(lastName);
        firstOrderFormPage.setOrderAddressField(address);
        firstOrderFormPage.setPhoneNumberField(phoneNumber);
        actualErrorMessage = firstOrderFormPage.clickNextButtonAndGetMetroStationFieldErrorMessage();

        Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test
    @Parameters(source = InvalidPhoneNumberParameters.class)
    public void testGetErrorMessagePhoneNumberField(String phoneNumber) {
        String expectedErrorMessage = "Введите корректный номер";
        String actualErrorMessage;
        MainPage mainPage = new MainPage(getDriver());
        FirstOrderFormPage firstOrderFormPage = new FirstOrderFormPage(getDriver());

        mainPage.clickAcceptCookieButton();
        mainPage.clickBottomOrderButton();
        firstOrderFormPage.setPhoneNumberField(phoneNumber);
        firstOrderFormPage.pressTabOnKeyboard();
        actualErrorMessage = firstOrderFormPage.getPhoneNumberFieldErrorMessage();

        Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
    }
}
