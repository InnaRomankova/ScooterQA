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

@RunWith(Parameterized.class)
public class NavigationTest extends BaseTest {

    private final String urlAddress;
    private final String pageName;
    private BaseProjectPage page;

    public NavigationTest(String urlAddress, String pageName) {
        this.urlAddress = urlAddress;
        this.pageName = pageName;
    }

    @Parameterized.Parameters
    public static Object[][] getURLData() {
        return new Object[][]{
                {"https://qa-scooter.praktikum-services.ru/", "HomePage"},
                {"https://qa-scooter.praktikum-services.ru/order", "FirstOrderFormPage"},
                {"https://qa-scooter.praktikum-services.ru/track?t=", "OrderStatusPage"}
        };
    }

    @Test
    public void testGoToHomePageByClickingOnScooterLogo() {
        String expectedURL = "https://qa-scooter.praktikum-services.ru/";
        getDriver().navigate().to(urlAddress);

        switch (pageName) {
            case "HomePage":
                page = new HomePage(getDriver());
                break;
            case "FirstOrderFormPage":
                page = new FirstOrderFormPage(getDriver());
                break;
            case "OrderStatusPage":
                page = new OrderStatusPage(getDriver());
                break;
        }
        String actualUrl = page
                .clickScooterLogoButton()
                .getCurrentURL();

        Assert.assertEquals(expectedURL, actualUrl);
    }
}
