package tests;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.NoSuchElementException;
import pages.HomePage;
import pages.OrderStatusPage;
import runner.BaseTest;
import testData.*;
import testData.dataProvider.URLDataParameters;
import utils.TestUtils;

import java.util.Map;

@RunWith(JUnitParamsRunner.class)
public class GettingInformationTest extends BaseTest {

    final String wrongOrderNumber = "wrongOrderNumber_123";

    @Test
    public void testGetAnswersToImportantQuestions() {
        Map<String, String> expectedAnswers = HomePageData.EXPECTED_ANSWERS;

        Map<String, String> actualAnswers = new HomePage(getDriver())
                .clickAcceptCookieButton()
                .getQuestionsAndAnswers(expectedAnswers.keySet());

        Assert.assertEquals(expectedAnswers, actualAnswers);
    }

    @Test
    @Parameters(source = URLDataParameters.class)
    public void testGetErrorMessageWhenClickOrderStatusButtonAndSetWrongOrderNumber(String urlAddress, PageName pageName) {
        getDriver().navigate().to(urlAddress);

        Boolean orderNotFoundPictureIsVisible = getCurrentPageInstance(pageName)
                .clickOrderStatusButton(getCurrentPageInstance(pageName))
                .setOderNumberIntoHeaderInputOderNumberField(getCurrentPageInstance(pageName), wrongOrderNumber)
                .clickGoButton()
                .verifyOrderNotFoundPictureIsVisible();

        Assert.assertEquals(true, orderNotFoundPictureIsVisible);
    }

    @Test
    public void testGetErrorMessageWhenClickLookButtonAndSetWrongOrderNumber() {
        final String firstName = "Иван";
        final String lastName = "Иванов";
        final String address = "г.Москва, ул.Строителей, д.18, кв.6";
        final MetroStation metroStation = MetroStation.KRASNOSELSKAYA;
        final String phoneNumber = "84999011234";
        final String deliveryDate = "5";
        final RentalPeriod rentalPeriod = RentalPeriod.ONE_DAY;
        final ScooterColor scooterColor = ScooterColor.BLACK_PEARL;
        final String comment = "3-й этаж";

        new HomePage(getDriver())
                .clickAcceptCookieButton()
                .clickBottomOrderButton();

        TestUtils.fillScooterOrderForm(this, firstName, lastName, address,
                        metroStation, phoneNumber, deliveryDate, rentalPeriod, scooterColor, comment)
                .clickBottomOrderButton()
                .acceptOrderInDialogBox()
                .clickLookStatusButton();

        try {
            new OrderStatusPage(getDriver())
                    .verifyOrderNotFoundPictureIsVisible();
        } catch (NoSuchElementException e) {
            System.out.println("Рисунок с сообщением \"Такого заказа нет\", как и ожидалось, отсутствует на странице");
        }

        Boolean orderNotFoundPictureIsVisible = new OrderStatusPage(getDriver())
                .setInputFieldForLookButton(wrongOrderNumber)
                .clickLookButton()
                .verifyOrderNotFoundPictureIsVisible();

        Assert.assertEquals(true, orderNotFoundPictureIsVisible);
    }
}
