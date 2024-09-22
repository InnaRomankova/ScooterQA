package tests;

import model.HomePage;
import model.SecondOrderFormPage;
import org.junit.Assert;
import org.junit.Test;
import runner.BaseTest;
import utils.MetroStation;

public class BuyScooterTest extends BaseTest {

    @Test
    public void testOrderScooter() {
        SecondOrderFormPage confirmationList = new HomePage(getDriver())
                .clickAcceptCookieButton()
                .clickOrderBottomButton()
                .setFirstNameField("Иван")
                .setLastNameField("Иванов")
                .setOrderAddressField("Москва")
                .setMetroStationDropDown(MetroStation.SOKOLNIKI)
                .setPhoneNumberField("375441234567")
                .clickNextButton()
                .setDateDeliveryField("10")
                .setRentalPeriodField("сутки")
                .clickBlackColorScooterCheckbox()
                .setCommentField("3-й этаж")
                .clickOrderBottomButton()
                .acceptOrderInDialogBox();

        Assert.assertTrue(confirmationList.getOrderConfirmationText().contains("Заказ оформлен"));
    }
}
