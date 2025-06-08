package base.smoke;

import io.qameta.allure.*;
import listeners.Listner;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.elements.Multiclass;
import utils.Log;

import static io.qameta.allure.SeverityLevel.NORMAL;
import static org.testng.Assert.*;
import static utils.AllureAttachmentTools.attachScreenshotPNG;

@Test(enabled = true, priority = 2)
@Listeners({Listner.class})
public class MulticlassTest {
    @BeforeClass(description = "Предусловия")
    public void beforeClass() {
        try {
            HomePage.open();
            HomePage.get(HomePage.buttons.MULTICLASS).clickButton();
            Log.printClassTitle(getClass().getSimpleName());
        } catch (Exception ex) {
            attachScreenshotPNG();
            assertTrue(false);
        }
    }


    @Test(enabled = true, description = "Найти кнопку WARNING на странице.", priority = 3)
    @Description("Тест входит на страницу с кнопкой и проверяет ее наличие. Кнопка имеет более одного элемента класса.")
    @Step("Найти кнопку на странице.")
    @Severity(NORMAL)
    @Feature("Smoke тесты")
    @Story("Простые тесты")
    public void findeButtonWarning() {
        assertNotNull(Multiclass.get(Multiclass.buttons.WARNING).getWebElement());
    }

    @Test(enabled = true, description = "Найти кнопку SUCCESS на странице.", priority = 2)
    @Description("Тест входит на страницу с кнопкой, проверяет ее наличие, проверяет что кнопка имеет класс отличный от того что применялся для поиска. Кнопка имеет более одного элемента класса.")
    @Step("Найти кнопку на странице.")
    @Severity(NORMAL)
    @Feature("Smoke тесты")
    @Story("Простые тесты")
    public void findeButtonSuccess() {
        WebElement webElement = Multiclass.get(Multiclass.buttons.SUCCESS).getWebElement();
        assertNotNull(webElement);
    }

    @Test(enabled = true, description = "Найти кнопку PRIMARY на странице.", priority = 1)
    @Description("Тест входит на страницу с кнопкой и проверяет ее наличие. Кнопка имеет более одного элемента класса.")
    @Step("Найти кнопку на странице.")
    @Severity(NORMAL)
    @Feature("Smoke тесты")
    @Story("Простые тесты")
    public void findeButtonPrimary() {
        assertNotNull(Multiclass.get(Multiclass.buttons.PRIMARY).getWebElement());
    }

    @AfterClass(alwaysRun = true, description = "Постусловия")
    public void logout() {
        try {
            Log.printClassTitle(getClass().getSimpleName());
        } catch (Exception ex) {
            attachScreenshotPNG();
            assertTrue(false);
        }
    }
}
