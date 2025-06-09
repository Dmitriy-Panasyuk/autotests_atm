package pages;

import elements.ButtonElement;
import elements.InputElement;
import elements.LabelElement;
import org.openqa.selenium.By;

import java.util.ArrayList;


public class HomePage extends BasePage {
    private static String URL = PageURL.HOME;

    public enum inputs {

    }

    public enum buttons {
        DYNAMIC_ID,
        MULTICLASS,
        HIDEN_LAYERS,
        LOAD_DELAY,
        AJAX_DATA,
        CLIENT_SIDE_DELAY,
        CLICK,
        TEXT_INPUT,
        SCROLBARS,
        DYNAMIC_TABLE,
        VERIFI_TEXT,
        PROGRESS_BAR,
        VISIBILITY,
        SAMPLE_APP,
        MOUSE_OVER,
        NON_BREAKING_SPACE,
        OVERLOPPED,
        SHADOW_DOM,
        ALERTS,
        FILE_UPLOAD,
        ANIMATED_BUTTON,
        DISABLED_INPUT,
        AUTO_WAIT
    }

    public enum labels {

    }

    /**
     * Открыть страницу
     */
    public static void open() {
        BasePage.open(URL);
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
            case DYNAMIC_ID:
                bys.add(By.tagName("a"));
                return new ButtonElement(bys, "Dynamic ID", true, "Кнопка перехода на страницу Dynamic ID");
            case MULTICLASS:
                bys.add(By.tagName("a"));
                return new ButtonElement(bys, "Class Attribute", true, "Кнопка перехода на страницу Class Attribute");
            case HIDEN_LAYERS:
                bys.add(By.tagName("a"));
                return new ButtonElement(bys, "Hidden Layers", true, "Кнопка перехода на страницу Hidden Layers");
            case LOAD_DELAY:
                bys.add(By.tagName("a"));
                return new ButtonElement(bys, "Load Delay", true, "Кнопка перехода на страницу Load Delay");
            case AJAX_DATA:
                bys.add(By.tagName("a"));
                return new ButtonElement(bys, "AJAX Data", true, "Кнопка перехода на страницу AJAX Data");
            case CLIENT_SIDE_DELAY:
                bys.add(By.tagName("a"));
                return new ButtonElement(bys, "Client Side Delay", true, "Кнопка перехода на страницу Client Side Delay");
            case CLICK:
                bys.add(By.tagName("a"));
                return new ButtonElement(bys, "Click", true, "Кнопка перехода на страницу Click");
            case TEXT_INPUT:
                bys.add(By.tagName("a"));
                return new ButtonElement(bys, "Text Input", true, "Кнопка перехода на страницу Text Input");
            case SCROLBARS:
                bys.add(By.tagName("a"));
                return new ButtonElement(bys, "Scrollbars", true, "Кнопка перехода на страницу Scrollbars");
            case DYNAMIC_TABLE:
                bys.add(By.tagName("a"));
                return new ButtonElement(bys, "Dynamic Table", true, "Кнопка перехода на страницу Dynamic Table");
            case VERIFI_TEXT:
                bys.add(By.tagName("a"));
                return new ButtonElement(bys, "Verify Text", true, "Кнопка перехода на страницу Verify Text");
            case PROGRESS_BAR:
                bys.add(By.tagName("a"));
                return new ButtonElement(bys, "Progress Bar", true, "Кнопка перехода на страницу Progress Bar");
            case VISIBILITY:
                bys.add(By.tagName("a"));
                return new ButtonElement(bys, "Visibility", true, "Кнопка перехода на страницу Visibility");
            case SAMPLE_APP:
                bys.add(By.tagName("a"));
                return new ButtonElement(bys, "Sample App", true, "Кнопка перехода на страницу Sample App");
            case MOUSE_OVER:
                bys.add(By.tagName("a"));
                return new ButtonElement(bys, "Mouse Over", true, "Кнопка перехода на страницу Mouse Over");
            case NON_BREAKING_SPACE:
                bys.add(By.tagName("a"));
                return new ButtonElement(bys, "Non-Breaking Space", true, "Кнопка перехода на страницу Non-Breaking Space");
            case OVERLOPPED:
                bys.add(By.tagName("a"));
                return new ButtonElement(bys, "Overlapped Element", true, "Кнопка перехода на страницу Overlapped Element");
            case SHADOW_DOM:
                bys.add(By.tagName("a"));
                return new ButtonElement(bys, "Shadow DOM", true, "Кнопка перехода на страницу Shadow DOM");
            case ALERTS:
                bys.add(By.tagName("a"));
                return new ButtonElement(bys, "Alerts", true, "Кнопка перехода на страницу Alerts");
            case FILE_UPLOAD:
                bys.add(By.tagName("a"));
                return new ButtonElement(bys, "File Upload", true, "Кнопка перехода на страницу File Upload");
            case ANIMATED_BUTTON:
                bys.add(By.tagName("a"));
                return new ButtonElement(bys, "Animated Button", true, "Кнопка перехода на страницу Animated Button");
            case DISABLED_INPUT:
                bys.add(By.tagName("a"));
                return new ButtonElement(bys, "Disabled Input", true, "Кнопка перехода на страницу Disabled Input");
            case AUTO_WAIT:
                bys.add(By.tagName("a"));
                return new ButtonElement(bys, "Auto Wait", true, "Кнопка перехода на страницу Auto Wait");
        }
        return new ButtonElement(bys);
    }

    /**
     * Отдает указаный элемент
     *
     * @param element
     * @return
     */
    public static LabelElement get(labels element) {
        ArrayList<By> bys = new ArrayList<>();
        switch (element) {


        }
        return new LabelElement(bys);
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

        }
        return new InputElement(bys);
    }
}
