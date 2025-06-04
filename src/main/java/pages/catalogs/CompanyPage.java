package pages.catalogs;

import elements.*;
import org.openqa.selenium.By;
import pages.BasePage;
import pages.PageURL;

import java.util.ArrayList;

import static elements.BaseElement.BY_DQWE_ATT_NAME;

public class CompanyPage extends BasePage {
   private static String URL = PageURL.CATALOG_COMPANIES;

   public enum tables {
      COMPANY,
   }

   public static TableElement get(tables element) {
      ArrayList<By> bys = new ArrayList<>();
      switch (element) {
         case COMPANY:
            bys.add(By.className("MuiTable-root"));
            return new TableElement(bys,  "Компании");
      }
      return null;
   }

   public enum buttons {
      CREATE,
      COMPANY_CREATE,
      COMPANY_CANCEL,
//      COMPANY_ADD_ROLE
   }
   public enum labels {
      CHECK_ERROR
   }

   public static ButtonElement get(buttons element) {
      ArrayList<By> bys = new ArrayList<>();
      switch (element) {
         case CREATE:
            bys.add(BY_DQWE_ATT_NAME);
            return new ButtonElement(bys, "Создать", true, "Создать");
         case COMPANY_CREATE:
            bys.add(BY_DQWE_ATT_NAME);
            return new ButtonElement(bys, "Подтвердить", true, "Подтвердить создание");
         case COMPANY_CANCEL:
            bys.add(BY_DQWE_ATT_NAME);
            return new ButtonElement(bys, "Отменить", true, "Отменить");
//         case COMPANY_ADD_ROLE:
//            bys.add(By.cssSelector("[" + DQWE_ATT_NAME + "='" + ButtonElement.DATA_QA_VALUE + "'] [data-testid='AddIcon']:first-child"));
//            return new ButtonElement(bys, "Добавить компанию", Visibility.INVISIBLE);
      }
      return new ButtonElement(bys);
   }

   public enum inputs {
      COMPANY_NAME,
   }

   public static InputElement get(inputs element) {
      ArrayList<By> bys = new ArrayList<>();
      switch (element) {
         case COMPANY_NAME:
            bys.add(BY_DQWE_ATT_NAME);
            return new InputElement(bys, "Название компании", false,  "Название компании");
      }
      return new InputElement(bys);
   }


   public static LabelElement get(labels element) {
      ArrayList<By> bys = new ArrayList<>();
      switch (element) {
         case CHECK_ERROR:
            bys.add(By.className("toastify-error"));
            return new LabelElement(bys, "Всплывающее окно Компания уже существует", Visibility.POPUP);
         default:
            return new LabelElement(bys);
      }
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
