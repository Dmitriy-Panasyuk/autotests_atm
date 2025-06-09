package base.smoke;

import io.qameta.allure.*;
import listeners.Listner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.elements.DynamicID;
import pages.HomePage;
import utils.Log;

import static io.qameta.allure.SeverityLevel.NORMAL;
import static org.testng.Assert.*;
import static utils.AllureAttachmentTools.attachScreenshotPNG;

@Test(enabled = true, priority = 1)
@Listeners({Listner.class})
public class ButtonDynamicIDTest {

    @BeforeClass(description = "Предусловия")
    public void beforeClass() {
        try {
            Log.printClassTitle(getClass().getSimpleName());
        } catch (Exception ex) {
            attachScreenshotPNG();
            assertTrue(false);
        }
    }


    @Test(enabled = true, description = "Нажать на кнопку с динамическим id.", priority = 1001)
    @Description("Тест входит на страницу с кнопкой, презагружает страницу, проверяет что у кнопки поменялся id.")
    @Step("Нажать на кнопку с динамическим id.")
    @Severity(NORMAL)
    @Feature("Smoke тесты")
    @Story("Простые тесты")
    public void dynamicIDButton() {
        HomePage.open();
        HomePage.get(HomePage.buttons.DYNAMIC_ID).clickButton();
        String initialID = DynamicID.get(DynamicID.buttons.DYNAMIC_ID).getWebElement().getAttribute("id");
        DynamicID.refreshPage();
        String changedID = DynamicID.get(DynamicID.buttons.DYNAMIC_ID).getWebElement().getAttribute("id");
        Log.println("Исходный id      :" + initialID);
        Log.println("id после нажатия :" + changedID);
        assertNotEquals(initialID,changedID);
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
