package tests;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import pages.FirstOrderFormPage;
import pages.HomePage;
import pages.OrderStatusPage;
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
        String actualUrl = new HomePage(getDriver())
                .getHeaderComponent()
                .clickScooterLogoButton()
                .getCurrentURL();

        Assert.assertEquals(scooterBaseUrl, actualUrl);
    }

    @Test
    public void testGoToHomePageByClickingOnScooterLogoOnFirstOrderFormPage() {
        getDriver().navigate().to(scooterFirstOrderFormUrl);

        String actualUrl = new FirstOrderFormPage(getDriver())
                .getHeaderComponent()
                .clickScooterLogoButton()
                .getCurrentURL();

        Assert.assertEquals(scooterBaseUrl, actualUrl);
    }

    @Test
    @Parameters(source = FirstScooterOrderFormParameters.class)
    public void testGoToHomePageByClickingOnScooterLogoOnSecondOrderFormPage(String firstName, String lastName, String orderAddress,
                                                                             MetroStation metroStation, String phoneNumber) {
        String actualUrl = new HomePage(getDriver())
                .clickAcceptCookieButton()
                .clickBottomOrderButton()
                .fillFirstScooterOrderFormAndClickNextButton(firstName, lastName, orderAddress, metroStation, phoneNumber)
                .getHeaderComponent()
                .clickScooterLogoButton()
                .getCurrentURL();

        Assert.assertEquals(scooterBaseUrl, actualUrl);
    }

    @Test
    public void testGoToHomePageByClickingOnScooterLogoOnOrderStatusPage() {
        getDriver().navigate().to(scooterOrderStatusPageUrl);

        String actualUrl = new OrderStatusPage(getDriver())
                .getHeaderComponent()
                .clickScooterLogoButton()
                .getCurrentURL();

        Assert.assertEquals(scooterBaseUrl, actualUrl);
    }

    @Test
    public void testGoToYandexOrDzenPageByClickingYandexLogoOnHomePage() {
        String actualUrl = new HomePage(getDriver())
                .getHeaderComponent()
                .clickYandexLogoButtonAndSwitchToYandexDzenPage()
                .getCurrentURL();

        MatcherAssert.assertThat(actualUrl, anyOf(containsString(dzenBaseUrl), startsWith(yandexBaseUrl)));
    }

    @Test
    public void testGoToYandexOrDzenPageByClickingYandexLogoOnFirstOrderFormPage() {
        getDriver().navigate().to(scooterFirstOrderFormUrl);

        String actualUrl = new FirstOrderFormPage(getDriver())
                .getHeaderComponent()
                .clickYandexLogoButtonAndSwitchToYandexDzenPage()
                .getCurrentURL();

        MatcherAssert.assertThat(actualUrl, anyOf(containsString(dzenBaseUrl), startsWith(yandexBaseUrl)));
    }

    @Test
    @Parameters(source = FirstScooterOrderFormParameters.class)
    public void testGoToYandexOrDzenPageByClickingYandexLogoOnSecondOrderFormPage(String firstName, String lastName, String orderAddress,
                                                                                  MetroStation metroStation, String phoneNumber) {
        String actualUrl = new HomePage(getDriver())
                .clickAcceptCookieButton()
                .clickBottomOrderButton()
                .fillFirstScooterOrderFormAndClickNextButton(firstName, lastName, orderAddress, metroStation, phoneNumber)
                .getHeaderComponent()
                .clickYandexLogoButtonAndSwitchToYandexDzenPage()
                .getCurrentURL();

        MatcherAssert.assertThat(actualUrl, anyOf(containsString(dzenBaseUrl), startsWith(yandexBaseUrl)));
    }

    @Test
    public void testGoToYandexOrDzenPageByClickingYandexLogoOnOrderStatusPage() {
        getDriver().navigate().to(scooterOrderStatusPageUrl);

        String actualUrl = new OrderStatusPage(getDriver())
                .getHeaderComponent()
                .clickYandexLogoButtonAndSwitchToYandexDzenPage()
                .getCurrentURL();

        MatcherAssert.assertThat(actualUrl, anyOf(containsString(dzenBaseUrl), startsWith(yandexBaseUrl)));
    }
}
