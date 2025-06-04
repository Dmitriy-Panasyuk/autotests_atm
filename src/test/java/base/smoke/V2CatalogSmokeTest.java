package base.smoke;

import io.qameta.allure.*;
import listeners.Listner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.catalogs.*;
import roles.Roles;
import utils.Log;

import static base.smoke.LoginTest.loginTest;
import static io.qameta.allure.SeverityLevel.NORMAL;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static utils.AllureAttachmentTools.attachScreenshotPNG;

@Test(enabled = true, priority = 4)
@Listeners({Listner.class})
public class V2CatalogSmokeTest {

   @BeforeClass(description = "До проверки справочников")
   public void beforeClass() {
      try {
         Log.printClassTitle(getClass().getSimpleName());
         Log.textLevelON();
         loginTest(HomePage.getURL(), Roles.roles.BIG_JACK);
         HomePage.get(HomePage.buttons.CATALOGS).clickButton();
      } catch (Exception ex) {
         attachScreenshotPNG();
         assertTrue(false);
      } finally {
         Log.textLevelOFF();
      }
   }

   @Test(enabled = true, description = "Проверка каталога  \"Роли\".", priority = 1402)
   @Description("Тест входит на страницу справочника \"Роли\" и проверяет наличие таблицы справочника.")
   @Step("Проверка каталога  \"Роли\".")
   @Severity(NORMAL)
   @Feature("Smoke тесты")
   @Story("Тесты страниц каталога")
   public void rolesPageTest() {
      Catalog.get(Catalog.buttons.ROLES).clickButton(false, true);
      assertNotNull(RolesPage.get(RolesPage.tables.ROLES).getWebElement(), "Таблица не найдена");
   }
   @Test(enabled = true, description = "Проверка каталога  \"Компании\".", priority = 1406)
   @Description("Тест входит на страницу справочника \"Компании\" и проверяет наличие таблицы справочника.")
   @Step("Проверка каталога  \"Компании\".")
   @Severity(NORMAL)
   @Feature("Smoke тесты")
   @Story("Тесты страниц каталога")
   public void companyPageTest() {
      Catalog.get(Catalog.buttons.COMPANIES).clickButton(false, true);
      assertNotNull(CompanyPage.get(CompanyPage.tables.COMPANY).getWebElement(), "Таблица не найдена");
   }

   @Test(enabled = true, description = "Проверка каталога  \"Центры финансовой ответственности (ЦФО)\".", priority = 1407)
   @Description("Тест входит на страницу справочника \"Центры финансовой ответственности (ЦФО)\" и проверяет наличие таблицы справочника.")
   @Step("Проверка каталога  \"Центры финансовой ответственности (ЦФО)\".")
   @Severity(NORMAL)
   @Feature("Smoke тесты")
   @Story("Тесты страниц каталога")
   public void financialResponsibilityPageTest() {
      Catalog.get(Catalog.buttons.FINANCIAL_RESPONSILIBILITYS).clickButton(false, true);
      assertNotNull(FinancialResponsibilityPage.get(FinancialResponsibilityPage.tables.FINANCIAL).getWebElement(), "Таблица не найдена");
   }

   @Test(enabled = true, description = "Проверка каталога  \"Место возникновения затрат (МВЗ)\".", priority = 1408)
   @Description("Тест входит на страницу справочника \"Место возникновения затрат (МВЗ)\" и проверяет наличие таблицы справочника.")
   @Step("Проверка каталога  \"Место возникновения затрат (МВЗ)\".")
   @Severity(NORMAL)
   @Feature("Smoke тесты")
   @Story("Тесты страниц каталога")
   public void costCenterPageTest() {
      Catalog.get(Catalog.buttons.COST_CENTERS).clickButton(false, true);
      assertNotNull(CostCenterPage.get(CostCenterPage.tables.CENTERS).getWebElement(), "Таблица не найдена");
   }

   @Test(enabled = true, description = "Проверка каталога  \"Корпоративные задачи\".", priority = 1409)
   @Description("Тест входит на страницу справочника \"Корпоративные задачи\" и проверяет наличие таблицы справочника.")
   @Step("Проверка каталога  \"Корпоративные задачи\".")
   @Severity(NORMAL)
   @Feature("Smoke тесты")
   @Story("Тесты страниц каталога")
   public void programsPageTest() {
      Catalog.get(Catalog.buttons.PROGRAMS).clickButton(false, true);
      assertNotNull(ProgramsPage.get(ProgramsPage.tables.PROGRAMS).getWebElement(), "Таблица не найдена");
   }

   @Test(enabled = true, description = "Проверка каталога  \"Проекты\".", priority = 1410)
   @Description("Тест входит на страницу справочника \"Проекты\" и проверяет наличие таблицы справочника.")
   @Step("Проверка каталога  \"Проекты\".")
   @Severity(NORMAL)
   @Feature("Smoke тесты")
   @Story("Тесты страниц каталога")
   public void projectsPageTest() {
      Catalog.get(Catalog.buttons.PROJECTS).clickButton(false, true);
      assertNotNull(ProjectsPage.get(ProjectsPage.tables.PROJECTS).getWebElement(), "Таблица не найдена");
   }

   @Test(enabled = true, description = "Проверка каталога  \"Платежные документы\".", priority = 1413)
   @Description("Тест входит на страницу справочника \"Платежные документы\" и проверяет наличие таблицы справочника.")
   @Step("Проверка каталога  \"Платежные документы\".")
   @Severity(NORMAL)
   @Feature("Smoke тесты")
   @Story("Тесты страниц каталога")
   public void paymentDocsPageTest() {
      Catalog.get(Catalog.buttons.PAYMENT_DOCS).clickButton(false, true);
      assertNotNull(PaymentDocsPage.get(PaymentDocsPage.tables.COMPANY).getWebElement(), "Таблица не найдена");
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
