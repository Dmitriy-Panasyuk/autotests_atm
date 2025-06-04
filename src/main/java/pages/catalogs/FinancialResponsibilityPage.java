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

public class FinancialResponsibilityPage extends BasePage {
   private static String URL = PageURL.CATALOG_COMPANIES_FINANCIAL_RESPONSIBILITY;

   public enum tables {
      FINANCIAL,

   }

   public enum enums {
      COMPANY_NAME,
      FINANCIAL_COMPANY,
      FINANCIAL_DIRECTORS,
      FINANCIAL_BUGET_ADMINS,
      FINANCIAL_ECONOMISTS

   }

   public enum buttons {
      CREATE,
      FINANCIAL_CREATE,
      FINANCIAL_CANCEL
   }

   public enum inputs {
      FINANCIAL_NAME
   }

   public static EnumElement get(enums element) {
      ArrayList<By> bys = new ArrayList<>();
      switch (element) {
         case COMPANY_NAME:
            bys.add(BY_DQWE_ATT_NAME);
            return new EnumElement(bys,  "Список компаний");
         case FINANCIAL_COMPANY:
            bys.add(BY_DQWE_ATT_NAME);
            bys.add(By.className("MuiFormControl-fullWidth"));
            return new EnumElement(bys, "Компания", false,  "Компания");
         case FINANCIAL_DIRECTORS:
            bys.add(BY_DQWE_ATT_NAME);
            return new EnumElement(bys, "Руководитель ЦФО", false,  "Руководитель ЦФО");
         case FINANCIAL_BUGET_ADMINS:
            bys.add(BY_DQWE_ATT_NAME);
            return new EnumElement(bys, "Администратор бюджета", false,  "Администратор бюджета");
         case FINANCIAL_ECONOMISTS:
            bys.add(BY_DQWE_ATT_NAME);
            return new EnumElement(bys, "Экономист", false,  "Экономист");
      }
      return null;
   }

   public static TableElement get(tables element) {
      ArrayList<By> bys = new ArrayList<>();
      switch (element) {
         case FINANCIAL:
            bys.add(By.className("MuiTable-root"));
            return new TableElement(bys, "Центры финансовой ответственности (ЦФО)");
      }
      return null;
   }

   public static ButtonElement get(buttons element) {
      ArrayList<By> bys = new ArrayList<>();
      switch (element) {
         case CREATE:
            bys.add(BY_DQWE_ATT_NAME);
            return new ButtonElement(bys, "Создать", true, "Создать");
         case FINANCIAL_CREATE:
            bys.add(BY_DQWE_ATT_NAME);
            return new ButtonElement(bys, "Подтвердить", true,  "Подтвердить создание");
         case FINANCIAL_CANCEL:
            bys.add(BY_DQWE_ATT_NAME);
            return new ButtonElement(bys, "Отменить", true, "Отменить создание");
      }
      return new ButtonElement(bys);
   }

   public static InputElement get(inputs element) {
      ArrayList<By> bys = new ArrayList<>();
      switch (element) {
         case FINANCIAL_NAME:
            bys.add(BY_DQWE_ATT_NAME);
            return new InputElement(bys, "Центр финансовой ответственности", false, "Центр финансовой ответственности");
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
    *
    * @return
    */
   public static String getURL() {
      return URL;
   }

}
