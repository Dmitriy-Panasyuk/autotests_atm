package pages.catalogs;

import elements.TableElement;
import org.openqa.selenium.By;
import pages.BasePage;
import pages.PageURL;

import java.util.ArrayList;

public class PaymentDocsPage extends BasePage {
   private static  String URL = PageURL.FIN_DOCS_PAYMENT;

   public enum tables {
      COMPANY,
   }

   public static TableElement get(tables element) {
      ArrayList<By> bys = new ArrayList<>();
      switch (element) {
         case COMPANY:
            bys.add(By.className("MuiTable-root"));
            return new TableElement(bys,  "Платежные документы");
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
