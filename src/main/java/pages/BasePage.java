package pages;

import io.qameta.allure.Allure;
import utils.Log;
import utils.Waits;

import java.util.HashMap;

import static common.CommonActions.getDriver;


public class BasePage {
   protected static String URL = null;
   private static HashMap<String, Boolean> isLogin = new HashMap<>();

   /**
    * Открыть страницу
    */
   public static void open(String URL) {
      Log.println(" Open URL " + URL);
      Allure.step("Перейти на URL " + URL);
      getDriver().get(URL);
      Waits.pageLoad();
   }

   public static boolean isLogin() {
      if (isLogin.get(Thread.currentThread().getName()) != null) {
         return isLogin.get(Thread.currentThread().getName());
      } else {
         isLogin.put(Thread.currentThread().getName(), false);
         return false;
      }

   }

   public static void loginSuccess() {
      if (!isLogin.containsKey(Thread.currentThread().getName())) {
         isLogin.put(Thread.currentThread().getName(), true);
      } else {
         isLogin.replace(Thread.currentThread().getName(), true);
      }
   }

   public static void logoutSuccess() {
      if (!isLogin.containsKey(Thread.currentThread().getName())) {
         isLogin.put(Thread.currentThread().getName(), false);
      } else {
         isLogin.replace(Thread.currentThread().getName(), false);
      }

   }
}
