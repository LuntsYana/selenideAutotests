package utillities;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.SelenideElement;
import core.PageObject.BotsPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class GeneralFunctions {
  private static final utilities.AllureLogger ALLURE_LOGGER = new utilities.AllureLogger(BotsPage.class);
  private static final int TIME = 10000;
  private SelenideDriver selenideDriver;

  public static void waiting(int ms) {
    if (ms < 1000) {
      ALLURE_LOGGER.info("wait for " + ms + " ms");
    }
    if (ms >= 1000) {
      ALLURE_LOGGER.info("wait for " + ms / 1000 + " sec");
    }
    try {
      Thread.sleep(ms);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static boolean isVisible(SelenideElement element) {
    try {
      return element.isDisplayed();
    } catch (Exception e) {
      ALLURE_LOGGER.error("Exception: " + e);
      return false;
    }
  }

  public static void checkLoadOfPage(int timeout) {
    ALLURE_LOGGER.info("Waiting for page loading");
    try {
      Thread.sleep(1000);
      new WebDriverWait(getWebDriver(), timeout / 1000)
          .until(
              webDriver ->
                  "complete".equals(Selenide.executeJavaScript("return document.readyState")));
    } catch (Throwable error) {
      Assert.fail("Page didn't load for the specified time");
    }
    ALLURE_LOGGER.info("Page loaded successfully");
  }

  public static void jsSelenideButtonClick(SelenideElement button) {
    JavascriptExecutor jse = (JavascriptExecutor) getWebDriver();
    jse.executeScript("arguments[0].click();", button);
  }

  public static void waitUntilUrlChange(String currentUrl, int timeout) {
    ALLURE_LOGGER.info("Waiting for url changing");
    try {
      new WebDriverWait(getWebDriver(), timeout / 1000)
          .until(webDriver -> !currentUrl.equals(webDriver.getCurrentUrl()));
    } catch (Throwable error) {
      Assert.fail("Url didn't change for the specified time");
    }
    ALLURE_LOGGER.info("Url changed successfully");
  }

  public  void clearInput(SelenideElement x) {
    for (int i = 0; i < 6; i++) {
      if (!$(x).getValue().isEmpty()) {
        $(x).sendKeys(Keys.BACK_SPACE);
      }
    }
  }

  public static SelenideElement waitUntilVisible(SelenideElement element) {
    return element.waitUntil(Condition.visible, TIME);
  }
}
