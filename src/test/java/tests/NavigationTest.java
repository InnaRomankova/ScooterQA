package tests;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import runner.BaseTest;
import testData.PageName;
import testData.dataProvider.URLDataParameters;

@RunWith(JUnitParamsRunner.class)
public class NavigationTest extends BaseTest {

    @Test
    @Parameters(source = URLDataParameters.class)
    public void testGoToHomePageByClickingOnScooterLogo(String urlAddress, PageName pageName) {
        String expectedURL = "https://qa-scooter.praktikum-services.ru/";
        getDriver().navigate().to(urlAddress);

        String actualUrl = getCurrentPageInstance(pageName)
                .clickScooterLogoButton()
                .getCurrentURL();

        Assert.assertEquals(expectedURL, actualUrl);
    }

    @Test
    @Parameters(source = URLDataParameters.class)
    public void testGoToDzenByClickingYandexLogo(String urlAddress, PageName pageName) {
        String expectedURL = "https://dzen.ru/?yredirect=true";
        getDriver().navigate().to(urlAddress);

        String actualUrl = getCurrentPageInstance(pageName)
                .clickYandexLogoButtonAndSwitchToYandexDzenPage()
                .getCurrentURL();

        Assert.assertEquals(expectedURL, actualUrl);
    }
}
