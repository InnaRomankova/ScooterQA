package utils;

import org.openqa.selenium.WebDriver;
import pages.FirstOrderFormPage;
import pages.SecondOrderFormPage;
import runner.BaseTest;
import testData.MetroStation;
import testData.RentalPeriod;
import testData.ScooterColor;

public class TestUtils {

    public static SecondOrderFormPage fillScooterOrderForm(BaseTest baseTest, String firstName, String lastName, String address,
                                                           MetroStation metroStation, String phoneNumber, String deliveryDate,
                                                           RentalPeriod rentalPeriod, ScooterColor scooterColor, String comment) {
        WebDriver driver = baseTest.getDriver();
        return new FirstOrderFormPage(driver)
                .setFirstNameField(firstName)
                .setLastNameField(lastName)
                .setOrderAddressField(address)
                .setMetroStationDropDown(metroStation)
                .setPhoneNumberField(phoneNumber)
                .clickNextButton()
                .setDateDeliveryField(deliveryDate)
                .setRentalPeriodField(rentalPeriod)
                .setScooterColorCheckbox(scooterColor)
                .setCommentField(comment);
    }
}
