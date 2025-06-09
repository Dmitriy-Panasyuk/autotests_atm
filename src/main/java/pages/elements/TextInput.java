package pages.elements;

import elements.ButtonElement;
import elements.InputElement;
import org.openqa.selenium.By;
import pages.BasePage;
import pages.PageURL;

import java.util.ArrayList;

import static common.CommonActions.getDriver;

public class TextInput extends BasePage {
    private static String URL = PageURL.TEXT_INPUT;

    public enum inputs {
        NEW_BUTTON_NAME
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
                bys.add(By.cssSelector("[class*='btn-primary']"));
                return new ButtonElement(bys, "Кнопка в которой меняется текст");
        }
        return new ButtonElement(bys);
    }
    public static InputElement get(inputs element) {
        ArrayList<By> bys = new ArrayList<>();
        switch (element) {
            case NEW_BUTTON_NAME:
                bys.add(By.tagName("input"));
                return new InputElement(bys, "Ввод нового текста кнопки");
        }
        return new InputElement(bys);
    }
}

