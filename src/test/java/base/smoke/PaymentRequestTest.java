package base.smoke;

import common.CommonActions;
import elements.BaseElement;
import io.qameta.allure.*;
import listeners.Listner;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.payment.PaymentPage;
import pages.payment.RequestPage;
import roles.Roles;
import utils.Log;
import utils.Utils;

import java.util.List;

import static base.smoke.LoginTest.loginTest;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static io.qameta.allure.SeverityLevel.NORMAL;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static pages.BasePage.isLogin;
import static pages.BasePage.logoutSuccess;
import static utils.AllureAttachmentTools.attachScreenshotPNG;

@Test(enabled = true, priority = 3)
@Listeners({Listner.class})
public class PaymentRequestTest {
   final String story = "Тесты создания заявки";
   final String feature = "Smoke тесты";

   @BeforeClass(description = "До проверки создания заявки")
   public void beforeClass() {
      try {
         Log.printClassTitle(getClass().getSimpleName());
         Log.textLevelON();
      } catch (Exception ex) {
         attachScreenshotPNG();
         assertTrue(false);
      }finally {
         Log.textLevelOFF();
      }
   }

   @Test(enabled = true, description = "Создать черновик заявки", priority = 1301)
   @Description("Тест входит на страницу заявок, создает черновик заявки, проверяет ее наличие в списке, удаляет ее и проверяет ее отсутствие в списке.")
   @Step("Создать черновик заявки")
   @Severity(NORMAL)
   @Feature(feature)
   @Story(story)
   public void createRequestDraftTest() {
      loginTest(HomePage.getURL(), Roles.roles.BIG_JACK);
      HomePage.get(HomePage.buttons.PAYMENT_REQUESTS).clickButton(false,true);
      PaymentPage.get(PaymentPage.buttons.CREATE).clickButton();
      String requestNumber = RequestPage.get(RequestPage.labels.REQUEST_NUM).getWebElement().getText();
      requestNumber = requestNumber.substring(requestNumber.indexOf("№") + 1);
      RequestPage.get(RequestPage.buttons.RETURN).clickButton();
      List<WebElement> tableRows = PaymentPage.get(PaymentPage.tables.REQUEST).findTableString("№", requestNumber);
      if (tableRows.size() == 1) {
         BaseElement.clickElement(tableRows.get(0),false,true);
         RequestPage.get(RequestPage.buttons.DELETE).clickButton();
         RequestPage.get(RequestPage.buttons.DELETE_SUBMIT).clickButton();
      } else {
         Assert.assertTrue(false, "Заявка не создана. \n");
      }
   }


   @AfterMethod(enabled = true,
           description = "Выход с текущего пользователя",
           alwaysRun = true)
   @Severity(CRITICAL)
   public static void logOut() {
      System.out.println("logOut");
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

   @AfterClass(alwaysRun = true, description = "После проверки создания заявки")
   public void logout() {
      try {
         Log.printClassTitle(getClass().getSimpleName());
         Log.textLevelON();
      } catch (Exception ex) {
         attachScreenshotPNG();
         assertTrue(false);
      }finally {
         Log.textLevelOFF();
      }
   }
}
