package pages.catalogs;

import elements.EnumElement;
import elements.TableElement;
import org.openqa.selenium.By;
import pages.BasePage;
import pages.PageURL;

import java.util.ArrayList;

import static elements.BaseElement.BY_DQWE_ATT_NAME;

public class CostsPage extends BasePage {
   private static String URL = PageURL.FIN_DOCS_COSTS;

   public enum tables {
      COSTS,
   }
   public enum enums {
      COMPANY_NAME
   }


   public static EnumElement get(enums element) {
      ArrayList<By> bys = new ArrayList<>();
      switch (element) {
         case COMPANY_NAME:
            bys.add(BY_DQWE_ATT_NAME);
            return new EnumElement(bys,  "Список компаний");
      }
      return null;
   }

   public static TableElement get(tables element) {
      ArrayList<By> bys = new ArrayList<>();
      switch (element) {
         case COSTS:
            bys.add(BY_DQWE_ATT_NAME);
            return new TableElement(bys, "Статьи расходов");
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
