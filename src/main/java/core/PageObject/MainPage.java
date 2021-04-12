package core.PageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static utillities.GeneralFunctions.checkLoadOfPage;
import static utillities.GeneralFunctions.isVisible;

public class MainPage extends BasePage {
  private final SelenideElement closePopupBtn = $(By.xpath("//div[contains(@class, 'header')]/button[contains(@class, 'ng-star-inserted') and contains(@class, 'button')]"));
  private static final utilities.AllureLogger ALLURE_LOGGER = new utilities.AllureLogger(BotsPage.class);

  public enum LeftSideBar {
    // left side bar
    BOTS("//a[contains(@href, '/flow2/bots')]");

    private final String xpath;

    LeftSideBar(String xpath) {
      this.xpath = xpath;
    }

    public void clickTab() {
      ALLURE_LOGGER.info("Click tab in left side bar");
      SelenideElement button = $(By.xpath(xpath));
      button.waitUntil(Condition.exist, TIME);
      button.scrollIntoView(false).click();
    }
  }

  public MainPage open(LeftSideBar button) {
    checkLoadOfPage(TIME);
    ALLURE_LOGGER.info("Open " + button);
    button.clickTab();
    return this;
  }

  public MainPage closePopup() {
    checkLoadOfPage(TIME);
    if (isVisible(closePopupBtn)) {
      closePopupBtn.click();
    }
    return this;
  }
}
