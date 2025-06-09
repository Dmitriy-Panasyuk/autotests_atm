package pages.elements;

import elements.ButtonElement;
import elements.LabelElement;
import org.openqa.selenium.By;
import pages.BasePage;
import pages.PageURL;

import java.util.ArrayList;

import static common.CommonActions.getDriver;

public class ClientDelay extends BasePage {
    private static String URL = PageURL.CLIENT_SIDE_DELAY;

    public enum inputs {

    }

    public enum buttons {
        JS
    }

    public enum labels {
        JS_SUCCESS
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
            case JS:
                bys.add(By.cssSelector("[class*='btn-primary']"));
                return new ButtonElement(bys, "Кнопка запускающая js скрипт");
        }
        return new ButtonElement(bys);
    }

    public static LabelElement get(labels element) {
        ArrayList<By> bys = new ArrayList<>();
        switch (element) {
            case JS_SUCCESS:
                bys.add(By.cssSelector("[class*='bg-success']"));
                return new LabelElement(bys, "Data calculated on the client side.");
        }
        return new LabelElement(bys);
    }
}

