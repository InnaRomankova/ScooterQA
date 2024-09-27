package tests;

import model.HomePage;
import model.SecondOrderFormPage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import runner.BaseTest;
import utils.MetroStation;
import utils.RentalPeriod;
import utils.ScooterColor;

@RunWith(Parameterized.class)
public class BuyScooterTest extends BaseTest {

    private final String firstName;
    private final String lastName;
    private final String address;
    private final MetroStation metroStation;
    private final String phoneNumber;
    private final String deliveryDate;
    private final RentalPeriod rentalPeriod;
    private final ScooterColor scooterColor;
    private final String comment;

    public BuyScooterTest(String firstName, String lastName, String address, MetroStation metroStation,
                          String phoneNumber, String deliveryDate, RentalPeriod rentalPeriod, ScooterColor color,
                          String comment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.deliveryDate = deliveryDate;
        this.rentalPeriod = rentalPeriod;
        this.scooterColor = color;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][]{
                {"Иван", "Иванов", "г.Москва, ул.Строителей, д.18, кв.6", MetroStation.KRASNOSELSKAYA, "84999011234",
                        "5", RentalPeriod.ONE_DAY, ScooterColor.BLACK_PEARL, "3-й этаж"},
                {"Петр", "Петров", "г.Москва, ул.Новокузнецкая, 4-15", MetroStation.PREOBRAZHENSKAYA_SQUARE,
                        "84999015555", "14", RentalPeriod.FIVE_DAYS, ScooterColor.GREY_HOPELESSNESS, "домофон не работает"}
        };
    }

    @Test
    public void testOrderScooterByBottomOrderButton() {
        SecondOrderFormPage confirmationList = new HomePage(getDriver())
                .clickAcceptCookieButton()
                .clickBottomOrderButton()
                .setFirstNameField(firstName)
                .setLastNameField(lastName)
                .setOrderAddressField(address)
                .setMetroStationDropDown(metroStation)
                .setPhoneNumberField(phoneNumber)
                .clickNextButton()
                .setDateDeliveryField(deliveryDate)
                .setRentalPeriodField(rentalPeriod)
                .setScooterColorCheckbox(scooterColor)
                .setCommentField(comment)
                .clickBottomOrderButton()
                .acceptOrderInDialogBox();

        Assert.assertTrue(confirmationList.getOrderConfirmationText().contains("Заказ оформлен"));
    }
}
