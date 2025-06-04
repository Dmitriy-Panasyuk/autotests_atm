package pages;

import elements.ButtonElement;
import elements.InputElement;
import elements.LabelElement;
import elements.Visibility;
import org.openqa.selenium.By;

import java.util.ArrayList;

import static elements.BaseElement.BY_DQWE_ATT_NAME;


public class HomePage extends BasePage {
   private static String URL = PageURL.HOME;

   public enum inputs {
      LOGIN,
      PASSWORD
   }

   public enum buttons {
      LOGIN_IMG,
      ENTER_LOGIN,
      LOGOUT_ICON,
      PAYMENT_REQUESTS,
      CATALOGS
   }

   public enum labels {
      CHECK_LIST,
      CHECK_LOGIN_FORM,
      CHECK_SUCCESS,
      CHECK_ERROR
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
         case LOGIN_IMG:
            bys.add(BY_DQWE_ATT_NAME);
            bys.add(By.cssSelector("[type='button']"));
            return new ButtonElement(bys, "Войти", true, "Иконка логина");
         case ENTER_LOGIN:
            bys.add(BY_DQWE_ATT_NAME);
            bys.add(By.cssSelector("[type='submit']"));
            return new ButtonElement(bys, "Войти", true, "Вход");
         case LOGOUT_ICON:
            bys.add(By.cssSelector("[data-testid='LogoutSharpIcon']"));
            return new ButtonElement(bys, "Иконка выхода с текущего пользователя");
         case PAYMENT_REQUESTS:
            bys.add(BY_DQWE_ATT_NAME);
            return new ButtonElement(bys, "Заявки на оплату", false, "Заявки на оплату");
         case CATALOGS:
            bys.add(By.className("MuiListItemIcon-root"));
            bys.add(By.cssSelector("[title='Справочники']"));
            return new ButtonElement(bys, "Справочники");
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
         case CHECK_LIST:
            bys.add(BY_DQWE_ATT_NAME);
            return new LabelElement(bys, "Отчеты", true, "Отчеты");
         case CHECK_LOGIN_FORM:
            bys.add(BY_DQWE_ATT_NAME);
            return new LabelElement(bys, "Войдите, используя свой логин", true, "Войдите, используя свой логин");
         case CHECK_SUCCESS:
            bys.add(By.className("toastify-success"));
            return new LabelElement(bys, "Всплывающее окно Логин успешен", Visibility.POPUP);
         case CHECK_ERROR:
            bys.add(By.className("toastify-error"));
            return new LabelElement(bys, "Всплывающее окно Логин провален", Visibility.POPUP);
         default:
            return new LabelElement(bys);
      }
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
         case LOGIN:
//            bys.add(BY_DQWE_ATT_NAME);
            bys.add(By.cssSelector("[name='username']"));
            return new InputElement(bys, "Ввод логина");
         case PASSWORD:
//            bys.add(BY_DQWE_ATT_NAME);
            bys.add(By.cssSelector("[name='password']"));
            return new InputElement(bys, "Ввод пароля");
      }
      return new InputElement(bys);
   }
}
