package tests;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import pages.HomePage;
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
        SecondOrderFormPage confirmationList = new HomePage(getDriver())
                .clickAcceptCookieButton()
                .clickBottomOrderButton()
                .fillFirstScooterOrderFormAndClickNextButton(firstName, lastName, address, metroStation, phoneNumber)
                .fillSecondScooterOrderForm(deliveryDate, rentalPeriod, scooterColor, comment)
                .clickBottomOrderButton()
                .acceptOrderInDialogBox();

        Assert.assertTrue(confirmationList.getOrderConfirmationText().contains("Заказ оформлен"));
    }

    @Test
    @Parameters(source = ScooterOrderFormParameters.class)
    public void testOrderScooterByTopOrderButton(String firstName, String lastName, String address,
                                                 MetroStation metroStation, String phoneNumber, String deliveryDate,
                                                 RentalPeriod rentalPeriod, ScooterColor scooterColor, String comment) {
        SecondOrderFormPage confirmationList = new HomePage(getDriver())
                .clickAcceptCookieButton()
                .getHeaderComponent()
                .clickTopOrderButton()
                .fillFirstScooterOrderFormAndClickNextButton(firstName, lastName, address, metroStation, phoneNumber)
                .fillSecondScooterOrderForm(deliveryDate, rentalPeriod, scooterColor, comment)
                .clickBottomOrderButton()
                .acceptOrderInDialogBox();

        Assert.assertTrue(confirmationList.getOrderConfirmationText().contains("Заказ оформлен"));
    }

    @Test
    @Parameters(source = InvalidFirstNameParameters.class)
    public void testGetErrorMessageInFirstNameField(String firstName) {
        String expectedErrorMessage = "Введите корректное имя";

        String actualErrorMessage = new HomePage(getDriver())
                .clickAcceptCookieButton()
                .clickBottomOrderButton()
                .setFirstNameField(firstName)
                .pressTabOnKeyboard()
                .getFirstNameFieldErrorMessage();

        Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test
    @Parameters(source = InvalidLastNameParameters.class)
    public void testGetErrorMessageInLastNameField(String lastName) {
        String expectedErrorMessage = "Введите корректную фамилию";

        String actualErrorMessage = new HomePage(getDriver())
                .clickAcceptCookieButton()
                .clickBottomOrderButton()
                .setLastNameField(lastName)
                .pressTabOnKeyboard()
                .getLastNameFieldErrorMessage();

        Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test
    @Parameters(source = InvalidAddressParameters.class)
    public void testGetErrorMessageAddressField(String address) {
        String expectedErrorMessage = "Введите корректный адрес";

        String actualErrorMessage = new HomePage(getDriver())
                .clickAcceptCookieButton()
                .clickBottomOrderButton()
                .setOrderAddressField(address)
                .pressTabOnKeyboard()
                .getAddressFieldErrorMessage();

        Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test
    @Parameters(source = FirstScooterOrderFormParameters.class)
    public void testGetErrorMessageMetroStationField(String firstName, String lastName, String address, MetroStation metroStation,
                                                     String phoneNumber) {
        String expectedErrorMessage = "Выберите станцию";

        String actualErrorMessage = new HomePage(getDriver())
                .clickAcceptCookieButton()
                .clickBottomOrderButton()
                .setFirstNameField(firstName)
                .setLastNameField(lastName)
                .setOrderAddressField(address)
                .setPhoneNumberField(phoneNumber)
                .clickNextButtonAndGetMetroStationFieldErrorMessage();

        Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test
    @Parameters(source = InvalidPhoneNumberParameters.class)
    public void testGetErrorMessagePhoneNumberField(String phoneNumber) {
        String expectedErrorMessage = "Введите корректный номер";

        String actualErrorMessage = new HomePage(getDriver())
                .clickAcceptCookieButton()
                .clickBottomOrderButton()
                .setPhoneNumberField(phoneNumber)
                .pressTabOnKeyboard()
                .getPhoneNumberFieldErrorMessage();

        Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
    }
}
