package pages.elements;

import elements.InputElement;
import org.openqa.selenium.By;
import pages.BasePage;
import pages.PageURL;

import java.util.ArrayList;

import static common.CommonActions.getDriver;

public class Overlapped extends BasePage {
    private static String URL = PageURL.OVERLOPPED;

    public enum inputs {
ID,
        NAME
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
    public static InputElement get(inputs element) {
        ArrayList<By> bys = new ArrayList<>();
        switch (element) {
            case ID:
                bys.add(By.id("id"));
                return new InputElement(bys, "Ввод ID");
            case NAME:
                bys.add(By.id("name"));
                return new InputElement(bys, "Ввод NAME");
        }
        return new InputElement(bys);
    }
}

