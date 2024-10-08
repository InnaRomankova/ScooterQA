package tests;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.NoSuchElementException;
import pages.FirstOrderFormPage;
import pages.HomePage;
import pages.OrderStatusPage;
import runner.BaseTest;
import testData.HomePageData;
import testData.dataProvider.FirstScooterOrderFormParameters;
import testData.enums.MetroStation;
import testData.enums.RentalPeriod;
import testData.enums.ScooterColor;

import java.util.Map;

import static runner.ProjectProperties.getPropertyValue;

@RunWith(JUnitParamsRunner.class)
public class GettingInformationTest extends BaseTest {

    private final String wrongOrderNumber = "wrongOrderNumber_123";
    private final String scooterBaseUrl = getPropertyValue("scooter.base.url");
    private final String scooterFirstOrderFormUrl = scooterBaseUrl + getPropertyValue("scooter.orderFormPage.endpoint");
    private final String scooterOrderStatusPageUrl = scooterBaseUrl + getPropertyValue("scooter.orderStatusPage.endpoint");

    @Test
    public void testGetAnswersToImportantQuestions() {
        Map<String, String> expectedAnswers = HomePageData.EXPECTED_ANSWERS;

        Map<String, String> actualAnswers = new HomePage(getDriver())
                .clickAcceptCookieButton()
                .getQuestionsAndAnswers(expectedAnswers.keySet());

        Assert.assertEquals(expectedAnswers, actualAnswers);
    }

    @Test
    public void testGetErrorMessageWhenClickOrderStatusButtonAndSetWrongOrderNumberOnHomePage() {
        Boolean orderNotFoundPictureIsVisible = new HomePage(getDriver())
                .getHeaderComponent()
                .clickOrderStatusButton()
                .setOderNumberIntoHeaderInputOderNumberField(wrongOrderNumber)
                .clickGoButton()
                .verifyOrderNotFoundPictureIsVisible();

        Assert.assertEquals(true, orderNotFoundPictureIsVisible);
    }

    @Test
    public void testGetErrorMessageWhenClickOrderStatusButtonAndSetWrongOrderNumberOnFirstOrderFormPage() {
        getDriver().navigate().to(scooterFirstOrderFormUrl);

        Boolean orderNotFoundPictureIsVisible = new FirstOrderFormPage(getDriver())
                .getHeaderComponent()
                .clickOrderStatusButton()
                .setOderNumberIntoHeaderInputOderNumberField(wrongOrderNumber)
                .clickGoButton()
                .verifyOrderNotFoundPictureIsVisible();

        Assert.assertEquals(true, orderNotFoundPictureIsVisible);
    }

    @Test
    @Parameters(source = FirstScooterOrderFormParameters.class)
    public void testGetErrorMessageWhenClickOrderStatusButtonAndSetWrongOrderNumberOnSecondOrderFormPage(String firstName,
                                                                                                         String lastName,
                                                                                                         String orderAddress,
                                                                                                         MetroStation metroStation,
                                                                                                         String phoneNumber) {
        Boolean orderNotFoundPictureIsVisible = new HomePage(getDriver())
                .clickAcceptCookieButton()
                .clickBottomOrderButton()
                .fillFirstScooterOrderFormAndClickNextButton(firstName, lastName, orderAddress, metroStation, phoneNumber)
                .getHeaderComponent()
                .clickOrderStatusButton()
                .setOderNumberIntoHeaderInputOderNumberField(wrongOrderNumber)
                .clickGoButton()
                .verifyOrderNotFoundPictureIsVisible();

        Assert.assertEquals(true, orderNotFoundPictureIsVisible);
    }

    @Test
    public void testGetErrorMessageWhenClickOrderStatusButtonAndSetWrongOrderNumberOnOrderStatusPage() {
        getDriver().navigate().to(scooterOrderStatusPageUrl);

        Boolean orderNotFoundPictureIsVisible = new OrderStatusPage(getDriver())
                .getHeaderComponent()
                .clickOrderStatusButton()
                .setOderNumberIntoHeaderInputOderNumberField(wrongOrderNumber)
                .clickGoButton()
                .verifyOrderNotFoundPictureIsVisible();

        Assert.assertEquals(true, orderNotFoundPictureIsVisible);
    }

    @Test
    @Parameters(source = FirstScooterOrderFormParameters.class)
    public void testGetErrorMessageWhenClickLookButtonAndSetWrongOrderNumber(String firstName, String lastName, String orderAddress,
                                                                             MetroStation metroStation, String phoneNumber) {
        final String deliveryDate = "5";
        final RentalPeriod rentalPeriod = RentalPeriod.ONE_DAY;
        final ScooterColor scooterColor = ScooterColor.BLACK_PEARL;
        final String comment = "3-й этаж";

        new HomePage(getDriver())
                .clickAcceptCookieButton()
                .clickBottomOrderButton()
                .fillFirstScooterOrderFormAndClickNextButton(firstName, lastName, orderAddress, metroStation, phoneNumber)
                .fillSecondScooterOrderForm(deliveryDate, rentalPeriod, scooterColor, comment)
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
