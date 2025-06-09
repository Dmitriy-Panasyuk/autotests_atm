package pages.elements;

import elements.ButtonElement;
import elements.LabelElement;
import org.openqa.selenium.By;
import pages.BasePage;
import pages.PageURL;

import java.util.ArrayList;

import static common.CommonActions.getDriver;

public class Ajax extends BasePage {
    private static String URL = PageURL.AJAX_DATA;

    public enum inputs {

    }

    public enum buttons {
        AJAX
    }

    public enum labels {
        AJAX_SUCCESS
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
            case AJAX:
                bys.add(By.cssSelector("[class*='btn-primary']"));
                return new ButtonElement(bys, "Кнопка запускающая AJAX-запрос");
        }
        return new ButtonElement(bys);
    }

    public static LabelElement get(labels element) {
        ArrayList<By> bys = new ArrayList<>();
        switch (element) {
            case AJAX_SUCCESS:
                bys.add(By.cssSelector("[class*='bg-success']"));
                return new LabelElement(bys, "Data loaded with AJAX get request.");
        }
        return new LabelElement(bys);
    }
}
