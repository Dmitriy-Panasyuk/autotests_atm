package pages.elements;

import elements.ButtonElement;
import elements.LabelElement;
import org.openqa.selenium.By;
import pages.BasePage;
import pages.PageURL;

import java.util.ArrayList;

import static common.CommonActions.getDriver;

public class MouseOver extends BasePage {
    private static String URL = PageURL.MOUSE_OVER;

    public enum inputs {

    }

    public enum buttons {
        CLICK_ME,
        LINK_BUTTON
    }

    public enum labels {
        CLICK_ME_ENUMERATOR,
        LINK_BUTTON_ENUMERATOR
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
            case CLICK_ME:
                bys.add(By.tagName("a"));
                bys.add(By.cssSelector("[class*='text-']"));
                return new ButtonElement(bys,"Click me",true, "Кнопка Click me");
            case LINK_BUTTON:
                bys.add(By.tagName("a"));
                bys.add(By.cssSelector("[class*='text-']"));
                return new ButtonElement(bys,"Link Button",true, "Кнопка Link Button");
        }
        return new ButtonElement(bys);
    }
    public static LabelElement get(labels element) {
        ArrayList<By> bys = new ArrayList<>();
        switch (element) {
            case CLICK_ME_ENUMERATOR:
                bys.add(By.id("clickCount"));
                return new LabelElement(bys, "Счетчик кнопки Click me");
            case LINK_BUTTON_ENUMERATOR:
                bys.add(By.id("clickButtonCount"));
                return new LabelElement(bys, "Счетчик кнопки Link Button");
        }
        return new LabelElement(bys);
    }
}

