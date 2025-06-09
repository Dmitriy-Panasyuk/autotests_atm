package pages.elements;

import elements.ButtonElement;
import elements.LabelElement;
import org.openqa.selenium.By;
import pages.BasePage;
import pages.PageURL;

import java.util.ArrayList;

import static common.CommonActions.getDriver;

public class ProgressBar extends BasePage {
    private static String URL = PageURL.PROGRESS_BAR;

    public enum inputs {

    }

    public enum buttons {
        START,
        STOP
    }

    public enum labels {
        PROGRESS,
        RESULT
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
            case START:
                bys.add(By.cssSelector("[class*='btn-primary']"));
                return new ButtonElement(bys, "Начать прогресс");
            case STOP:
                bys.add(By.cssSelector("[class*='btn-info']"));
                return new ButtonElement(bys, "Остановить прогресс");

        }
        return new ButtonElement(bys);
    }
    public static LabelElement get(labels element) {
        ArrayList<By> bys = new ArrayList<>();
        switch (element) {
            case PROGRESS:
                bys.add(By.id("progressBar"));
                return new LabelElement(bys, "Progress Bar");
            case RESULT:
                bys.add(By.id("result"));
                return new LabelElement(bys, "Progress Bar");
        }
        return new LabelElement(bys);
    }
}

