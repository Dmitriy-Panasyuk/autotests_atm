package base.e2e;

import base.smoke.LoginTest;
import elements.BaseElement;
import elements.Enabled;
import io.qameta.allure.*;
import listeners.Listner;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import pages.BasePage;
import pages.HomePage;
import pages.PageURL;
import pages.payment.PaymentPage;
import pages.payment.RequestPage;
import roles.Roles;
import utils.DataBasePG;
import utils.Log;
import utils.Utils;

import java.sql.Statement;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import static base.smoke.LoginTest.loginTest;
import static constants.Constant.ALLURE_RESULTS_PATH;
import static constants.Constant.TEST_RUN_ID;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static org.testng.Assert.*;
import static pages.BasePage.isLogin;
import static pages.payment.RequestPageND.*;
import static utils.AllureAttachmentTools.attachScreenshotPNG;

@Test(enabled = true, priority = 10002)
@Listeners({Listner.class})
public class PaymentRequestE2ETest {
   static final String feature = "E2E тесты";
   static final String story = "Тесты статусов заявок";

   @BeforeClass(description = "До проверки создания заявки")
   public void beforeClass() {
      try {
         Log.printClassTitle(getClass().getSimpleName());
         Log.textLevelON();
      } catch (Exception ex) {
         attachScreenshotPNG();
         assertTrue(false);
      } finally {
         Log.textLevelOFF();
      }
   }

   @Step("Создать заявку")
   @Severity(CRITICAL)
   @Feature(feature)
   @Story(story)
   public String createRequest(Roles.roles role, String company, String financial, String costCenter, String contractors, String project, String expencesTask
           , String paymentDiscription, String contractorsType
           , String contractorsNumber, String costDocType, String costDocNumber, String docData
           , String filePath, String task, String projectTask
           , String value, String currency, String costData) {
      String requestNumber;

      loginTest(HomePage.getURL(), role);
      HomePage.get(HomePage.buttons.PAYMENT_REQUESTS).clickButton(false, true);
      PaymentPage.get(PaymentPage.buttons.CREATE).clickButton(true, true);
      requestNumber = RequestPage.get(RequestPage.labels.REQUEST_NUM).getWebElement().getText();
      requestNumber = requestNumber.substring(requestNumber.indexOf("№") + 1);
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);

      get(inputs.PAYMENT_DISCRIPTION).sendKeysInInput(paymentDiscription);
      get(enums.COMPANY_NAME).clickOnEnumItem(company);
      get(enums.FINANCIAL_NAME).clickOnEnumItem(financial);
      get(enums.COST_CENTER_NAME).clickOnEnumItem(costCenter);
      get(enums.CONTRACTORS_TYPE_NAME).clickOnEnumItem(contractorsType);
      get(enums.CONTRACTORS_NAME).clickOnEnumItem(contractors);
      get(enums.CONTRACT_NUMBER).clickOnEnumItem(contractorsNumber);
      get(enums.COST_DOCUMENT_TYPE).clickOnEnumItem(costDocType);
      get(inputs.COST_DOCUMENT_NUMBER).sendKeysInInput(costDocNumber);
      get(inputs.DOC_DATA).sendKeysInInput(docData);
      get(inputs.FILE_PATH).getWebElement().sendKeys(filePath);
      get(enums.PROJECT).clickOnEnumItem(project);
      get(enums.TASK).clickOnEnumItem(task);
      get(enums.EXPENCES_TASK).clickOnEnumItem(expencesTask);
      get(inputs.PROJECT_TASKS).sendKeysInInput(projectTask);
      get(inputs.VALUE).sendKeysInInput(value);
      get(enums.CURRENCY).clickOnEnumItem(currency);
      if (!costData.equals("")) {
         String[] d = costData.split(".");
         get(dates.COST_DATA).setDate(Integer.parseInt(d[0]), Integer.parseInt(d[0]), Integer.parseInt(d[0]));
      } else {
         get(dates.COST_DATA).setRandomActiveDate();
      }
      get(buttons.SAVE).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      get(buttons.REGISTRATION).clickButton();
      get(buttons.REGISTRATION_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      logOut();

      return requestNumber;
   }

   public String createRequest(Roles.roles role, String company, String financial, String costCenter, String contractors
           , String project, String expencesTask) {
      String requestNumber = createRequest(role,
              company,
              financial,
              costCenter,
              contractors,
              project,
              expencesTask,
              "test description " + TEST_RUN_ID,
              "ВГО Тим",
              "По счёту, без договора",
              "Счет",
              TEST_RUN_ID,
              "1111" + (Year.now().getValue() + 1),
              ALLURE_RESULTS_PATH + "/temp/test_doc.txt",
              "Вне задачи",
              "test task " + TEST_RUN_ID,
              TEST_RUN_ID + ".99",
              "USD",
              "");
      return requestNumber;
   }

   @Test(enabled = true, description = "Проверить наличие id тестовых ЦФО в БД", priority = 201300)
   @Description("Тест проверяет наличие  id тестовых ЦФО в БД. При отсутствии нужных id ЦФО добавляет их БД")
   @Step("Проверить наличие id тестовых ЦФО в БД")
   @Severity(CRITICAL)
   @Feature(feature)
   @Story(story)
   public void checkFCidIntoDB() throws Exception {

      String url = "jdbc:postgresql://" + PageURL.HOME.replace("http://", "") + ":5432/workflow";
      Statement st = DataBasePG.createStatement("postgres", "postgres", url);
      int[] ids = DataBasePG.getFinaceCentersIDs(st);
      int[] actualIDs;
      if (PageURL.HOME.contains("http://999.99.9.99")) {
         actualIDs = DataBasePG.actualIDsFeat;
      } else {
         actualIDs = DataBasePG.actualIDsDev;
      }
      if (!Utils.isArraysContain(ids, actualIDs)) {
         Log.println("Change IDs in DB");
         DataBasePG.addFinaceCentersIDs(st, Utils.combineArrays(ids, actualIDs));
      } else {
         Log.println("All IDs are present in DB");
      }
      st.close();
   }

   @Test(enabled = true, description = "Создать оплаченную заявку", priority = 201301)
   @Description("Тест создает оплаченную заявку. Заявка проходит все статусы.")
   @Step("Создать оплаченную заявку")
   @Severity(CRITICAL)
   @Feature(feature)
   @Story(story)
   public void createRequestFullTest() {

      String requestNumber = createRequest(
              Roles.roles.JACK,
              "AutoTestCompany_1",
              "ATFinRresponsibility_1",
              "ATCostCenter_1",
              "ATContractor_1",
              "ATProject_1",
              "ATCost_1");

      loginTest(HomePage.getURL(), Roles.roles.JACK8);
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      get(buttons.SAVE).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      get(buttons.ON_APPROVAL).clickButton();
      get(buttons.ON_APPROVAL_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      logOut();

      loginTest(HomePage.getURL(), Roles.roles.JACK6);
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      get(buttons.APPROV).clickButton();
      get(buttons.APPROV_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      logOut();

      loginTest(HomePage.getURL(), Roles.roles.JACK7);
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      get(buttons.APPROV).clickButton();
      get(buttons.APPROV_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      logOut();

      loginTest(HomePage.getURL(), Roles.roles.JACK2);
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      get(buttons.SAVE).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      get(buttons.ON_AFFIRMATION).clickButton();
      get(buttons.ON_AFFIRMATION_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      logOut();

      loginTest(HomePage.getURL(), Roles.roles.JACK3);
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      get(buttons.AFFIRM).clickButton();
      get(buttons.AFFIRM_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      logOut();

      loginTest(HomePage.getURL(), Roles.roles.JACK2);
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      get(buttons.ON_PAYMENT).clickButton();
      get(buttons.ON_PAYMENT_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      logOut();

      loginTest(HomePage.getURL(), Roles.roles.JACK4);
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      get(buttons.REGISTR).clickButton();
      get(buttons.REGISTR_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      logOut();

      loginTest(HomePage.getURL(), Roles.roles.JACK2);
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      get(buttons.ADD_PAYMENT).clickButton();
      get(inputs.REQUEST_NUMBER_1C).sendKeysInInput(TEST_RUN_ID);
      get(inputs.COST_DATA_1C).sendKeysInInput("1212" + (Year.now().getValue() + 1));
      get(buttons.ADD_PAYMENT_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      assertNotNull(get(labels.STATUS_PAID).getWebElement(), "Object is null. ");
      logOut();
   }


   @Test(enabled = true, description = "Создать оплаченную заявку", priority = 201302)
   @Description("Тест создает оплаченную заявку. Заявка проходит все статусы используя заместителей для продвижения заявки по статусам.")
   @Step("Создать оплаченную заявку. Заместители")
   @Severity(CRITICAL)
   @Feature(feature)
   @Story(story)
   public void createRequestFullDeputiesTest() {

      String requestNumber = createRequest(
              Roles.roles.JACK,
              "AutoTestCompany_1",
              "ATFinRresponsibility_1",
              "ATCostCenter_1",
              "ATContractor_1",
              "ATProject_1",
              "ATCost_1");


      loginTest(HomePage.getURL(), Roles.roles.JACK_TEST6);
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      get(buttons.SAVE).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      get(buttons.ON_APPROVAL).clickButton();
      get(buttons.ON_APPROVAL_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      logOut();

      loginTest(HomePage.getURL(), Roles.roles.JACK_TEST7);
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      get(buttons.APPROV).clickButton();
      get(buttons.APPROV_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      logOut();

      loginTest(HomePage.getURL(), Roles.roles.JACK_TEST6);
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      get(buttons.APPROV).clickButton();
      get(buttons.APPROV_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      logOut();

      loginTest(HomePage.getURL(), Roles.roles.BIG_JACK);
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      get(buttons.SAVE).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      get(buttons.ON_AFFIRMATION).clickButton();
      get(buttons.ON_AFFIRMATION_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      logOut();

      loginTest(HomePage.getURL(), Roles.roles.JACK_TEST5);
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      get(buttons.AFFIRM).clickButton();
      get(buttons.AFFIRM_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      logOut();

      loginTest(HomePage.getURL(), Roles.roles.BIG_JACK);
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      get(buttons.ON_PAYMENT).clickButton();
      get(buttons.ON_PAYMENT_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      logOut();

      loginTest(HomePage.getURL(), Roles.roles.JACK_TEST7);
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      get(buttons.REGISTR).clickButton();
      get(buttons.REGISTR_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      logOut();

      loginTest(HomePage.getURL(), Roles.roles.BIG_JACK);
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      get(buttons.ADD_PAYMENT).clickButton();
      get(inputs.REQUEST_NUMBER_1C).sendKeysInInput(TEST_RUN_ID);
      get(inputs.COST_DATA_1C).sendKeysInInput("1212" + (Year.now().getValue() + 1));
      get(buttons.ADD_PAYMENT_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      assertNotNull(get(labels.STATUS_PAID).getWebElement(), "Object is null. ");
      logOut();
   }


   @Test(enabled = true, description = "Пропуск статуса АБ. Отклонена РКБ", priority = 201303)
   @Description("Тест создает заявку. Согласование АБ пропускается. РКБ отправляет на доработку и после отклоняет заявку.")
   @Step("Пропуск статуса АБ. Отклонена РКБ")
   @Severity(CRITICAL)
   @Feature(feature)
   @Story(story)
   public void skipStatusBudgetAdminTest() {
      String requestNumber = createRequest(
              Roles.roles.JACK,
              "AutoTestCompany_1",
              "ATFinRresponsibility_2",
              "ATCostCenter_2",
              "ATContractor_1",
              "ATProject_1",
              "ATCost_2");

      loginTest(HomePage.getURL(), Roles.roles.JACK6);
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      get(buttons.ON_REVISION).clickButton();
      get(inputs.ON_REVISION_COMMENT).sendKeysInInput("test comment on revision");
      get(buttons.ON_REVISION_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      logOut();

      loginTest(HomePage.getURL(), Roles.roles.JACK);
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      get(buttons.SAVE).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      get(buttons.REGISTRATION).clickButton();
      get(buttons.REGISTRATION_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      logOut();

      loginTest(HomePage.getURL(), Roles.roles.JACK6);
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      get(buttons.REJECT).clickButton();
      get(inputs.ON_REVISION_COMMENT).sendKeysInInput("test comment on revision");
      get(buttons.REJECT_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      assertNotNull(get(labels.STATUS_REJECTED).getWebElement(), "Object is null. ");
      logOut();
   }


   @Test(enabled = true, description = "Пропуск статуса АБ и РКБ. Отклонена РП", priority = 201304)
   @Description("Тест создает заявку. Согласования АБ, РКБ пропускаются. РП отправляет на доработку и после отклоняет заявку.")
   @Step("Пропуск статуса АБ и РКБ. Отклонена РП")
   @Severity(CRITICAL)
   @Feature(feature)
   @Story(story)
   public void skipStatusBudgetAdminAndBureauManagerTest() {
      String requestNumber = createRequest(
              Roles.roles.JACK,
              "AutoTestCompany_1",
              "ATFinRresponsibility_2",
              "ATCostCenter_2",
              "ATContractor_1",
              "ATProject_2",
              "ATCost_2");

      loginTest(HomePage.getURL(), Roles.roles.JACK7);
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      get(buttons.ON_REVISION).clickButton();
      get(inputs.ON_REVISION_COMMENT).sendKeysInInput("test comment on revision");
      get(buttons.ON_REVISION_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      logOut();

      loginTest(HomePage.getURL(), Roles.roles.JACK);
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      get(buttons.SAVE).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      get(buttons.REGISTRATION).clickButton();
      get(buttons.REGISTRATION_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      logOut();

      loginTest(HomePage.getURL(), Roles.roles.JACK7);
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      get(buttons.REJECT).clickButton();
      get(inputs.ON_REVISION_COMMENT).sendKeysInInput("test comment on revision");
      get(buttons.REJECT_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      assertNotNull(get(labels.STATUS_REJECTED).getWebElement(), "Object is null. ");
      logOut();

   }


   @Test(enabled = true, description = "Комбинированный пропуск статусов", priority = 201305)
   @Description("Тест создает заявку. Согласования АБ, РКБ и СПК пропускаются. Согласование экономистом пропускается (автор заявки). Экономист (автор) отклоняет заявку.")
   @Step("Комбинированный пропуск статусов")
   @Severity(CRITICAL)
   @Feature(feature)
   @Story(story)
   public void combinedStatusSkippingTest() {
      String requestNumber = createRequest(
              Roles.roles.JACK2,
              "AutoTestCompany_1",
              "ATFinRresponsibility_3",
              "ATCostCenter_3",
              "ATContractor_1",
              "ATProject_3",
              "ATCost_3");

      loginTest(HomePage.getURL(), Roles.roles.JACK3);
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      get(buttons.AFFIRM).clickButton();
      get(buttons.AFFIRM_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      logOut();

      loginTest(HomePage.getURL(), Roles.roles.JACK2);
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      get(buttons.ON_PAYMENT).clickButton();
      get(buttons.ON_PAYMENT_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      get(buttons.REJECT).clickButton();
      get(inputs.ON_REVISION_COMMENT).sendKeysInInput("test comment on revision");
      get(buttons.REJECT_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      assertNotNull(get(labels.STATUS_REJECTED).getWebElement(), "Object is null. ");
      logOut();


   }

   @Test(enabled = true, description = "Блокировка пропуска автора", priority = 201306)
   @Description("Тест создает заявку. Согласования АБ, РКБ пропускаются. Согласование экономистом пропускается (автор заявки). Руководитель ЦФО отправляет заявку на доработку. После доработки экономист (автор) отклоняет заявку.")
   @Step("Блокировка пропуска автора")
   @Severity(CRITICAL)
   @Feature(feature)
   @Story(story)
   public void authorPassBlockingTest() {
      String requestNumber = createRequest(
              Roles.roles.JACK2,
              "AutoTestCompany_1",
              "ATFinRresponsibility_3",
              "ATCostCenter_3",
              "ATContractor_1",
              "ATProject_3",
              "ATCost_3");

      loginTest(HomePage.getURL(), Roles.roles.JACK3);
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      get(buttons.ON_REVISION).clickButton();
      get(inputs.ON_REVISION_COMMENT).sendKeysInInput("test comment on revision");
      get(buttons.ON_REVISION_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      logOut();

      loginTest(HomePage.getURL(), Roles.roles.JACK2);
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      get(buttons.SAVE).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      get(buttons.REGISTRATION).clickButton();
      get(buttons.REGISTRATION_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      get(buttons.REJECT).clickButton();
      get(inputs.ON_REVISION_COMMENT).sendKeysInInput("test comment on revision");
      get(buttons.REJECT_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      assertNotNull(get(labels.STATUS_REJECTED).getWebElement(), "Object is null. ");
      logOut();

   }


   @Test(enabled = true, description = "Пропуск автора. АБ автор заявки", priority = 201307)
   @Description("Тест создает заявку. Согласования АБ пропускаются тк он является автором. Руководитель проекта отклоняет заявку.")
   @Step("Пропуск автора. АБ автор заявки")
   @Severity(CRITICAL)
   @Feature(feature)
   @Story(story)
   public void budgetAdminPassBAAutor() {
      String requestNumber = createRequest(
              Roles.roles.JACK8,
              "AutoTestCompany_1",
              "ATFinRresponsibility_1",
              "ATCostCenter_1",
              "ATContractor_1",
              "ATProject_2",
              "ATCost_1");

      loginTest(HomePage.getURL(), Roles.roles.JACK7);
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      get(buttons.REJECT).clickButton();
      get(inputs.ON_REVISION_COMMENT).sendKeysInInput("test comment on revision");
      get(buttons.REJECT_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      assertNotNull(get(labels.STATUS_REJECTED).getWebElement(), "Object is null. ");
      logOut();

   }

   @Test(enabled = true, description = "Проверка создания заявки экономистом", priority = 201308)
   @Description("Тест создает заявку. Автор экономист. АБ отправляет заявку на доработку. Автор снова направляет заявку на оформление и АБ отклоняет заявку.")
   @Step("Проверка создания заявки экономистом")
   @Severity(CRITICAL)
   @Feature(feature)
   @Story(story)
   public void economistCreateRequest() {
      String requestNumber = createRequest(
              Roles.roles.JACK2,
              "AutoTestCompany_1",
              "ATFinRresponsibility_1",
              "ATCostCenter_1",
              "ATContractor_1",
              "ATProject_1",
              "ATCost_1");

      loginTest(HomePage.getURL(), Roles.roles.JACK8);
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      get(buttons.ON_REVISION).clickButton();
      get(inputs.ON_REVISION_COMMENT).sendKeysInInput("test comment on revision");
      get(buttons.ON_REVISION_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      logOut();

      loginTest(HomePage.getURL(), Roles.roles.JACK2);
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      get(buttons.SAVE).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      get(buttons.REGISTRATION).clickButton();
      get(buttons.REGISTRATION_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      logOut();

      loginTest(HomePage.getURL(), Roles.roles.JACK8);
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      get(buttons.REJECT).clickButton();
      get(inputs.ON_REVISION_COMMENT).sendKeysInInput("test comment on revision");
      get(buttons.REJECT_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      assertNotNull(get(labels.STATUS_REJECTED).getWebElement(), "Object is null. ");
      logOut();

   }


   @Test(enabled = true, description = "Пропуск автора. РКБ автор заявки", priority = 201309)
   @Description("Тест создает заявку. Согласования РКБ пропускаются тк он является автором. Руководитель ЦФО отклоняет заявку.")
   @Step("Пропуск автора. РКБ автор заявки")
   @Severity(CRITICAL)
   @Feature(feature)
   @Story(story)
   public void bureauManagerPassBMAutor() {
      String requestNumber = createRequest(
              Roles.roles.JACK6,
              "AutoTestCompany_1",
              "ATFinRresponsibility_2",
              "ATCostCenter_2",
              "ATContractor_1",
              "ATProject_3",
              "ATCost_2");

      loginTest(HomePage.getURL(), Roles.roles.JACK2);
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      get(buttons.SAVE).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      get(buttons.ON_AFFIRMATION).clickButton();
      get(buttons.ON_AFFIRMATION_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      logOut();

      loginTest(HomePage.getURL(), Roles.roles.JACK3);
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      get(buttons.REJECT).clickButton();
      get(inputs.ON_REVISION_COMMENT).sendKeysInInput("test comment on revision");
      get(buttons.REJECT_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      assertNotNull(get(labels.STATUS_REJECTED).getWebElement(), "Object is null. ");
      logOut();

   }


   @Test(enabled = true, description = "Пропуск автора. РП автор заявки", priority = 201310)
   @Description("Тест создает заявку. Согласования РП пропускаются тк он является автором. Руководитель ЦФО отклоняет заявку.")
   @Step("Пропуск автора. РКБ автор заявки")
   @Severity(CRITICAL)
   @Feature(feature)
   @Story(story)
   public void projectManagerPassPMAutor() {
      String requestNumber = createRequest(
              Roles.roles.JACK7,
              "AutoTestCompany_1",
              "ATFinRresponsibility_2",
              "ATCostCenter_2",
              "ATContractor_1",
              "ATProject_2",
              "ATCost_2");

      loginTest(HomePage.getURL(), Roles.roles.JACK2);
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      get(buttons.SAVE).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      get(buttons.ON_AFFIRMATION).clickButton();
      get(buttons.ON_AFFIRMATION_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      logOut();

      loginTest(HomePage.getURL(), Roles.roles.JACK3);
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      get(buttons.AFFIRM).clickButton();
      get(buttons.AFFIRM_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      logOut();

      loginTest(HomePage.getURL(), Roles.roles.JACK2);
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      get(buttons.REJECT).clickButton();
      get(inputs.ON_REVISION_COMMENT).sendKeysInInput("test comment on revision");
      get(buttons.REJECT_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      assertNotNull(get(labels.STATUS_REJECTED).getWebElement(), "Object is null. ");
      logOut();

   }

   @Test(enabled = true, description = "Наблюдатель переназначает заявку", priority = 201311)
   @Description("Тест создает заявку. Автор назначает наблюдателя. Наблюдатель переназначает заявку на себя.")
   @Step("Наблюдатель переназначает заявку")
   @Severity(CRITICAL)
   @Feature(feature)
   @Story(story)
   public void reviewerReassignsRequest() {

      loginTest(HomePage.getURL(), Roles.roles.JACK);
      HomePage.get(HomePage.buttons.PAYMENT_REQUESTS).clickButton(false, true);
      PaymentPage.get(PaymentPage.buttons.CREATE).clickButton(true, true);
      String requestNumber = RequestPage.get(RequestPage.labels.REQUEST_NUM).getWebElement().getText();
      requestNumber = requestNumber.substring(requestNumber.indexOf("№") + 1);
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);

      get(inputs.PAYMENT_DISCRIPTION).sendKeysInInput("test description " + TEST_RUN_ID);
      get(enums.COMPANY_NAME).clickOnEnumItem("AutoTestCompany_1");
      get(enums.FINANCIAL_NAME).clickOnEnumItem("ATFinRresponsibility_2");
      get(enums.COST_CENTER_NAME).clickOnEnumItem("ATCostCenter_2");
      get(enums.CONTRACTORS_TYPE_NAME).clickOnEnumItem("ВГО Тим");
      get(enums.CONTRACTORS_NAME).clickOnEnumItem("ATContractor_1");
      get(enums.CONTRACT_NUMBER).clickOnEnumItem("По счёту, без договора");
      get(enums.COST_DOCUMENT_TYPE).clickOnEnumItem("Счет");
      get(inputs.COST_DOCUMENT_NUMBER).sendKeysInInput(TEST_RUN_ID);
      get(inputs.DOC_DATA).sendKeysInInput("1111" + (Year.now().getValue() + 1));
      get(inputs.FILE_PATH).getWebElement().sendKeys(ALLURE_RESULTS_PATH + "/temp/test_doc.txt");
      get(enums.PROJECT).clickOnEnumItem("ATProject_2");
      get(enums.TASK).clickOnEnumItem("Вне задачи");
      get(enums.EXPENCES_TASK).clickOnEnumItem("ATCost_2");
      get(inputs.PROJECT_TASKS).sendKeysInInput("test task " + TEST_RUN_ID);
      get(inputs.VALUE).sendKeysInInput(TEST_RUN_ID + ".99");
      get(enums.CURRENCY).clickOnEnumItem("USD");
      get(dates.COST_DATA).setRandomActiveDate();
      get(buttons.REVIEWERS).clickButton();
      get(enums.REVIEWERS).clickOnEnumItem("ATM TechTestUser");
      get(buttons.AFFIRM_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      get(buttons.SAVE).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      get(buttons.REGISTRATION).clickButton();
      get(buttons.REGISTRATION_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      logOut();

      loginTest(HomePage.getURL(), Roles.roles.JACK7);
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      get(buttons.APPROV).clickButton();
      get(buttons.APPROV_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      logOut();

      loginTest(HomePage.getURL(), Roles.roles.JACK2);
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      get(buttons.ON_REVISION).clickButton();
      get(inputs.ON_REVISION_COMMENT).sendKeysInInput("test comment on revision");
      get(buttons.ON_REVISION_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      logOut();


      loginTest(HomePage.getURL(), Roles.roles.BIG_JACK);
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      get(buttons.ASSIGN_TO_YOURSELF).clickButton();
      try {
         Thread.sleep(6000);
      } catch (InterruptedException e) {
         throw new RuntimeException(e);
      }
      get(buttons.REJECT).clickButton();
      get(inputs.ON_REVISION_COMMENT).sendKeysInInput("test comment on revision");
      get(buttons.REJECT_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      assertNotNull(get(labels.STATUS_REJECTED).getWebElement(), "Object is null. ");
      logOut();

   }


   @Test(enabled = true, description = "Пропуск статуса по триггеру ЦФО\n", priority = 201312)
   @Description("Тест создает заявку. Согласования АБ, РКБ пропускаются (пропуск РКБ происходит из-за того что id ЦФО не зарегестрировано в воркфлоу). Согласование экономистом пропускается (автор заявки). Руководитель ЦФО отправляет заявку на доработку. После доработки экономист (автор) отклоняет заявку.")
   @Step("Блокировка пропуска автора")
   @Severity(CRITICAL)
   @Feature(feature)
   @Story(story)
   public void finResponsibilytyTriggerPassTest() {
      String requestNumber = createRequest(
              Roles.roles.JACK2,
              "AutoTestCompany_1",
              "ATFinRresponsibility_NotRegistered",
              "ATCostCenter_NotRegistered",
              "ATContractor_1",
              "ATProject_4",
              "ATCost_NotRegistered");

      loginTest(HomePage.getURL(), Roles.roles.JACK3);
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      get(buttons.ON_REVISION).clickButton();
      get(inputs.ON_REVISION_COMMENT).sendKeysInInput("test comment on revision");
      get(buttons.ON_REVISION_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      logOut();

      loginTest(HomePage.getURL(), Roles.roles.JACK2);
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      get(buttons.SAVE).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      get(buttons.REGISTRATION).clickButton();
      get(buttons.REGISTRATION_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      get(buttons.REJECT).clickButton();
      get(inputs.ON_REVISION_COMMENT).sendKeysInInput("test comment on revision");
      get(buttons.REJECT_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      assertNotNull(get(labels.STATUS_REJECTED).getWebElement(), "Object is null. ");
      logOut();

   }


   @Test(enabled = true, description = "Проверка доступности элементов для заполнения", priority = 201313)
   @Description("Тест создает оплаченную заявку. Заявка проходит все статусы.")
   @Step("Создать оплаченную заявку")
   @Severity(CRITICAL)
   @Feature(feature)
   @Story(story)
   public void elementsStatusCheck() {

      loginTest(HomePage.getURL(), Roles.roles.JACK);
      HomePage.get(HomePage.buttons.PAYMENT_REQUESTS).clickButton(false, true);
      PaymentPage.get(PaymentPage.buttons.CREATE).clickButton(true, true);
      String requestNumber = RequestPage.get(RequestPage.labels.REQUEST_NUM).getWebElement().getText();
      requestNumber = requestNumber.substring(requestNumber.indexOf("№") + 1);
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);


      get(inputs.PAYMENT_DISCRIPTION).sendKeysInInput("test description " + TEST_RUN_ID);
      get(enums.COMPANY_NAME).clickOnEnumItem("AutoTestCompany_1");
      get(enums.FINANCIAL_NAME).clickOnEnumItem("ATFinRresponsibility_1");
      get(enums.COST_CENTER_NAME).clickOnEnumItem("ATCostCenter_1");
      get(enums.CONTRACTORS_TYPE_NAME).clickOnEnumItem("ВГО Тим");
      get(enums.CONTRACTORS_NAME).clickOnEnumItem("ATContractor_1");
      get(enums.CONTRACT_NUMBER).clickOnEnumItem("По счёту, без договора");
      get(enums.COST_DOCUMENT_TYPE).clickOnEnumItem("Счет");
      get(inputs.COST_DOCUMENT_NUMBER).sendKeysInInput(TEST_RUN_ID);
      get(inputs.DOC_DATA).sendKeysInInput("1111" + (Year.now().getValue() + 1));
      get(inputs.FILE_PATH).getWebElement().sendKeys(ALLURE_RESULTS_PATH + "/temp/test_doc.txt");
      get(enums.PROJECT).clickOnEnumItem("ATProject_1");
      get(enums.TASK).clickOnEnumItem("Вне задачи");
      get(enums.EXPENCES_TASK).clickOnEnumItem("ATCost_1");
      get(inputs.PROJECT_TASKS).sendKeysInInput("test task " + TEST_RUN_ID);
      get(inputs.VALUE).sendKeysInInput(TEST_RUN_ID + ".99");
      get(enums.CURRENCY).clickOnEnumItem("USD");
      get(dates.COST_DATA).setRandomActiveDate();
      checkElements(true,
              true,
              true,
              true,
              true,
              true,
              true,
              true,
              true,
              true,
              true,
              true,
              true,
              true,
              true,
              true,
              true,
              true,
              true,
              true,
              true);
      get(buttons.SAVE).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      get(buttons.REGISTRATION).clickButton();
      get(buttons.REGISTRATION_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      logOut();


      loginTest(HomePage.getURL(), Roles.roles.JACK8);
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      checkElements(false,
              false,
              true,
              true,
              true,
              true,
              true,
              true,
              true,
              true,
              true,
              true,
              true,
              true,
              true,
              true,
              true,
              true,
              true,
              true,
              true);
      get(buttons.SAVE).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      get(buttons.ON_APPROVAL).clickButton();
      get(buttons.ON_APPROVAL_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      logOut();

      loginTest(HomePage.getURL(), Roles.roles.JACK6);
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      checkElements(false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              true,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false);
      get(buttons.APPROV).clickButton();
      get(buttons.APPROV_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      logOut();

      loginTest(HomePage.getURL(), Roles.roles.JACK7);
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      checkElements(false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              true,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false);
      get(buttons.APPROV).clickButton();
      get(buttons.APPROV_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      logOut();

      loginTest(HomePage.getURL(), Roles.roles.JACK2);
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      checkElements(false,
              false,
              false,
              true,
              true,
              true,
              true,
              true,
              true,
              true,
              true,
              true,
              true,
              true,
              false,
              true,
              false,
              true,
              true,
              true,
              true);
      get(buttons.SAVE).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      get(buttons.ON_AFFIRMATION).clickButton();
      get(buttons.ON_AFFIRMATION_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      logOut();

      loginTest(HomePage.getURL(), Roles.roles.JACK3);
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      checkElements(false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              true,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false);
      get(buttons.AFFIRM).clickButton();
      get(buttons.AFFIRM_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      logOut();

      loginTest(HomePage.getURL(), Roles.roles.JACK2);
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      checkElements(false,
              false,
              false,
              true, // Поменять на false !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              true,
              false,
              false,
              false,
              false,
              false,
              true,
              false,
              true);
      get(buttons.ON_PAYMENT).clickButton();
      get(buttons.ON_PAYMENT_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      logOut();

      loginTest(HomePage.getURL(), Roles.roles.JACK4);
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      checkElements(false,
              false,
              false,
              false,// Поменять на true !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              true,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false);
      get(buttons.REGISTR).clickButton();
      get(buttons.REGISTR_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      logOut();

      loginTest(HomePage.getURL(), Roles.roles.JACK2);
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      checkElements(false,
              false,
              false,
              true, // Поменять на false !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              true,
              false,
              false,
              false,
              false,
              false,
              true,
              false,
              true);
      get(buttons.ADD_PAYMENT).clickButton();
      get(inputs.REQUEST_NUMBER_1C).sendKeysInInput(TEST_RUN_ID);
      get(inputs.COST_DATA_1C).sendKeysInInput("1212" + (Year.now().getValue() + 1));
      get(buttons.ADD_PAYMENT_SUBMIT).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      assertNotNull(get(labels.STATUS_PAID).getWebElement(), "Object is null. ");
      logOut();
   }

   @Test(enabled = true, description = "Проверка комментариев главного экономиста", priority = 201314)
   @Description("Тест создает заявку. К заявки добавляется комментарий и проверяетсяя его наличие.")
   @Step("Проверка комментариев главного экономиста")
   @Severity(CRITICAL)
   @Feature(feature)
   @Story(story)
   public void headEconomistCommentaire() {

      loginTest(HomePage.getURL(), Roles.roles.JACK5);
      HomePage.get(HomePage.buttons.PAYMENT_REQUESTS).clickButton(false, true);
      PaymentPage.get(PaymentPage.buttons.CREATE).clickButton(true, true);
      String requestNumber = RequestPage.get(RequestPage.labels.REQUEST_NUM).getWebElement().getText();
      requestNumber = requestNumber.substring(requestNumber.indexOf("№") + 1);
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      get(inputs.PAYMENT_DISCRIPTION).sendKeysInInput("test description " + TEST_RUN_ID);
      get(enums.COMPANY_NAME).clickOnEnumItem("AutoTestCompany_1");
      get(enums.FINANCIAL_NAME).clickOnEnumItem("ATFinRresponsibility_1");
      get(enums.COST_CENTER_NAME).clickOnEnumItem("ATCostCenter_1");
      get(enums.CONTRACTORS_TYPE_NAME).clickOnEnumItem("ВГО Тим");
      get(enums.CONTRACTORS_NAME).clickOnEnumItem("ATContractor_1");
      get(enums.CONTRACT_NUMBER).clickOnEnumItem("По счёту, без договора");
      get(enums.COST_DOCUMENT_TYPE).clickOnEnumItem("Счет");
      get(inputs.COST_DOCUMENT_NUMBER).sendKeysInInput(TEST_RUN_ID);
      get(inputs.DOC_DATA).sendKeysInInput("1111" + (Year.now().getValue() + 1));
      get(inputs.FILE_PATH).getWebElement().sendKeys(ALLURE_RESULTS_PATH + "/temp/test_doc.txt");
      get(enums.PROJECT).clickOnEnumItem("ATProject_1");
      get(enums.TASK).clickOnEnumItem("Вне задачи");
      get(enums.EXPENCES_TASK).clickOnEnumItem("ATCost_1");
      get(inputs.PROJECT_TASKS).sendKeysInInput("test task " + TEST_RUN_ID);
      get(inputs.VALUE).sendKeysInInput(TEST_RUN_ID + ".99");
      get(enums.CURRENCY).clickOnEnumItem("USD");
      get(dates.COST_DATA).setRandomActiveDate();
      get(buttons.SAVE).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      PaymentPage.open();
      PaymentPage.get(PaymentPage.buttons.DROP_ARROW).clickButton();
      PaymentPage.get(PaymentPage.buttons.VIEW_ALL).clickButton();
      List<WebElement> tableRows = PaymentPage.get(PaymentPage.tables.REQUEST).findTableString("№", requestNumber);
      if (tableRows.size() == 1) {
         BaseElement.clickElement(tableRows.get(0), false, true);
      }
      get(buttons.ADD_COMMENT).clickButton();
      ArrayList<WebElement> addresse = get(enums.ADDRESSEE).getEnumItems();
      assertTrue(addresse.size() > 0);
      get(enums.ADDRESSEE).clickOnEnumItem("Bob Budget Manager");
      get(inputs.COMMENT).sendKeysInInput("test comment for Bob Budget Manager " + TEST_RUN_ID);
      get(inputs.COMMENT_FILE_PATH).getWebElement().sendKeys(ALLURE_RESULTS_PATH + "/temp/test_doc.txt");
      get(buttons.CONFIRM).clickButton();
      get(buttons.SAVE).clickButton();
      get(labels.TOASTIFY_SUCCESS).getWebElement();
      logOut();

      loginTest(HomePage.getURL(), Roles.roles.JACK8);
      BasePage.open(PageURL.PAYMENT + "/" + requestNumber);
      assertNotNull(get(labels.COMMENT).getWebElement());
      assertNotNull(get(buttons.COMMENT_DOWNLOAD_FILE).getWebElement());
      logOut();

   }

   private void checkElements(boolean company, boolean finance_center, boolean cost_center_id, boolean description, boolean memo_number, boolean memo_date, boolean stream, boolean contractor_id, boolean contract_id, boolean payment_type_id, boolean payment_number, boolean payment_date, boolean file_upload, boolean file_delete, boolean project_id, boolean subject_id, boolean expense_id, boolean additional_analytics, boolean total_amount, boolean currency, boolean payment_deadline) {
      Allure.step("Проверка элементов на доступноть к действиям", step -> {
         checkElement(get(enums.COMPANY_NAME), company);
         checkElement(get(enums.FINANCIAL_NAME), finance_center);
         checkElement(get(enums.COST_CENTER_NAME), cost_center_id);
         checkElement(get(inputs.PAYMENT_DISCRIPTION), description);
//      assertEquals(get(enums.COMPANY_NAME).isEnable(), memo_number);
//      assertEquals(get(enums.COMPANY_NAME).isEnable(), memo_date);
         checkElement(get(enums.CONTRACTORS_TYPE_NAME), stream);
         checkElement(get(enums.CONTRACTORS_NAME), contractor_id);
         checkElement(get(enums.CONTRACT_NUMBER), contract_id);
         checkElement(get(enums.COST_DOCUMENT_TYPE), payment_type_id);
         checkElement(get(inputs.COST_DOCUMENT_NUMBER), payment_number);
         checkElement(get(inputs.DOC_DATA), payment_date);
         assertEquals(get(buttons.ATTACH_FILE).isElemenExist(), file_upload);
         assertEquals(get(buttons.DELETE_FILE).isElemenExist(), file_delete);
         checkElement(get(enums.PROJECT), project_id);
         checkElement(get(enums.TASK), subject_id);
         checkElement(get(enums.EXPENCES_TASK), expense_id);
         checkElement(get(inputs.PROJECT_TASKS), additional_analytics);
         checkElement(get(inputs.VALUE), total_amount);
         checkElement(get(enums.CURRENCY), currency);
         checkElement(get(dates.COST_DATA), payment_deadline);
      });
   }

   private void checkElement(Enabled element, boolean value) {
      Allure.step("Проверить элемент " + ((BaseElement) element).getElementName(), step -> {
         assertEquals(element.isEnable(), value);
      });
   }

   @AfterMethod(enabled = true,
           description = "Выход с текущего пользователя",
           alwaysRun = true)
   @Severity(CRITICAL)
   public static void logOut() {
      if (isLogin())
         LoginTest.logOut();
   }

   @AfterClass(alwaysRun = true, description = "После тестов статусов заявок")
   public void afterClass() {
      try {
         Log.printClassTitle(getClass().getSimpleName());
         Log.textLevelON();
      } catch (Exception ex) {
         attachScreenshotPNG();
         assertTrue(false);
      } finally {
         Log.textLevelOFF();
      }
   }
}
