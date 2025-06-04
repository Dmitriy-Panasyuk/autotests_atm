package base.e2e;

import base.smoke.LoginTest;
import common.CommonActions;
import elements.ButtonElement;
import elements.EnumElement;
import elements.TableElement;
import io.qameta.allure.*;
import listeners.Listner;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.HomePage;
import pages.catalogs.*;
import pages.payment.PaymentPage;
import roles.Roles;
import utils.Log;
import utils.Waits;

import java.util.ArrayList;

import static base.smoke.LoginTest.loginTest;
import static constants.Constant.TEST_RUN_ID;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static io.qameta.allure.SeverityLevel.NORMAL;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;
import static utils.AllureAttachmentTools.attachScreenshotPNG;
import static utils.Utils.equalsURLs;


@Test(enabled = true, priority = 10001)
@Listeners({Listner.class})
public class CatalogE2ETest {
   static final String feature = "E2E тесты";
   static final String story = "Тесты страниц каталога";
   static String companyName = "";
   static String responsibilityName = "";
   public static ArrayList<String> urls = new ArrayList<>();
   public static ArrayList<TableElement> tables = new ArrayList<>();
   public static ArrayList<ButtonElement[]> clicElements = new ArrayList<>();
   public static ArrayList<String> enumValues = new ArrayList<>();
   public static ArrayList<EnumElement> enums = new ArrayList<>();
   public static ArrayList<String> columnNames = new ArrayList<>();
   public static ArrayList<String> columnValues = new ArrayList<>();

   @BeforeClass(description = "До E2E проверки справочников")
   public void beforeClass() {
      try {
         Log.printClassTitle(getClass().getSimpleName());
         Log.textLevelON();
         loginTest(HomePage.getURL(), Roles.roles.BIG_JACK);
         HomePage.get(HomePage.buttons.PAYMENT_REQUESTS).clickButton();
         PaymentPage.get(PaymentPage.buttons.CATALOGS).clickButton();
      } catch (Exception ex) {
         attachScreenshotPNG();
         assertTrue(false);
      }finally {
         Log.textLevelOFF();
      }
   }

   @Test(enabled = true, description = "Каталог  \"Роли\". Создать", priority = 201401)
   @Description("Создает новую роль и проверяет ее наличие в списке.")
   @Step("Создать роль")
   @Severity(NORMAL)
   @Feature(feature)
   @Story(story)
   public void rolesPageTest() {
      if (!equalsURLs(CommonActions.getDriver().getCurrentUrl(), RolesPage.getURL())) {
         Catalog.get(Catalog.buttons.ROLES).clickButton();
      }

      RolesPage.get(RolesPage.buttons.CREATE).clickButton();
      String testName = "test Name " + TEST_RUN_ID;
      RolesPage.get(RolesPage.inputs.ROLE_NAME).sendKeysInInput("test Name " + TEST_RUN_ID);
      RolesPage.get(RolesPage.inputs.ROLE_EMAIL).sendKeysInInput(TEST_RUN_ID + "@test.ru");
      RolesPage.get(RolesPage.inputs.ROLE_DEPARTAMENT).sendKeysInInput("testDepartament");
      RolesPage.get(RolesPage.inputs.ROLE_POST).sendKeysInInput("testPost");
      RolesPage.get(RolesPage.enums.ROLE_COMPANY_NAME).clickOnEnumItem(0);
//      RolesPage.get(RolesPage.buttons.ROLE_ADD_ROLE).clickButton();
//      RolesPage.get(RolesPage.enums.ROLE_ROLE).clickOnEnumItem(0);
      RolesPage.get(RolesPage.buttons.ROLE_CREATE).clickButton();
      ArrayList<WebElement> rows = RolesPage.get(RolesPage.tables.ROLES).findTableString("ФИО сотрудника", testName);
      if (rows.size() != 1) {
         assertTrue(false, "Пользователь не был создан. \n");
      }
      urls.add(RolesPage.getURL());
      tables.add(RolesPage.get(RolesPage.tables.ROLES));
      clicElements.add(new ButtonElement[]{});
      enums.add(null);
      enumValues.add("");
      columnNames.add("ФИО сотрудника");
      columnValues.add(testName);
   }

   @Test(enabled = true, description = "Каталог  \"Контрагенты\". Создать", priority = 201402)
   @Description("Создает нового контрагента и проверяет его наличие в списке.")
   @Step("Создать контрагента")
   @Severity(NORMAL)
   @Feature(feature)
   @Story(story)
   public void contractorsPageTest() throws Exception {
      if (!equalsURLs(CommonActions.getDriver().getCurrentUrl(), ContractorsPage.getURL())) {
         Catalog.get(Catalog.buttons.CONTRACTORS).clickButton();
      }
      ContractorsPage.get(ContractorsPage.buttons.CREATE).clickButton();
      String testName = "test Contractors " + TEST_RUN_ID;
      ContractorsPage.get(ContractorsPage.enums.CONTRACTOR_TYPE).clickOnEnumItem("ВГО ТиМ");
      ContractorsPage.get(ContractorsPage.inputs.CONTRACTOR_NAME).sendKeysInInput(testName);
      ContractorsPage.get(ContractorsPage.inputs.CONTRACTOR_DISRIPTION).sendKeysInInput(testName + " Discription");
      ContractorsPage.get(ContractorsPage.inputs.CONTRACTOR_INN).sendKeysInInput("99" + TEST_RUN_ID);
      ContractorsPage.get(ContractorsPage.inputs.CONTRACTOR_NICKNAME).sendKeysInInput(testName + " NickName");
      WebElement createButton = ContractorsPage.get(ContractorsPage.enums.CONTRACTOR_TYPE).getWebElement();
      ContractorsPage.get(ContractorsPage.buttons.CONTRACTORS_CREATE).clickButton();
      Waits.invisibleElement(createButton);
      ContractorsPage.get(ContractorsPage.buttons.INTERNAL_TAM).clickButton();
      ArrayList<WebElement> rows = ContractorsPage.get(ContractorsPage.tables.CONTRACTORS).findTableString("Контрагент", testName);
      if (rows.size() != 1) {
         assertTrue(false, "Контрагент не был создан. \n");
      }
      urls.add(ContractorsPage.getURL());
      tables.add(ContractorsPage.get(ContractorsPage.tables.CONTRACTORS));
      clicElements.add(new ButtonElement[]{ContractorsPage.get(ContractorsPage.buttons.INTERNAL_TAM)});
      enums.add(null);
      enumValues.add("");
      columnNames.add("Контрагент");
      columnValues.add(testName);
   }

   @Test(enabled = true, description = "Каталог  \"Компании\". Создать", priority = 201403)
   @Description("Создает новую компанию и проверяет ее наличие в списке.")
   @Step("Создать компанию")
   @Severity(NORMAL)
   @Feature(feature)
   @Story(story)
   public void companyPageTest() throws Exception {
      if (!equalsURLs(CommonActions.getDriver().getCurrentUrl(), CompanyPage.getURL())) {
         Catalog.get(Catalog.buttons.COMPANIES).clickButton();
      }
      CompanyPage.get(CompanyPage.buttons.CREATE).clickButton();
      String testName = "test Company " + TEST_RUN_ID;
      companyName = testName;
      CompanyPage.get(CompanyPage.inputs.COMPANY_NAME).sendKeysInInput(testName);

      WebElement createButton = CompanyPage.get(CompanyPage.buttons.COMPANY_CREATE).getWebElement();
      CompanyPage.get(CompanyPage.buttons.COMPANY_CREATE).clickButton();
      Waits.invisibleElement(createButton);
      ArrayList<WebElement> rows = CompanyPage.get(CompanyPage.tables.COMPANY).findTableString("Компания", testName);
      if (rows.size() != 1) {
         assertTrue(false, "Компания не была создана. \n");
      }
      urls.add(CompanyPage.getURL());
      tables.add(CompanyPage.get(CompanyPage.tables.COMPANY));
      clicElements.add(new ButtonElement[]{});
      enums.add(null);
      enumValues.add("");
      columnNames.add("Компания");
      columnValues.add(testName);
   }

   @Test(enabled = true, description = "Каталог  \"Компании\". Создать с именем которое уже существует", priority = 201404)
   @Description("Создает новую компанию с именем которое уже есть в списке и проверяет что появилась ошибка.")
   @Step("Создать компанию с именем которое уже существует")
   @Severity(NORMAL)
   @Feature(feature)
   @Story(story)
   public void companyPageTestFailed() throws Exception {
      if (!equalsURLs(CommonActions.getDriver().getCurrentUrl(), CompanyPage.getURL())) {
         Catalog.get(Catalog.buttons.COMPANIES).clickButton();
      }
      CompanyPage.get(CompanyPage.buttons.CREATE).clickButton();
      String testName = "test Company " + TEST_RUN_ID;
      companyName = testName;
      CompanyPage.get(CompanyPage.inputs.COMPANY_NAME).sendKeysInInput(testName);

      WebElement createButton = CompanyPage.get(CompanyPage.buttons.COMPANY_CREATE).getWebElement();
      CompanyPage.get(CompanyPage.buttons.COMPANY_CREATE).clickButton();
      Waits.invisibleElement(createButton);
      ArrayList<WebElement> rows = CompanyPage.get(CompanyPage.tables.COMPANY).findTableString("Компания", testName);
      if (rows.size() != 1) {
         assertNull(CompanyPage.get(CompanyPage.labels.CHECK_ERROR).getWebElement(), "Всплывающее окно об ошибке не появилось");
      }
   }

   @Test(enabled = true, description = "Каталог  \"Центры финансовой ответственности (ЦФО)\". Создать", priority = 201405)
   @Description("Создает новый ЦФО и проверяет его наличие в списке.")
   @Step("Создать ЦФО")
   @Severity(NORMAL)
   @Feature(feature)
   @Story(story)
   public void financialResponsibilityPageTest() throws Exception {
      if (!equalsURLs(CommonActions.getDriver().getCurrentUrl(), FinancialResponsibilityPage.getURL())) {
         Catalog.get(Catalog.buttons.FINANCIAL_RESPONSILIBILITYS).clickButton();
      }
      FinancialResponsibilityPage.get(FinancialResponsibilityPage.buttons.CREATE).clickButton();
      responsibilityName = "test Responsibility " + TEST_RUN_ID;
      if (companyName.equals("")) {
         companyName = "ООО «КОСМИЧЕСКИЕ ТРАНСПОРТНЫЕ СИСТЕМЫ»";
      }
      FinancialResponsibilityPage.get(FinancialResponsibilityPage.inputs.FINANCIAL_NAME).sendKeysInInput(responsibilityName);
      FinancialResponsibilityPage.get(FinancialResponsibilityPage.enums.FINANCIAL_COMPANY).clickOnEnumItem(companyName);
      FinancialResponsibilityPage.get(FinancialResponsibilityPage.enums.FINANCIAL_DIRECTORS).clickOnEnumItem("Third Jack");
      FinancialResponsibilityPage.get(FinancialResponsibilityPage.enums.FINANCIAL_BUGET_ADMINS).clickOnEnumItem("Jack Test Sixth");
      FinancialResponsibilityPage.get(FinancialResponsibilityPage.enums.FINANCIAL_ECONOMISTS).clickOnEnumItem("Second Jack");
      WebElement createButton = FinancialResponsibilityPage.get(FinancialResponsibilityPage.buttons.FINANCIAL_CREATE).getWebElement();
      FinancialResponsibilityPage.get(FinancialResponsibilityPage.buttons.FINANCIAL_CREATE).clickButton();
      Waits.invisibleElement(createButton);
      FinancialResponsibilityPage.get(FinancialResponsibilityPage.enums.COMPANY_NAME).clickOnEnumItem(companyName);
      ArrayList<WebElement> rows = FinancialResponsibilityPage.get(FinancialResponsibilityPage.tables.FINANCIAL).findTableString("Центр финансовой ответственности", responsibilityName);
      if (rows.size() != 1) {
         assertTrue(false, "ЦФО не был создан. \n");
      }
      urls.add(FinancialResponsibilityPage.getURL());
      tables.add(FinancialResponsibilityPage.get(FinancialResponsibilityPage.tables.FINANCIAL));
      clicElements.add(new ButtonElement[]{});
      enums.add(FinancialResponsibilityPage.get(FinancialResponsibilityPage.enums.COMPANY_NAME));
      enumValues.add(companyName);
      columnNames.add("Центр финансовой ответственности");
      columnValues.add(responsibilityName);
   }

   @Test(enabled = true, description = "Каталог  \"Место возникновения затрат (МВЗ)\". Создать", priority = 201406)
   @Description("Создает новое МВЗ и проверяет его наличие в списке.")
   @Step("Создать МВЗ")
   @Severity(NORMAL)
   @Feature(feature)
   @Story(story)
   public void costCenterPageTest() throws Exception {
      if (!equalsURLs(CommonActions.getDriver().getCurrentUrl(), CostCenterPage.getURL())) {
         Catalog.get(Catalog.buttons.COST_CENTERS).clickButton();
      }
      CostCenterPage.get(CostCenterPage.buttons.CREATE).clickButton();
      String testName = "test CostCenter " + TEST_RUN_ID;
      CostCenterPage.get(CostCenterPage.inputs.CENTER_NAME).sendKeysInInput(testName);
      CostCenterPage.get(CostCenterPage.enums.CENTER_COMPANY).clickOnEnumItem(companyName);
      CostCenterPage.get(CostCenterPage.enums.CENTER_RESPONSIBILITY).clickOnEnumItem(responsibilityName);

      WebElement createButton = CostCenterPage.get(CostCenterPage.buttons.CENTER_CREATE).getWebElement();
      CostCenterPage.get(CostCenterPage.buttons.CENTER_CREATE).clickButton();
      Waits.invisibleElement(createButton);
      CostCenterPage.get(CostCenterPage.enums.COMPANY_NAME).clickOnEnumItem(companyName);
      ArrayList<WebElement> rows = CostCenterPage.get(CostCenterPage.tables.CENTERS).findTableString("Место возникновения затрат", testName);
      if (rows.size() != 1) {
         assertTrue(false, "МВЗ не было создано. \n");
      }
      urls.add(CostCenterPage.getURL());
      tables.add(CostCenterPage.get(CostCenterPage.tables.CENTERS));
      clicElements.add(new ButtonElement[]{});
      enums.add(CostCenterPage.get(CostCenterPage.enums.COMPANY_NAME));
      enumValues.add(companyName);
      columnNames.add("Место возникновения затрат");
      columnValues.add(testName);
   }


   @Test(enabled = true,
           description = "Дезактивация созданных значений",
           priority = 201499)
   @Step("Дезактивация созданных значений")
   @Description("Дезактивация созданных значений")
   @Severity(CRITICAL)
   @Feature(feature)
   @Story(story)
   public static void deactivateValues() {
      for (int i = tables.size() - 1; i >= 0; i--) {
         int finalI = i;
         Allure.step("Дезактивация созданного значения в таблице '" + tables.get(finalI).getElementName() + "'", () -> {
            String title = "Deactivating a created value in a table '" + tables.get(finalI).getElementName();
            Log.textLevelOFF();
            Log.println("\n" + title, "p");
            Log.textLevelON();
            BasePage.open(urls.get(finalI));
            for (ButtonElement button : clicElements.get(finalI)) {
               Thread.sleep(8000);
               button.clickButton();
            }
            if (enums.get(finalI) != null) {
               enums.get(finalI).clickOnEnumItem(enumValues.get(finalI));
            }
            ArrayList<WebElement> rows = tables.get(finalI).findTableString(columnNames.get(finalI), columnValues.get(finalI));
            if (rows.size() == 1) {
               Catalog.get(Catalog.buttons.ROW_MENU).clickButton();
               Catalog.get(Catalog.buttons.ROW_MENU_CHANGE_STATUS).clickButton();
               Catalog.get(Catalog.buttons.ROW_MENU_CHANGE_STATUS_CONFIRM).clickButton();
               Catalog.get(Catalog.buttons.ROW_MENU_CHANGE_STATUS_ICON).clickButton();
               Catalog.get(Catalog.buttons.ROW_MENU_CHANGE_STATUS_SAVE_ICON).clickButton();

            } else {
               assertTrue(false, "Созданное значение не найдено. \n");
            }
         });
      }
   }

   @AfterClass(alwaysRun = true, description = "После E2E проверки справочников")
   public void logout() {
      try {
         Log.printClassTitle(getClass().getSimpleName());
         Log.textLevelON();
         LoginTest.logOut();
      } catch (Exception ex) {
         attachScreenshotPNG();
         assertTrue(false);
      }finally {
         Log.textLevelOFF();
      }
   }
}
