package pages.elements;

import elements.ButtonElement;
import org.openqa.selenium.By;
import pages.BasePage;
import pages.PageURL;

import java.util.ArrayList;

import static common.CommonActions.getDriver;

public class AutoWait extends BasePage {
    private static String URL = PageURL.AUTO_WAIT;

    public enum inputs {

    }

    public enum buttons {

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
    public static ButtonElement get(Ajax.buttons element) {
        ArrayList<By> bys = new ArrayList<>();
        switch (element) {

        }
        return new ButtonElement(bys);
    }
}

