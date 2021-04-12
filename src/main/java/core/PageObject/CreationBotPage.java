package core.PageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.logging.Logger;

import static com.codeborne.selenide.Selenide.$;
import static utillities.GeneralFunctions.*;

public class CreationBotPage extends BasePage {
  private final SelenideElement nameBotInput =
      $(By.xpath("//input[contains(@class, 'mat-input-element')]"));
  private final SelenideElement backBtn =
      $(By.xpath("//button[contains(@class, 'back-button')]"));
  private final SelenideElement addBotBtn =
      $(By.xpath("//span[contains(@class, 'mat-button-wrapper') and text()=' Создать бота ']"));
  private final SelenideElement activateBtn = $(By.xpath("//span[text()=' Активировать ']"));

  private static final utilities.AllureLogger ALLURE_LOGGER = new utilities.AllureLogger(BotsPage.class);

  public CreationBotPage addNewBot(String name) {
    ALLURE_LOGGER.info("Add new bot write Name");
    checkLoadOfPage(TIME);
    waitUntilVisible(nameBotInput).setValue(name).shouldBe(Condition.exactValue(name));
    waitUntilVisible(nameBotInput).setValue(name).shouldBe(Condition.exactValue(name));
    waitUntilVisible(addBotBtn);
    jsSelenideButtonClick(addBotBtn);
    return this;
  }

  public boolean isVisibleActivateBotBtn() {
    ALLURE_LOGGER.info("Check visible activate bot btn");
    checkLoadOfPage(TIME);
    return isVisible(activateBtn);
  }

  public MainPage backToMainPage() {
    ALLURE_LOGGER.info("Back to main page");
    waitUntilVisible(backBtn).click();
    return new MainPage();
  }
}
