package pages.catalogs;

import elements.TableElement;
import org.openqa.selenium.By;
import pages.BasePage;
import pages.PageURL;

import java.util.ArrayList;

import static elements.BaseElement.BY_DQWE_ATT_NAME;

public class ContractsPage extends BasePage {
   private static String URL = PageURL.FIN_DOCS_CONTRACTS;

   public enum tables {
      COMPANY,
   }

   public static TableElement get(tables element) {
      ArrayList<By> bys = new ArrayList<>();
      switch (element) {
         case COMPANY:
            bys.add(BY_DQWE_ATT_NAME);
            return new TableElement(bys,  "Договоры");
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
