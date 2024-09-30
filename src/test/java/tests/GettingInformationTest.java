package tests;

import org.junit.Assert;
import org.junit.Test;
import pages.HomePage;
import runner.BaseTest;
import testData.HomePageData;

import java.util.Map;

public class GettingInformationTest extends BaseTest {

    @Test
    public void testGetAnswersToImportantQuestions() {
        Map<String, String> expectedAnswers = HomePageData.EXPECTED_ANSWERS;

        Map<String, String> actualAnswers = new HomePage(getDriver())
                .clickAcceptCookieButton()
                .getQuestionsAndAnswers(expectedAnswers.keySet());

        Assert.assertEquals(expectedAnswers, actualAnswers);
    }
}
