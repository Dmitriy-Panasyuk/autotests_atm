package pages.elements;

import elements.ButtonElement;
import org.openqa.selenium.By;
import pages.BasePage;
import pages.PageURL;

import java.util.ArrayList;

import static common.CommonActions.getDriver;

public class DynamicID {
    private static String URL = PageURL.DYNAMIC_ID;

    public enum inputs {

    }

    public enum buttons {
        DYNAMIC_ID
    }

    public enum labels {

    }

    /**
     * Открыть страницу
     */
    public static void open() {
        BasePage.open(URL);
    }
    public static void refreshPage() {
        getDriver().navigate().refresh();
    }

    /**
     * Отдает URL страницы
     *
     * @return
     */
    public static String getURL() {
        return URL;
    }

    /**
     * Отдает указаный элемент
     *
     * @param element
     * @return
     */
    public static ButtonElement get(buttons element) {
        ArrayList<By> bys = new ArrayList<>();
        switch (element) {
            case DYNAMIC_ID:
                bys.add(By.cssSelector("[class*='btn-primary']"));
                return new ButtonElement(bys,  "Кнопка с динамическим ID");
        }
        return new ButtonElement(bys);
    }
}
