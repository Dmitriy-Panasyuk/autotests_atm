package base.smoke;

import io.qameta.allure.*;
import listeners.Listner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.catalogs.*;
import pages.payment.PaymentPage;
import roles.Roles;
import utils.Log;

import static base.smoke.LoginTest.loginTest;
import static io.qameta.allure.SeverityLevel.NORMAL;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static utils.AllureAttachmentTools.attachScreenshotPNG;

@Test(enabled = true, priority = 2)
@Listeners({Listner.class})
public class CatalogSmokeTest {

   @BeforeClass(description = "До проверки справочников")
   public void beforeClass() {
      try {
         Log.printClassTitle(getClass().getSimpleName());
         Log.textLevelON();
         loginTest(HomePage.getURL(), Roles.roles.JACK);
         HomePage.get(HomePage.buttons.PAYMENT_REQUESTS).clickButton();
         PaymentPage.get(PaymentPage.buttons.CATALOGS).clickButton();
      } catch (Exception ex) {
         attachScreenshotPNG();
         assertTrue(false);
      } finally {
         Log.textLevelOFF();
      }
   }




   @Test(enabled = true, description = "Проверка каталога  \"Руководители ЦФО\".", priority = 1201)
   @Description("Тест входит на страницу справочника \"Руководители ЦФО\" и проверяет наличие таблицы справочника.")
   @Step("Проверка каталога  \"Руководители ЦФО\".")
   @Severity(NORMAL)
   @Feature("Smoke тесты")
   @Story("Тесты страниц каталога")
   public void directorsPageTest() {
      Catalog.get(Catalog.buttons.DIRECTORS).clickButton(false, true);
      assertNotNull(DirectorsPage.get(DirectorsPage.tables.DIRECTORS).getWebElement(), "Таблица не найдена");
   }

   @Test(enabled = true, description = "Проверка каталога  \"Администраторы бюджета\".", priority = 1203)
   @Description("Тест входит на страницу справочника \"Администраторы бюджета\" и проверяет наличие таблицы справочника.")
   @Step("Проверка каталога  \"Администраторы бюджета\".")
   @Severity(NORMAL)
   @Feature("Smoke тесты")
   @Story("Тесты страниц каталога")
   public void budgetAdminsPageTest() {
      Catalog.get(Catalog.buttons.BUDGET_ADMINS).clickButton(false, true);
      assertNotNull(BudgetAdminsPage.get(BudgetAdminsPage.tables.ADMINS).getWebElement(), "Таблица не найдена");
   }

   @Test(enabled = true, description = "Проверка каталога  \"Экономисты\".", priority = 1204)
   @Description("Тест входит на страницу справочника \"Экономисты\" и проверяет наличие таблицы справочника.")
   @Step("Проверка каталога  \"Экономисты\".")
   @Severity(NORMAL)
   @Feature("Smoke тесты")
   @Story("Тесты страниц каталога")
   public void economistsPageTest() {
      Catalog.get(Catalog.buttons.ECONOMISTS).clickButton(false, true);
      assertNotNull(EconomistsPage.get(EconomistsPage.tables.ECONOMISTS).getWebElement(), "Таблица не найдена");
   }

   @Test(enabled = true, description = "Проверка каталога  \"Руководители проектов\".", priority = 1205)
   @Description("Тест входит на страницу справочника \"Руководители проектов\" и проверяет наличие таблицы справочника.")
   @Step("Проверка каталога  \"Руководители проектов\".")
   @Severity(NORMAL)
   @Feature("Smoke тесты")
   @Story("Тесты страниц каталога")
   public void projectManagersPageTest() {
      Catalog.get(Catalog.buttons.PROJECT_MANAGERS).clickButton(false, true);
      assertNotNull(ProjectManagersPage.get(ProjectManagersPage.tables.MANAGERS).getWebElement(), "Таблица не найдена");
   }




   @Test(enabled = true, description = "Проверка каталога  \"Контрагенты\".", priority = 1212)
   @Description("Тест входит на страницу справочника \"Контрагенты\" и проверяет наличие таблицы справочника.")
   @Step("Проверка каталога  \"Контрагенты\".")
   @Severity(NORMAL)
   @Feature("Smoke тесты")
   @Story("Тесты страниц каталога")
   public void externalContractorsPageTest() {
      Catalog.get(Catalog.buttons.CONTRACTORS).clickButton(false, true);
      assertNotNull(ContractorsPage.get(ContractorsPage.tables.CONTRACTORS).getWebElement(), "Таблица не найдена");
   }


   @Test(enabled = true, description = "Проверка каталога  \"Договоры\".", priority = 1214)
   @Description("Тест входит на страницу справочника \"Договоры\" и проверяет наличие таблицы справочника.")
   @Step("Проверка каталога  \"Договоры\".")
   @Severity(NORMAL)
   @Feature("Smoke тесты")
   @Story("Тесты страниц каталога")
   public void contractsPageTest() {
      Catalog.get(Catalog.buttons.CONTRACTS).clickButton(false, true);
      assertNotNull(ContractsPage.get(ContractsPage.tables.COMPANY).getWebElement(), "Таблица не найдена");
   }

   @Test(enabled = true, description = "Проверка каталога  \"Статьи расходов\".", priority = 1215)
   @Description("Тест входит на страницу справочника \"Статьи расходов\" и проверяет наличие таблицы справочника.")
   @Step("Проверка каталога  \"Статьи расходов\".")
   @Severity(NORMAL)
   @Feature("Smoke тесты")
   @Story("Тесты страниц каталога")
   public void costsPageTest() {
      Catalog.get(Catalog.buttons.COSTS).clickButton(false, true);
      assertNotNull(CostsPage.get(CostsPage.tables.COSTS).getWebElement(), "Таблица не найдена");
   }

   @AfterClass(alwaysRun = true, description = "После проверки справочников")
   public void logout() {
      try {
         Log.printClassTitle(getClass().getSimpleName());
         Log.textLevelON();
         LoginTest.logOut();
      } catch (Exception ex) {
         attachScreenshotPNG();
         assertTrue(false);
      } finally {
         Log.textLevelOFF();
      }
   }
}
