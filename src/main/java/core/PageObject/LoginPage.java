package core.PageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.logging.Logger;

import static com.codeborne.selenide.Selenide.$;
import static utillities.GeneralFunctions.checkLoadOfPage;
import static utillities.GeneralFunctions.waitUntilVisible;

/** Login Page. Open this every test */
public class LoginPage extends BasePage {

  private final SelenideElement loginInput = $(By.id("input-email"));
  private final SelenideElement passwordInput = $(By.id("input-password"));
  private final SelenideElement domainInput = $(By.id("input-domain"));
  private final SelenideElement loginBtn = $(By.className("btn-lead-b"));

  private static final utilities.AllureLogger ALLURE_LOGGER = new utilities.AllureLogger(BotsPage.class);

  /**
   * @param accountDomain account domain
   * @return this
   */
  public LoginPage enterAccountDomain(String accountDomain) {
    checkLoadOfPage(TIME);
    waitUntilVisible(domainInput)
        .setValue(accountDomain)
            .shouldBe(Condition.exactValue(accountDomain));
    waitUntilVisible(loginBtn).click();
    return this;
  }
  /**
   * @param login email
   * @param password password
   */
  public LoginPage login(String login, String password) {
    ALLURE_LOGGER.info("Login in botHelp");
    checkLoadOfPage(TIME);
    waitUntilVisible(loginInput).setValue(login).shouldBe(Condition.exactValue(login));
    waitUntilVisible(passwordInput).setValue(password).shouldBe(Condition.exactValue(password));
    waitUntilVisible(loginBtn).click();
    return this;
  }
}
