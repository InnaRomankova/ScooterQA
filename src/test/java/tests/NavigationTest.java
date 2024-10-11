package tests;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import pages.*;
import runner.BaseTest;
import testData.dataProvider.FirstScooterOrderFormParameters;
import testData.enums.MetroStation;

import static org.hamcrest.CoreMatchers.*;
import static testData.UrlData.*;

@RunWith(JUnitParamsRunner.class)
public class NavigationTest extends BaseTest {

    @Test
    public void testStayAtMainPageByClickingOnScooterLogoOnMainPage() {
        String actualUrl;
        MainPage mainPage = new MainPage(getDriver());

        mainPage.getHeaderComponent().clickScooterLogoButton();
        actualUrl = mainPage.getCurrentURL();

        Assert.assertEquals(SCOOTER_BASE_URL, actualUrl);
    }

    @Test
    public void testGoToMainPageByClickingOnScooterLogoOnFirstOrderFormPage() {
        String actualUrl;
        FirstOrderFormPage firstOrderFormPage = new FirstOrderFormPage(getDriver());
        MainPage mainPage = new MainPage(getDriver());

        getDriver().navigate().to(SCOOTER_FIRST_ORDER_FORM_URL);
        firstOrderFormPage.getHeaderComponent().clickScooterLogoButton();
        actualUrl = mainPage.getCurrentURL();

        Assert.assertEquals(SCOOTER_BASE_URL, actualUrl);
    }

    @Test
    @Parameters(source = FirstScooterOrderFormParameters.class)
    public void testGoToMainPageByClickingOnScooterLogoOnSecondOrderFormPage(String firstName, String lastName, String orderAddress,
                                                                             MetroStation metroStation, String phoneNumber) {
        String actualUrl;
        MainPage mainPage = new MainPage(getDriver());
        FirstOrderFormPage firstOrderFormPage = new FirstOrderFormPage(getDriver());
        SecondOrderFormPage secondOrderFormPage = new SecondOrderFormPage(getDriver());

        mainPage.clickAcceptCookieButton();
        mainPage.clickBottomOrderButton();
        firstOrderFormPage.fillFirstScooterOrderFormAndClickNextButton(firstName, lastName, orderAddress, metroStation, phoneNumber);
        secondOrderFormPage.getHeaderComponent().clickScooterLogoButton();
        actualUrl = mainPage.getCurrentURL();

        Assert.assertEquals(SCOOTER_BASE_URL, actualUrl);
    }

    @Test
    public void testGoToMainPageByClickingOnScooterLogoOnOrderStatusPage() {
        String actualUrl;
        OrderStatusPage orderStatusPage = new OrderStatusPage(getDriver());
        MainPage mainPage = new MainPage(getDriver());

        getDriver().navigate().to(SCOOTER_ORDER_STATUS_PAGE_URL);
        orderStatusPage.getHeaderComponent().clickScooterLogoButton();
        actualUrl = mainPage.getCurrentURL();

        Assert.assertEquals(SCOOTER_BASE_URL, actualUrl);
    }

    @Test
    public void testGoToYandexOrDzenPageByClickingYandexLogoOnMainPage() {
        String actualUrl;
        MainPage mainPage = new MainPage(getDriver());
        YandexDzenPage yandexDzenPage = new YandexDzenPage(getDriver());

        mainPage.getHeaderComponent().clickYandexLogoButtonAndSwitchToYandexDzenPage();
        actualUrl = yandexDzenPage.getCurrentURL();

        MatcherAssert.assertThat(actualUrl, anyOf(containsString(DZEN_BASE_URL), startsWith(YANDEX_BASE_URL)));
    }

    @Test
    public void testGoToYandexOrDzenPageByClickingYandexLogoOnFirstOrderFormPage() {
        String actualUrl;
        FirstOrderFormPage firstOrderFormPage = new FirstOrderFormPage(getDriver());
        YandexDzenPage yandexDzenPage = new YandexDzenPage(getDriver());

        getDriver().navigate().to(SCOOTER_FIRST_ORDER_FORM_URL);
        firstOrderFormPage.getHeaderComponent().clickYandexLogoButtonAndSwitchToYandexDzenPage();
        actualUrl = yandexDzenPage.getCurrentURL();

        MatcherAssert.assertThat(actualUrl, anyOf(containsString(DZEN_BASE_URL), startsWith(YANDEX_BASE_URL)));
    }

    @Test
    @Parameters(source = FirstScooterOrderFormParameters.class)
    public void testGoToYandexOrDzenPageByClickingYandexLogoOnSecondOrderFormPage(String firstName, String lastName, String orderAddress,
                                                                                  MetroStation metroStation, String phoneNumber) {
        String actualUrl;
        MainPage mainPage = new MainPage(getDriver());
        FirstOrderFormPage firstOrderFormPage = new FirstOrderFormPage(getDriver());
        SecondOrderFormPage secondOrderFormPage = new SecondOrderFormPage(getDriver());
        YandexDzenPage yandexDzenPage = new YandexDzenPage(getDriver());

        mainPage.clickAcceptCookieButton();
        mainPage.clickBottomOrderButton();
        firstOrderFormPage.fillFirstScooterOrderFormAndClickNextButton(firstName, lastName, orderAddress, metroStation, phoneNumber);
        secondOrderFormPage.getHeaderComponent().clickYandexLogoButtonAndSwitchToYandexDzenPage();
        actualUrl = yandexDzenPage.getCurrentURL();

        MatcherAssert.assertThat(actualUrl, anyOf(containsString(DZEN_BASE_URL), startsWith(YANDEX_BASE_URL)));
    }

    @Test
    public void testGoToYandexOrDzenPageByClickingYandexLogoOnOrderStatusPage() {
        String actualUrl;
        OrderStatusPage orderStatusPage = new OrderStatusPage(getDriver());
        YandexDzenPage yandexDzenPage = new YandexDzenPage(getDriver());

        getDriver().navigate().to(SCOOTER_ORDER_STATUS_PAGE_URL);
        orderStatusPage.getHeaderComponent().clickYandexLogoButtonAndSwitchToYandexDzenPage();
        actualUrl = yandexDzenPage.getCurrentURL();

        MatcherAssert.assertThat(actualUrl, anyOf(containsString(DZEN_BASE_URL), startsWith(YANDEX_BASE_URL)));
    }
}
