package pages.elements;

import elements.ButtonElement;
import elements.InputElement;
import elements.LabelElement;
import org.openqa.selenium.By;
import pages.BasePage;
import pages.PageURL;

import java.util.ArrayList;

import static common.CommonActions.getDriver;

public class SampleApp extends BasePage {
    private static String URL = PageURL.SAMPLE_APP;

    public enum inputs {
        LOGIN,
        PASSWORD
    }

    public enum buttons {
        LOG_IN
    }

    public enum labels {
        STATUS
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
            case LOGIN:
                bys.add(By.cssSelector("[name*='UserName']"));
                return new InputElement(bys, "Ввод логина");
            case PASSWORD:
                bys.add(By.cssSelector("[name*='Password']"));
                return new InputElement(bys, "Ввод пароля");
        }
        return new InputElement(bys);
    }
    public static ButtonElement get(buttons element) {
        ArrayList<By> bys = new ArrayList<>();
        switch (element) {
            case LOG_IN:
                bys.add(By.cssSelector("[class*='btn-primary']"));
                return new ButtonElement(bys, "Кнопка логина");
        }
        return new ButtonElement(bys);
    }
    public static LabelElement get(labels element) {
        ArrayList<By> bys = new ArrayList<>();
        switch (element) {
            case STATUS:
                bys.add(By.id("loginstatus"));
                return new LabelElement(bys, "Статус логина");
        }
        return new LabelElement(bys);
    }
}

