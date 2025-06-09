package pages.elements;

import elements.BaseElement;
import elements.LabelElement;
import org.openqa.selenium.By;
import pages.BasePage;
import pages.PageURL;

import java.util.ArrayList;

import static common.CommonActions.getDriver;

public class VerifyText extends BasePage {
    private static String URL = PageURL.VERIFI_TEXT;

    public enum inputs {

    }

    public enum buttons {

    }

    public enum labels {
        LABLE
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
    public static LabelElement get(labels element) {
        ArrayList<By> bys = new ArrayList<>();
        BaseElement parent;
        switch (element) {
            case LABLE:
                bys.add(By.cssSelector("[class*='bg-primary']"));
                parent =  new LabelElement(bys, "Родительский элемент.");
                bys = new ArrayList<>();
                bys.add(By.cssSelector("[class*='badge-secondary']"));
                LabelElement label = new LabelElement(bys, "Искомое значение.");
                label.setParent(parent);
                return label;

        }
        return new LabelElement(bys);
    }
}

