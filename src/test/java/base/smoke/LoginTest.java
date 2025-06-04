package base.smoke;

import common.CommonActions;
import elements.ButtonElement;
import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import listeners.Listner;
import org.testng.annotations.*;
import pages.BasePage;
import pages.HomePage;
import pages.payment.PaymentPage;
import roles.Roles;
import utils.Log;
import utils.Utils;

import static io.qameta.allure.SeverityLevel.CRITICAL;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static pages.BasePage.*;
import static utils.AllureAttachmentTools.attachScreenshotPNG;
import static utils.Utils.equalsURLs;


@Test(enabled = true, priority = 1, description = "Кейс входа на сайт")
@Listeners({Listner.class})
public class LoginTest {

   @BeforeClass(description = "До тестов логина")
   public void beforeClass() {
      Log.printClassTitle(getClass().getSimpleName());
      Log.textLevelON();
      Log.textLevelOFF();
   }

   @Test(enabled = true,
           dataProvider = "pageURL",
           description = "Логин")
   @Step("Логин")
   @Description("Тест проводит стандартные действия для авторизации")
   @Severity(CRITICAL)
   @Feature("Smoke тесты")
   @Story("Тесты логина")
   public static void loginTest(String URL, Roles.roles role) {
      Log.println("Login : " + role.name());

      if (isLogin()) {
         assertTrue(true, "Логин уже был совершен");
      } else {
         BasePage.open(URL);
         ButtonElement enterButton = HomePage.get(HomePage.buttons.ENTER_LOGIN);
         if (equalsURLs(URL, HomePage.getURL())) {
            HomePage.get(HomePage.buttons.LOGIN_IMG).clickButton();
         } else {
            Log.println("setNewURLAfterClic");
            enterButton.setNewURLAfterClic(true);
         }
         HomePage.get(HomePage.inputs.LOGIN).sendKeysInInput(Roles.getRoleLogin(role));
         HomePage.get(HomePage.inputs.PASSWORD).sendKeysInInput(Roles.getRolePassword(role));
         enterButton.clickButton();
         loginSuccess();
         assertNotNull(HomePage.get(HomePage.labels.CHECK_SUCCESS).getWebElement(),
                 "Object does not correspond to the expected");
         assertTrue(equalsURLs(CommonActions.getDriver().getCurrentUrl(), URL),
                 "URL не соответствует открываемой странице. \n" +
                         "Запрашиваемый адрес: "+URL +
                         " Фактический адрес : "+CommonActions.getDriver().getCurrentUrl()+"\n");
      }
   }

   @Test(enabled = true,
           description = "Логин с неправильным паролем",
           priority = 1/*,
           expectedExceptions = {org.openqa.selenium.StaleElementReferenceException.class}*/)
   @Step("Логин с неправильным паролем")
   @Description("Тест проводит стандартные действия для авторизации с вводом неправильного пароля. Тест успешно пройден если авторизация не прошла.")
   @Severity(CRITICAL)
   @Feature("Smoke тесты")
   @Story("Тесты логина")
   public static void loginTestFailed() {
      HomePage.open();
      HomePage.get(HomePage.buttons.LOGIN_IMG).clickButton();
      HomePage.get(HomePage.inputs.LOGIN).sendKeysInInput(Roles.UserWrongPassword.LOGIN);
      HomePage.get(HomePage.inputs.PASSWORD).sendKeysInInput(Roles.UserWrongPassword.PASSWORD);
      HomePage.get(HomePage.buttons.ENTER_LOGIN).clickButton();
      assertNotNull(HomePage.get(HomePage.labels.CHECK_ERROR).getWebElement().getText(),
              "Object does not correspond to the expected");
      attachScreenshotPNG();
   }

   @Test(enabled = true,
           dependsOnMethods = "loginTest",
           description = "Выход с текущего пользователя",
           alwaysRun = true)
   @Description("Выход из текущего пользователя")
   @Severity(CRITICAL)
   @Owner("d.Panasyuk")
   @AllureId("RS_UI_OVER9000") // Не отображается в репорте
//   @Epic("Объеденяет в Behaviors уровень 1") // Объеденяет в Behaviors уровень 1
   @Feature("Smoke тесты") // Объеденяет в Behaviors уровень 2
   @Story("Тесты логина") // Объеденяет в Behaviors уровень 3
   @Tag("Ставит тэг, просто отображается")
   @Step("Выход с текущего пользователя")
   public static void logOut() {
      Log.println("logOut");
      if (isLogin()) {
         if (!Utils.equalsURLs(CommonActions.getDriver().getCurrentUrl(), HomePage.getURL())) {
            HomePage.open();
         }
         HomePage.get(HomePage.buttons.LOGOUT_ICON).clickButton();
         assertNotNull(HomePage.get(HomePage.buttons.LOGIN_IMG).getWebElement(), "Object is null. ");
         logoutSuccess();
      } else {
         assertTrue(false, "Ни один пользователь не залогинен");
      }
   }

   @DataProvider(name = "pageURL")
   public static Object[][] getURLs() {
      return new Object[][]{{PaymentPage.getURL(), Roles.roles.JACK}};
   }

   @AfterClass(description = "После тестов логина")
   public void afterClass() {
      Log.printClassTitle(getClass().getSimpleName());
      Log.textLevelON();
      Log.textLevelOFF();
   }
}
