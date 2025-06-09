package pages.elements;

import elements.LabelElement;
import elements.TableElement;
import org.openqa.selenium.By;
import pages.BasePage;
import pages.PageURL;

import java.util.ArrayList;

import static common.CommonActions.getDriver;

public class DynamicTable extends BasePage {
    private static String URL = PageURL.DYNAMIC_TABLE;

    public enum inputs {

    }

    public enum buttons {

    }

    public enum tables {
        TABLE
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
    public static TableElement get(tables element) {
        ArrayList<By> bys = new ArrayList<>();
        switch (element) {
            case TABLE:
                bys.add(By.cssSelector("[role*='table']"));
                bys.add(By.cssSelector("[aria-describedby*='table_desc']"));
                return new TableElement(bys, "Таблица данных.");

        }
        return new TableElement(bys);
    }
    public static LabelElement get(labels element) {
        ArrayList<By> bys = new ArrayList<>();
        switch (element) {
            case LABLE:
                bys.add(By.cssSelector("[class*='bg-warning']"));
                return new LabelElement(bys, "Искомое значение.");
        }
        return new LabelElement(bys);
    }
}

