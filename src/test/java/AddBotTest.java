import core.PageObject.BotsPage;
import core.PageObject.CreationBotPage;
import core.PageObject.MainPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddBotTest extends TestPage {
  private static final String NAME = "TEST";

  @Override
  @BeforeClass(alwaysRun = true)
  public void setUp() {
    super.setUp();
  }

  @BeforeMethod(alwaysRun = true)
  public void goToPage() {
    new MainPage().closePopup().open(MainPage.LeftSideBar.BOTS);
    BotsPage botsPage = new BotsPage().deleteActiveBots();
  }

  /**
   * Login Open Bots page Add bot Check visible activate btn Check visible creation bot in bots page
   */
  @Test(priority = 1, testName = "Add bot vk in botHelp")
  @Severity(SeverityLevel.CRITICAL)
  @Description(
      "Login, open Bots page, add bot, check visible activate btn, check visible creation bot in bots page")
  public void addBotTest() {
    boolean activateBotBtn =
        new BotsPage()
            .addFirstBot()
            .choosePlatformForBot(BotsPage.Platforms.VK)
            .addNewBot(NAME)
            .isVisibleActivateBotBtn();
    Assert.assertTrue(activateBotBtn, "Activate btn is not visble!");
    new CreationBotPage().backToMainPage().open(MainPage.LeftSideBar.BOTS);
    boolean creationBtn = new BotsPage().isVisibleCreationBot();
    Assert.assertTrue(activateBotBtn, "Creation bot is not visble!");
  }
}
