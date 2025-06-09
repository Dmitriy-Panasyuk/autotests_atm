package pages.elements;

import elements.ButtonElement;
import org.openqa.selenium.By;
import pages.BasePage;
import pages.PageURL;

import java.util.ArrayList;

import static common.CommonActions.getDriver;

public class Nbsp extends BasePage {
    private static String URL = PageURL.NON_BREAKING_SPACE;

    public enum inputs {

    }

    public enum buttons {
        BUTTON
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
            case BUTTON:
                bys.add(By.tagName("*"));
                return new ButtonElement(bys,"My Button",true, "Кнопка");
        }
        return new ButtonElement(bys);
    }
}

