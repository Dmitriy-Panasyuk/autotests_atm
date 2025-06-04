package elements;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import utils.Log;

import java.util.ArrayList;


public class ButtonElement extends BaseElement {

   public static final int DATA_QA_VALUE = 3;

   public ButtonElement(ArrayList<By> bys) {
      super(bys);
      this.type = DATA_QA_VALUE;
      if (this.bys.contains(BY_DQWE_ATT_NAME)) {
         this.bys.set(this.bys.indexOf(BY_DQWE_ATT_NAME), By.cssSelector("[" + DQWE_ATT_NAME + "='" + this.type + "']"));
      }
   }

   public ButtonElement(ArrayList<By> bys, String name) {
      this(bys);
      this.elementName = name;
   }

   public ButtonElement(ArrayList<By> bys, String Text, boolean isTextEquals) {
      super(bys, Text, isTextEquals);
      this.type = DATA_QA_VALUE;
      if (this.bys.contains(BY_DQWE_ATT_NAME)) {
         this.bys.set(this.bys.indexOf(BY_DQWE_ATT_NAME), By.cssSelector("[" + DQWE_ATT_NAME + "='" + this.type + "']"));
      }
   }

   public ButtonElement(ArrayList<By> bys, Visibility visibility) {
      super(bys, visibility);
      this.type = DATA_QA_VALUE;
      if (this.bys.contains(BY_DQWE_ATT_NAME)) {
         this.bys.set(this.bys.indexOf(BY_DQWE_ATT_NAME), By.cssSelector("[" + DQWE_ATT_NAME + "='" + this.type + "']"));
      }
   }

   public ButtonElement(ArrayList<By> bys, String name, Visibility visibility) {
      this(bys, visibility);
      this.elementName = name;
   }


   public ButtonElement(ArrayList<By> bys, String Text, boolean isTextEquals, Visibility visibility) {
      super(bys, Text, isTextEquals, visibility);
      this.type = DATA_QA_VALUE;
      if (this.bys.contains(BY_DQWE_ATT_NAME)) {
         this.bys.set(this.bys.indexOf(BY_DQWE_ATT_NAME), By.cssSelector("[" + DQWE_ATT_NAME + "='" + this.type + "']"));
      }
   }

   public ButtonElement(ArrayList<By> bys, String Text, boolean isTextEquals, String name) {
      super(bys, Text, isTextEquals);
      this.type = DATA_QA_VALUE;
      if (this.bys.contains(BY_DQWE_ATT_NAME)) {
         this.bys.set(this.bys.indexOf(BY_DQWE_ATT_NAME), By.cssSelector("[" + DQWE_ATT_NAME + "='" + this.type + "']"));
      }
      this.elementName = name;
   }

   public ButtonElement(ArrayList<By> bys, String Text, boolean isTextEquals, String name, Visibility visibility) {
      super(bys, Text, isTextEquals, visibility);
      this.type = DATA_QA_VALUE;
      if (this.bys.contains(BY_DQWE_ATT_NAME)) {
         this.bys.set(this.bys.indexOf(BY_DQWE_ATT_NAME), By.cssSelector("[" + DQWE_ATT_NAME + "='" + this.type + "']"));
      }
      this.elementName = name;
   }

   public void clickButton() {
      Log.println("Click button '" + this.elementName + "'");
      Allure.step("Активировать кнопку '" + this.elementName + "'", () -> {
         this.getElement();
         try {
            this.clickElement();
         } catch (Exception ex) {
            throw new Exception("The element '" + this.elementName + "' cannot be clicked");
         }
      });
   }
   public void clickButton(boolean isOpenNewWindow, boolean isOpenNewURL) {
      Log.println("Click button '" + this.elementName + "'");
      Allure.step("Активировать кнопку '" + this.elementName + "'", () -> {
         this.setNewURLAfterClic(isOpenNewURL);
         this.setNewWindowAfterClic(isOpenNewWindow);
         this.getElement();
         try {
            this.clickElement();
         } catch (Exception ex) {
            throw new Exception("The element '" + this.elementName + "' cannot be clicked");
         }
      });
   }
}
