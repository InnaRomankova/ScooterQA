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
import static runner.ProjectProperties.getPropertyValue;

@RunWith(JUnitParamsRunner.class)
public class NavigationTest extends BaseTest {

    private final String scooterBaseUrl = getPropertyValue("scooter.base.url");
    private final String scooterFirstOrderFormUrl = scooterBaseUrl + getPropertyValue("scooter.orderFormPage.endpoint");
    private final String scooterOrderStatusPageUrl = scooterBaseUrl + getPropertyValue("scooter.orderStatusPage.endpoint");
    private final String dzenBaseUrl = getPropertyValue("dzen.base.url");
    private final String yandexBaseUrl = getPropertyValue("yandex.base.url");

    @Test
    public void testStayAtHomePageByClickingOnScooterLogoOnHomePage() {
        String actualUrl;
        MainPage mainPage = new MainPage(getDriver());

        mainPage.getHeaderComponent().clickScooterLogoButton();
        actualUrl = mainPage.getCurrentURL();

        Assert.assertEquals(scooterBaseUrl, actualUrl);
    }

    @Test
    public void testGoToHomePageByClickingOnScooterLogoOnFirstOrderFormPage() {
        String actualUrl;
        FirstOrderFormPage firstOrderFormPage = new FirstOrderFormPage(getDriver());
        MainPage mainPage = new MainPage(getDriver());

        getDriver().navigate().to(scooterFirstOrderFormUrl);
        firstOrderFormPage.getHeaderComponent().clickScooterLogoButton();
        actualUrl = mainPage.getCurrentURL();

        Assert.assertEquals(scooterBaseUrl, actualUrl);
    }

    @Test
    @Parameters(source = FirstScooterOrderFormParameters.class)
    public void testGoToHomePageByClickingOnScooterLogoOnSecondOrderFormPage(String firstName, String lastName, String orderAddress,
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

        Assert.assertEquals(scooterBaseUrl, actualUrl);
    }

    @Test
    public void testGoToHomePageByClickingOnScooterLogoOnOrderStatusPage() {
        String actualUrl;
        OrderStatusPage orderStatusPage = new OrderStatusPage(getDriver());
        MainPage mainPage = new MainPage(getDriver());

        getDriver().navigate().to(scooterOrderStatusPageUrl);
        orderStatusPage.getHeaderComponent().clickScooterLogoButton();
        actualUrl = mainPage.getCurrentURL();

        Assert.assertEquals(scooterBaseUrl, actualUrl);
    }

    @Test
    public void testGoToYandexOrDzenPageByClickingYandexLogoOnHomePage() {
        String actualUrl;
        MainPage mainPage = new MainPage(getDriver());
        YandexDzenPage yandexDzenPage = new YandexDzenPage(getDriver());

        mainPage.getHeaderComponent().clickYandexLogoButtonAndSwitchToYandexDzenPage();
        actualUrl = yandexDzenPage.getCurrentURL();

        MatcherAssert.assertThat(actualUrl, anyOf(containsString(dzenBaseUrl), startsWith(yandexBaseUrl)));
    }

    @Test
    public void testGoToYandexOrDzenPageByClickingYandexLogoOnFirstOrderFormPage() {
        String actualUrl;
        FirstOrderFormPage firstOrderFormPage = new FirstOrderFormPage(getDriver());
        YandexDzenPage yandexDzenPage = new YandexDzenPage(getDriver());

        getDriver().navigate().to(scooterFirstOrderFormUrl);
        firstOrderFormPage.getHeaderComponent().clickYandexLogoButtonAndSwitchToYandexDzenPage();
        actualUrl = yandexDzenPage.getCurrentURL();

        MatcherAssert.assertThat(actualUrl, anyOf(containsString(dzenBaseUrl), startsWith(yandexBaseUrl)));
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

        MatcherAssert.assertThat(actualUrl, anyOf(containsString(dzenBaseUrl), startsWith(yandexBaseUrl)));
    }

    @Test
    public void testGoToYandexOrDzenPageByClickingYandexLogoOnOrderStatusPage() {
        String actualUrl;
        OrderStatusPage orderStatusPage = new OrderStatusPage(getDriver());
        YandexDzenPage yandexDzenPage = new YandexDzenPage(getDriver());

        getDriver().navigate().to(scooterOrderStatusPageUrl);
        orderStatusPage.getHeaderComponent().clickYandexLogoButtonAndSwitchToYandexDzenPage();
        actualUrl = yandexDzenPage.getCurrentURL();

        MatcherAssert.assertThat(actualUrl, anyOf(containsString(dzenBaseUrl), startsWith(yandexBaseUrl)));
    }
}
