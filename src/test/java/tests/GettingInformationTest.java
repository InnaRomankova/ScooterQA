package tests;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.NoSuchElementException;
import pages.FirstOrderFormPage;
import pages.MainPage;
import pages.OrderStatusPage;
import pages.SecondOrderFormPage;
import runner.BaseTest;
import testData.MainPageData;
import testData.dataProvider.FirstScooterOrderFormParameters;
import testData.enums.MetroStation;
import testData.enums.RentalPeriod;
import testData.enums.ScooterColor;

import java.util.Map;

import static testData.UrlData.SCOOTER_FIRST_ORDER_FORM_URL;
import static testData.UrlData.SCOOTER_ORDER_STATUS_PAGE_URL;


@RunWith(JUnitParamsRunner.class)
public class GettingInformationTest extends BaseTest {

    private final String wrongOrderNumber = "wrongOrderNumber_123";

    @Test
    public void testGetAnswersToImportantQuestions() {
        Map<String, String> expectedAnswers = MainPageData.EXPECTED_ANSWERS;
        Map<String, String> actualAnswers;
        MainPage mainPage = new MainPage(getDriver());

        mainPage.clickAcceptCookieButton();
        actualAnswers = mainPage.getQuestionsAndAnswers(expectedAnswers.keySet());

        Assert.assertEquals(expectedAnswers, actualAnswers);
    }

    @Test
    public void testGetErrorMessageWhenClickOrderStatusButtonAndSetWrongOrderNumberOnMainPage() {
        boolean orderNotFoundPictureIsVisible;
        MainPage mainPage = new MainPage(getDriver());
        OrderStatusPage orderStatusPage = new OrderStatusPage(getDriver());

        mainPage.getHeaderComponent().clickOrderStatusButton();
        mainPage.getHeaderComponent().setOderNumberIntoHeaderInputOderNumberField(wrongOrderNumber);
        mainPage.getHeaderComponent().clickGoButton();
        orderNotFoundPictureIsVisible = orderStatusPage.verifyOrderNotFoundPictureIsVisible();

        Assert.assertTrue(orderNotFoundPictureIsVisible);
    }

    @Test
    public void testGetErrorMessageWhenClickOrderStatusButtonAndSetWrongOrderNumberOnFirstOrderFormPage() {
        boolean orderNotFoundPictureIsVisible;
        FirstOrderFormPage firstOrderFormPage = new FirstOrderFormPage(getDriver());
        OrderStatusPage orderStatusPage = new OrderStatusPage(getDriver());

        getDriver().navigate().to(SCOOTER_FIRST_ORDER_FORM_URL);
        firstOrderFormPage.getHeaderComponent().clickOrderStatusButton();
        firstOrderFormPage.getHeaderComponent().setOderNumberIntoHeaderInputOderNumberField(wrongOrderNumber);
        firstOrderFormPage.getHeaderComponent().clickGoButton();
        orderNotFoundPictureIsVisible = orderStatusPage.verifyOrderNotFoundPictureIsVisible();

        Assert.assertTrue(orderNotFoundPictureIsVisible);
    }

    @Test
    @Parameters(source = FirstScooterOrderFormParameters.class)
    public void testGetErrorMessageWhenClickOrderStatusButtonAndSetWrongOrderNumberOnSecondOrderFormPage(String firstName,
                                                                                                         String lastName,
                                                                                                         String orderAddress,
                                                                                                         MetroStation metroStation,
                                                                                                         String phoneNumber) {
        boolean orderNotFoundPictureIsVisible;
        MainPage mainPage = new MainPage(getDriver());
        FirstOrderFormPage firstOrderFormPage = new FirstOrderFormPage(getDriver());
        SecondOrderFormPage secondOrderFormPage = new SecondOrderFormPage(getDriver());
        OrderStatusPage orderStatusPage = new OrderStatusPage(getDriver());

        mainPage.clickAcceptCookieButton();
        mainPage.clickBottomOrderButton();
        firstOrderFormPage.fillFirstScooterOrderFormAndClickNextButton(firstName, lastName, orderAddress, metroStation, phoneNumber);
        secondOrderFormPage.getHeaderComponent().clickOrderStatusButton();
        secondOrderFormPage.getHeaderComponent().setOderNumberIntoHeaderInputOderNumberField(wrongOrderNumber);
        secondOrderFormPage.getHeaderComponent().clickGoButton();
        orderNotFoundPictureIsVisible = orderStatusPage.verifyOrderNotFoundPictureIsVisible();

        Assert.assertTrue(orderNotFoundPictureIsVisible);
    }

    @Test
    public void testGetErrorMessageWhenClickOrderStatusButtonAndSetWrongOrderNumberOnOrderStatusPage() {
        boolean orderNotFoundPictureIsVisible;
        OrderStatusPage orderStatusPage = new OrderStatusPage(getDriver());

        getDriver().navigate().to(SCOOTER_ORDER_STATUS_PAGE_URL);
        orderStatusPage.getHeaderComponent().clickOrderStatusButton();
        orderStatusPage.getHeaderComponent().setOderNumberIntoHeaderInputOderNumberField(wrongOrderNumber);
        orderStatusPage.getHeaderComponent().clickGoButton();
        orderNotFoundPictureIsVisible = orderStatusPage.verifyOrderNotFoundPictureIsVisible();

        Assert.assertTrue(orderNotFoundPictureIsVisible);
    }

    @Test
    @Parameters(source = FirstScooterOrderFormParameters.class)
    public void testGetErrorMessageWhenClickLookButtonAndSetWrongOrderNumber(String firstName, String lastName, String orderAddress,
                                                                             MetroStation metroStation, String phoneNumber) {
        boolean orderNotFoundPictureIsVisible;
        final String deliveryDate = "5";
        final RentalPeriod rentalPeriod = RentalPeriod.ONE_DAY;
        final ScooterColor scooterColor = ScooterColor.BLACK_PEARL;
        final String comment = "3-й этаж";
        MainPage mainPage = new MainPage(getDriver());
        FirstOrderFormPage firstOrderFormPage = new FirstOrderFormPage(getDriver());
        SecondOrderFormPage secondOrderFormPage = new SecondOrderFormPage(getDriver());
        OrderStatusPage orderStatusPage = new OrderStatusPage(getDriver());

        mainPage.clickAcceptCookieButton();
        mainPage.clickBottomOrderButton();
        firstOrderFormPage.fillFirstScooterOrderFormAndClickNextButton(firstName, lastName, orderAddress, metroStation, phoneNumber);
        secondOrderFormPage.fillSecondScooterOrderForm(deliveryDate, rentalPeriod, scooterColor, comment);
        secondOrderFormPage.clickBottomOrderButton();
        secondOrderFormPage.acceptOrderInDialogBox();
        secondOrderFormPage.clickLookStatusButton();

        try {
            orderStatusPage.verifyOrderNotFoundPictureIsVisible();
        } catch (NoSuchElementException e) {
            System.out.println("Рисунок с сообщением \"Такого заказа нет\", как и ожидалось, отсутствует на странице");
        }

        orderStatusPage.setInputFieldForLookButton(wrongOrderNumber);
        orderStatusPage.clickLookButton();
        orderNotFoundPictureIsVisible = orderStatusPage.verifyOrderNotFoundPictureIsVisible();

        Assert.assertTrue(orderNotFoundPictureIsVisible);
    }
}
