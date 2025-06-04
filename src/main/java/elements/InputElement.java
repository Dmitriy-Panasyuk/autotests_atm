package elements;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Log;
import utils.Waits;

import java.util.ArrayList;

public class InputElement extends BaseElement implements Enabled  {
   public static final int DATA_QA_VALUE = 2;

   public InputElement(ArrayList<By> bys) {
      super(bys);
      this.type = DATA_QA_VALUE;
      if (this.bys.contains(BY_DQWE_ATT_NAME)) {
         this.bys.set(this.bys.indexOf(BY_DQWE_ATT_NAME), By.cssSelector("[" + DQWE_ATT_NAME + "='" + this.type + "']"));
      }
   }

   public InputElement(ArrayList<By> bys, String name) {
      this(bys);
      this.elementName = name;
   }

   public InputElement(ArrayList<By> bys, String Text, boolean isTextEquals) {
      super(bys, Text, isTextEquals);
      this.type = DATA_QA_VALUE;
      if (this.bys.contains(BY_DQWE_ATT_NAME)) {
         this.bys.set(this.bys.indexOf(BY_DQWE_ATT_NAME), By.cssSelector("[" + DQWE_ATT_NAME + "='" + this.type + "']"));
      }
   }

   public InputElement(ArrayList<By> bys, Visibility visibility) {
      super(bys, visibility);
      this.type = DATA_QA_VALUE;
      if (this.bys.contains(BY_DQWE_ATT_NAME)) {
         this.bys.set(this.bys.indexOf(BY_DQWE_ATT_NAME), By.cssSelector("[" + DQWE_ATT_NAME + "='" + this.type + "']"));
      }
   }

   public InputElement(ArrayList<By> bys, String name, Visibility visibility) {
      this(bys, visibility);
      this.elementName = name;
   }

   public InputElement(ArrayList<By> bys, String Text, boolean isTextEquals, Visibility visibility) {
      super(bys, Text, isTextEquals, visibility);
      this.type = DATA_QA_VALUE;
      if (this.bys.contains(BY_DQWE_ATT_NAME)) {
         this.bys.set(this.bys.indexOf(BY_DQWE_ATT_NAME), By.cssSelector("[" + DQWE_ATT_NAME + "='" + this.type + "']"));
      }
   }

   public InputElement(ArrayList<By> bys, String Text, boolean isTextEquals, String name) {
      super(bys, Text, isTextEquals);
      this.type = DATA_QA_VALUE;
      if (this.bys.contains(BY_DQWE_ATT_NAME)) {
         this.bys.set(this.bys.indexOf(BY_DQWE_ATT_NAME), By.cssSelector("[" + DQWE_ATT_NAME + "='" + this.type + "']"));
      }
      this.elementName = name;
   }

   public InputElement(ArrayList<By> bys, String Text, boolean isTextEquals, String name, Visibility visibility) {
      super(bys, Text, isTextEquals, visibility);
      this.type = DATA_QA_VALUE;
      if (this.bys.contains(BY_DQWE_ATT_NAME)) {
         this.bys.set(this.bys.indexOf(BY_DQWE_ATT_NAME), By.cssSelector("[" + DQWE_ATT_NAME + "='" + this.type + "']"));
      }
      this.elementName = name;
   }


   public void sendKeysInInput(String keyToSend) {
      Log.println("Input in '" + this.elementName + "'. Enter value: '" + keyToSend + "'");
      Allure.step("Ввод в инпут '" + this.elementName + "'", step -> {
         step.parameter("Вводимое значение", keyToSend);
         this.getElement();
         Waits.clickableElement(this.webElement);
         if (this.webElement.getTagName().equalsIgnoreCase("input") || this.webElement.getTagName().equalsIgnoreCase("textarea")) {
            this.webElement.sendKeys(keyToSend);
         } else {
            try {
               Waits.elementWait(ExpectedConditions.and(ExpectedConditions.presenceOfElementLocated(By.tagName("input"))));
               this.webElement.findElement(By.tagName("input")).sendKeys(keyToSend);

            } catch (Exception ex) {
               try {
                  Waits.elementWait(ExpectedConditions.and(ExpectedConditions.presenceOfElementLocated(By.tagName("textarea"))));
                  this.webElement.findElement(By.tagName("textarea")).sendKeys(keyToSend);
               } catch (Exception ex1) {
                  throw new Exception("* Text cannot be entered");
               }
            }

         }
      });
   }

   public void clear() {
      Log.println("Clear value '" + this.elementName + "'");
      Allure.step("Удалить значение '" + this.elementName + "'", step -> {
         this.getElement();
         if (this.webElement.getTagName().equalsIgnoreCase("input") || this.webElement.getTagName().equalsIgnoreCase("textarea")) {
            this.webElement.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
         } else {
            Waits.elementWait(ExpectedConditions.and(ExpectedConditions.presenceOfElementLocated(By.tagName("input"))));
            this.webElement.findElement(By.tagName("input")).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
         }
      });
   }
   public boolean isEnable() {
      if (this.getElement().getAttribute("class").contains("Mui-disabled")) {
         return false;
      }
      return true;
   }
}
