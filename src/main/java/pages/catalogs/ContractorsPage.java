package pages.catalogs;

import elements.*;
import org.openqa.selenium.By;
import pages.BasePage;
import pages.PageURL;

import java.util.ArrayList;

import static elements.BaseElement.BY_DQWE_ATT_NAME;

public class ContractorsPage extends BasePage {
   private static String URL = PageURL.FIN_DOCS_CONTRACTORS;

   public enum tables {
      CONTRACTORS,
   }

   public enum buttons {
      INTERNAL,
      INTERNAL_TAM,
      EXTERNAL,
      EMPLOYES,
      CREATE,
      CONTRACTORS_CREATE,
      CONTRACTORS_CANCEL
   }

   public enum inputs {
      CONTRACTOR_NAME,
      CONTRACTOR_DISRIPTION,
      CONTRACTOR_INN,
      CONTRACTOR_NICKNAME
   }

   public enum enums {
      CONTRACTOR_TYPE
   }

   public static TableElement get(tables element) {
      ArrayList<By> bys = new ArrayList<>();
      switch (element) {
         case CONTRACTORS:
            bys.add(By.className("MuiDataGrid-root"));
            return new TableElement(bys,  "Контрагенты");
      }
      return null;
   }

   public static ButtonElement get(buttons element) {
      ArrayList<By> bys = new ArrayList<>();
      switch (element) {
         case INTERNAL:
            bys.add(BY_DQWE_ATT_NAME);
            return new ButtonElement(bys, "ВГО", true,  "ВГО");
         case INTERNAL_TAM:
            bys.add(BY_DQWE_ATT_NAME);
//            bys.add(By.cssSelector("[type='button']"));
//            bys.add(By.className("MuiButtonBase-root"));
//            bys.add(By.className("MuiTab-root"));
            return new ButtonElement(bys, "ВГО ТиМ", true, "ВГО ТиМ");
         case EXTERNAL:
            bys.add(BY_DQWE_ATT_NAME);
            return new ButtonElement(bys, "Внешний", true,  "Внешний");
         case EMPLOYES:
            bys.add(BY_DQWE_ATT_NAME);
            return new ButtonElement(bys, "Сотрудники", true,  "Сотрудники");
         case CREATE:
            bys.add(BY_DQWE_ATT_NAME);
            return new ButtonElement(bys, "Создать", true, "Создать");
         case CONTRACTORS_CREATE:
            bys.add(BY_DQWE_ATT_NAME);
            return new ButtonElement(bys, "Подтвердить", true, "Подтвердить создание");
         case CONTRACTORS_CANCEL:
            bys.add(BY_DQWE_ATT_NAME);
            return new ButtonElement(bys, "Отменить", true, "Отменить");
      }
      return new ButtonElement(bys);
   }

   public static InputElement get(inputs element) {
      ArrayList<By> bys = new ArrayList<>();
      switch (element) {
         case CONTRACTOR_NAME:
            bys.add(BY_DQWE_ATT_NAME);
            return new InputElement(bys, "Контрагент", false, "Контрагент");
         case CONTRACTOR_DISRIPTION:
            bys.add(BY_DQWE_ATT_NAME);
            return new InputElement(bys, "Описание", false, "Описание");
         case CONTRACTOR_INN:
            bys.add(BY_DQWE_ATT_NAME);
            return new InputElement(bys, "ИНН", false, "ИНН");
         case CONTRACTOR_NICKNAME:
            bys.add(BY_DQWE_ATT_NAME);
            return new InputElement(bys, "Псевдоним", false, "Псевдоним");
      }
      return new InputElement(bys);
   }

   public static EnumElement get(enums element) {
      ArrayList<By> bys = new ArrayList<>();
      switch (element) {
         case CONTRACTOR_TYPE:
            bys.add(BY_DQWE_ATT_NAME);
            return new EnumElement(bys, "Тип контрагента", false, "Тип контрагента");
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
    *
    * @return
    */
   public static String getURL() {
      return URL;
   }

}
