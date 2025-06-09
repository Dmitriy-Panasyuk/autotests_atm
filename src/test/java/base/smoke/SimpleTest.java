package base.smoke;

import common.CommonActions;
import elements.InputElement;
import elements.TableElement;
import io.qameta.allure.*;
import listeners.Listner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.elements.*;
import utils.Log;

import static io.qameta.allure.SeverityLevel.NORMAL;
import static org.testng.Assert.*;
import static utils.AllureAttachmentTools.attachScreenshotPNG;

@Test(enabled = true, priority = 3)
@Listeners({Listner.class})
public class SimpleTest {

    @BeforeClass(description = "Предусловия")
    public void beforeClass() {
        try {
            Log.printClassTitle(getClass().getSimpleName());
        } catch (Exception ex) {
            attachScreenshotPNG();
            assertTrue(false);
        }
    }


    @Test(enabled = true, description = "Спрятанный слой с кнопкой.", priority = 3001)
    @Description("Тест входит на страницу, нажимает зеленую кнопку, проверяет что появилась синяя кнопка, нажимает синюю кнопку.")
    @Step("Спрятанный слой с кнопкой")
    @Severity(NORMAL)
    @Feature("Smoke тесты")
    @Story("Простые тесты")
    public void hiddenLayers() {
        HomePage.open();
        HomePage.get(HomePage.buttons.DYNAMIC_ID).clickButton();
        HiddenLayers.open();
        HiddenLayers.get(HiddenLayers.buttons.GREEN).clickButton();
        WebElement blueButton = HiddenLayers.get(HiddenLayers.buttons.BLUE).getWebElement();
        assertNotNull(blueButton);
        blueButton.click();
    }

    @Test(enabled = true, description = "Страница с задержкой загрузки.", priority = 3002)
    @Description("Тест входит на страницу, ожидает пока страница загрузится, проверяет наличие кнопки.")
    @Step("Страница с задержкой загрузки")
    @Severity(NORMAL)
    @Feature("Smoke тесты")
    @Story("Простые тесты")
    public void loadDelay() {
        HomePage.open();
        HomePage.get(HomePage.buttons.LOAD_DELAY).clickButton();
        WebElement button = LoadDelay.get(LoadDelay.buttons.BUTTON).getWebElement();
        assertNotNull(button);
    }

    @Test(enabled = true, description = "Страница с AJAX.", priority = 3003)
    @Description("Тест входит на страницу, нажимает кнопку активирующую AJAX запрос, ожидает и проверяет наличие текста.")
    @Step("Страница с AJAX")
    @Severity(NORMAL)
    @Feature("Smoke тесты")
    @Story("Простые тесты")
    public void ajaxRequestWait() {
        HomePage.open();
        HomePage.get(HomePage.buttons.AJAX_DATA).clickButton();
        Ajax.get(Ajax.buttons.AJAX).clickButton();
        WebElement lable = Ajax.get(Ajax.labels.AJAX_SUCCESS).getWebElement();
        assertNotNull(lable);
    }

    @Test(enabled = true, description = "Страница с js задержкой.", priority = 3003)
    @Description("Тест входит на страницу, нажимает кнопку активирующую js скрипт, ожидает и проверяет наличие текста.")
    @Step("Страница с js задержкой")
    @Severity(NORMAL)
    @Feature("Smoke тесты")
    @Story("Простые тесты")
    public void clientDelay() {
        HomePage.open();
        HomePage.get(HomePage.buttons.CLIENT_SIDE_DELAY).clickButton();
        ClientDelay.get(ClientDelay.buttons.JS).clickButton();
        WebElement lable = ClientDelay.get(ClientDelay.labels.JS_SUCCESS).getWebElement();
        assertNotNull(lable);
    }

    @Test(enabled = true, description = "Кнопка с изменяемым классом.", priority = 3004)
    @Description("Тест входит на страницу, нажимает на кнопку, проверяет что кнопка обновилась")
    @Step("Кнопка с изменяемым классом")
    @Severity(NORMAL)
    @Feature("Smoke тесты")
    @Story("Простые тесты")
    public void click() {
        HomePage.open();
        HomePage.get(HomePage.buttons.CLICK).clickButton();
        Click.get(Click.buttons.PRIMARY).clickButton();
        WebElement button = Click.get(Click.buttons.SUCCESS).getWebElement();
        assertNotNull(button);
    }

    @Test(enabled = true, description = "Изменить текст кнопки.", priority = 3005)
    @Description("Тест входит на страницу, вводит новое название кнопки, проверяет что кнопка обновилась")
    @Step("Изменить текст кнопки")
    @Severity(NORMAL)
    @Feature("Smoke тесты")
    @Story("Простые тесты")
    public void newButtonText() {
        HomePage.open();
        HomePage.get(HomePage.buttons.TEXT_INPUT).clickButton();
        String expectedText = "New Button Text";
        TextInput.get(TextInput.inputs.NEW_BUTTON_NAME).sendKeysInInput(expectedText);
        TextInput.get(TextInput.buttons.BUTTON).clickButton();
        String actualText = TextInput.get(TextInput.buttons.BUTTON).getWebElement().getText();
        assertEquals(actualText, expectedText);
    }

    @Test(enabled = true, description = "Нажать на скрытую кнопку.", priority = 3006)
    @Description("Тест входит на страницу, вводит новое название кнопки, проверяет что кнопка обновилась")
    @Step("Изменить текст кнопки")
    @Severity(NORMAL)
    @Feature("Smoke тесты")
    @Story("Простые тесты")
    public void сlickHidingButton() {
        HomePage.open();
        HomePage.get(HomePage.buttons.SCROLBARS).clickButton();
        Scrollbars.get(Scrollbars.buttons.BUTTON).clickButton();
    }

    @Test(enabled = true, description = "Поиск в динамической таблице.", priority = 3007)
    @Description("Тест входит на страницу, находит лэйбл с искомыми значениями и проверяет эти значения в таблице")
    @Step("Поиск в динамической таблице")
    @Severity(NORMAL)
    @Feature("Smoke тесты")
    @Story("Простые тесты")
    public void dynamicTable() {
        HomePage.open();
        HomePage.get(HomePage.buttons.DYNAMIC_TABLE).clickButton();
        String lableText = DynamicTable.get(DynamicTable.labels.LABLE).getWebElement().getText().trim();
        String[] values = lableText.split(" ");
        values[1] = values[1].replace(":", "");
        TableElement table = DynamicTable.get(DynamicTable.tables.TABLE);
        boolean flag = false;
        for (WebElement row : table.getTableItemsV1()) {
            if (row.getText().contains(values[0])) {
                for (WebElement cell : row.findElements(By.cssSelector("[role='cell']"))) {
                    if (cell.getText().equals(values[2])) {
                        flag = true;
                        break;
                    }
                }
            }
        }
        Log.println(flag);
        assertTrue(flag);
    }

    @Test(enabled = true, description = "Найти лэйбл в сложной структуре.", priority = 3008)
    @Description("Тест входит на страницу, и ищет элемент используя родительский элемент")
    @Step("Найти лэйбл в сложной структуре")
    @Severity(NORMAL)
    @Feature("Smoke тесты")
    @Story("Простые тесты")
    public void findElementWithText() {
        HomePage.open();
        HomePage.get(HomePage.buttons.VERIFI_TEXT).clickButton();
        WebElement webElement = VerifyText.get(VerifyText.labels.LABLE).getWebElement();
        assertNotNull(webElement);
        String actualText = webElement.getText();
        Log.println("actualText  : " + actualText);
    }

    @Test(enabled = true, description = "Остановить Progress Bar.", priority = 3009)
    @Description("Тест входит на страницу, запускает прогресс бар, ждет значения 75% и останавливает его.")
    @Step("Остановить Progress Bar")
    @Severity(NORMAL)
    @Feature("Smoke тесты")
    @Story("Простые тесты")
    public void stopProgressBar() {
        HomePage.open();
        HomePage.get(HomePage.buttons.PROGRESS_BAR).clickButton();
        ProgressBar.get(ProgressBar.buttons.START).clickButton();
        WebElement webElement = ProgressBar.get(ProgressBar.labels.PROGRESS).getWebElement();
        do {
        } while (!webElement.getAttribute("aria-valuenow").equals("75"));

        ProgressBar.get(ProgressBar.buttons.STOP).clickButton();
        Log.println(ProgressBar.get(ProgressBar.labels.RESULT).getWebElement().getText());

        //wait.until работает медленнее
//        ProgressBar.get(ProgressBar.buttons.START).clickButton();
//        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(360));
//        wait.until(ExpectedConditions.attributeToBe(webElement,"aria-valuenow","75"));
//        ProgressBar.get(ProgressBar.buttons.STOP).clickButton();
//        Log.println(ProgressBar.get(ProgressBar.labels.RESULT).getWebElement().getText());
    }

    @Test(enabled = true, description = "Попытаться нажать на скрытые кнопки.", priority = 3010)
    @Description("Тест входит на страницу, проверяет видимость и пытается нажать на кнопки если их находит.")
    @Step("Попытаться нажать на скрытые кнопки")
    @Severity(NORMAL)
    @Feature("Smoke тесты")
    @Story("Простые тесты")
    public void asd() {
        HomePage.open();
        HomePage.get(HomePage.buttons.VISIBILITY).clickButton();
        Visibility.get(Visibility.buttons.HIDE).clickButton();
        try {
            Visibility.get(Visibility.buttons.REMOVED).clickButton();
        } catch (Exception ex) {
        }
        try {
            Visibility.get(Visibility.buttons.ZERO_WIDTH).clickButton();
        } catch (Exception ex) {
        }
        try {
            Visibility.get(Visibility.buttons.OWERLAPED).clickButton();
        } catch (Exception ex) {
        }
        try {
            Visibility.get(Visibility.buttons.OPACITY_0).clickButton();
        } catch (Exception ex) {
        }
        try {
            Visibility.get(Visibility.buttons.HIDDEN).clickButton();
        } catch (Exception ex) {
        }
        try {
            Visibility.get(Visibility.buttons.DISPLAY_NONE).clickButton();
        } catch (Exception ex) {
        }
        try {
            Visibility.get(Visibility.buttons.OFFSCREEN).clickButton();
        } catch (Exception ex) {
        }
    }

    @Test(enabled = true, description = "Логин.", priority = 3011)
    @Description("Тест входит на страницу, вводит логин и пароль, проверяет статус логина")
    @Step("Логин")
    @Severity(NORMAL)
    @Feature("Smoke тесты")
    @Story("Простые тесты")
    public void login() {
        HomePage.open();
        HomePage.get(HomePage.buttons.SAMPLE_APP).clickButton();
        String userName = "user";
        SampleApp.get(SampleApp.inputs.LOGIN).sendKeysInInput(userName);
        SampleApp.get(SampleApp.inputs.PASSWORD).sendKeysInInput("pwd");
        SampleApp.get(SampleApp.buttons.LOG_IN).clickButton();
        String actualText = SampleApp.get(SampleApp.labels.STATUS).getWebElement().getText();
        String expectedText = "Welcome, " + userName + "!";
        Log.println("actualText   : " + actualText);
        Log.println("expectedText : " + expectedText);

        assertEquals(actualText, expectedText);
    }

    @Test(enabled = true, description = "Элемент меняется при наведении.", priority = 3012)
    @Description("Тест входит на страницу, нажимает на кнопку которая меняется в DOM при наведении на нее")
    @Step("Элемент меняется при наведении")
    @Severity(NORMAL)
    @Feature("Smoke тесты")
    @Story("Простые тесты")
    public void chengedElement() {
        String clicked;
        HomePage.open();
        HomePage.get(HomePage.buttons.MOUSE_OVER).clickButton();
        for (int i = 0; i < 2; i++) MouseOver.get(MouseOver.buttons.CLICK_ME).clickButton();
        clicked = MouseOver.get(MouseOver.labels.CLICK_ME_ENUMERATOR).getWebElement().getText();
        Log.println("Количество нажатий: " + clicked);
        for (int i = 0; i < 2; i++) MouseOver.get(MouseOver.buttons.LINK_BUTTON).clickButton();
        clicked = MouseOver.get(MouseOver.labels.LINK_BUTTON_ENUMERATOR).getWebElement().getText();
        Log.println("Количество нажатий: " + clicked);
    }

    @Test(enabled = true, description = "Поиск элемента с nbps.", priority = 3013)
    @Description("Тест входит на страницу, нажимает на кнопку поиск которой происходит по тексту(текст содержит nbps)")
    @Step("Поиск элемента с nbps")
    @Severity(NORMAL)
    @Feature("Smoke тесты")
    @Story("Простые тесты")
    public void nbps() {
        HomePage.open();
        HomePage.get(HomePage.buttons.NON_BREAKING_SPACE).clickButton();
        WebElement button = Nbsp.get(Nbsp.buttons.BUTTON).getWebElement();
        Log.println(button.getText());
        button.click();
    }

    @Test(enabled = false, description = "Ввод текста в полузакрытое поле.", priority = 3014)
    @Description("Тест входит на страницу, нажимает на кнопку поиск которой происходит по тексту(текст содержит nbps)")
    @Step("Ввод текста в полузакрытое поле")
    @Severity(NORMAL)
    @Feature("Smoke тесты")
    @Story("Простые тесты")
    public void overlapped() {
        HomePage.open();
        HomePage.get(HomePage.buttons.OVERLOPPED).clickButton();
        Overlapped.get(Overlapped.inputs.ID).sendKeysInInput("NEW TEST ID");
        attachScreenshotPNG();
        InputElement inputElement =  Overlapped.get(Overlapped.inputs.NAME);
        WebElement webElement = inputElement.getWebElement();
        Actions action = new Actions(CommonActions.getDriver());
        action.moveToElement(webElement);
        action.scrollFromOrigin(WheelInput.ScrollOrigin.fromElement(webElement),100,100);
        action.build();
        action.perform();
        inputElement.sendKeysInInput("NEW TEST NAME");
        attachScreenshotPNG();
    }

    @AfterClass(alwaysRun = true, description = "Постусловия")
    public void afterClass() {
        try {
            Log.printClassTitle(getClass().getSimpleName());
        } catch (Exception ex) {
            attachScreenshotPNG();
            assertTrue(false);
        }
    }
}
