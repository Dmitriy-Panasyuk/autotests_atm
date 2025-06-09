package pages.elements;

import elements.ButtonElement;
import org.openqa.selenium.By;
import pages.BasePage;
import pages.PageURL;

import java.util.ArrayList;

import static common.CommonActions.getDriver;

public class HiddenLayers  extends BasePage {
    private static String URL = PageURL.HIDEN_LAYERS;

    public enum inputs {

    }

    public enum buttons {
        GREEN,
        BLUE
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
            case GREEN:
                bys.add(By.id("greenButton"));
                return new ButtonElement(bys,  "Зеленая кнопка");
            case BLUE:
                bys.add(By.id("blueButton"));
                return new ButtonElement(bys,  "Синяя кнопка");
        }
        return new ButtonElement(bys);
    }
}
