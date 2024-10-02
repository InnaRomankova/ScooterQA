package tests;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import pages.HomePage;
import pages.SecondOrderFormPage;
import runner.BaseTest;
import testData.MetroStation;
import testData.RentalPeriod;
import testData.ScooterColor;
import testData.dataProvider.ScooterOrderFormParameters;
import utils.TestUtils;

@RunWith(JUnitParamsRunner.class)
public class BuyingScooterTest extends BaseTest {

    @Test
    @Parameters(source = ScooterOrderFormParameters.class)
    public void testOrderScooterByBottomOrderButton(String firstName, String lastName, String address,
                                                    MetroStation metroStation, String phoneNumber, String deliveryDate,
                                                    RentalPeriod rentalPeriod, ScooterColor scooterColor, String comment) {
        new HomePage(getDriver())
                .clickAcceptCookieButton()
                .clickBottomOrderButton();

        SecondOrderFormPage confirmationList = TestUtils.fillScooterOrderForm(this, firstName, lastName, address,
                        metroStation, phoneNumber, deliveryDate, rentalPeriod, scooterColor, comment)
                .clickBottomOrderButton()
                .acceptOrderInDialogBox();

        Assert.assertTrue(confirmationList.getOrderConfirmationText().contains("Заказ оформлен"));
    }

    @Test
    @Parameters(source = ScooterOrderFormParameters.class)
    public void testOrderScooterByTopOrderButton(String firstName, String lastName, String address,
                                                 MetroStation metroStation, String phoneNumber, String deliveryDate,
                                                 RentalPeriod rentalPeriod, ScooterColor scooterColor, String comment) {
        new HomePage(getDriver())
                .clickAcceptCookieButton()
                .clickTopOrderButton();

        SecondOrderFormPage confirmationList = TestUtils.fillScooterOrderForm(this, firstName, lastName, address,
                        metroStation, phoneNumber, deliveryDate, rentalPeriod, scooterColor, comment)
                .clickBottomOrderButton()
                .acceptOrderInDialogBox();

        Assert.assertTrue(confirmationList.getOrderConfirmationText().contains("Заказ оформлен"));
    }
}
