import core.Constants;
import core.PageObject.LoginPage;
import io.qameta.allure.Step;

import java.util.logging.Logger;

import static com.codeborne.selenide.Selenide.open;

public class TestPage {
  private final Logger logger = Logger.getLogger(TestPage.class.getName());

  @Step("Login")
  public void setUp() {
    open(Constants.MAIN_URL);
    logger.info("Open login page and login");
    new LoginPage().enterAccountDomain(Constants.DOMAIN).login(Constants.NAME, Constants.PASSWORD);
  }
}
