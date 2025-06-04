package base.smoke;


import common.CommonActions;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.Step;
import org.testng.annotations.Test;
import pages.catalogs.CompanyPage;
import utils.Log;

import static io.qameta.allure.SeverityLevel.TRIVIAL;


@Test(enabled = false, priority = 2)
public class TestTest {
   @Test(enabled = false)
   public void beforeClass() {
      Log.printClassTitle(getClass().getSimpleName());

   }

   @Test(enabled = true,
           description = "Тестовый тест 1")
   @Step("Логин")
   @Description("Тут млжет быть что угодно, эксперементы тк скзть.")
   @Severity(TRIVIAL)
   public static void aTest() {
      CommonActions.setCurrentPlatform();
      CommonActions.getDriver();
      CompanyPage.open("https://www.google.com");
      sleep(8000);
      CommonActions.closeDriver();
   }

   @Test(enabled = true,
           description = "Тестовый тест 2")
   @Step("Логин")
   @Description("Тут млжет быть что угодно, эксперементы тк скзть.")
   @Severity(TRIVIAL)
   public static void bTest()  {
      CommonActions.setCurrentPlatform();
      CommonActions.getDriver();
      CompanyPage.open("https://ya.ru/");
      sleep(8000);
      CommonActions.closeDriver();
   }

   public static void sleep(int mills) {
      try {
         Thread.sleep(mills);
      } catch (InterruptedException e) {
         throw new RuntimeException(e);
      }
   }


}
