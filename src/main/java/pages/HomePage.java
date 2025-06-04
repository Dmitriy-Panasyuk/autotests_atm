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
      DYNAMIC_ID
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
