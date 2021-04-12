package core.PageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.logging.Logger;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static utillities.GeneralFunctions.*;

public class BotsPage extends BasePage {
  private final SelenideElement addBotBtn = $(By.xpath("//button[contains(@class, 'bh-button')]"));
  private final ElementsCollection optionBtn =
      $$(By.xpath("//button[contains(@class, 'option-button')]"));
  private final ElementsCollection menuItem =
      $$(By.xpath("//button[contains(@class, 'mat-menu-item')]"));
  private final SelenideElement botsGrid = $(By.className("bots-grid"));
  private final SelenideElement deleteBtn =
          $(By.xpath("//span[contains(@class, 'mat-button-wrapper') and text()=' Удалить ']"));

  private static final utilities.AllureLogger ALLURE_LOGGER = new utilities.AllureLogger(BotsPage.class);

  public enum Platforms {
    VK("//a[contains (@href, '/flow2/flow-builder/newvk/edit')]"),
    VIBER("//a[contains (@href, '/flow2/flow-builder/newviber/edit')]"),
    TELEGRAM("//a[contains (@href, '/flow2/flow-builder/newtg/edit')]"),
    FACEBOOK("//a[contains (@href, '/flow2/flow-builder/newfb/edit')]");

    private final String xpath;

    Platforms(String xpath) {
      this.xpath = xpath;
    }

    public void clickItem() {
      $(By.xpath(xpath)).waitUntil(Condition.visible, TIME).click();
    }
  }

  public BotsPage addFirstBot() {
    ALLURE_LOGGER.info("Click on btn Create new bot");
    checkLoadOfPage(TIME);
    waitUntilVisible(addBotBtn).click();
    return this;
  }

  public CreationBotPage choosePlatformForBot(Platforms platform) {
    ALLURE_LOGGER.info("Open platform " + platform);
    checkLoadOfPage(TIME);
    platform.clickItem();
    return new CreationBotPage();
  }

  public BotsPage deleteActiveBots() {
    ALLURE_LOGGER.info("Delete all active bots");
    checkLoadOfPage(TIME);
    for (SelenideElement element : optionBtn) {
      waitUntilVisible(element).click();
      menuItem.get(4).click();
      waitUntilVisible(deleteBtn);
      jsSelenideButtonClick(deleteBtn);
    }
    return this;
  }

  public boolean isVisibleCreationBot() {
    ALLURE_LOGGER.info("Check visible creation bot");
    checkLoadOfPage(TIME);
    return isVisible(botsGrid);
  }
}
