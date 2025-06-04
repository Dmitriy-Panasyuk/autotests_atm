package pages.payment;


import elements.ButtonElement;
import elements.TableElement;
import elements.Visibility;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import pages.BasePage;
import pages.PageURL;
import utils.Log;
import utils.Waits;

import java.util.ArrayList;

import static common.CommonActions.getDriver;
import static elements.BaseElement.BY_DQWE_ATT_NAME;

public class PaymentPage extends BasePage {
   private static String URL = PageURL.PAYMENT;

   public enum buttons {
      CREATE,
      CATALOGS,
      DROP_ARROW,
      VIEW_ALL
   }

   public enum tables {
      REQUEST,
   }

   public static ButtonElement get(buttons element) {
      ArrayList<By> bys = new ArrayList<>();
      switch (element) {
         case VIEW_ALL:
            bys.add(By.tagName("li"));
            bys.add(By.cssSelector("[role='menuitem']"));
            return new ButtonElement(bys, "Показать все", true,  "Каталоги");
         case DROP_ARROW:
            bys.add(By.tagName("svg"));
            bys.add(By.className("MuiSvgIcon-root"));
            bys.add(By.cssSelector("[data-testid='ArrowDropDownIcon']"));
            return new ButtonElement(bys, "Иконка раскрытия списка", Visibility.INVISIBLE);
         case CREATE:
            bys.add(BY_DQWE_ATT_NAME);
            ButtonElement button =new ButtonElement(bys, "+ Создать заявку", true, "Создать заявку");
            button.setNewWindowAfterClic(true);
            return button;
         case CATALOGS:
            bys.add(By.cssSelector("[data-testid='ListAltSharpIcon']"));
            return new ButtonElement(bys,  "Каталоги");
      }
      return new ButtonElement(bys);
   }

   public static TableElement get(tables element) {
      ArrayList<By> bys = new ArrayList<>();
      switch (element) {
         case REQUEST:
            bys.add(By.className("MuiTable-root"));
            return new TableElement(bys, "Таблица заявок");
      }
      return null;
   }

   public static String getURL() {
      return URL;
   }

   public static void open() {
      Allure.step("Перейти на URL " + URL);
      Log.println("Перейти на URL " + URL);
      getDriver().get(URL);
      Waits.pageLoad();
   }
}
