package pages.catalogs;

import elements.*;
import org.openqa.selenium.By;
import pages.BasePage;
import pages.PageURL;

import java.util.ArrayList;

import static elements.BaseElement.BY_DQWE_ATT_NAME;
import static elements.BaseElement.DQWE_ATT_NAME;

public class RolesPage extends BasePage {
   private static String URL = PageURL.EMPLOYEES_ROLES;

   public enum tables {
      ROLES
   }

   public enum buttons {
      CREATE,
      ROLE_CREATE,
      ROLE_CANCEL,
      ROLE_ADD_ROLE
   }

   public enum inputs {
      ROLE_NAME,
      ROLE_EMAIL,
      ROLE_LOGIN,
      ROLE_DEPARTAMENT,
      ROLE_POST
   }

   public enum enums {
      ROLE_COMPANY_NAME,
      ROLE_ROLE
   }

   public static TableElement get(tables element) {
      ArrayList<By> bys = new ArrayList<>();
      switch (element) {
         case ROLES:
            bys.add(By.className("MuiTable-root"));
            return new TableElement(bys,  "Роли и ограничения");
      }
      return null;
   }

   public static ButtonElement get(buttons element) {
      ArrayList<By> bys = new ArrayList<>();
      switch (element) {
         case CREATE:
            bys.add(BY_DQWE_ATT_NAME);
            return new ButtonElement(bys, "Создать", true, "Создать");
         case ROLE_CREATE:
            bys.add(BY_DQWE_ATT_NAME);
            return new ButtonElement(bys, "Подтвердить", true,  "Подтвердить создание");
         case ROLE_CANCEL:
            bys.add(BY_DQWE_ATT_NAME);
            return new ButtonElement(bys, "Отменить", true,  "Отменить");
         case ROLE_ADD_ROLE:
            bys.add(By.cssSelector("[" + DQWE_ATT_NAME + "='" + ButtonElement.DATA_QA_VALUE + "'] [data-testid='AddIcon']:first-child"));
            return new ButtonElement(bys, "Добавить роль", Visibility.INVISIBLE);
      }
      return new ButtonElement(bys);
   }

   public static InputElement get(inputs element) {
      ArrayList<By> bys = new ArrayList<>();
      switch (element) {
         case ROLE_NAME:
            bys.add(BY_DQWE_ATT_NAME);
            return new InputElement(bys, "ФИО", false,  "ФИО");
         case ROLE_EMAIL:
            bys.add(BY_DQWE_ATT_NAME);
            return new InputElement(bys, "Email", false,  "Email");
         case ROLE_LOGIN:
            bys.add(BY_DQWE_ATT_NAME);
            return new InputElement(bys, "Логин", false,  "Логин");
         case ROLE_DEPARTAMENT:
            bys.add(BY_DQWE_ATT_NAME);
            return new InputElement(bys, "Отдел", false,  "Отдел");
         case ROLE_POST:
            bys.add(BY_DQWE_ATT_NAME);
            return new InputElement(bys, "Должность", false,  "Должность");

      }
      return new InputElement(bys);
   }

   public static EnumElement get(enums element) {
      ArrayList<By> bys = new ArrayList<>();
      switch (element) {
         case ROLE_COMPANY_NAME:
            bys.add(BY_DQWE_ATT_NAME);
            return new EnumElement(bys, "Компания", false, "Список компаний");
         case ROLE_ROLE:
            bys.add(By.cssSelector("[aria-haspopup='listbox']"));
            bys.add(By.id("role-id"));
            EnumElement enumElement = new EnumElement(bys/*, "Роли", true*/,"Список ролей",Visibility.INVISIBLE);
//            enumElement.idAttribute="aria-controls";
            return enumElement;
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
