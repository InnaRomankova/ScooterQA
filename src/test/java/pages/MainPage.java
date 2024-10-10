package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.component.ScooterHeaderComponent;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MainPage extends BasePage {

    //Кнопка, чтобы принять cookie "Да все привыкли"
    private final By acceptCookieButton = By.cssSelector("div[class^='App_CookieConsent'] button");

    //Кнопка "Заказать" внизу страницы
    private final By bottomOrderButton = By.cssSelector("div[class^=Home]>button");

    //Блок "Вопросы о важном"
    private final By questionsAboutImportantBlock = By.xpath("//div[contains(text(),'Вопросы о важном')]");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage clickAcceptCookieButton() {
        getDriver().findElement(acceptCookieButton).click();

        return this;
    }

    public void clickBottomOrderButton() {
        getDriver().findElement(bottomOrderButton).click();
    }

    public Map<String, String> getQuestionsAndAnswers(Set<String> questions) {
        Map<String, String> answers = new HashMap<>();

        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView();",
                getDriver().findElement(questionsAboutImportantBlock));

        for (String question : questions) {
            getWait(10).until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("//div[contains(text(),'%s')]", question)))).click();
            String answer = getWait(3).until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(String.format("//div[contains(text(),'%s')]/../following-sibling::div/p", question)))).getText();

            answers.put(question, answer);
        }
        return answers;
    }

    public ScooterHeaderComponent getHeaderComponent() {
        return new ScooterHeaderComponent(getDriver());
    }
}
