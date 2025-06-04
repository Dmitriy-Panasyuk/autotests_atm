package pages.catalogs;

import elements.ButtonElement;
import elements.EnumElement;
import elements.InputElement;
import elements.TableElement;
import org.openqa.selenium.By;
import pages.BasePage;
import pages.PageURL;

import java.util.ArrayList;

import static elements.BaseElement.BY_DQWE_ATT_NAME;

public class CostCenterPage extends BasePage {
   private static String URL = PageURL.COMPANIES_FINANCIAL_RESPONSIBILITY_COST_CENTER;

   public enum tables {
      CENTERS,
   }
   public enum enums {
      COMPANY_NAME,
      CENTER_COMPANY,
      CENTER_RESPONSIBILITY,

   }

   public enum buttons {
      CREATE,
      CENTER_CREATE,
      CENTER_CANCEL
   }

   public enum inputs {
      CENTER_NAME
   }
   public static EnumElement get(enums element) {
      ArrayList<By> bys = new ArrayList<>();
      switch (element) {
         case COMPANY_NAME:
            bys.add(BY_DQWE_ATT_NAME);
            return new EnumElement(bys,  "Список компаний");
         case CENTER_COMPANY:
            bys.add(BY_DQWE_ATT_NAME);
            bys.add(By.className("MuiFormControl-fullWidth"));
            return new EnumElement(bys,"Компания", false,  "Компания");
         case CENTER_RESPONSIBILITY:
            bys.add(BY_DQWE_ATT_NAME);
            return new EnumElement(bys,"Центр финансовой ответственности", false,  "ЦФО");
      }
      return null;
   }

   public static TableElement get(tables element) {
      ArrayList<By> bys = new ArrayList<>();
      switch (element) {
         case CENTERS:
            bys.add(By.className("MuiTable-root"));
            return new TableElement(bys, "Место возникновения затрат (МВЗ)");
      }
      return null;
   }
   public static ButtonElement get(buttons element) {
      ArrayList<By> bys = new ArrayList<>();
      switch (element) {
         case CREATE:
            bys.add(BY_DQWE_ATT_NAME);
            return new ButtonElement(bys, "Создать", true, "Создать");
         case CENTER_CREATE:
            bys.add(BY_DQWE_ATT_NAME);
            return new ButtonElement(bys, "Подтвердить", true,  "Подтвердить создание");
         case CENTER_CANCEL:
            bys.add(BY_DQWE_ATT_NAME);
            return new ButtonElement(bys, "Отменить", true, "Отменить создание");
      }
      return new ButtonElement(bys);
   }
   public static InputElement get(inputs element) {
      ArrayList<By> bys = new ArrayList<>();
      switch (element) {
         case CENTER_NAME:
            bys.add(BY_DQWE_ATT_NAME);
            return new InputElement(bys, "Место возникновения затрат", false, "Место возникновения затрат");
      }
      return new InputElement(bys);
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
