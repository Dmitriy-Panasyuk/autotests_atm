package pages.catalogs;

import elements.TableElement;
import org.openqa.selenium.By;
import pages.BasePage;
import pages.PageURL;

import java.util.ArrayList;

public class ProgramsPage extends BasePage {
   private static  String URL = PageURL.CATALOG_PROGRAMS;

   public enum tables {
      PROGRAMS,
   }

   public static TableElement get(tables element) {
      ArrayList<By> bys = new ArrayList<>();
      switch (element) {
         case PROGRAMS:
            bys.add(By.className("MuiTable-root"));
            return new TableElement(bys,  "Корпоративные задачи");
      }
      return null;
   }

   /**
    * Открыть страницу
    */
   public static void open() {
      BasePage.open(URL);
   }

   /**
    * Отдает URL страницы
    * @return
    */
   public static String getURL() {
      return URL;
   }

}
