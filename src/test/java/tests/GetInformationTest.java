package tests;

import model.HomePage;
import org.junit.Assert;
import org.junit.Test;
import runner.BaseTest;
import utils.HomePageData;

import java.util.Map;

public class GetInformationTest extends BaseTest {

    @Test
    public void testGetAnswersToImportantQuestions() {
        Map<String, String> expectedAnswers = HomePageData.EXPECTED_ANSWERS;

        Map<String, String> actualAnswers = new HomePage(getDriver())
                .clickAcceptCookieButton()
                .getQuestionsAndAnswers(expectedAnswers.keySet());

        Assert.assertEquals(expectedAnswers, actualAnswers);
    }
}
