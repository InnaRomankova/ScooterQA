package model;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HomePage extends BasePage {

    //Кнопка, чтобы принять cookie "Да все привыкли"
    private final By acceptCookieButton = By.cssSelector("div[class^='App_CookieConsent'] button");

    //Кнопка "Заказать" внизу страницы
    private final By bottomOrderButton = By.cssSelector("div[class^=Home]>button");

    //Кнопка "Заказать" вверху страницы
    private final By topOrderButton = By.xpath("//div[@class='Header_Nav__AGCXC']/button[text()='Заказать']");

    //Блок "Вопросы о важном"
    private final By questionsAboutImportantBlock = By.xpath("//div[contains(text(),'Вопросы о важном')]");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage clickAcceptCookieButton() {
        getDriver().findElement(acceptCookieButton).click();

        return this;
    }

    public FirstOrderFormPage clickBottomOrderButton() {
        getDriver().findElement(bottomOrderButton).click();

        return new FirstOrderFormPage(getDriver());
    }

    public FirstOrderFormPage clickTopOrderButton() {
        getDriver().findElement(topOrderButton).click();

        return new FirstOrderFormPage(getDriver());
    }

    public Map<String, String> getQuestionsAndAnswers(Set<String> questions) {
        Map<String, String> answers = new HashMap<>();

        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView();",
                getDriver().findElement(questionsAboutImportantBlock));

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10000));

        for (String question : questions) {

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("//div[contains(text(),'%s')]", question)))).click();
            String answer = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(String.format("//div[contains(text(),'%s')]/../following-sibling::div/p", question)))).getText();

            answers.put(question, answer);
        }
        return answers;
    }
}
