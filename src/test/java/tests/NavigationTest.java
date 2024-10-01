package tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.BaseProjectPage;
import pages.FirstOrderFormPage;
import pages.HomePage;
import pages.OrderStatusPage;
import runner.BaseTest;
import testData.PageName;

@RunWith(Parameterized.class)
public class NavigationTest extends BaseTest {

    private final String urlAddress;
    private final PageName pageName;

    public NavigationTest(String urlAddress, PageName pageName) {
        this.urlAddress = urlAddress;
        this.pageName = pageName;
    }

    @Parameterized.Parameters
    public static Object[][] getURLData() {
        return new Object[][]{
                {"https://qa-scooter.praktikum-services.ru/", PageName.HOME_PAGE},
                {"https://qa-scooter.praktikum-services.ru/order", PageName.FIRST_ORDER_FORM_PAGE},
                {"https://qa-scooter.praktikum-services.ru/track?t=", PageName.ORDER_STATUS_PAGE}
        };
    }

    public BaseProjectPage getCurrentPageInstance() {
        switch (pageName) {
            case HOME_PAGE:
                return new HomePage(getDriver());

            case FIRST_ORDER_FORM_PAGE:
                return new FirstOrderFormPage(getDriver());

            case ORDER_STATUS_PAGE:
                return new OrderStatusPage(getDriver());
        }
        return null;
    }

    @Test
    public void testGoToHomePageByClickingOnScooterLogo() {
        String expectedURL = "https://qa-scooter.praktikum-services.ru/";
        getDriver().navigate().to(urlAddress);

        String actualUrl = getCurrentPageInstance()
                .clickScooterLogoButton()
                .getCurrentURL();

        Assert.assertEquals(expectedURL, actualUrl);
    }

    @Test
    public void testGoToDzenByClickingYandexLogo() {
        String expectedURL = "https://dzen.ru/?yredirect=true";
        getDriver().navigate().to(urlAddress);

        String actualUrl = getCurrentPageInstance()
                .clickYandexLogoButtonAndSwitchToYandexDzenPage()
                .getCurrentURL();

        Assert.assertEquals(expectedURL, actualUrl);
    }
}
