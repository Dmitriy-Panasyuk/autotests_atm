package pages.elements;

import elements.ButtonElement;
import org.openqa.selenium.By;
import pages.BasePage;
import pages.PageURL;

import java.util.ArrayList;

import static common.CommonActions.getDriver;

public class Visibility extends BasePage {
    private static String URL = PageURL.VISIBILITY;

    public enum inputs {

    }

    public enum buttons {
        HIDE,
        REMOVED,
        ZERO_WIDTH,
        OWERLAPED,
        OPACITY_0,
        HIDDEN,
        DISPLAY_NONE,
        OFFSCREEN,
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
            case HIDE:
                bys.add(By.cssSelector("[class*='btn-primary']"));
                return new ButtonElement(bys, "Скрыть элементы");
            case REMOVED:
                bys.add(By.cssSelector("[class*='btn-danger']"));
                return new ButtonElement(bys, "Кнопка Removed");
            case ZERO_WIDTH:
                bys.add(By.cssSelector("[class*='btn-warning']"));
                return new ButtonElement(bys, "Кнопка Zero Width");
            case OWERLAPED:
                bys.add(By.cssSelector("[class*='btn-success']"));
                return new ButtonElement(bys, "Кнопка Overlapped");
            case OPACITY_0:
                bys.add(By.id("transparentButton"));
                return new ButtonElement(bys, "Кнопка Opacity 0");
            case HIDDEN:
                bys.add(By.id("invisibleButton"));
                return new ButtonElement(bys, "Кнопка Visibility Hidden");
            case DISPLAY_NONE:
                bys.add(By.id("notdisplayedButton"));
                return new ButtonElement(bys, "Кнопка Display None");
            case OFFSCREEN:
                bys.add(By.id("offscreenButton"));
                return new ButtonElement(bys, "Кнопка Offscreen");

        }
        return new ButtonElement(bys);
    }
}

