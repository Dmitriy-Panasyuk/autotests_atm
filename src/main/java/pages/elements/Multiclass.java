package pages.elements;

import elements.ButtonElement;
import org.openqa.selenium.By;
import pages.BasePage;
import pages.PageURL;

import java.util.ArrayList;

import static common.CommonActions.getDriver;

public class Multiclass extends BasePage {
    private static String URL = PageURL.MULTICLASS;

    public enum inputs {

    }

    public enum buttons {
        WARNING,
        SUCCESS,
        PRIMARY
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
            case WARNING:
                bys.add(By.cssSelector("[class*='btn-warning']"));
                return new ButtonElement(bys,  "Кнопка warning");
        }switch (element) {
            case SUCCESS:
                bys.add(By.cssSelector("[class*='btn-success']"));
                return new ButtonElement(bys,  "Кнопка success");
        }switch (element) {
            case PRIMARY:
                bys.add(By.cssSelector("[class*='btn-primary btn-test']"));
                return new ButtonElement(bys,  "Кнопка primary");
        }
        return new ButtonElement(bys);
    }
}
